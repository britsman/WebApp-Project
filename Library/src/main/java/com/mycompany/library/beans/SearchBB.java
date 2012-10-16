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
public class SearchBB implements Serializable{
    private String id=null,title=null,creator=null,description=null,language,genre,type;
    private String topSearch=null;
    private int fromYear = 0,toYear =0;
    private boolean inStock = true;
    private List<Item> result;
    
    public SearchBB() {
        
    }
    
    public void searchAll(){     
        QueryProccessor query = WebbLib.INSTANCE.getQueryProccessor();
        if(topSearch!=""){
        result = query.searchAll(topSearch);
        }
     }
    
    
    
    public void searchAdvanced(){
        QueryProccessor query = WebbLib.INSTANCE.getQueryProccessor();
        if(id=="")id=null;
        if(title=="")title=null;
        if(creator=="")creator=null;
        if(description=="")description=null;
        
        genre=null;
        language=null;
        type=null;
        result = query.searchItem(id, title, creator, description, fromYear, toYear, inStock, language, genre);
        System.out.println("DETTA ÄR INNE I ADVANCED först genre"+genre+" sen sprak"+language+" sen typ "+type);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
