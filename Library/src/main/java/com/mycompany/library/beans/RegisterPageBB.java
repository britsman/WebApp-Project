package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import java.io.Serializable;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hannes
 */
@Named("register")
@SessionScoped
public class RegisterPageBB implements Serializable {
    
    @Inject
    private UserRegistryBean users;
    @Inject
    private UserPageBB privateUserBean;
    @Inject
    private TemplateBB loggedUser;
    private String username = "";
    private String email = "";
    private String password = "";
    private String confirmPassword = "";
    private String redirect = null;
    private Long generatedCode;
    private Long inputCode;
    private boolean triedRegister = false;

    public RegisterPageBB() {}

    public void registerUser() {
        if (!triedRegister) {
            if (checkUser()) {
                if (checkPassword()) {
                    generatedCode = new Long(new Random().nextInt(100));
                    users.sendRegCode(email, generatedCode);
                    triedRegister = true;
                }
            }
        }
        else{
            completeRegistration();
        }
    }
    public void completeRegistration(){
        triedRegister = false;
        if (checkUser() && generatedCode == inputCode) {
                User newUser = new User(username, password, email, 0.0);
                newUser = users.update(newUser);
                privateUserBean.setUser(newUser);
                loggedUser.setLoggedInUser(newUser);              
                redirect = "userPage?faces-redirect=true";
        }
        else{
            //visa att registration failed (koden var fel)
            redirect = "login?faces-redirect=true";
        }
    }
    
    public String access(){
        return redirect;
    }

    public boolean checkUser() {
        if (users.getByUserName(username) == null) {
            return true;
        }
        return false;
    }

    public boolean checkPassword() {
        if (password.contentEquals(confirmPassword)) {
            return true;
        }
        return false;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public Long getInputCode() {
        return inputCode;
    }

    public void setInputCode(Long inputCode) {
        this.inputCode = inputCode;
    }   
}
