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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *  Class representing reservation ques on unavailable items.
 */
@Entity
public class ReservedItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //OneToOne since one item can either be reserved or not reserved
    @OneToOne(fetch=FetchType.EAGER)
    private Item item;   
    //Que of users waiting to borrow
    @OneToMany(cascade={CascadeType.MERGE, CascadeType.REMOVE}, 
    fetch=FetchType.EAGER)
    @JoinColumn(name="RESERVEDITEM_ID")
    private List<QueElement> que;
    
    public ReservedItem() {
    }
    public ReservedItem(Item item, User user) {
        this.que = new ArrayList<>();
        this.que.add(new QueElement(1, user));
        this.item = item;
        reserve(user);
    }

    private void reserve(User user){ 
        user.setReservedItems(this);
    }
    public int getQuePosition(User user){      
        QueryProccessor qp = WebLib.INSTANCE.getQueryProccessor();
        return qp.getQuePosition(user, this);
    }
    public void updatePositions(User user) {
        QueryProccessor qp = WebLib.INSTANCE.getQueryProccessor();
        qp.updatePositions(user, this);
        user.updateReservation(this);      
    }
    public void firstInQueBorrow() {
        UserRegistry users = WebLib.INSTANCE.getUsers();
        QueryProccessor query = WebLib.INSTANCE.getQueryProccessor();        
        User tempUser = query.getUserAtPosition(1, this);
        this.updatePositions(tempUser);
        tempUser = users.update(tempUser);
        if (this.que.size() == 1) {
            query.removeReservedItem(this.id);
        }
        tempUser.tryBorrowItem(this.item);
        users.update(tempUser);
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<QueElement> getQue() {
        return que;
    }

    public void setQue(User user) {
        this.que.add(new QueElement((this.que.size()+1), user));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}