/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.CreatorCollection;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.ItemCollection;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author user
 */
@SessionScoped
public class ItemBean implements Serializable{
    ItemCollection items = WebbLib.INSTANCE.getItems();
      
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
