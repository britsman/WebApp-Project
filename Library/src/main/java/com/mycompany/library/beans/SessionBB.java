/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.Item;
import com.mycompany.library.core.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Named("currentSession")
@SessionScoped
public class SessionBB implements Serializable {
    
    private User loggedInUser=null;
    private String logOutString;
    private List<Item> searchResult = new ArrayList<>();
    
    public SessionBB() {
    }
    public void logOut(){
        loggedInUser = null; 
        clearSearch();
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
    
    public String myPageLinkName(){
        if(loggedInUser == null){
            return "Logga in/Registrera";
        }
        else{
            return "Min sida";
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
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
    public List<Item> getSearchResult() {
        return searchResult;
    }
    public void setSearchResult(List<Item> searchResult) {
        this.searchResult = searchResult;
    }
    public void clearSearch(){
        searchResult = new ArrayList<>();
    }
}
