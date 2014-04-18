package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOCRUD;
import com.springapp.domain_objects.BaseDomainObject;
import com.springapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.*;


abstract class HibernateDAOCRUD<T extends BaseDomainObject> implements DAOCRUD<T> {

    //private Class<T> innerClass;

    /*protected HibernateDAOCRUD() {
        this.innerClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }*/
    abstract protected Class getInnerClass();

    public void add(T t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public void add(Collection<T> t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int i = 0;
            for (T obj : t) {
                session.save(obj);
                i++;
                if (i == 20) {   //TODO:property with JDBC batch size
                    session.flush();
                    session.clear();
                    i = 0;
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public void update(T t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

   /* public T getById(int id) throws SQLException {
        Session session = null;
        T t = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = (T) session.get(this.getInnerClass(), id);
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return t;
    }*/

    public List<T> getAll() throws SQLException {
        Session session = null;
        List<T> t = new ArrayList<T>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.createCriteria(this.getInnerClass()).list();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return t;
    }

    public List<T> getAllWithLimit(int offset, int limit) throws SQLException {
        Session session = null;
        List<T> t = new ArrayList<T>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.createCriteria(this.getInnerClass()).setFirstResult(offset).setMaxResults(limit).list();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return t;
    }

    public int getCount() throws SQLException {
        Session session = null;
        int count = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            count = (int) session.createCriteria(this.getInnerClass()).setProjection(Projections.rowCount()).uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return count;
    }

    //TODO:test this one
    public List<T> getRandom(int count) throws SQLException {
        Session session = null;
        List<T> t = new ArrayList<T>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            HashSet<Long> offsets = new HashSet<Long>();
            /*Long maximum = this.getCount();
            while(offsets.size()<count){
                offsets.add((Long)(Math.random()*))
            }*/
            t = session.createCriteria(this.getInnerClass()).add(Restrictions.sqlRestriction("1=1 order by rand()")).setMaxResults(count).list();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return t;
    }

   /* public void remove(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(this.getById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }*/

    public void remove(T t) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }






}
