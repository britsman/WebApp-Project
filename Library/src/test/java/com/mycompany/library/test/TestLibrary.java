/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.test;

import com.mycompany.library.core.Book;
import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Creator;
import com.mycompany.library.core.CreatorCollection;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.ItemCollection;
import com.mycompany.library.core.QueryProccessor;
import com.mycompany.library.core.ReservedItem;
import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author user
 */
public class TestLibrary {
    private String creatorName = "Author";
    private String itemId = "0975-533";
    private String userName = "Eric";
    
    @Test
    public void testAddItem(){
        ItemCollection items = WebbLib.INSTANCE.getItems();
        CreatorCollection creators = WebbLib.INSTANCE.getCreators();
        List<Creator> temp = new ArrayList<>();
        Creator creator = creators.getByName(creatorName);
        Item item = items.find(itemId);
        temp.add(creator);
        if(item == null && creator == null){
            creator = new Creator(creatorName);
            temp.add(creator);
            item = new Book (itemId,"testbook", temp, "publisher", 
            "English", 2012, 200, "comedy", "img", "desc", 1, 7, 10);   
            items.add(item);
        }
        else{        
            temp.add(creator);
            item = new Book (itemId,"testbook", temp, "publisher", 
            "English", 2012, 200, "comedy", "img", "desc", 1, 7, 10);   
            items.update(item);
        }
    }
    @Test
    public void testAddUser(){
        UserRegistry users = WebbLib.INSTANCE.getUsers();
        User user = users.getByUsername(userName);
        if(user == null){
            user = new User(userName, "password","test@email", 0.0);
            persistUser(user, true);
        }
        else
        {
            persistUser(user, false);
        }
    }
    private void persistUser(User user, boolean newUser){
        UserRegistry users = WebbLib.INSTANCE.getUsers();
        ItemCollection items = WebbLib.INSTANCE.getItems();
        Item item = items.find(itemId);
        user.setBookmarkedItems(item);
        BorrowedItem borrowed = new BorrowedItem(item, user); 
        /*
        QueryProccessor q = WebbLib.INSTANCE.getQueryProccessor();
        ReservedItem temp = q.findReservedItem(item);
        System.out.println("!!!!!" + temp + "!!!!!");
        if(temp != null){
            temp.reserveItem(user);
        }
        else{
            temp = new ReservedItem(item, user); 
        }
        **/
        if(newUser){
            users.add(user);
        }
        else{
            users.update(user);
        }
    }
}
