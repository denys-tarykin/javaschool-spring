package com.springapp.util;

import com.springapp.domain_objects.Category;
import com.springapp.domain_objects.Tag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Shichirin on 13.04.14.
 */
public class ProductValidator extends Validator {

    private String name;

    private String description;

    private Integer price;

    private Set<Category> categories = new HashSet<Category>(0);

    private Set<Tag> tags = new HashSet<Tag>(0);

    private Map<String, String> errors = new HashMap<>();

    public ProductValidator(String name,String description,Integer price,Set<Category> categories,Set<Tag> tags){
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.tags = tags;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setError(String key,String error) {
        this.errors.put(key,error);
    }

    public boolean Validate(){
       if(ValidateName()&&ValidateDescription())
            return true;
       else
           return false;
    }

    private boolean ValidateName(){
        if(notNull(this.name)) {
            if (ValidateString(this.name)) {
                return true;
            } else {
                setError("Name", "Wrong name");
                return false;
            }
        }else {
            setError("Name","Enter your name");
            return false;
        }
    }

    private boolean ValidateDescription(){
        if(ValidateString(this.description)){
            return true;
        }else{
            setError("Description","Wrong description");
            return false;
        }
    }
}
