package com.springapp.api.implementation;

import com.springapp.api.CategoryService;
import com.springapp.api.ProductService;
import com.springapp.dao.Factory;
import com.springapp.domain_objects.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Shichirin on 20.03.14.
 */
public class CategoryServiceImpl implements CategoryService{
    Logger logger = Logger.getLogger(ProductService.class.getName());

    public List<Category>LoadCategories(){
        List<Category> cats=null;
        try {
           cats = Factory.getInstance().DAOCategory().getAll();
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return cats;
    }
}
