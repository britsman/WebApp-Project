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
    
    private static final WebbLib library = WebbLib.INSTANCE;
    
    // Should probably be injected along with login
    // Or stored in http request session thingy
    private User user;
    
    // Default constructor.
    public UserPageBB() {}
    
    public User getUser() {
        User u = library.getUsers().find(602L);
        u.setIsLibrarian(true);
        return u;
    }
    
    public List<BorrowedItem> getBorrowedItems() {
        User u = library.getUsers().find(602L);
        return u.getBorrowedItems();
    }
    
    public List<Item> getFavorites() {
        return library.getUsers().find(602L).getBookmarkedItems();
    }
}
