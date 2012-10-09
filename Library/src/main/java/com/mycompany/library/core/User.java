/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Eric
 */
@Entity
@Table(name="LIBRARY_USERS")
public class User implements Serializable{ 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    //do we need some kind of security measure for storing passwords?
    private String password;
    private String email;
    private Double feesOwed;
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<BorrowedItem> borrowedItems;
    @ManyToMany(mappedBy = "que", cascade=CascadeType.MERGE)
    private List<ReservedItem> reservedItems;
    @OneToMany
    private List<Item> bookmarkedItems;

    public User() {
    }
    public User(String username, String password, String email, 
    Double feesOwed) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.feesOwed = feesOwed;
        this.borrowedItems = new ArrayList<>();
        this.reservedItems = new ArrayList<>();
        this.bookmarkedItems = new ArrayList<>();
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getFeesOwed() {
        return feesOwed;
    }

    public void setFeesOwed(Double feesOwed) {
        this.feesOwed = feesOwed;
    }

    public List<BorrowedItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(BorrowedItem borrowedItem) {
        this.borrowedItems.add(borrowedItem);
    }

    public List<ReservedItem> getReservedItems() {
        return reservedItems;
    }

    public void setReservedItems(ReservedItem reservedItem) {
        this.reservedItems.add(reservedItem);
    }

    public List<Item> getBookmarkedItems() {
        return bookmarkedItems;
    }

    public void setBookmarkedItems(Item item) {
        this.bookmarkedItems.add(item);
    }
    
    
}
