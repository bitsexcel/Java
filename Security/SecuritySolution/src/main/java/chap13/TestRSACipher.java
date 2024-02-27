package com.richware.chap13;

import java.io.*;

import java.math.BigInteger;

import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;

import javax.crypto.*;

import sun.misc.*;


/**
 * Class TestRSACiphers
 * Description: This is an example to
 * tests the RSA cipher.
 *
 * Copyright:    Copyright (c) 2002
 * Company:      HungryMinds
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
public final class TestRSACipher
 {

  /**
   * Constructor TestRSACipher
   *
   *
   */
  public TestRSACipher() {}

  /**
   * Method main
   * Description: This is a Sample JAAS application
   *
   *
   * @param args none
   *
   */
  public static void main(String[] args)
   {

    try
     {
      String       message = "This is a test, hackers beware.";
      SecureRandom _random = new SecureRandom();

      /*
       * Generate the RSA Keys
       */
      KeyPairGenerator kpg =
        KeyPairGenerator.getInstance("RSA");

      kpg.initialize(1024);
      System.out.println("Generating a key pair...");

      KeyPair keyPair = kpg.generateKeyPair();

      System.out.println("Done generating keys.\n");

      /*
       * Get the public and private keys.
       */
      PublicKey  publKey = keyPair.getPublic();
      PrivateKey privKey = keyPair.getPrivate();

      /*
       * Create a base-64 encoder for displaying binary data.
       */
      BASE64Encoder encoder = new BASE64Encoder();

      /*
       * Register the provider.
       */
      Security
        .insertProviderAt(new RichProvider(), 2);

      /*
       * Create a byte array from the message.
       */
      byte[] messageBytes = message.getBytes("UTF8");

      /*
       * Create the cipher algorithms
       * cipher is formatted algorithm/mode/padding or algorithm
       */
      Cipher cipher  = Cipher.getInstance("RSA", "RichWare");
      /* 
       * Create an algorithm for decryption
       */
      
      Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1_V1_5",
                                          "RichWare");

      /*
       * Encrypt the message with the public key.
       */
      cipher.init(Cipher.ENCRYPT_MODE, publKey, _random);

      byte[] encryptedMessage =
        cipher.doFinal(messageBytes, 0, messageBytes.length);

      System.out.println("Encrypted message:\n"
                         + encoder.encode(encryptedMessage));

      /*
       * Decrypt the message with the private key.
       */
      cipher2.init(Cipher.DECRYPT_MODE, privKey, _random);

      byte[] decryptedMessage       =
        cipher2.doFinal(encryptedMessage, 0,
                        encryptedMessage.length);
      String decryptedMessageString =
        new String(decryptedMessage, "UTF8");

      System.out.println("\nDecrypted message: "
                         + decryptedMessageString);

      /*
       * Check that the decrypted message and the original
       * message are the same.
       */
      if (decryptedMessageString.equals(message))
       {
        System.out.println("\nTest succeeded.");
      }
      else
       {
        System.out.println("\nTest failed.");
      }
    }

    /*
     * Catches
     */
    catch (Exception ex)
     {
      ex.printStackTrace();
    }
  }
}
