/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Hannes
 */
@Named("userBean") 
@SessionScoped
public class UserBean implements Serializable{
    
    private String loggedInUser = "ompa";
    
    public UserBean(){    
    }
    
    

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    
    
    
    
    
}
