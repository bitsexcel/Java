package chap04; 
import java.util.*;

import java.math.*;

import java.security.*;

import javax.crypto.spec.*;


/**
 * Class DHSimpleApp
 * Description: This is an example of a
 * simple Diffie-Hellman
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
public class DHSimpleApp
 {

  public final static int pValue  = 47;
  public final static int gValue  = 71;
  public final static int XaValue = 9;
  public final static int XbValue = 14;

  /**
   * Method main
   * Description: Main Driver
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
        "DH Proving the algorithm*************************");

      /*
       * Step 1
       * Pick p and q
       */
      BigInteger p = new BigInteger(Integer.toString(pValue));
      BigInteger g = new BigInteger(Integer.toString(gValue));

      System.out.println("p = " + p);
      System.out.println("g = " + g);

      /*
       * Step 2
       * Select the random numbers
       */
      BigInteger Xa =
        new BigInteger(Integer.toString(XaValue));
      BigInteger Xb =
        new BigInteger(Integer.toString(XbValue));

      System.out.println("Xa = " + Xa);
      System.out.println("Xb = " + Xb);

      /*
       * Step 3
       * Calculate Ya
       */
      BigInteger Ya = g.modPow(Xa, p);

      System.out.println("Ya = " + Ya);

      /*
       * Step 4
       * Calculate Yb
       */
      BigInteger Yb = g.modPow(Xb, p);

      System.out.println("Yb = " + Yb);

      /*
       * Step 5
       * User A calculates K
       */
      BigInteger Ka = Ya.modPow(Xa, p);

      System.out.println("Users A, K = " + Ka);

      /*
       * Step 6
       * User B calculates K
       */
      BigInteger Kb = Yb.modPow(Xb, p);

      System.out.println("Users B, K = " + Kb);

      DHSimpleApp app = new DHSimpleApp();

      app.createKey();

      /*
       * Generate a 512 bit Prime to pass as p and g
       */
      int          bitLength = 512;  // 512 bits
      SecureRandom rnd       = new SecureRandom();

      System.out.println("BitLength : " + bitLength);
      System.out
        .println("Selecting Prime Numbers..............");

      p = BigInteger.probablePrime(bitLength, rnd);
      g = BigInteger.probablePrime(bitLength, rnd);

      System.out.println("P *********************");
      System.out.println(p);
      System.out.println("G *********************");
      System.out.println(g);
      app.createSpecificKey(p, g);
    }

    /*
     * Catches
     */
    catch (Exception ex)
     {
      ex.printStackTrace();
    }
  }

  /**
   * Method createKey
   * Description: This is an example of
   * letting the algorithm choose
   * the values
   *
   */
  public void createKey()
   {

    try
     {
      System.out.println();
      System.out.println(
        "Diffie-Hellman letting the algorithm choose******************");

      KeyPairGenerator kpg =
        KeyPairGenerator.getInstance("DiffieHellman");

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
      KeyFactory kfactory =
        KeyFactory.getInstance("DiffieHellman");

      /*
       * Create the DH public key spec
       */
      DHPublicKeySpec kspec =
        (DHPublicKeySpec) kfactory
          .getKeySpec(kp.getPublic(), DHPublicKeySpec.class);

      /*
       * Print out public key values
       */
      System.out
        .println("Public Key Y **********************");
      System.out.println(kspec.getY());
      System.out
        .println("Public Key G **********************");
      System.out.println(kspec.getG());
      System.out
        .println("Public Key P **********************");
      System.out.println(kspec.getP());
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

  /**
   * Method createSpecificKey
   * Description: This is an example of
   * choosing e
   *
   */
  public void createSpecificKey(BigInteger p, BigInteger g)
   {

    try
     {

      /*
       * Another provider specific to the signature instead of JSSE
       */
      System.out.println();
      System.out.println(
        "Diffie-Hellman Choosing the prime, must be at least 512 bits***********************");

      KeyPairGenerator kpg =
        KeyPairGenerator.getInstance("DiffieHellman");

      /* A strong key uses 512 to 2048 bits
       * the bits must be multiples of 64
       */
      System.out.println("Provider =" + kpg.getProvider());

      /*
       * Select the parameters
       */

      /*
       * Step 1
       * Pick p and q
       */
      DHParameterSpec param = new DHParameterSpec(p, g);

      kpg.initialize(param);

      KeyPair kp = kpg.generateKeyPair();

      /* Read the keys
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
      KeyFactory kfactory =
        KeyFactory.getInstance("DiffieHellman");

      /*
       * Create the DH public key spec
       */
      DHPublicKeySpec kspec =
        (DHPublicKeySpec) kfactory
          .getKeySpec(kp.getPublic(), DHPublicKeySpec.class);

      /*
       * Print out public key values
       */
      System.out
        .println("Public Key Y **********************");
      System.out.println(kspec.getY());
      System.out
        .println("Public Key G **********************");
      System.out.println(kspec.getG());
      System.out
        .println("Public Key P **********************");
      System.out.println(kspec.getP());
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
