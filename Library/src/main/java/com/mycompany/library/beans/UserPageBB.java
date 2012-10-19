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
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean for UserPage.xhtml page.
 * @author estelius
 */
@Named("userPage")
@SessionScoped
public class UserPageBB implements Serializable {
    
    private UserRegistryBean users;
    private User user;

    // Default constructor.
    public UserPageBB() {}
    
    @Inject
    public UserPageBB(UserRegistryBean users) {
        this.users = users;
    }
    
    public String getUsetName(){
        return user.getUsername();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public int getQueuePosition(ReservedItem reservedItem) { 
       return reservedItem.getQuePosition(user);
    }

    /* Managing bookmarked items */
    
    public List<Item> getBookmarkedItems() {
        return user.getBookmarkedItems();
    }
    
    public void bookmarkItem(Item item) {
        user.setBookmarkedItems(item);
        users.update(user);
    }
    
    public void removeBookmakedItem(Item item) {
        user.removeBookmarkedItem(item);
        users.update(user);
    }
    
    /* Managing reserved items */
    
    public List<ReservedItem> getReservedItems() {
        return user.getReservedItems();
    }
    
    public void reserveItem(Item item) {
        user.tryReserveItem(item);
        user = users.update(user);
    }
    
    public void removeReservedItem(ReservedItem reservedItem) {
        reservedItem.updatePositions(user);
        user.updateReservation(reservedItem);
        user = users.update(user);
    }
    
    /* Managing borrowed items */
    
    public List<BorrowedItem> getBorrowedItems() {
        return user.getBorrowedItems();
    }
    
    public void borrowItem(Item item) {
        user.tryBorrowItem(item);
        user = users.update(user);
    }
    
    public void removeBorrowedItem(BorrowedItem borrowedItem) {
        user.removeBorrowedItem(borrowedItem);
        user = users.update(user);
    }
    
    /* Managing dates */
    
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
