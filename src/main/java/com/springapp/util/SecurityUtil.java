package com.springapp.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by aurdo on 04/03/14.
 */
public class SecurityUtil {

    public static String md5(String input) {
        //byte[] bytes = input.getBytes();
        Logger logger = Logger.getLogger(SecurityUtil.class.getName());
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(input.getBytes(), 0, input.length());
            return String.format("%032X", new BigInteger(1, digest.digest()));

        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Hash generation error", e);
            //Todo:throw exception or check for zero-string at the upper level
        }
        return "";
    }

    public static String md5(String input, String salt) {
        return md5(md5(input) + salt);
    }


}