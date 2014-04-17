package com.springapp.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aurdo on 04/03/14.
 */
public class SecurityUtil {

    public static String md5(String input) {
        //byte[] bytes = input.getBytes();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(input.getBytes(), 0, input.length());
            //TODO:Conversion to string
            return String.format("%032X", new BigInteger(1, digest.digest()));

        } catch (NoSuchAlgorithmException e) {
            //TODO:Throw exception
        }
        return "";
    }


}