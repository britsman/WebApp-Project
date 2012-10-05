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

/**
 * Superclass for authors, musicians, bands etc. Due to @MappedSuperclass, all 
 * attributes that aren't unique to a certain type of creator can be placed here.
 * @author Eric
 */
@Entity
public class Creator implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    /*Could be either person or a bandname, no need to divide into first/last 
     * name, just use whole name for string comparisons.*/
    private String name;
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Item> items;

    public Creator() {
    }
    public Creator(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
