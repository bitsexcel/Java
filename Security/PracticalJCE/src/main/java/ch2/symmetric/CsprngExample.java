/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch2.symmetric;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdaniel
 */
public class CsprngExample {

    public static void main(String[] args) {

        try {
            //Locate a SHAIPRNG provider
            SecureRandom csprng = SecureRandom.getInstance("SHA1PRNG");

            //Generate a randome boolean value
            boolean randomBoolean = csprng.nextBoolean();

            //Generate a random int value
            int randomInt = csprng.nextInt();

            //Generate 3 random bytes
            byte[] randomBytes = new byte[3];
            csprng.nextBytes(randomBytes);
            
            

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CsprngExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
