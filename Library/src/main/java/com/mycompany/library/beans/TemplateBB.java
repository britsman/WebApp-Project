/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hannes
 */
@Named("template")
@SessionScoped
public class TemplateBB implements Serializable {
    
    @Inject
    private UserPageBB privateUserBean;
    private User loggedInUser=null;
    private String logOutString;
    

    
    public TemplateBB() {
    }
    
    
    public void logOut(){
        loggedInUser = null;
        privateUserBean.setUser(null);
        
        
    }
    
    public String loggedUser(){        
        if(loggedInUser == null){
            logOutString="";
            return "Ej inloggad";
        }
        else{
            logOutString = "Logga ut";
            return ("Inloggad som "+loggedInUser.getUsername());
        }
    }
    
    public String myPage(){
        if(loggedInUser == null){
            return "login.xhtml";
        }
        else{
            return "userPage.xhtml";
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public UserPageBB getPrivateUserBean() {
        return privateUserBean;
    }

    public void setPrivateUserBean(UserPageBB privateUserBean) {
        this.privateUserBean = privateUserBean;
    }
    
     public void actionListener(ActionEvent e) {
        logOut();
    }

    public String getLogOutString() {
        return logOutString;
    }

    public void setLogOutString(String logOutString) {
        this.logOutString = logOutString;
    }

  
    
  
    
    


}
