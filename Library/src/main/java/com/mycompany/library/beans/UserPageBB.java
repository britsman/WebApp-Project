package com.mycompany.library.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Backing bean for UserPage.xhtml page.
 * @author estelius
 */
@Named("userPage")
@SessionScoped
public class UserPageBB implements Serializable {
    
    // Default constructor.
    public UserPageBB() {}
    
    public void getUser() {
        
    }
    
    public void getBorrowedItems() {
        
    }
    
    public void getFavorites() {
        
    }
}
