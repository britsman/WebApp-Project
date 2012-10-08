
package com.mycompany.library.core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author sjoholmf
 */
public class Controller<T, K> implements Serializable {
     
    private EntityManagerFactory emf;
    private Class<T> clazz;
    
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
        } 
        catch (Exception ex) {
            System.err.println("Add exception: " + ex.getMessage());
        } 
        finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void remove(K id) {
        EntityManager em = null;
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            T t = em.getReference(clazz, id);
            em.remove(t);
            em.getTransaction().commit();
        }catch(Exception ex){
            System.err.println("Add exception: " + ex.getMessage());
                        
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public T update(T t) {
        EntityManager em = null;
        T t2 = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            t2 = em.merge(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("Add exception: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return t2;
    }

    public T find(K id) {    
        EntityManager em = emf.createEntityManager();
        return em.find(clazz, id);
    }

    public List<T> getAll() {
        EntityManager em = emf.createEntityManager();
        String query = "select t from " + clazz.getSimpleName() + " t";
        TypedQuery<T> q = em.createQuery(query, clazz);
        List<T> found = q.getResultList();
        return found;
    }

    public List<T> getRange( int firstResult, int maxResults) {
        return getAll().subList(firstResult, firstResult+maxResults);
    }

    public int getCount() {
        return getAll().size();
    }
    
}
