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
    private String itemId = "0975-526";
    private String userName = "Eric3";
    
    @Test
    public void orderedTest(){//Tests were being done async, causing bugs.
        testAddItem();
        testAddUser();
    }
    
    //@Test
    public void testAddItem(){
        ItemCollection items = WebbLib.INSTANCE.getItems();
        CreatorCollection creators = WebbLib.INSTANCE.getCreators();
        List<Creator> temp = new ArrayList<>();
        Creator creator = creators.getByName(creatorName);
        if (creator == null) {
            creator = new Creator(creatorName);
        }
        temp.add(creator);
        Item item = new Book(itemId, "testbook", temp, "publisher",
        "English", 2012, 200, "comedy", "img", "desc", 1, 7, 10);
        item = items.update(item);
    }
    //@Test
    public void testAddUser(){
        UserRegistry users = WebbLib.INSTANCE.getUsers();
        ItemCollection items = WebbLib.INSTANCE.getItems();
        QueryProccessor q = WebbLib.INSTANCE.getQueryProccessor();
        User user = users.getByUsername(userName);
        if(user == null){
            user = new User(userName, "password","test@email", 0.0);
        }
        Item item = items.find(itemId);
        user.setBookmarkedItems(item);
        new BorrowedItem(item, user);  
        ReservedItem reserve = q.findReservedItem(item);
        if(reserve == null){
            reserve = new ReservedItem(item, user); 
        }
        else{
            reserve.setQue(user);
            user.setReservedItems(reserve);
        }
        user = users.update(user);
        System.out.println("\n" + user.getReservedItems() + "\n");
        System.out.println("\n" + user.getBorrowedItems() + "\n");
    }
}
