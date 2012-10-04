/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    @ManyToMany
    private List<User> que;
    
    public ReservedItem() {
    }
    public ReservedItem(Item item, User user) {
        this.item = item;
        this.que = new ArrayList<>();
        reserve(user);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<User> getQue() {
        return que;
    }

    public void setQue(List<User> que) {
        this.que = que;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private void reserve(User user){
        reserveItem(user);
    }
    public void reserveItem(User user){
        this.que.add(user);
        List <ReservedItem> temp = user.getReservedItems();
        temp.add(this);
        user.setReservedItems(temp);
    }
}
