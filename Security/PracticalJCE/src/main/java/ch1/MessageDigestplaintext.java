/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch1;

import java.security.MessageDigest;

/**
 *
 * @author Oficina
 */
public class MessageDigestplaintext {

    public static void main(String[] args) {
        try {

            String plainText = "This is a secret message";
            
            MessageDigest md5Implementation = MessageDigest.getInstance("MD5");
            MessageDigest sha1Implementation = MessageDigest.getInstance("SHA1");
            md5Implementation.update(plainText.getBytes());
            byte[] md5 = md5Implementation.digest();

            String smd5 = md5.toString();
            sha1Implementation.update(plainText.getBytes());
            byte[] sha1 = sha1Implementation.digest();

            String ssha1 = sha1.toString();
            System.out.println("md5 digest: " + smd5);
            System.out.println("sha1 digest: " + ssha1);
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
