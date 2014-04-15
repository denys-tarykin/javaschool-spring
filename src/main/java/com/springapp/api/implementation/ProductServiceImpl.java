package com.springapp.api.implementation;

import com.springapp.api.ProductService;
import com.springapp.dao.Factory;
import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Product;
import com.springapp.domain_objects.Tag;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Shichirin on 18.03.14.
 */
@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = Logger.getLogger(ProductService.class.getName());

    public void addProduct(Product Product) {
        try {
            Factory.getInstance().DAOProduct().add(Product);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }

    }

    public List<Product> getAllProducts() {
        List<Product> products=null;
        try {
            products = Factory.getInstance().DAOProduct().getAll();
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }
        return products;
    }

    public void removeProduct(Integer id) {
        try {
            Factory.getInstance().DAOProduct().remove(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"DB error:",e);
        }

    }

    public List<Product> getProducts(int offset, int limit) {
        List<Product> products = null;
        try {
            products = Factory.getInstance().DAOProduct().getAllWithLimit(offset, limit);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error", e);
        }
        return products;
    }

    public Product getProduct(int id) {
        Product product = null;
        try {
            product = Factory.getInstance().DAOProduct().getById(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error:", e);
        }
        return product;
    }

    public void edit(Product product) {
        try {
            Factory.getInstance().DAOProduct().update(product);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error:", e);
        }
    }

    public void edit(int id, String name, String description, BigDecimal price, HashSet<Category> categories, HashSet<Tag> tags) {
        Product product = this.getProduct(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategories(categories);
        product.setTags(tags);
        this.edit(product);
    }
}
