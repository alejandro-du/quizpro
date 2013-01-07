package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public abstract class AbstractDao<T> implements IAbstractDao<T> {
    
    public List<T> ejecutarSelect(Query query) {
        
        List<T> list = null;
        
        try {
            list = query.getResultList();
        }
        catch (NoResultException e) {
            list.clear();
        }
        
        return list;
    }

    public boolean ejecutarUpdate(Query query, EntityManager em) {
        
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        try {
            query.executeUpdate();
            transaction.commit();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
            if(transaction.isActive()) {
                transaction.rollback();
            }
            
            return false;
            
        } finally {
            em.close();
        }
        return true;
    }
    
    /**
     * Usa java reflection para obtener el nombre del tipo parámetro T.
     * @return Nombre de la clase correspondiente a T.
     */
    public String getNombreT() {
        
        Type type = getClass().getGenericSuperclass();
        T vo = null;
        
        if (type instanceof ParameterizedType) {
            try {
                ParameterizedType paramType = (ParameterizedType) type;
                Class<T> tClass = (Class<T>) paramType.getActualTypeArguments()[0];

                vo = tClass.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return vo.getClass().getSimpleName();
    }

    public List<T> buscarTodos() {
        
        Query query = getEntityManager().createQuery("SELECT vo FROM " + getNombreT() + " vo");
        return (List<T>) ejecutarSelect(query);
    }

    public T buscarPorId(Long id) {
        Query query = getEntityManager().createQuery("SELECT vo FROM " + getNombreT() + " vo WHERE vo.id = :id");
        query.setParameter("id", id);
        
        List<T> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista.get(0);
    }
    
    public boolean guardarOActualizarPorId(Object vo) {
        
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.persist(em.merge(vo));
            transaction.commit();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
            if(transaction.isActive()) {
                transaction.rollback();
            }
            
            return false;
            
        } finally {
            em.close();
        }
        
        return true;
   }
    
    public boolean eliminarPorId(Long id) {
        
        if(buscarPorId(id) == null) {
            return false;
        }
        
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        try {
            Query query = em.createQuery("DELETE FROM " + getNombreT() + " vo WHERE vo.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
            if(transaction.isActive()) {
                transaction.rollback();
            }
            
            return false;
            
        } finally {
            em.close();
        }
        return true;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return javax.persistence.Persistence.createEntityManagerFactory("quizProPU");
    }

    public EntityManager getEntityManager() {
        
        return getEntityManagerFactory().createEntityManager();
    }

}
