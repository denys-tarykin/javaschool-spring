package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOProduct;
import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Product;
import org.hibernate.Session;
import com.springapp.util.HibernateUtil;

import java.sql.SQLException;

public class HibernateDAOProduct extends HibernateDAO<Product> implements DAOProduct {

    protected Class getInnerClass() {
        return Product.class;
    }

    protected void CreateNewProduct(Product product, Category category)throws SQLException {
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
    }

}
