package com.mycompany.library.core;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Superclass for all item types (Book is currently the only itemtype stored by the library).
 */
@Entity
@Table(name="ITEMS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING,length=20)
@DiscriminatorValue("I")
public class Item implements Serializable {
    // Unique identifier (stored as strings since ISBN contains '-' signs).
    @Id
    private String id;
    private String title;
    @ManyToMany(mappedBy = "items", cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
    private List<Creator> creators;
    // Link to image file.
    private String image;
    // Link to text file containing description.
    private String description;
    // How many days the item can be loaned.
    private int loan_period;
    /* How much the "lateness" fee is increased by for each increment. varies
    between item types. */
    private int fee;
    private int year_released;
    private String genre;
    private String language;
    private int quantity;
    @Transient
    private String creatorNames;
    private String publisher;
            
    public Item() {
    }

    public Item(String id, String title,String publisher, List<Creator> creators, String language,
    int year, String genre, String image, String description, int quantity, 
    int loan_period, int fee) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.creators = creators;
        this.language = language;
        this.year_released = year;
        this.genre = genre;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
        this.loan_period = loan_period;
        this.fee = fee;
        addItems();
    }
    public Item(String id, String title,String publisher, List<Creator> creators, String language,
    int year, String genre, int quantity) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.creators = creators;
        this.language = language;
        this.year_released = year;
        this.genre = genre;
        this.image = "/resources/img/defaultBook.png";
        this.description = "No description available.";
        this.quantity = quantity;
        this.loan_period = 7;
        this.fee = 10;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
        return year_released;
    }
    public void setYear(int year) {
        this.year_released = year;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
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
        this.creators.addAll(creators);
    }
    private void addItems(){
        for(int i = 0; i < this.creators.size(); i++){
            this.creators.get(i).setItems(this);
        }
    }
    public void setCreatorNames(String names){
        this.creatorNames = names;
    }
    public String getCreatorNames(){
        creatorNames ="";
        QueryProccessor qp = WebLib.INSTANCE.getQueryProccessor();
        List<String> temp = qp.getCreatorNames(this);
        for (String s : temp) {
            creatorNames += s + ", ";
        }
        if (!creatorNames.isEmpty()) {
            creatorNames= creatorNames.substring(0, creatorNames.length() - 2);
        }
        return creatorNames;
    }

        
        
}
