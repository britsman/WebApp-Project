package com.mycompany.library.beans;

import com.mycompany.library.core.Item;
import com.mycompany.library.core.ItemCollection;
import com.mycompany.library.core.WebLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *Proxy bean for ItemCollection collection.
 */
@ApplicationScoped
public class ItemBean implements Serializable{
    ItemCollection items = WebLib.INSTANCE.getItems();
      
    public List<Item> getAll(){
        return items.getAll();
    }
    public void add(Item item){
        items.add(item);
    } 
    public void remove(String id){
        items.remove(id);
    }
    public Item update(Item item){
        return items.update(item);
    }
    public Item find(String id){
        return items.find(id);
    }
    public List<Item> getByTitle(String name){
        return items.getByTitle(name);
    }
}
