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

    //private Set<Category> categories = new HashSet<Category>(0);

    //private Set<Tag> tags = new HashSet<Tag>(0);

    private Map<String, String> errors = new HashMap<>();

    public ProductValidator(String name,String description,String price){
        this.name = name;
        this.description = description;
        this.price = price;
        //this.categories = categories;
        //this.tags = tags;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setError(String key,String error) {
        this.errors.put(key,error);
    }

    public boolean Validate(){
       if(ValidateName()&&ValidateDescription()&&ValidatePrive())
            return true;
       else
           return false;
    }

    private boolean ValidateName(){
        if(notNull(this.name)) {
            if (ValidateString(this.name)) {
                if(checkStringLength(this.name,15,100))
                    return true;
                else{
                    setError("name","Wrong name");
                    return false;
                }
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
        if(notNull(this.description)) {
                if(checkStringLength(this.description,20,500))
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

    private boolean ValidatePrive(){
        if(notNull(this.price)){
            if(ValidateInt(this.price)){
                if(checkStringLength(this.description,1,12))
                    return true;
                else{
                    setError("Description","Wrong prince");
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
}
