/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
            //exception will always occur if the reserved item doesnt exist yet, 
            //which is ok since it will be created when return value evaluates as null.
           System.err.println("Query exception: " + e.getMessage());
        }
        finally{
            if (em != null) {
                em.close();
            }
        }        
        return reserved;
    }
    public void removeReservedItem(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "delete from ReservedItem ri where ri.id= :id";
            Query q = em.createQuery(query);
            q.setParameter("id", id);
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Query exception: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    void removeBorrowedItem(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "delete from BorrowedItem b where b.id= :id";
            Query q = em.createQuery(query);
            q.setParameter("id", id);
            em.getTransaction().begin();
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Query exception: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<BorrowedItem> getAllBorrowedItems() {
        List<BorrowedItem> items = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT bi from BorrowedItem bi";
            TypedQuery<BorrowedItem> q = em.createQuery(query, BorrowedItem.class);
            items = q.getResultList();

        } catch (Exception e) {
            System.err.println("Query exception: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return items;
    }
    //Till avancerad sökning
    public List<Item> searchItem(String id, String title, String creator, String publisher, String description, int fromYear, int toYear, boolean inStock, String language, String genre){
        EntityManager em;
        List<Item> resultList = null;
        String q = "Select i from Book i where 1=1 ";
        if(id != null) {q += " AND i.id = '"+id+"'";}
        if(title != null){q += " AND lower(i.title) like '%"+title.toLowerCase()+"%'";}
        if(creator != null){q += " And i.id in (select i2.id from Item i2, Creator c where i2.creators=c and lower(c.name) like '%"+creator.toLowerCase()+"%')";}
        if(publisher != null){q += " AND lower(i.publisher) like '%"+publisher.toLowerCase()+"%'";}
        if(description != null){q += " AND lower(i.description) like '%"+description.toLowerCase()+"%'";}
        if (fromYear != 0 && toYear != 0){q += " and i.year_released between "+fromYear+" and "+toYear;}
        else if(fromYear == 0 && toYear != 0){q += " and i.year_released <"+toYear;}
        else if(fromYear != 0 && toYear == 0){q += " and i.year_released >"+fromYear;}
        if(inStock){q += " AND i.quantity > 0";}
        if(language != null){q += " and lower(language) = '" + language.toLowerCase()+"'";}
        if(genre != null){q += " and lower(genre) = '" + genre.toLowerCase()+"'";}
        
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
        search = search.toLowerCase();
        
        String q = "SELECT i from Item i where i.id='"+search+"' or lower(i.title) like '%"+search+"%' or lower(i.description) like '%"+search+
                "%' OR i.id in (select i2.id from Item i2, Creator c where i2.creators=c and lower(c.name) like '%"+search+"%')";
        
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
    public List<String> getCreatorNames(Item item){
        List<String> names = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT c.name from Item i inner join i.creators c "+
            "WHERE i= :item";
            TypedQuery<String> q = em.createQuery(query, String.class);
            q.setParameter("item", item);
            names = q.getResultList();

        } catch (Exception e) {
            System.err.println("Query exception: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return names;
    }
    public int getQuePosition(User user, ReservedItem reservation){
        int position = 0;
        EntityManager em = emf.createEntityManager();
        try {
            String query = "select q.position from ReservedItem"+
            " ri inner join ri.que q where ri= :reservation AND q.user = :user";
            TypedQuery<Integer> q = em.createQuery(query, Integer.class);
            q.setParameter("reservation", reservation);
            q.setParameter("user", user);
            position = q.getSingleResult();
            
        }
        catch(Exception e){
           System.err.println("Query exception: " + e.getMessage());
        }
        finally{
            if (em != null) {
                em.close();
            }
        }       
        return position;
    }
    public void updatePositions(User user, ReservedItem reserved){
        EntityManager em = emf.createEntityManager();
        try {
            String query1 = "delete from QueElement q where q.id in (select q1.id from "+
            "ReservedItem ri inner join ri.que q1 where ri= :reserved and q.user= :user)";
            String query2 = "update QueElement q set "+
            "q.position= (q.position - 1) where q.id in (select q1.id from "+
            "ReservedItem ri inner join ri.que q1 where ri= :reserved)";
            Query q1 = em.createQuery(query1);
            Query q2 = em.createQuery(query2);
            q1.setParameter("reserved", reserved);
            q1.setParameter("user", user);
            q2.setParameter("reserved", reserved);
            em.getTransaction().begin();
            q1.executeUpdate();
            q2.executeUpdate();
            em.getTransaction().commit();
        }
        catch(Exception e){
           System.err.println("Query exception: " + e.getMessage());
           e.printStackTrace();
        }
        finally{
            if (em != null) {
                em.close();
            }
        }       
    }
}
