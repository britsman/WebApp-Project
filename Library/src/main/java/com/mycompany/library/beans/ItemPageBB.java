package com.mycompany.library.beans;

import com.mycompany.library.core.Book;
import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.ReservedItem;
import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author user
 */
@Named("SingleItem")
@ConversationScoped
public class ItemPageBB implements Serializable{
    
    @Inject private Conversation convo;
    private static final WebbLib library = WebbLib.INSTANCE;
    private UserRegistry users = library.getUsers();
    private ItemBean items;
    private Book book;
    private String redirectPage;
    private User user;
    
    //Needed since another constructor has been specified.
    public ItemPageBB(){}
    
    @Inject
    public ItemPageBB(ItemBean items){
        this.items = items;
    }
    
    public Book getBook(){
        return book;
    } 

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void bookListener(Item item, String redirectPage) {
        if (convo.isTransient()) {
            convo.begin();
        }
        this.book = (Book) item;
        this.redirectPage = redirectPage;
    }
    
    public String action() {
        if (!convo.isTransient()) {
            convo.end();
        }
        try {
            return this.redirectPage + "?faces-redirect=true"; // Go back
        } catch (Exception e) {
            // Not implemented
            //return "error?faces-redirect=true&amp;cause=" + e.getMessage();
            return null;
        }
    }
    
    public String buttonValue(){
        if(book.getQuantity() > 0){
            return "Låna";
        }
        else{
            return "Reservera";
        }
    }
    
    public boolean buttonVisible(){
        return user != null;
    }
    
    public void borrowOrReserve(){
        if(book.getQuantity() > 0){
            user.tryBorrowItem(book);
        }
        else{
            user.tryReserveItem(book);
        }
        users.update(user);
    }
    
    @PreDestroy  // MUST HAVE back button etc.
    public void destroy() {
        if (convo != null) {
            if (!convo.isTransient()) {
                convo.end();
            }
        }
    }
}
