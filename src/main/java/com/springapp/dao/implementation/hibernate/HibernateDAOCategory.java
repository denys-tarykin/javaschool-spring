package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOCategory;
import com.springapp.domain_objects.Category;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.springapp.util.HibernateUtil;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class HibernateDAOCategory extends HibernateDAOCRUD<Category> implements DAOCategory {

    protected Class getInnerClass() {
        return Category.class;
    }

    public void swap(int id1, int id2) {
    }

    public Set getForProductCreate(Set<Integer> ids) throws SQLException {
        Session session = null;
        Set<Category> categories = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Category cats;
            categories = new HashSet<Category>(session.createCriteria(Category.class).add(Restrictions.in("id", ids)).list());

        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return categories;
    }

}


/*
import domain_objects.Category;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateDAOCategory implements dao.interfaces.DAOCategory {

    public void add(Category cat) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cat);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка данных", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Category cat) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(cat);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка данных", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Category getById(int id) throws SQLException {
        Session session = null;
        Category cat = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cat = (Category) session.load(Category.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка данных", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cat;
    }

    public List<Category> getAll() throws SQLException {
        Session session = null;
        List<Category> cats = new ArrayList<Category>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Class tmmp = Category.class;
            cats = session.createCriteria(Category.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка данных I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cats;
    }

    public void remove(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.Delete(this.getById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка данных", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


}
*/