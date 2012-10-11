package com.mycompany.library.beans;

import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.User;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Backing bean for UserPage.xhtml page.
 * @author estelius
 */
@Named("userPage")
@SessionScoped
public class UserPageBB implements Serializable {
    
    private WebbLib library;
    
    private User user;
    
    public String getUsetName(){
        return user.getUsername();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    // Default constructor.
    public UserPageBB() {}
    
    public UserPageBB(WebbLib library) {
        this.library = library;
    }
        
    public List<BorrowedItem> getBorrowedItems() {
        return user.getBorrowedItems();
    }
    
    public List<Item> getFavorites() {
        return user.getBookmarkedItems();
    }
}
