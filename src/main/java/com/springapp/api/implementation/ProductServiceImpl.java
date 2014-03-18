package com.springapp.api.implementation;

import com.springapp.api.ProductService;
import com.springapp.dao.Factory;
import com.springapp.domain_objects.Product;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Shichirin on 18.03.14.
 */
@Service
public class ProductServiceImpl implements ProductService {


    public void addProduct(Product Product) {
        try {
            Factory.getInstance().DAOProduct().add(Product);
        } catch (SQLException e) {
           //TODO:log
        }

    }

    public List<Product> listProduct(){
        List<Product> products=null;
        try {
            products =Factory.getInstance().DAOProduct().getAll();
        } catch (SQLException e) {
            //TODO:log
        }
        return products;
    }

    public void removeProduct(Integer id) {
        try {
            Factory.getInstance().DAOProduct().remove(id);
        } catch (SQLException e) {
            //TODO:log
        }

    }
}
