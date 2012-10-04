/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Superclass for books, CD:S etc. Due to @MappedSuperclass, all attributes
 * that aren't unique to a certain type of item can be placed here.
 * @author Eric
 */
@Entity
@Table(name="ITEMS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING,length=20)
@DiscriminatorValue("I")
public class Item implements Serializable{
    //Unique identifier (stored as strings since ISBN contains '-' signs).
    @Id
    private String id;
    private String title;
    @ManyToMany(mappedBy = "items")
    private List<Creator> creators;
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

    public Item() {
    }

    public Item(String id, String title, List<Creator> creators, int language,
    int year, String genre, String image, String description, int quantity, 
    int loan_period, int fee) {
        this.id = id;
        this.title = title;
        this.creators = creators;
        this.language = language;
        this.year = year;
        this.genre = genre;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
        this.loan_period = loan_period;
        this.fee = fee;
        addItems();
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

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }
    private void addItems(){
        for(int i = 0; i < this.creators.size(); i++){
            List<Item> temp = this.creators.get(i).getItems();
            temp.add(this);
            this.creators.get(i).setItems(temp);
            temp = null;
        }
    }
}
