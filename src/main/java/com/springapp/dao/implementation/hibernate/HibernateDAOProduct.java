package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOProduct;
import com.springapp.domain_objects.Product;

public class HibernateDAOProduct extends HibernateDAOIdentifiable<Product> implements DAOProduct {

    protected Class getInnerClass() {
        return Product.class;
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
