
package com.mycompany.library.core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author sjoholmf
 */
public class Controller<T, K> implements Serializable {
     
    private EntityManagerFactory emf;
    private Class<T> clazz;
    
    @Id
    @GeneratedValue
    private Long id;
    
    public Controller() {}
    
    protected Controller(Class<T> clazz, String puName) {
        this.clazz = clazz;
        emf = Persistence.createEntityManagerFactory(puName);
    }

    
    public void add(T t) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("Add exception: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void remove(K id) {
        
    }

    public void update(T t) {
        
    }

    public T find(K id) {    
        
        return null;
    }

    public List<T> getAll() {
        return null;
    }

    public List<T> getRange( int firstResult, int maxResults) {
        return null;
    }

    public int getCount() {
        return 0;
    }
    
}
