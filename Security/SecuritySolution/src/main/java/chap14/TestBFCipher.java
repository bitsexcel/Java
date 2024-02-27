package com.richware.chap14;

import java.io.*;
import java.math.BigInteger;

import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;

import javax.crypto.*;

import sun.misc.*;


/**
 * Class TestBFCipher
 * Description: This is an example to
 * tests the Blowfish cipher.
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
public final class TestBFCipher
 {

  /**
   * Constructor TestRSACipher
   *
   *
   */
  public TestBFCipher() {}

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
      String localDirectory  = System.getProperty("user.dir");
      System.out.println("Changing directory to Chapter 11"); 
      System.setProperty("user.dir",localDirectory + "\\com\\richware\\chap11\\");
      localDirectory  = System.getProperty("user.dir");
      if((args[0] == null) ||  (args[1] == null){
         System.out.println("This application requires an input file and output file"); 
         System.out.println("as arguments"); 
      }	
      String localInputFile  = localDirectory + args[0];
      System.out.println("Openining Chapter 14 plus the input file as an argument: " + localInputFile); 
      String localOutputFile  = localDirectory + args[1];
      System.out.println("Openining Chapter 14 plus the output file as an argument: " + localOutputFile); 

      /*
       * Generate a Blowfish Key
       */
      System.out.println("Generating a Blowfish key...This could take several minutes");

      KeyGenerator keyGenerator =
        KeyGenerator.getInstance("Blowfish");

      /*
       * Set the key Size
       * Blowfish can be from 32 bits to 448 bits
       */
      keyGenerator.init(128);

      Key secretKey = keyGenerator.generateKey();

      System.out.println("Done generating the key.");

      /*
       * Blowfish modes
       * Blowfish/CFB/NoPadding
       * Blowfish/OFB/NoPadding
       * Blowfish/ECB/PKCS5Padding
       * Blowfish/CBC/PKCS5Padding
       */

      // Create a cipher using that key to initialize it
      Cipher cipherOut =
        Cipher.getInstance("Blowfish/CFB/NoPadding");

      cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);

      /*
       * Create a base-64 encoder for displaying binary data.
       */
      BASE64Encoder encoder = new BASE64Encoder();

      /*
       * When the CFB or OFB is used
       * IV needs to passed as well as key
       * Unless it is hardcoded in the algorithm
       */

      /*
       * Get IV
       * if not in CFB or OFB,
       * returns null
       */
      byte iv[] = cipherOut.getIV();

      if (iv != null)
       {
        System.out
          .println("Initilization Vector of the Cipher:\n"
                   + encoder.encode(iv));
      }

      /*
       * Get the Input File Stream
       */
      FileInputStream fin = new FileInputStream(localInputFile); 

      /*
       * Start that CipherOutputStream
       * The Stream will encrypt while
       * writing
       */
      FileOutputStream   fout  =
        new FileOutputStream(localOutputFile);
      CipherOutputStream cout  = new CipherOutputStream(fout,
                                   cipherOut);
      int                input = 0;

      while ((input = fin.read()) != -1)
       {
        cout.write(input);
      }

      fin.close();
      cout.close();
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
