package com.mycompany.library.beans;

import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 * Backing bean representing the current "session" (Not real session per se, each
 * period from a login to a logout/logout to a login is treated as a "session").
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

    public List<BorrowedItem> updatedBorrowedList(){
        return loggedInUser.getBorrowedItems();
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
