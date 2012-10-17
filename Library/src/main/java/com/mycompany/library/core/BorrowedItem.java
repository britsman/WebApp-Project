/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Eric
 */
@Entity
public class BorrowedItem implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Item item;
    private @Temporal(javax.persistence.TemporalType.DATE) Date loanDate = new Date();
    @ManyToOne(cascade=CascadeType.MERGE)
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
    private void borrowItem(){
        ItemCollection items = WebbLib.INSTANCE.getItems();
        this.user.setBorrowedItems(this);
        this.item.setQuantity((this.item.getQuantity())-1);
        items.update(this.item);
    }
    public void removeFromTable(){
        QueryProccessor q = WebbLib.INSTANCE.getQueryProccessor();
        q.removeBorrowedItem(this.id);
    }
    public void checkCollectDatePassed(){
        ItemCollection items = WebbLib.INSTANCE.getItems();
        Long milliPerDay = 86400000L;
        Date today = new Date();
        int daysUncollected = (int)((today.getTime() - this.loanDate.getTime()) / milliPerDay);
        if (daysUncollected >= 3){
            this.item.setQuantity((this.item.getQuantity())+1);
            this.removeFromTable();
        }
        items.update(this.item);
    }
}
