package com.richware.chap07;

/*
 * Different imports than RichDSAKey
 */
import javax.crypto.*;
import javax.crypto.spec.*;

import java.security.*;

import java.io.*;


/**
 * Class RichDESKey
 * Description: A custom demonstration of
 * creating, writting, reading and
 * recreating a DES public key.
 *
 * Copyright:    Copyright (c) 2002 Wiley Publishing, Inc.
 * @author Rich Helton <rhelton@richware.com>
 * @version 1.0  01-FEB-2002
* DISCALIMER: Limit of Liability/Disclaimer of Warranty: 
* This code is used for educational purposes only. This software is provided "As Is" and 
* there are no implied warranties. While the publisher and author
* have used their best efforts in preparing this book and software, they make no
* representations or warranties and specifically disclaim any implied
* warranties of merchantability or fitness for a particular purpose. No
* warranty may be created or extended by sales representatives or written
* sales materials. The advice and strategies contained herein may not be
* suitable for your situation. You should consult with a professional where
* appropriate. Neither the publisher nor author shall be liable for any loss
* of profit or any other commercial damages, including but not limited to
* special, incidental, consequential, or other damages, including, but not limited to,
* procurement of substitute goods or services, loss of use, data, profits, or business
* interruption by cause of use or theory of use arising in any damage.
 */
public class RichDESKey {

  /**
   * Method main
   * Description: The main driver to run the methods.
   *
   *
   * @param args (no arguments presently).
   *
   */
  public static void main(String args[]) {

    try {

      /*
       * Generate the Secret key
       */
      KeyGenerator keyGen = KeyGenerator.getInstance("DES");
      SecureRandom random =
        SecureRandom.getInstance("SHA1PRNG", "SUN");

      random.setSeed(101L);
      keyGen.init(56, random);

      SecretKey sKey = keyGen.generateKey();

      /*
       * Initialize the KeyFactory for DSA
       */
      SecretKeyFactory kfactory =
        SecretKeyFactory.getInstance("DES");

      /*
       * Create the DSA public key spec
       */
      DESKeySpec kspec = (DESKeySpec) kfactory.getKeySpec(sKey,
                           DESKeySpec.class);

      /*
       * Create the output stream
       */
      System.out.println("********Saving Secret Key*******");
      System.out.println(sKey);

      FileOutputStream   fos =
        new FileOutputStream("secretKeys");
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      /*
       * Write the Key
       */
      oos.writeObject(kspec.getKey());

      /*
       * Create the input stream
       */
      FileInputStream   fin =
        new FileInputStream("secretKeys");
      ObjectInputStream ois = new ObjectInputStream(fin);

      /*
       * Read the key variables
       */
      byte[] kMaterial = (byte[]) ois.readObject();

      /*
       * Create the public key again
       */
      DESKeySpec keyspec = new DESKeySpec(kMaterial);
      SecretKey  newKey  = kfactory.generateSecret(keyspec);

      System.out.println("********SecretKey rebuilt*******");
      System.out.println(newKey);
      System.out.println("Do the keys equal :"
                         + newKey.equals(sKey));

      /*
       * Catches
       */
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
