/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.Item;
import com.mycompany.library.core.QueryProccessor;
import com.mycompany.library.core.User;
import com.mycompany.library.core.WebbLib;
import com.mycompany.library.core.Book;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hannes
 */
@Named("search")
@SessionScoped
public class SearchBB implements Serializable {

    private UserRegistryBean users;
    private TemplateBB template;
    private String id, title, creator, publisher, description, language, genre,type;
    private String topSearch;
    private int fromYear, toYear;
    private boolean inStock;
    private List<Item> result;
    private User user;
    private Book book;

    public SearchBB() {
    }
    @Inject
    public SearchBB(UserRegistryBean users, TemplateBB template) {
        this.users = users;
        this.template = template;
        this.user = template.getLoggedInUser();
    }

    public void searchAll() {
        QueryProccessor query = WebbLib.INSTANCE.getQueryProccessor();
        if (!topSearch.equals("")) {
            result = query.searchAll(topSearch);
        }
    }

    public void searchAdvanced() {
        QueryProccessor query = WebbLib.INSTANCE.getQueryProccessor();
        genre=null;
        language=null;
        type=null;
        result = query.searchItem(id, title, creator, publisher, description, fromYear, toYear, inStock, language, genre);

    }
    
     public void borrowOrReserve(Item item){
        book = (Book) item;
        if(book.getQuantity() > 0){
            user.tryBorrowItem(book);
        }
        else{
            user.tryReserveItem(book);
        }
        user = users.update(user);
        user = template.getLoggedInUser();
    }
    
     public void bookMark(Item item){
         this.book = (Book) item;
        if(!user.getBookmarkedItems().contains(book)){
            user.setBookmarkedItems(book);
        }        
        user = users.update(user);
        user = template.getLoggedInUser();
    }
    
    

    public boolean linkVisible(){  
        user = template.getLoggedInUser();
        return user != null;
    }
    
    public String bookMarkImg(Item item){
        book = (Book) item;
        if(user.getBookmarkedItems().contains(book)){
            return "/resources/img/star_full.png";
        }
        return "/resources/img/star_none.png";
    }
    
     
    
    public List<Item> getResult() {
        return result;
    }

    public void setResult(List<Item> result) {
        this.result = result;
    }

    public String getTopSearch() {
        return topSearch;
    }

    public void setTopSearch(String topSearch) {
        this.topSearch = topSearch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.isEmpty()) {
            this.id = null;
        } else {
            this.id =id;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) {
            this.title = null;
        } else {
            this.title =title;
        }
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        if (creator.isEmpty()) {
            this.creator = null;
        } else {
            this.creator = creator;
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher.isEmpty()) {
            this.publisher = null;
        } else {
            this.publisher = publisher;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.isEmpty()) {
            this.description = null;
        } else {
            this.description = description;
        }
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        
            this.language = language;
        
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        
            this.genre = genre;
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
