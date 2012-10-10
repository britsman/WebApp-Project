/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.component.UIOutput;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Backing bean for the findBookPage.xhtml page
 *
 * @author Hannes
 */
@Named("login")
@RequestScoped
public class LoginPageBB implements Serializable {

    @Inject
    private UserPageBB privateUserBean;
    @NotNull
    @Size(min = 1, max = 8, message = "Must use 1-8 chars")
    private String userName;
    private String password;
    private UIOutput txtOutput;
    private boolean AccessGranted = false;

    public LoginPageBB() {
    }

    public void validateUser() {
        UserRegistry users = WebbLib.INSTANCE.getUsers();
        User user = users.getByUsername(userName);
        if (user == null) {
            AccessGranted = false;
            txtOutput.setValue("Användare inte hittad!");
            password ="";
        } else {
            validatePassword(user);
            access();
        }
    }
    
    
    public boolean validatePassword(User u){
        if(u.getPassword().equalsIgnoreCase(password)){
            privateUserBean.setUser(u);
            AccessGranted = true;
            return true;
        }
        return false;
    }

    public String access() {
        if (AccessGranted == true) {
            return "userPage?faces-redirect=true";
        } else {
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