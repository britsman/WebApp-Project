/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Superclass for books, CD:S etc. Due to @MappedSuperclass, all attributes
 * that aren't unique to a certain type of item can be placed here.
 * @author Eric
 */
@MappedSuperclass
public class Item implements Serializable{
    //Unique identifier (stored as strings since ISBN contains '-' signs).
    @Id
    private String id;
    private String title;
    //Link to image file.
    private String image;
    //Link to text file containing description.
    private String description;
    //How many days the item can be loaned.
    private int loan_period;
    /*How much the "lateness" fee is increased by for each increment. varies
    between item types.**/
    private int fee;
    private int year;
    private String genre;
    private int language;
    private int quantity;

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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getLoan_period() {
        return loan_period;
    }
    public void setLoan_period(int loan_period) {
        this.loan_period = loan_period;
    }   
    public int getFee() {
        return fee;
    }
    public void setFee(int fee) {
        this.fee = fee;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getLanguage() {
        return language;
    }
    public void setLanguage(int language) {
        this.language = language;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
