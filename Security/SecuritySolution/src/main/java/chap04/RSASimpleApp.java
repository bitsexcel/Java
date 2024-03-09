package chap04; 
import java.util.*;

import java.math.*;

import java.security.*;
import java.security.spec.*;


/**
 * Class RSASimpleApp
 * Description: This is an example of a
 * simple RSA
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
public class RSASimpleApp
 {

  public final static int p      = 47;
  public final static int q      = 71;
  public final static int eValue = 79;
  public final static int mValue = 43;
  public final static int KEYSIZE_MIN     =  512;
  public final static int KEYSIZE_DEFAULT =  1024;
  public final static int KEYSIZE_MAX     = 2048;
  public final static int bitLength = KEYSIZE_DEFAULT; // KeySize
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
      BigInteger c        = null;
      BigInteger d        = null;
      BigInteger phi      = null;
      BigInteger minusOne = null;
      BigInteger e        = null;
      BigInteger m        = null;

      System.out.println();
      System.out.println(
        "RSA Proving the algorithm*************************");

      /*
       * Step 1
       * Pick p and q
       */
      System.out.println("p = " + RSASimpleApp.p);
      System.out.println("q = " + RSASimpleApp.q);

      /*
       * Step 2
       * Calculate n = p * q
       */
      BigInteger n = new BigInteger(Integer.toString(p * q));

      System.out.println("n = " + n);

      /*
       * Step 3
       * Calculate phi = (p - 1) * (q - 1)
       */
      phi = new BigInteger(Integer.toString((p - 1)
                                            * (q - 1)));

      System.out.println("phi = " + phi);

      /*
       * Step 4
       * Select e a prime less than phi
       */
      e = new BigInteger(Integer.toString(eValue));

      if (e.intValue() < phi.intValue())
       {
        System.out.println("e = " + e);

        minusOne = new BigInteger(Integer.toString(-1));

        /*
         * Step 5
         * Calculate d
         */
        d = e.modPow(minusOne, phi);

        System.out.println("d = " + d);

        /*
         * Step 6
         * Display private and public key
         */
        System.out.println("Public Key {n,e} = {" + n + ","
                           + e + "}");
        System.out.println("Private Key {n,d} = {" + n + ","
                           + d + "}");

        m = new BigInteger(Integer.toString(mValue));

        System.out.println("Encrypting value....." + m);

        c = m.modPow(e, n);

        System.out.println("Ciphertext value = " + c);
        System.out.println("Decrypting value.....");

        BigInteger plaintext = c.modPow(d, n);

        System.out.println("Plaintext value = " + plaintext);
      }
      else
       {
        System.out.println("e must be less than phi");
      }

      RSASimpleApp app = new RSASimpleApp();

      app.createKey();
      app.createSpecificKey();
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
        "RSA letting the algorithm choose******************");

      KeyPairGenerator kpg =
        KeyPairGenerator.getInstance("RSA");

      /*
       * A strong key uses 512 to 2048 bits
       * the bits must be multiples of 8
       */
      System.out.println("Provider =" + kpg.getProvider());
      kpg.initialize(bitLength);

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
      KeyFactory kfactory = KeyFactory.getInstance("RSA");

      /*
       * Create the RSA public key spec
       */
      RSAPublicKeySpec kspec =
        (RSAPublicKeySpec) kfactory
          .getKeySpec(kp.getPublic(), RSAPublicKeySpec.class);

      /*
       * Print out public key values
       */
      System.out.println("Public Key Modulus ="
                         + kspec.getModulus());
      System.out.println("Public Key Exponent ="
                         + kspec.getPublicExponent());
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
  public void createSpecificKey()
   {

    try
     {

      /*
       * Another provider specific to the signature instead of JSSE
       */
      System.out.println();
      System.out.println(
        "RSA Choosing the exponent*************************");

      KeyPairGenerator kpg =
        KeyPairGenerator.getInstance("RSA", "SunRsaSign");

      /* A strong key uses 512 to 2048 bits
       * the bits must be multiples of 8
       */
      System.out.println("Provider =" + kpg.getProvider());

      BigInteger e = new BigInteger(Integer.toString(eValue));

      System.out.println("e =" + e);

      /*
       * Select the exponent
       */
      RSAKeyGenParameterSpec param =
        new RSAKeyGenParameterSpec(bitLength, e);

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
      KeyFactory kfactory = KeyFactory.getInstance("RSA",
                              "SunRsaSign");

      /*
       * Create the RSA public key spec
       */
      RSAPublicKeySpec kspec =
        (RSAPublicKeySpec) kfactory
          .getKeySpec(kp.getPublic(), RSAPublicKeySpec.class);

      /*
       * Print out public key values
       */
      System.out.println("Public Key Modulus ="
                         + kspec.getModulus());
      System.out.println("Public Key Exponent ="
                         + kspec.getPublicExponent());
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
