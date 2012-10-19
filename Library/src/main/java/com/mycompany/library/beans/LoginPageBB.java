package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.component.UIOutput;
import javax.inject.Inject;

/**
 * Backing bean for the findBookPage.xhtml page
 * @author Hannes
 */
@Named("login")
@RequestScoped
public class LoginPageBB implements Serializable {

    private UserPageBB privateUserBean;
    private TemplateBB loggedUser;
    private UserRegistryBean users;
    private String userName;
    private String password;
    private UIOutput txtOutput;
    private boolean accessGranted = false;
    private boolean isLibrarian = false;

    public LoginPageBB() {}
    
    @Inject
    public LoginPageBB(UserPageBB privateUserBean, TemplateBB loggedUser, UserRegistryBean users) {
        this.privateUserBean = privateUserBean;
        this.loggedUser = loggedUser;
        this.users = users;
    }
    public void validateUser() {
        User user = users.getByUserName(userName);
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
            privateUserBean.setUser(u);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void actionListener(ActionEvent e) {
        Logger.getAnonymousLogger().log(Level.INFO, "");
    }
}