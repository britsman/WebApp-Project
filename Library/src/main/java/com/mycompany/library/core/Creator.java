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
 * @author Eric
 */
@Entity
public class Creator implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(cascade={CascadeType.MERGE})
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

    public void setItems(Item item) {
        this.items.add(item);
    }
}
