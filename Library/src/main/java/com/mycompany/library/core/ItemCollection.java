package com.mycompany.library.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sjoholmf
 */
public class ItemCollection extends Controller<Item, String> {
    
    private ItemCollection(String puName){
        super(Item.class, puName);
        
    }
    
    public static ItemCollection newInstance(String puName){
        return new ItemCollection(puName);
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