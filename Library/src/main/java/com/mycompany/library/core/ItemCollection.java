package com.mycompany.library.core;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class representing collection of all items.
 */
public class ItemCollection extends Controller<Item, String> {
    
    public ItemCollection(String puName){
        super(Item.class, puName);
        
    }
    public List<Item> getByTitle(String name) {
        List<Item> found = new ArrayList<>();
        for (Item i : getAll()) {
            if (i.getTitle().equals(name)) {
                found.add(i);
            }
        }
        return found;
    }
    
    
}
