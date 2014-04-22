package com.springapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shichirin on 13.04.14.
 */
public class Validator {
    public boolean ValidateString(String text){
        Pattern p = Pattern.compile("[A-Za-z0-9\\s()#]+",Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(text);
        if(m.matches()) {
            return true;
        }else
            return false;
    }

    public boolean notNull(String value){
        if(value.isEmpty())
            return false;
        else
            return true;
    }
    public boolean checkStringLength(String text,int minLenght,int maxLenght){
        if(text.length() <= maxLenght && text.length()>= minLenght)
            return true;
        else
            return false;
    }

    public boolean ValidateInt(String intData){
        Pattern p = Pattern.compile("[0-9]+(\\.[0-9]{1,2})?");
        Matcher m = p.matcher(intData);
        if (m.matches()) {
            return true;
        } else
            return false;
    }

    public boolean cheskCategoriesArray(String[] array){
        if(array.length >= 1)
            return true;
        else
            return false;
    }

}
