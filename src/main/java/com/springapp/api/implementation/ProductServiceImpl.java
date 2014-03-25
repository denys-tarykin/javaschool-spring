package com.springapp.api.implementation;

import com.springapp.api.ProductService;
import com.springapp.dao.Factory;
import com.springapp.domain_objects.Product;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public List<Product> listProduct(){
        List<Product> products=null;
        try {
            products =Factory.getInstance().DAOProduct().getAll();
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
}
