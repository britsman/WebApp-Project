/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

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
            e.printStackTrace();
        }
        finally{
            if (em != null) {
                em.close();
            }
        }        
        return reserved;
    }
}
