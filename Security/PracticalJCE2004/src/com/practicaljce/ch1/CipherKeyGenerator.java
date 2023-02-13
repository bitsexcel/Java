/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicaljce.ch1;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Oficina
 */
public class CipherKeyGenerator {
    
    public static void main(String[] args){
        try{
            
            //Buscar un generador de claves para el cifrado DES
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            
            //Genera una clave secreta que pueda ser utilizada por el cifrado DES
            SecretKey key = kg.generateKey();
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "DES");
            
            //Busca una instancia de un cifrado DES
            Cipher cipher = Cipher.getInstance("DES");
            
            //Inicializa el cifrado usando la clave secreta
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            
            //Cifra nuestro mensaje
            String plainText = "This is a secret message";
            byte[] cipherText = cipher.doFinal(plainText.getBytes());
            
            System.out.println("Resulting Cipher Text:n");
            for(int i =0; i<cipherText.length; i++)
            {
                System.out.print(cipherText[i] + " ");
            }
            System.out.println("");
           
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
