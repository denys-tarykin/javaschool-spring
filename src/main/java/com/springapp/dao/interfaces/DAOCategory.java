package com.springapp.dao.interfaces;

import  com.springapp.domain_objects.Category;

public interface DAOCategory extends DAO<Category> {
    public void swap(int id1, int id2);
}
