/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch4.random.number;

import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 *
 * @author jdaniel
 */
public class RandomNumbers {
   public static void main(String[] argv)
    {
        try {
            // creating the object of SecureRandom and getting instance
            // By using getInstance() method
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
  
            // Declaring the string variable
            String str = "Tajmahal";
  
            // Declaring the byte Array
            // converting string into byte
            byte[] b = str.getBytes();
  
            // printing the byte array
            System.out.println("Byte array before operation : "
                               + Arrays.toString(b));
  
            // generating user-specified number of random bytes
            // using nextBytes() method
            sr.nextBytes(b);
  
            // printing the new byte array
            System.out.println("Byte array after operation : "
                               + Arrays.toString(b));
        }
  
        catch (NoSuchAlgorithmException e) {
  
            System.out.println("Exception thrown : " + e);
        }
        catch (ProviderException e) {
  
            System.out.println("Exception thrown : " + e);
        }
    }
    
}
