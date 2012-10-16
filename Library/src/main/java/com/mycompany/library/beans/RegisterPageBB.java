package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
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

    private static final WebbLib library = WebbLib.INSTANCE;
    private UserRegistry users = library.getUsers();
    @Inject
    private UserPageBB privateUserBean;
    @Inject
    private TemplateBB loggedUser;
    private String username = "";
    private String email = "";
    private String password = "";
    private String confirmPassword = "";
    private String redirect = null;

    public RegisterPageBB() {}

    public void registerUser() {
        if (checkUser()) {
            if (checkPassword()) {
                User newUser = new User(username, password, email, 0.0);
                library.getUsers().add(newUser);
                privateUserBean.setUser(newUser);
                loggedUser.setLoggedInUser(newUser);
                redirect = "userPage?faces-redirect=true";
            }
        }
    }
    
    public String access(){
        return redirect;
    }

    public boolean checkUser() {
        if (users.getByUsername(username) == null) {
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
}
