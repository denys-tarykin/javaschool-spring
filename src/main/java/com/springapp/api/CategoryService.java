package com.springapp.api;

import com.springapp.domain_objects.Category;

import java.util.List;

/**
 * Created by Shichirin on 20.03.14.
 */
public interface CategoryService {
    public List<Category>LoadCategories();
}
