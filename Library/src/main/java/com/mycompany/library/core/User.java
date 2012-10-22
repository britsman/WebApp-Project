package com.mycompany.library.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private boolean isLibrarian;
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<BorrowedItem> borrowedItems;
    @OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
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
        this.isLibrarian = false;
        this.borrowedItems = new ArrayList<>();
        this.reservedItems = new ArrayList<>();
        this.bookmarkedItems = new ArrayList<>();
    }
    
    public void tryBorrowItem(Item item) {
        UserRegistry userRegistry = WebLib.INSTANCE.getUsers();
        if(item.getQuantity() > 0 && !this.hasBorrowed(item.getId())) {
            new BorrowedItem(item, this);
        }
    }
    
    public void removeBorrowedItem(BorrowedItem borrowedItem) {
        if (borrowedItems.contains(borrowedItem)) {
            ItemCollection ic = WebLib.INSTANCE.getItems();
            
            borrowedItem.removeFromTable();
            borrowedItems.remove(borrowedItem);
            
            Item item = borrowedItem.getItem();
            int quantity = item.getQuantity();
            item.setQuantity(quantity + 1);
            
            ic.update(item);
        }
    }
    
    public void tryReserveItem(Item item) {
        QueryProccessor q = WebLib.INSTANCE.getQueryProccessor();
        ReservedItem reserve = q.findReservedItem(item);
        if (reserve == null) {
            reserve = new ReservedItem(item, this);
        } else if (!this.hasReserved(reserve.getId())) {
            reserve.setQue(this);
            this.setReservedItems(reserve);
        }
    }
            
    public void updateReservation(ReservedItem reserved){
        for(int i = 0; i < this.reservedItems.size(); i++){
            if(reserved.getId() == this.reservedItems.get(i).getId()){
                this.reservedItems.remove(i);
                break;
            }
        }
    }
    
    public boolean hasReserved(Long id) {
        boolean hasIt = false;
        for (int i = 0; i < this.reservedItems.size(); i++) {
            if (id == this.reservedItems.get(i).getId()) {
                hasIt = true;
                break;
            }
        }
        return hasIt;
    }
    
    public boolean hasBorrowed(String id) {
        boolean hasIt = false;
        for (int i = 0; i < this.borrowedItems.size(); i++) {
            if (id.equals(this.borrowedItems.get(i).getItem().getId())){
                hasIt = true;
                break;
            }
        }
        return hasIt;
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

    public boolean isIsLibrarian() {
        return isLibrarian;
    }

    public void setIsLibrarian(boolean isLibrarian) {
        this.isLibrarian = isLibrarian;
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
    
    public void removeBookmarkedItem(Item item) {
        bookmarkedItems.remove(item);
    }
}
