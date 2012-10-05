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
    
    @Test
    public void testAdd(){
        ItemCollection items = WebbLib.INSTANCE.getItems();
        UserRegistry users = WebbLib.INSTANCE.getUsers();
        CreatorCollection creators = WebbLib.INSTANCE.getCreators();
        List<Creator> temp = new ArrayList<>();
        Creator creator = new Creator("test");      
        temp.add(creator);
        Item item = new Book ("0975-532","testbook", temp, "publisher", 
        "English", 2012, 200, "comedy", "img", "desc", 1, 7, 10);
        User user = new User("Eric", "password","test@email", 0.0);
        items.add(item);
        creators.add(creator);
        ReservedItem reserved = new ReservedItem(item, user); 
        BorrowedItem borrowed = new BorrowedItem(item, user); 
        user.setBookmarkedItems(item);
        user.setBorrowedItems(borrowed);
        user.setReservedItems(reserved);
        users.add(user);
    }

}
