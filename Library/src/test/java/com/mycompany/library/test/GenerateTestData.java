/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.test;

import com.mycompany.library.core.Book;
import com.mycompany.library.core.Creator;
import com.mycompany.library.core.CreatorCollection;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.ItemCollection;
import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sjoholmf
 */
public class GenerateTestData {
    
    public GenerateTestData() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void runTests(){
        genData();
        generateUserData();
    }
    
    
    public void genData() {
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
        "English", 1851, 544, "Horror", "img", "Tuff bok om valar och grejer, inte skriven av Jules Verne", 1, 7, 10);
        item1 = items.update(item1);
        
        c1 = creators.getByName("Jules Verne");
        if (c1 == null) {
            c1 = new Creator("Jules Verne");
        }
        cList.set(0, c1);
        Item item2 = new Book("978-2080702999", "La tour du Monde en quatre-vingts jours", cList, "Flammarion", "French", 1873, 200,
        "Adventure", "img", "Tuff bok, inte lika många valar dock.", 1, 7, 10);
        item2 = items.update(item2);
        
        c1 = item2.getCreators().get(0);
        cList.set(0, c1);
        
        Item item3 = new Book("978-0486440880", "Journey to the Center of the Earth", cList, "Dover Thrift", "English", 1864, 200, 
                "Adventure", "img", "Massa grejer i jorden", 0, 7, 10);
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
        
        Item item4 = new Book("978-9144076065", "Vägen till C", cList, "Studentlitteratur", "Swedish", 2011, 269, "Computers", "img", 
                "Här står det en massa om C", 1, 10, 20);
        item4 = items.update(item4);
        
        c1 = creators.getByName("JRR Tolkien");
        if(c1 == null){
            c1 = new Creator("JRR Tolkien");
        }
        cList.clear();
        cList.add(c1);
        Item item5 = new Book("978-0618574957", "The Two Towers", cList, "Mariner Books", "English", 2005, 448, "Fantasy", "img",
                "Andra boken i LotR", 4, 7, 10);
        item5 = items.update(item5);
        
        c1 = creators.getByName("JRR Tolkien");
        if(c1 == null){
            c1 = new Creator("JRR Tolkien");
        }
        cList.set(0, c1);
        Item item6 = new Book("978-0618574940", "The Fellowship of the Ring", cList, "Mariner Books", "English", 2005, 544, "Fantasy", "img",
                "Första boken i LotR", 4, 7, 10);
        item6 = items.update(item6);
        
        c1 = creators.getByName("JRR Tolkien");
        if(c1 == null){
            c1 = new Creator("JRR Tolkien");
        }
        cList.set(0, c1);
        Item item7 = new Book("978-0618574971", "The Return of the King", cList, "Mariner Books", "English", 2005, 544, "Fantasy", "img",
                "Tredje och sista boken i LotR", 4, 7, 10);
        item7 = items.update(item7);
        
        
    }
    
    public void generateUserData() {
        UserRegistry ur = WebbLib.INSTANCE.getUsers();
        
        User user1 = new User("user1", "user1", "user1@user.mail", 0.0);
        User user2 = new User("user2", "user2", "user2@user.mail", 0.0);
        User user3 = new User("user3", "user3", "user3@user.mail", 0.0);
        
        user1.setIsLibrarian(true);
        
        user2.setFeesOwed(20.0);
        
        ur.add(user1);
        ur.add(user2);
        ur.add(user3);
    }
}
