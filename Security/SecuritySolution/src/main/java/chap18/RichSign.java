package com.richware.chap18;
import java.security.*;

import java.io.*;


/**
 * Class RichSign 
 * Description: A custom demonstration of
 * signing an object.
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
public class RichSign {

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
       System.out.println("Starting RichSign....");
      /*
       * Create the Serialized object
       */
      String str = "I am verified";

      /*
       * Create the KeyPair
       */
      KeyPairGenerator keyGen =
        KeyPairGenerator.getInstance("DSA");

      /*
       * Create the signature
       */
      Signature sign    = Signature.getInstance("SHA1withDSA");
      KeyPair   keyPair = keyGen.generateKeyPair();

      System.out.println("Creating signed object ...");

      /*
       * Create the Signed object
       */
      SignedObject so = new SignedObject(str,
                                         keyPair.getPrivate(),
                                         sign);

      System.out.println("\nVerifying signature ...");

      /*
       * verify the Signed object
       * print the results
       */
      if (so.verify(keyPair.getPublic(), sign)) {
        System.out.println(so.getObject());
      } else {
        System.out.println("Signature NOT verified!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}