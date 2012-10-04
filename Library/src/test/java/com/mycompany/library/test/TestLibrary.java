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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

/**
 *
 * @author user
 */
public class TestLibrary {
    /* Måste skappa en "main" singleton klass som håller instansen, ska fixa
     * det när jag kommer hem.
    @Test
    public void testAdd(){
        ItemCollection items =;
        UserRegistry users = ;
        CreatorCollection creators =;
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
        user.getBookmarkedItems().add(item);
        user.getBorrowedItems().add(borrowed);
        user.getReservedItems().add(reserved);
        users.add(user);
    }
    **/

}
