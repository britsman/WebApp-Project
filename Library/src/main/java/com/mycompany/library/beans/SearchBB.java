/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.Item;
import com.mycompany.library.core.QueryProccessor;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Hannes
 */
@Named("search")
@SessionScoped
public class SearchBB implements Serializable {

    private String id, title, creator, publisher, description, language, genre,type;
    private String topSearch;
    private int fromYear, toYear;
    private boolean inStock;
    private List<Item> result;

    public SearchBB() {
    }

    public void searchAll() {
        QueryProccessor query = WebbLib.INSTANCE.getQueryProccessor();
        if (!topSearch.equals("")) {
            result = query.searchAll(topSearch);
        }
    }

    public void searchAdvanced() {
        QueryProccessor query = WebbLib.INSTANCE.getQueryProccessor();
//        if(id=="")id=null;
//        if(title=="")title=null;
//        if(creator=="")creator=null;
//        if(description=="")description=null;
//        
        genre=null;
        language=null;
        type=null;
        result = query.searchItem(id, title, creator, publisher, description, fromYear, toYear, inStock, language, genre);

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
