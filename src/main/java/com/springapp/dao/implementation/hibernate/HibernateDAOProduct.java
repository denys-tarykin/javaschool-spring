package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOProduct;
import com.springapp.domain_objects.Product;
import com.springapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateDAOProduct extends HibernateDAOIdentifiable<Product> implements DAOProduct {

    protected Class getInnerClass() {
        return Product.class;
    }

    public List loadProductsByCategory(int cat_id) throws SQLException {
        Session session = null;
        List<Product> products = new ArrayList<Product>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            products = session.createCriteria(this.getInnerClass())
                                            .createAlias("categories", "categories")
                                            .add(Restrictions.eq("categories.id", cat_id))
                                            .list();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return products;
    }
    /*public void createNewProduct(Product product, Category category) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }*/

}
