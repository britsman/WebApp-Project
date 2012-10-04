package com.mycompany.library.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Backing bean for the adminPage.xhtml page.
 * @author estelius
 */
@Named("admin")
@SessionScoped
public class AdminPageBB implements Serializable {
    
    // private final UserRegistry ur = UserRegistry.getInstance();
    
    // Defaul constructor.
    public AdminPageBB() {}
    
    // Create and add a new user.
    public void createUser() {
        
    }
    
    // Remove an existing user.
    public void removeUser() {
        
    }
    
    // Edit an existing user.
    public void editUser() {
        
    }
    
    // Refresh users.
    public void refresh() {
        
    }
}
