package com.richware.chap05;

import java.util.*;
import java.math.*;
import java.security.*;
import javax.crypto.spec.*;

/**
 * Class ECCSimpleApp
 * Description: This is a example of a
 * simple ECC
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
public class ECCSimpleApp
 {
  /**
   * Method main
   * Description: The Main test driver
   *
   *
   * @param args none
   *
   */
  public static void main(String[] args)
   {
    try
     {
      System.out.println();
      System.out.println(
        "ECC letting the algorithm choose randoms**************");
      KeyPairGenerator kpg =
        KeyPairGenerator.getInstance("ECC");

      /*
       * A strong key uses 512 to 2048 bits
       * the bits must be multiples of 64
       */
      System.out.println("Provider =" + kpg.getProvider());
      kpg.initialize(512);

      KeyPair kp = kpg.generateKeyPair();

      /*
       * Read the keys
       * produced by the algorithm
       */
      System.out.println("Public Key ="
                         + kp.getPublic().getEncoded());
      System.out.println("Public Key Algorithm ="
                         + kp.getPublic().getAlgorithm());
      System.out.println("Public Key Format ="
                         + kp.getPublic().getFormat());
      System.out.println("Private Key ="
                         + kp.getPrivate().getEncoded());
      System.out.println("Private Key Algorithm ="
                         + kp.getPrivate().getAlgorithm());
      System.out.println("Private Key Format ="
                         + kp.getPrivate().getFormat());

      /*
       * Initialize the KeyFactory for DSA
       */
      KeyFactory kfactory = KeyFactory.getInstance("ECC");

      /*
       * Create the DH public key spec
       */
      ECCPublicKeySpec kspec =
        (ECCPublicKeySpec) kfactory
          .getKeySpec(kp.getPublic(), ECCPublicKeySpec.class);

      /*
       * Print out public Public Q point public values
       * to be sent to the other user
       */
      System.out.println("Public Key QY =" + kspec.getQY());
      System.out.println("Public Key QX =" + kspec.getQX());
    }

    /*
     * Catches
     */
    catch (java.security.NoSuchAlgorithmException ex)
     {
      ex.printStackTrace();
    }
    catch (Exception ex)
     {
      ex.printStackTrace();
    }
  }
}
