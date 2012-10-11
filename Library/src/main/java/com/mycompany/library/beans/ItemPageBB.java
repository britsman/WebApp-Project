/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.Book;
import com.mycompany.library.core.Item;
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
    private ItemBean items;
    private Book book;
    
    public ItemPageBB(){
    //Needed since another constructor has been specified.
    }
    @Inject
    public ItemPageBB(ItemBean items){
        this.items = items;
    }
    public Book getBook(){
        return book;
    }      
    public void BookListener(Item item) {
        this.book = (Book) item;
        if (convo.isTransient()) {
            convo.begin();
        }
    }
    public String action() {
        if (!convo.isTransient()) {
            convo.end();
        }
        try {
            return "booklist?faces-redirect=true"; // Go back
        } catch (Exception e) {
            // Not implemented
            //return "error?faces-redirect=true&amp;cause=" + e.getMessage();
            return null;
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
