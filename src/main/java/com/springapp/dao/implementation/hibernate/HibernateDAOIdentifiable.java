package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOIdentifiable;
import com.springapp.domain_objects.IdentifiableEntity;
import com.springapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 3/29/14.
 */
abstract class HibernateDAOIdentifiable<T extends IdentifiableEntity> extends HibernateDAOCRUD<T> implements DAOIdentifiable<T> {

    public T getById(int id) throws SQLException {
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
    }

    public Set getByIds(Collection<Integer> ids) throws SQLException {
        Session session = null;
        Set<T> categories = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Category cats;
            categories = new HashSet<T>(session.createCriteria(this.getInnerClass()).add(Restrictions.in("id", ids)).list());

        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categories;
    }

    public Set getByIds(Integer[] ids) throws SQLException {
        Session session = null;
        Set<T> categories = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Category cats;
            categories = new HashSet<T>(session.createCriteria(this.getInnerClass()).add(Restrictions.in("id", ids)).list());

        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categories;
    }

    public void remove(int id) throws SQLException {
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
    }



}