/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Eric
 */
public class QueryProccessor {
    private EntityManagerFactory emf;
    
    public QueryProccessor(){
    }
    public QueryProccessor(EntityManagerFactory emf){
        this.emf = emf;
    }
    public ReservedItem findReservedItem(Item item){
        ReservedItem reserved = null;
        EntityManager em = emf.createEntityManager();
        try {
            String query = "select ri from ReservedItem ri where ri.item= :item";
            TypedQuery<ReservedItem> q = em.createQuery(query, ReservedItem.class);
            q.setParameter("item", item);
            reserved = q.getSingleResult();
        }
        catch(Exception e){
           // e.printStackTrace();
        }
        finally{
            if (em != null) {
                em.close();
            }
        }        
        return reserved;
    }
    
    //Till avancerad sökning
    public List<Item> searchItem(String id, String title, String creator, String description, int fromYear, int toYear, boolean inStock, String language, String genre){
        EntityManager em;
        List<Item> resultList = null;
        String q = "Select i from Item i where 1=1 ";
        if(id != null) {q += " AND i.id = '"+id+"'";}
        if(title != null){q += " AND i.title = '"+title+"'";}
        
        if(creator != null){q += " And i.id in (select i2.id from Item i2, Creator c where i2.creators=c and c.name='"+creator+"')";}
        if(description != null){q += " AND i.description like '%"+description+"%'";}
        if (fromYear != 0 && toYear != 0){q += " and i.year_released between "+fromYear+" and "+toYear;}
        else if(fromYear == 0 && toYear != 0){q += " and i.year_released <"+toYear;}
        else if(fromYear != 0 && toYear == 0){q += " and i.year_released >"+fromYear;}
        if(inStock){q += " AND i.quantity > 0";}
        if(language != null){q += " and language = '" + language+"'";}
        if(genre != null){q += " and genre = '" + genre+"'";}
        
        System.out.println(q);
        
        try{
            em = emf.createEntityManager();
            TypedQuery<Item> query = em.createQuery(q, Item.class);
            resultList = query.getResultList();
            
        }
        catch(Exception e){
            System.err.println("Query exception: " + e.getMessage());
        }
        finally{
            return resultList;
        }
    }
    
    //söker efter search-argumentet i id, titel, description och creatorname
    public List<Item> searchAll(String search){
        List<Item> results = null;
        EntityManager em = emf.createEntityManager();
        
        String q = "SELECT i from Item i where i.id='"+search+"' or i.title like '%"+search+"%' or i.description like '%"+search+
                "%' OR i.id in (select i2.id from Item i2, Creator c where i2.creators=c and c.name like '%"+search+"%')";
        
        System.out.println(q);
        
        try{
            em = emf.createEntityManager();
            TypedQuery<Item> query = em.createQuery(q, Item.class);
            results = query.getResultList();
            
        }
        catch(Exception e){
            System.err.println("Query exception: " + e.getMessage());
        }
        finally{
            return results;
        }
    }
}
