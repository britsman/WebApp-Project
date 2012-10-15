package com.mycompany.library.beans;

import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.User;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
    
    public List<Item> getFavorites() {
        return user.getBookmarkedItems();
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
