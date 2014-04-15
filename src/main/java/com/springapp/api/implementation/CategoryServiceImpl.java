package com.springapp.api.implementation;

import com.springapp.api.CategoryService;
import com.springapp.api.ProductService;
import com.springapp.dao.Factory;
import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Product;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Shichirin on 20.03.14.
 */
public class CategoryServiceImpl implements CategoryService{
    Logger logger = Logger.getLogger(ProductService.class.getName());

    public List<Category> loadCategories() {
        List<Category> cats=null;
        try {
           cats = Factory.getInstance().DAOCategory().getAll();
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return cats;
    }

    public HashSet<Category> loadCategories(Collection<Integer> ids) {
        HashSet<Category> cats = null;
        try {
            cats = Factory.getInstance().DAOCategory().getByIds(ids);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error:", e);
        }
        return cats;
    }

    public HashSet<Category> loadCategories(Integer[] ids) {
        HashSet<Category> cats = null;
        try {
            cats = Factory.getInstance().DAOCategory().getByIds(ids);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error:", e);
        }
        return cats;
    }

    public void Delete(int id){
        try {
            Factory.getInstance().DAOCategory().remove(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error:", e);
        }
    }

    public void newCategory(String name, String desc) {
        try {
            Category cat = new Category();
            cat.setName(name);
            cat.setDescription(desc);
            Factory.getInstance().DAOCategory().add(cat);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error:", e);
        }
    }

    public Category getCategory(int id){
        Category cat=null;
        try {
            cat = Factory.getInstance().DAOCategory().getById(id);
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error:", e);
        }
        return cat;
    }

    public void editCategory(int id,String name,String desc){
        try {
            Category cat = Factory.getInstance().DAOCategory().getById(id);
            cat.setName(name);
            cat.setDescription(desc);
            Factory.getInstance().DAOCategory().update(cat);
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error:", e);
        }
    }

    public List<Product>LoadProductByCategory(int cat_id,int offset){
        List<Product> products=null;
        try {
            products = Factory.getInstance().DAOProduct().loadProductsByCategory(cat_id,offset);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return products;
    }
}
