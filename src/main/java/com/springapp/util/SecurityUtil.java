package com.springapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aurdo on 04/03/14.
 */
public class SecurityUtil {

    public static String md5(String input) {
        byte[] bytes = input.getBytes();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            //TODO:Conversion to string
            return digest.digest(bytes).toString();

        } catch (NoSuchAlgorithmException e) {
            //TODO:Throw exception
        }
        return "";

    }
}