package com.mycompany.library.beans;

import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.ReservedItem;
import com.mycompany.library.core.User;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    
    private User user;

    // Default constructor.
    public UserPageBB() {}
    
    public String getUsetName(){
        return user.getUsername();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
        
    public List<BorrowedItem> getBorrowedItems() {
        return user.getBorrowedItems();
    }
    
    public List<Item> getBookmarkedItems() {
        return user.getBookmarkedItems();
    }
    
    public List<ReservedItem> getReservedItems() {
        return user.getReservedItems();
    }
    
    public void bookmarkItem(Item item) {
        user.setBookmarkedItems(item);
    }
    
    public void reserveItem(Item item) {
        ReservedItem reservedItem = new ReservedItem(item, user);
        user.setReservedItems(reservedItem);
    }
    
    public void borrowItem(Item item) {
        BorrowedItem borrowedItem = new BorrowedItem(item, user);
        user.setBorrowedItems(borrowedItem);
    }
    
    public String returnDate(Date loanDate, BorrowedItem item) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loanDate);
        calendar.add(Calendar.DATE, item.getItem().getLoan_period());
        return formatDate(calendar.getTime());
    }
    
    public String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
