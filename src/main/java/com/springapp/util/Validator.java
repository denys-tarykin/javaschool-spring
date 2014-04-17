package com.springapp.util;

/**
 * Created by Shichirin on 13.04.14.
 */
public class Validator {
    public boolean ValidateString(String name){
        if (name.matches("(^A-Za-z0-9)")) {
            return true;
        }
        return false;
    }
    public boolean notNull(String value){
        if(value.isEmpty())
            return false;
        else
            return true;
    }
}
