package com.mycompany.library.beans;

import com.mycompany.library.core.Book;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.User;
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
    
    @Inject
    private Conversation convo;
    private UserRegistryBean users;
    private TemplateBB template;
    private Book book;
    private String redirectPage;
    
    public ItemPageBB(){}

    @Inject
    public ItemPageBB(UserRegistryBean users, TemplateBB template) {
        this.users = users;
        this.template = template;
    }
    public Book getBook(){
        return book;
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
        return template.getLoggedInUser() != null;
    }
    
    public void borrowOrReserve(){
        if(book.getQuantity() > 0){
            template.getLoggedInUser().tryBorrowItem(book);
        }
        else{
            template.getLoggedInUser().tryReserveItem(book);
        }
        template.setLoggedInUser(users.update(template.getLoggedInUser()));
    }
    public void bookMark(){
        if(!template.getLoggedInUser().getBookmarkedItems().contains(book)){
            template.getLoggedInUser().setBookmarkedItems(book);
            template.setLoggedInUser(users.update(template.getLoggedInUser()));
        }
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
