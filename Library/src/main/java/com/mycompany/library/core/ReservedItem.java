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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Eric
 */
@Entity
public class ReservedItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //OneToOne since one item can either be reserved or not reserved
    @OneToOne
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
    private void reserve(User user){ 
        user.setReservedItems(this);
    }
    public int getQuePosition(User user){      
        QueryProccessor qp = WebbLib.INSTANCE.getQueryProccessor();
        return qp.getQuePosition(user, this);
    }
    public void updatePositions(User user) {
        QueryProccessor qp = WebbLib.INSTANCE.getQueryProccessor();
        
        qp.updatePositions(user, this);
    }
}