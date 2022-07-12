/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jccastellanos 2015-04-13
 */
public class StringUtil {

    public static String toUTF8(String str) {
        if (str != null) {
            return new String(str.getBytes(), StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    /**
     * Metodo de utilidad para generar un md5 de la cadena que ser recibe
     *
     * @param cadena
     * @return
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String generateMD5(String cadena) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(cadena.getBytes());
        byte[] byteData = md.digest();
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
