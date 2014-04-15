package com.springapp.api;

import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Product;
import com.springapp.domain_objects.Tag;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Shichirin on 18.03.14.
 */
public interface  ProductService {

    public void addProduct(Product Product);

    public List<Product> getAllProducts();

    public void removeProduct(Integer id);

    public List<Product> getProducts(int offset, int limit);

    public Product getProduct(int id);

    public void edit(Product product);

    public void edit(int id, String name, String description, BigDecimal price, HashSet<Category> categories, HashSet<Tag> tags);

}
