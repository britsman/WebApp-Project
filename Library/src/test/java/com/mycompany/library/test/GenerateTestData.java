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
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author sjoholmf
 */
public class GenerateTestData {
    private final Long milliPerDay = 86400000L;
    
    @Test
    public void runTests(){
        generateItemData();
        generateUserData();
    }
    
    
    public void generateItemData() {
        CreatorCollection creators = WebbLib.INSTANCE.getCreators();
        
        List<Creator> cList = new ArrayList<>();
        ItemCollection items = WebbLib.INSTANCE.getItems();
                
        Creator c1 = null;
        Creator c2 = null;
        
        c1 = creators.getByName("Herman Melville");
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
        
        c1 = creators.getByName("Jan Skansholm");
        c2 = creators.getByName("Ulf Bilting");
        
        if (c1 == null) {
            c1 = new Creator("Jan Skansholm");
        }
        if(c2 == null){
            c2 = new Creator("Ulf Bilting");
        }
        
        cList.clear();
        cList.add(c1);
        cList.add(c2);
        
        Item item4 = new Book("978-9144076065", "Vägen till C", cList, "Studentlitteratur", "Swedish", 2011, 269, "Computers", "/resources/img/defaultBook.png", 
                "Här står det en massa om C", 1, 10, 20);
        item4 = items.update(item4);
        
        c1 = creators.getByName("JRR Tolkien");
        if(c1 == null){
            c1 = new Creator("JRR Tolkien");
        }
        cList.clear();
        cList.add(c1);
        Item item5 = new Book("978-0618574957", "The Two Towers", cList, "Mariner Books", "English", 2005, 448, "Fantasy", "/resources/img/defaultBook.png",
                "Andra boken i LotR", 4, 7, 10);
        item5 = items.update(item5);
        
        c1 = creators.getByName("JRR Tolkien");
        if(c1 == null){
            c1 = new Creator("JRR Tolkien");
        }
        cList.set(0, c1);
        Item item6 = new Book("978-0618574940", "The Fellowship of the Ring", cList, "Mariner Books", "English", 2005, 544, "Fantasy", "/resources/img/defaultBook.png",
                "Första boken i LotR", 4, 7, 10);
        item6 = items.update(item6);
        
        c1 = creators.getByName("JRR Tolkien");
        if(c1 == null){
            c1 = new Creator("JRR Tolkien");
        }
        cList.set(0, c1);
        Item item7 = new Book("978-0618574971", "The Return of the King", cList, "Mariner Books", "English", 2005, 544, "Fantasy", "/resources/img/defaultBook.png",
                "Tredje och sista boken i LotR", 4, 7, 10);
        item7 = items.update(item7);
        
        
    }
    public void generateUserData() {
        // Making some users.
        UserRegistry ur = WebbLib.INSTANCE.getUsers();
        ItemCollection ic = WebbLib.INSTANCE.getItems();
        QueryProccessor q = WebbLib.INSTANCE.getQueryProccessor();
        
        User user1 = ur.getByUsername("user1");
        User user2 = ur.getByUsername("user2");
        User user3 = ur.getByUsername("user3");
        User user4 = ur.getByUsername("user4");
        if(user1 == null){
            user1 = new User("user1", "user1", "user1@user.mail", 0.0);
        }
        if (user2 == null) {
            user2 = new User("user2", "user2", "eric_britsman@hotmail.com", 0.0);
        }
        if (user3 == null) {
            user3 = new User("user3", "user3", "user3@user.mail", 0.0);            
        }
        if (user4 == null) {           
            user4 = new User("user4", "user4", "user4@user.mail", 0.0);
        }           
        user1.setIsLibrarian(true);
        user2.setFeesOwed(20.0);
        
        // Making some creators.
        CreatorCollection cc = WebbLib.INSTANCE.getCreators();
        
        Creator creator1 = cc.getByName("creator1");
        Creator creator2 = cc.getByName("creator2");
        Creator creator3 = cc.getByName("creator3");
        
        if (creator1 == null) {
            creator1 = new Creator("creator1");
        }
        if (creator2 == null) {
            creator2 = new Creator("creator2");
        }
        if (creator3 == null) {
            creator3 = new Creator("creator3");
        }
        List<Creator> creators1 = new ArrayList<>();  
        
        creators1.add(creator1);
        creators1.add(creator2);
        creators1.add(creator3); 
        
        Item item1 = new Book("1", "title1", creators1, "Publisher1", "Language1", 2012, 1337, "Genre1", 5);
        item1 = ic.update(item1);
        creator1 = item1.getCreators().get(0);
        creator2 = item1.getCreators().get(1);
        creator3 = item1.getCreators().get(2);
        creators1.set(0, creator1);
        creators1.set(1, creator2);
        creators1.set(2, creator3);
        Item item2 = new Book("2", "title2", creators1, "Publisher2", "Language2", 2012, 1337, "Genre2", 5);
        item2 = ic.update(item2);
        creator1 = item2.getCreators().get(0);
        creator2 = item2.getCreators().get(1);
        creator3 = item2.getCreators().get(2);
        creators1.set(0, creator1);
        creators1.set(1, creator2);
        creators1.set(2, creator3);
        Item item3 = new Book("3", "title3", creators1, "Publisher3", "Language3", 2012, 1337, "Genre3", 5);
        item3 = ic.update(item3);
        
        // BorrowedItem
        if(!user2.hasBorrowed(item2.getId())){
            BorrowedItem borrowedItem1 = new BorrowedItem(item2, user2);
            borrowedItem1.setLoanDate(new Date(borrowedItem1.getLoanDate().getTime()- milliPerDay * 6));
            borrowedItem1.setCollected(true);
        }
        
        // ReservedItem
        if(q.findReservedItem(item3) == null){
        ReservedItem reservedItem1 = new ReservedItem(item3, user3);
        }
        
        // BookmarkedItem
        if(!user4.getBookmarkedItems().contains(item1)){
            user4.setBookmarkedItems(item1);
        }
        ur.update(user1);
        ur.update(user2);
        ur.update(user3);
        ur.update(user4);
    }
}
