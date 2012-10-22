package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean for login part of login.xhtml
 */
@Named("login")
@RequestScoped
public class LoginPageBB implements Serializable {

    private SessionBB loggedUser;
    private UserRegistryBean users;
    private String username;
    private String password;
    private UIOutput txtOutput;
    private boolean accessGranted = false;
    private boolean isLibrarian = false;

    public LoginPageBB() {}
    
    @Inject
    public LoginPageBB(SessionBB loggedUser, UserRegistryBean users) {
        this.loggedUser = loggedUser;
        this.users = users;
    }
    public void validateUser() {
        User user = users.getByUserName(username);
        if (user == null) {
            accessGranted = false;
            txtOutput.setValue("Användare inte hittad!");
            password ="";
        } else {
            txtOutput.setValue("Fel lösenord");
            validatePassword(user);
            validateLibrarian(user);
            access();
        }
    }
    public boolean validatePassword(User u){
        if(u.getPassword().equalsIgnoreCase(password)){
            loggedUser.setLoggedInUser(u);
            accessGranted = true;
            return true;
        }
        return false;
    }

    public void validateLibrarian(User u){   
        if(u.isIsLibrarian()){
           isLibrarian = true;
        }
        
    }
    
    
    public String access() {
        System.out.println(isLibrarian);
        if (accessGranted == true) {
             loggedUser.clearSearch();
             return"userPage?faces-redirect=true";
        }
        else {
            return null;
        }
    }
    
    public UIOutput getTxtOutput() {
        return txtOutput;
    }

    public void setTxtOutput(UIOutput txtOutput) {
        this.txtOutput = txtOutput;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}