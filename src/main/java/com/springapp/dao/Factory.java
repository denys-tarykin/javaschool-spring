package com.springapp.dao;

import  com.springapp.dao.implementation.hibernate.HibernateDAOCategory;
import  com.springapp.dao.implementation.hibernate.HibernateDAOProduct;
import  com.springapp.dao.implementation.hibernate.HibernateDAOUser;

public class Factory {

    private HibernateDAOCategory DAO_CATEGORY = null;
    private HibernateDAOUser DAO_USER = null;
    private HibernateDAOProduct DAO_PRODUCT = null;
    private static Factory INSTANCE = null;

    public static Factory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Factory();
        }
        return INSTANCE;
    }

    public HibernateDAOCategory DAOCategory() {
        if (DAO_CATEGORY == null) {
            DAO_CATEGORY = new HibernateDAOCategory();
        }
        return DAO_CATEGORY;
    }

    public HibernateDAOUser DAOUser() {
        if (DAO_USER == null) {
            DAO_USER = new HibernateDAOUser();
        }
        return DAO_USER;
    }
    public HibernateDAOProduct DAOProduct() {
        if (DAO_PRODUCT == null) {
            DAO_PRODUCT = new HibernateDAOProduct();
        }
        return DAO_PRODUCT;
    }

}
