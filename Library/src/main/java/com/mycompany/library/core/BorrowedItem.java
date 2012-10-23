package com.mycompany.library.core;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *  Class representing an item a user has borrowed/needs to collect/needs to
 *  return.
 */
@Entity
public class BorrowedItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
    private Item item;
    private @Temporal(javax.persistence.TemporalType.DATE)
    Date loanDate = new Date();
    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
    private User user;
    private boolean collected;

    public BorrowedItem() {
    }

    public BorrowedItem(Item item, User user) {
        this.item = item;
        this.user = user;
        this.collected = false;
        borrowItem();
    }

    private void borrowItem() {
        this.user.setBorrowedItems(this);
        this.item.setQuantity((this.item.getQuantity()) - 1);
    }

    public void removeFromTable() {
        QueryProccessor q = WebLib.INSTANCE.getQueryProccessor();
        q.removeBorrowedItem(this.id);
    }

    public void checkCollectDatePassed() {    
        QueryProccessor query = WebLib.INSTANCE.getQueryProccessor();
        UserRegistry users = WebLib.INSTANCE.getUsers();
        Long milliPerDay = 86400000L;
        Date today = new Date();
        int daysUncollected = (int) ((today.getTime() - this.loanDate.getTime()) / milliPerDay);
        if (daysUncollected >= 3) {
            this.user.removeBorrowedItem(this);
            users.update(this.user);      
            ReservedItem tempReserved = query.findReservedItem(this.item);
            if (tempReserved != null) {
                tempReserved.firstInQueBorrow();
            }
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
