package com.mycompany.library.core;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *  Class representing a certain user's position in a reserveque.
 */
@Entity
@Table(name="RESERVEDITEM_QUE")
public class QueElement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)        
    Long id;
    private int position;
    @ManyToOne
    private User user;

    public QueElement() {
    }
    public QueElement(int p, User u) {
        this.position = p;
        this.user = u;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
    
}
