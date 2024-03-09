package chap07;

import java.security.*;
import java.security.spec.*;

import java.io.*;

import java.math.BigInteger;


/**
 * Class RichDSAKey
 * Description: A custom demonstration of
 * creating, writting, reading and
 * recreating a DSA public key.
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
public class RichDSAKey {

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
       * Generate the key Pair
       */
      KeyPairGenerator keyGen =
        KeyPairGenerator.getInstance("DSA");
      SecureRandom     random =
        SecureRandom.getInstance("SHA1PRNG", "SUN");

      random.setSeed(101L);
      keyGen.initialize(1024, random);

      KeyPair keypair = keyGen.generateKeyPair();

      /*
       * Initialize the KeyFactory for DSA
       */
      KeyFactory kfactory = KeyFactory.getInstance("DSA");

      /*
       * Create the DSA public key spec
       */
      DSAPublicKeySpec kspec =
        (DSAPublicKeySpec) kfactory
          .getKeySpec(keypair
            .getPublic(), DSAPublicKeySpec.class);

      /*
       * Create the output stream
       */
      System.out.println("********Saving PublicKey*******");
      System.out.println(keypair.getPublic());

      FileOutputStream   fos =
        new FileOutputStream("publicKeys");
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      /*
       * Write the Y,P, Q and G variables
       */
      oos.writeObject(kspec.getY());
      oos.writeObject(kspec.getP());
      oos.writeObject(kspec.getQ());
      oos.writeObject(kspec.getG());

      /*
       * Create the input stream
       */
      FileInputStream   fin =
        new FileInputStream("publicKeys");
      ObjectInputStream ois = new ObjectInputStream(fin);

      /*
       * Read the Y,P, Q and G variables
       */
      BigInteger Y = (BigInteger) ois.readObject();
      BigInteger P = (BigInteger) ois.readObject();
      BigInteger Q = (BigInteger) ois.readObject();
      BigInteger G = (BigInteger) ois.readObject();

      /*
       * Create the public key again
       */
      DSAPublicKeySpec keyspec = new DSAPublicKeySpec(Y, P, Q,
                                   G);
      PublicKey        pkey    =
        kfactory.generatePublic(keyspec);

      System.out.println("********PublicKey rebuilt*******");
      System.out.println(pkey);

      /*
       * Catches
       */
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
