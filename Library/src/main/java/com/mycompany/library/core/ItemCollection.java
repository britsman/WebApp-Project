package com.mycompany.library.core;

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
    
    
}
