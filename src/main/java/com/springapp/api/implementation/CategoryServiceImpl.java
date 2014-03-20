package com.springapp.api.implementation;

import com.springapp.api.CategoryService;
import com.springapp.dao.Factory;
import com.springapp.domain_objects.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Shichirin on 20.03.14.
 */
public class CategoryServiceImpl implements CategoryService{
    public List<Category>LoadCategories(){
        List<Category> cats=null;
        try {
           cats = Factory.getInstance().DAOCategory().getAll();
        } catch (SQLException e) {
           //TODO:logger;
        }
        return cats;
    }
}
