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
    private String itemId = "0975-523";
    private String userName = "Eric1";
    
    @Test
    public void orderedTest(){//Tests were being done async, causing bugs.
        testAddItem();
        testAddUser();
        //ska fixa så testAddUser lägger till flera grejer, annars kmmr detta
        //test ta bort reservationen så fort den läggs tillför varje ny user).
        //testAlterQue(); 
        testSearch();
        //samma som för testAlterQue, kör inte denna om ni inte har mer än en grej i dbn.
        //testRemove(); 
    }
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
        "English", 2012, 200, "comedy", "/resources/img/defaultBook.png", "desc", 1, 7, 10);
        item = items.update(item);
    }
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
        if(item.getQuantity() > 0 && !user.hasBorrowed(item.getId())){
        new BorrowedItem(item, user);  
        }
        ReservedItem reserve = q.findReservedItem(item);
        if (reserve == null) {
            reserve = new ReservedItem(item, user);
        } else if (!user.getReservedItems().contains(reserve)) {
            reserve.setQue(user);
            user.setReservedItems(reserve);
        }
        user = users.update(user);
    }
    public void testAlterQue(){
        UserRegistry users = WebbLib.INSTANCE.getUsers();
        QueryProccessor q = WebbLib.INSTANCE.getQueryProccessor();
        ItemCollection items = WebbLib.INSTANCE.getItems();
        User user = users.getByUsername(userName);
        Item item = items.find(itemId);
        ReservedItem reserve = q.findReservedItem(item);
        if(reserve != null && user.hasReserved(reserve.getId())){
            reserve.updatePositions(user);
            user.updateReservation(reserve);
            user = users.update(user);
            if(reserve.getQue().size() == 1){
                reserve = q.findReservedItem(item);
                q.removeReservedItem(reserve.getId());
            }
        }
    }
    public void testSearch(){
        CreatorCollection creators = WebbLib.INSTANCE.getCreators();
        List<Creator> cList = new ArrayList<>();
        ItemCollection items = WebbLib.INSTANCE.getItems();
        
        Creator c1 = creators.getByName("Herman Melville");
        if (c1 == null) {
            c1 = new Creator("Herman Melville");
        }
        cList.add(c1);
        Item item1 = new Book("978-0140623178", "Moby Dick", cList, "Penguin",
        "English", 1851, 544, "Horror", "/resources/img/defaultBook.png", "Tuff bok om valar och grejer, inte skriven av Jules Verne", 1, 7, 10);
        item1 = items.update(item1);
        
        c1 = creators.getByName("Jules Verne");
        if (c1 == null) {
            c1 = new Creator("Jules Verne");
        }
        cList.set(0, c1);
        Item item2 = new Book("978-2080702999", "La tour du Monde en quatre-vingts jours", cList, "Flammarion", "French", 1873, 200,
        "Adventure", "/resources/img/defaultBook.png", "Tuff bok, inte lika många valar dock.", 1, 7, 10);
        item2 = items.update(item2);
        
        c1 = item2.getCreators().get(0);
        cList.set(0, c1);
        
        Item item3 = new Book("978-0486440880", "Journey to the Center of the Earth", cList, "Dover Thrift", "English", 1864, 200, 
                "Adventure", "/resources/img/defaultBook.png", "Massa grejer i jorden", 0, 7, 10);
       item3 = items.update(item3);
       
        //Här börjar sökningen
        QueryProccessor q = WebbLib.INSTANCE.getQueryProccessor();
        List<Item> foundItems = null;
        
        foundItems = q.searchItem(null, "Moby Dick", null, null, null, 0, 0, false, null, null);
        System.out.println("Results for title Moby Dick: " + foundItems.get(0).getId());
        System.out.println("Expected: " + item1.getId());
        
        foundItems = q.searchItem(null, null, "Jules Verne", null, null, 0, 0, false, null, null);
        System.out.println("Results for creator Jules Verne: " + foundItems.get(0).getId() + 
        " & " + foundItems.get(1).getId());
        System.out.println("Expected: " + item2.getId() + " & " + item3.getId());
        
        foundItems= q.searchItem(null, null, null, null, "valar", 0, 0, false, null, null);
        System.out.println("Böcker om valar: " + foundItems);
        
        foundItems= q.searchItem(null, null, null, null, "valar", 0, 0, true, null, null);
        System.out.println("Böcker om valar i lager: " + foundItems);
        
        foundItems= q.searchItem(null, null, null, null, null, 1864, 1864, false, null, null);
        System.out.println("Böcker skrivna 1864: " + foundItems);
        
        foundItems= q.searchItem(null, null, null, null, null, 1860, 0, false, null, null);
        System.out.println("Böcker skrivna efter 1860: " + foundItems);
        
        foundItems= q.searchItem(null, null, null, null, null, 0, 1870, false, null, null);
        System.out.println("Böcker skrivna före 1870: " + foundItems);
        
        foundItems = q.searchAll("Verne");
        System.out.println("Antal resultat: " + foundItems.size()); 
        
        foundItems= q.searchItem(null, null, null, "Penguin", null, 0, 0, false, null, null);
        System.out.println("Böcker utgivna via Penguin: " + foundItems);
        
    }
    public void testRemove(){
        
        UserRegistry users = WebbLib.INSTANCE.getUsers();
        User user = users.getByUsername(userName);
        try{
        user.getBookmarkedItems().remove(0);
        user.getBorrowedItems().get(0).removeFromTable();
        user.getBorrowedItems().remove(0);
        users.update(user);
        }
        catch(Exception e){
            //Do nothing
        }
    }
}