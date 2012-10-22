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
    private SessionBB session;
    
    // Default constructor.
    public UserPageBB() {}
    
    @Inject
    public UserPageBB(UserRegistryBean users, SessionBB session) {
        this.users = users;
        this.session = session;
    }
    public int getQueuePosition(ReservedItem reservedItem) {        
        return reservedItem.getQuePosition(session.getLoggedInUser());
    }
    
    /* Managing bookarked items */
    
    public void bookmarkItem(Item item) {
        if (!session.getLoggedInUser().getBookmarkedItems().contains(item)) {
            session.getLoggedInUser().setBookmarkedItems(item);
            session.setLoggedInUser(users.update(session.getLoggedInUser()));
        }
    }
    
    public void removeBookmakedItem(Item item) {
        session.getLoggedInUser().removeBookmarkedItem(item);
        session.setLoggedInUser(users.update(session.getLoggedInUser()));
    }
    
    /* Managing resereved items */
    
    public void reserveItem(Item item) {
        session.getLoggedInUser().tryReserveItem(item);
        session.setLoggedInUser(users.update(session.getLoggedInUser()));
    }
    
    public void removeReservedItem(ReservedItem reservedItem) {
        QueryProccessor q = WebLib.INSTANCE.getQueryProccessor();
        reservedItem.updatePositions(session.getLoggedInUser());
        session.setLoggedInUser(users.update(session.getLoggedInUser()));
        if(reservedItem.getQue().size() == 1){
            reservedItem = q.findReservedItem(reservedItem.getItem());
            q.removeReservedItem(reservedItem.getId());
        }
    }
    
    /* Managing borrowed items */
    
    public void borrowItem(Item item) {
        session.getLoggedInUser().tryBorrowItem(item);
        session.setLoggedInUser(users.update(session.getLoggedInUser()));
    }
    
    public void removeBorrowedItem(BorrowedItem borrowedItem) {
        if(!borrowedItem.isCollected()){
        session.getLoggedInUser().removeBorrowedItem(borrowedItem);
        session.setLoggedInUser(users.update(session.getLoggedInUser()));
        }
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
