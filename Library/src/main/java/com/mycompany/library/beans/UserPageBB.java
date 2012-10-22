package com.mycompany.library.beans;

import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.QueryProccessor;
import com.mycompany.library.core.ReservedItem;
import com.mycompany.library.core.WebLib;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean for UserPage.xhtml page.
 * @author estelius
 */
@Named("userPage")
@RequestScoped
public class UserPageBB implements Serializable {
    
    private UserRegistryBean users;
    private SessionBB template;
    
    // Default constructor.
    public UserPageBB() {}
    
    @Inject
    public UserPageBB(UserRegistryBean users, SessionBB template) {
        this.users = users;
        this.template = template;
    }
    public int getQueuePosition(ReservedItem reservedItem) {        
        return reservedItem.getQuePosition(template.getLoggedInUser());
    }
    
    /* Managing bookarked items */
    
    public void bookmarkItem(Item item) {
        if (!template.getLoggedInUser().getBookmarkedItems().contains(item)) {
            template.getLoggedInUser().setBookmarkedItems(item);
            template.setLoggedInUser(users.update(template.getLoggedInUser()));
        }
    }
    
    public void removeBookmakedItem(Item item) {
        template.getLoggedInUser().removeBookmarkedItem(item);
        template.setLoggedInUser(users.update(template.getLoggedInUser()));
    }
    
    /* Managing resereved items */
    
    public void reserveItem(Item item) {
        template.getLoggedInUser().tryReserveItem(item);
        template.setLoggedInUser(users.update(template.getLoggedInUser()));
    }
    
    public void removeReservedItem(ReservedItem reservedItem) {
        QueryProccessor q = WebLib.INSTANCE.getQueryProccessor();
        reservedItem.updatePositions(template.getLoggedInUser());
        template.setLoggedInUser(users.update(template.getLoggedInUser()));
        if(reservedItem.getQue().size() == 1){
            reservedItem = q.findReservedItem(reservedItem.getItem());
            q.removeReservedItem(reservedItem.getId());
        }
    }
    
    /* Managing borrowed items */
    
    public void borrowItem(Item item) {
        template.getLoggedInUser().tryBorrowItem(item);
        template.setLoggedInUser(users.update(template.getLoggedInUser()));
    }
    
    public void removeBorrowedItem(BorrowedItem borrowedItem) {
        template.getLoggedInUser().removeBorrowedItem(borrowedItem);
        template.setLoggedInUser(users.update(template.getLoggedInUser()));
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
