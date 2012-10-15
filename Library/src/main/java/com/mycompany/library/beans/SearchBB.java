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
    private String id,title,creator,description,language,genre,type;
    private String topSearch;
    private int fromYear,toYear;
    private boolean inStock;
    private List<Item> result;
    
    public SearchBB() {
        
    }
    
    public void searchAll(){
        
        QueryProccessor query = WebbLib.INSTANCE.getQueryProccessor();
        result = query.searchAll(topSearch);
        
        System.out.println(result);
    }
    
    public void searchAdvanced(String id, String title, String creator, String description, int fromYear, int toYear, boolean inStock, String language, String genre){
        QueryProccessor query = WebbLib.INSTANCE.getQueryProccessor();
        result = query.searchItem(id, title, creator, description, fromYear, toYear, inStock, language, genre);
        System.out.println(result);
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
