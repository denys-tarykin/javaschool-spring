package com.springapp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shichirin on 13.04.14.
 */
public class ProductValidator extends Validator {

    private String name;

    private String description;

    private String price;

    private String[] categories ;

    //private Set<Tag> tags = new HashSet<Tag>(0);

    private Map<String, String> errors = new HashMap<>();

    public ProductValidator(String name,String description,String price,String[] categories){
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
        //this.tags = tags;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setError(String key,String error) {
        this.errors.put(key,error);
    }

    public boolean Validate(){
        if (ValidateName() && ValidateDescription() && ValidatePrice() && ValidateCateories())
            return true;
       else
           return false;
    }

    private boolean ValidateName(){
        if(notNull(this.name)) {
            if (checkStringLength(this.name, 4, 100))
                return true;
                else{
                    setError("name","Wrong name");
                    return false;
                }
        }else {
            setError("Name", "Enter name");
            return false;
        }
    }

    private boolean ValidateDescription(){
        if(notNull(this.description)) {
            if (checkStringLength(this.description, 10, 500))
                return true;
                else{
                    setError("Description","Wrong description");
                    return false;
                }
        }else{
            setError("Description","Wrong description");
            return false;
        }

    }

    private boolean ValidatePrice() {
        if(notNull(this.price)){
            if(ValidateInt(this.price)){
                if (checkStringLength(this.price, 1, 12))
                    return true;
                else{
                    setError("Price", "Wrong prince");
                    return false;
                }
            }else {
                setError("Prince","Wrong prince");
                return false;
            }
        }else{
            setError("Prince","Enter prince");
            return false;
        }
    }

    private boolean ValidateCateories(){
        if(cheskCategoriesArray(this.categories)){
              return true;
        }else {
            setError("Categories","Select some categories");
            return false;
        }
    }
}
