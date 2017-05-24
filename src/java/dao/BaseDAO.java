/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.StoredProcedureCall;

/**
 *
 * @author quyqu
 * @param <T>
 */
public abstract class BaseDAO<T> {

    protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("OnlineBookStorePU");

    private Class classe;

    public BaseDAO(Class classe) {
        //faz o atributo classe receber a classe da entidade, que deve ser passada no construtor      
        this.classe = classe;
    }

    public <T> T insertObject(T obj) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.flush();
            em.getTransaction().commit();
            em.clear();
            em.close();
        } catch (Exception e) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        return obj;
    }

    public boolean updateObject(T obj) {
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            em.clear();
            em.close();
        } catch (Exception ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean deleteObject(T obj) {
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(obj));
            em.getTransaction().commit();
            em.clear();
            em.close();
        } catch (Exception ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Object findObjectById(String Id) {
        Object Obj = null;
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            Obj = em.find(this.classe, Id);
            em.getTransaction().commit();
            em.clear();
            em.close();
        } catch (Exception ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return Obj;
    }

    public List<T> executeNamedQuery(String queryName) {
        List<T> Objects = new ArrayList<T>();
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<T> query = em.createNamedQuery(queryName, this.classe);
            Objects = query.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
        } catch (Exception ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return Objects;
    }

    public Object executeNamedQuery(String queryName, Hashtable<String, Integer> params) {
        Object Objects = new ArrayList<T>();
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<T> query = em.createNamedQuery(queryName, this.classe);
            if (params != null) {
                for (Map.Entry m : params.entrySet()) {
                    query.setParameter((String) m.getKey(), m.getValue());
                }
            }
            Objects = query.getSingleResult();
            em.getTransaction().commit();
            em.clear();
            em.close();
        } catch (Exception ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return Objects;
    }

    public List<T> executeNamedQueryForList(String queryName, Hashtable<String, Integer> params) {
        List<T> Objects = new ArrayList<T>();
        try {
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<T> query = em.createNamedQuery(queryName, this.classe);
            for (Map.Entry m : params.entrySet()) {
                query.setParameter((String) m.getKey(), m.getValue());
            }
            Objects = query.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
        } catch (Exception ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return Objects;
    }

    public List<T> executeStoredProcedure(String queryName, Hashtable<String, Integer> params) {
        List<T> Objects = new ArrayList<T>();
        try {
            EntityManager em = factory.createEntityManager();
            StoredProcedureQuery query = em.createNamedStoredProcedureQuery(queryName);
            for (Map.Entry m : params.entrySet()) {
                query.setParameter((String) m.getKey(), m.getValue());
            }
            Objects = query.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return Objects;
    }
}