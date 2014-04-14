package com.springapp.api;

import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Product;

import java.util.List;

/**
 * Created by Shichirin on 20.03.14.
 */
public interface CategoryService {
    public List<Category>LoadCategories();

    public void Delete(int id);

    public void newCategory(String name, String desc);

    public Category getCategory(int id);

    public void editCategory(int id,String name,String desc);
    public List<Product>LoadProductByCategory(int cat_id,int offset);
}
