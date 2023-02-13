package ch1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.security.*;
import javax.crypto.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oficina
 */
public class SecretWriting {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        if (args.length < 2) {
            System.out.println("Usage: SecretWriting -e|-d text");
            return;

        }
        Logger logger = Logger.getLogger("myLog");
        Key key = null;
        String base64 = "";
        
             
        
        try {
            
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(".\\SecretKey.ser"));
            key = (Key) in.readObject();
            in.close();
            logger.info("secretkey.ser");
        } catch (FileNotFoundException fnfe) {
            logger.info("FileNotFoundException");
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(new SecureRandom());
            key = generator.generateKey();
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("SecretKey.ser"));
            out.writeObject(key);
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(SecretWriting.class.getName()).log(Level.SEVERE, null, ex);
        }
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        logger.info("argumentos: " + args[0].toString());
        
        try {

            if (args[0].equals("-e")) {

                cipher.init(Cipher.ENCRYPT_MODE, key);
                String amalgam = args[1];
                for (int i = 2; i < args.length; i++) {
                    amalgam += " " + args[i];
                }
                byte[] stringBytes = amalgam.getBytes("UTF8");
                byte[] raw = cipher.doFinal(stringBytes);

                base64 = Base64.getEncoder().encodeToString(raw);
                System.out.println(base64);
            }

            if (args[0].equals("-d")) {
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] raw2 = Base64.getDecoder().decode(args[1].toString());
                String raw3 = new String(raw2, "UTF8");
                logger.info(raw3);
                byte[] stringBytes2 = cipher.doFinal(raw2);
                String result = new String(stringBytes2, "UTF8");
                System.out.println(result);
            } else {
                logger.info("else" +args[1]);
            }
        }
        catch(Exception ex){
            logger.info("catch: " +ex.getMessage());
        }

    }

}
