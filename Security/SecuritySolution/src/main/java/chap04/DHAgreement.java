package com.richware.chap04; 
import java.math.*;

import java.security.*;
import java.security.spec.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;


/**
 * Class DHAgreement
 * Description: This is an example of a
 * man in the middle attack
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
public class DHAgreement implements Runnable
 {

  byte                    userA[];  // Public Key from A
  byte                    userB[];  // Public Key from B
  byte                    userAfromM[];  // Public Key from A altered by M
  byte                    userBfromM[];  // Public Key from B altered by M
  boolean                 userAStarted = false;
  boolean                 userMStarted = false;
  byte[]                  ciphertext_from_A;
  byte[]                  ciphertext_from_M;  // CipherText from M
  public final static int bitLength = 512;  // The size of the key
  BigInteger              p, g;

  /**
   * Method run
   *
   *
   */
  public synchronized void run()
   {

    if (!userMStarted)
     {
      userMStarted = true;

      doUserM();
    }
    else if (!userAStarted)
     {
      userAStarted = true;

      doUserA();
    }
    else
     {
      doUserB();
    }
  }

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
      DHAgreement test = new DHAgreement();

      new Thread(test).start();  // Starts User M
      new Thread(test).start();  // Starts User A
      new Thread(test).start();  // Starts User B
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
   * Method doUserA
   *
   *
   */
  public synchronized void doUserA()
   {

    try
     {
      System.out.println("A->Starting....");
      System.out.println();

      /*
       * User A generates a Key Pair
       */
      KeyPairGenerator kpg =
        KeyPairGenerator.getInstance("DH");

      /*
       * Initializes based on Key Length
       */
      kpg.initialize(bitLength);
      System.out.println("A-> Generating Keys....");
      System.out.println();

      KeyPair kp = kpg.generateKeyPair();

      /*
       * Create the DH public key spec
       */
      DHParameterSpec kspec =
        ((DHPublicKey) kp.getPublic()).getParams();

      /*
       * Get the G, P, and public key
       * to distribute
       */
      g     = kspec.getG();
      p     = kspec.getP();
      userA = kp.getPublic().getEncoded();

      /*
       * Print out public key values
       */
      System.out.println();
      System.out.println("A->G Value**********************");
      System.out.println(g);
      System.out.println();
      System.out.println("A->P Value**********************");
      System.out.println(p);
      System.out.println();
      System.out
        .println("A->Public Key**********************");
      System.out.println(userA);
      notifyAll();

      /*
       * Do the KeyAgreement
       */
      KeyAgreement ka = KeyAgreement.getInstance("DH");

      ka.init(kp.getPrivate());

      /*
       * Actually getting Key from M
       */
      while (userBfromM == null)
       {
        wait();
      }

      System.out.println("A->Got Public Key from B?.......");
      System.out.println(userBfromM);
      System.out.println();

      /*
       * Implement the Key Agreement
       */
      KeyFactory         kf       =
        KeyFactory.getInstance("DH");
      X509EncodedKeySpec x509Spec =
        new X509EncodedKeySpec(userBfromM);
      PublicKey          pk       =
        kf.generatePublic(x509Spec);

      ka.doPhase(pk, true);

      /*
       * Implement the Key Agreement
       */
      byte             secret[] = ka.generateSecret();
      SecretKeyFactory skf      =
        SecretKeyFactory.getInstance("DES");

      /*
       * Send a DES Cipher to B
       * M will actually pick it up and translate it
       */
      DESKeySpec desSpec   = new DESKeySpec(secret);
      SecretKey  secretKey = skf.generateSecret(desSpec);
      Cipher     c         =
        Cipher.getInstance("DES/ECB/PKCS5Padding");

      c.init(Cipher.ENCRYPT_MODE, secretKey);

      ciphertext_from_A =
        c.doFinal("How are you B?".getBytes());

      notifyAll();
    }
    catch (Exception e)
     {
      e.printStackTrace();
    }
  }

  /**
   * Method doUserB
   *
   *
   */
  public synchronized void doUserB()
   {

    try
     {
      System.out.println("B->Starting User....");
      System.out.println();

      while (userAfromM == null)
       {
        wait();
      }

      System.out.println();
      System.out
        .println("B->User A's Public?**********************");
      System.out.println(userAfromM);
      System.out
        .println("B->User A's G Value?**********************");
      System.out.println(g);
      System.out.println();
      System.out
        .println("B->User A's P Value?**********************");
      System.out.println(p);

      /*
       * Generate a Key Pair
       * based on the p and g received
       */
      KeyPairGenerator kpg    =
        KeyPairGenerator.getInstance("DH");
      DHParameterSpec  dhSpec = new DHParameterSpec(p, g);

      kpg.initialize(dhSpec);

      KeyPair kp = kpg.generateKeyPair();

      /*
       * Distribute Public Key
       */
      userB = kp.getPublic().getEncoded();

      System.out.println();
      System.out
        .println("B->Public Key**********************");
      System.out.println(userB);
      notifyAll();

      /*
       * Key Agreement
       */
      KeyAgreement ka = KeyAgreement.getInstance("DH");

      ka.init(kp.getPrivate());

      /*
       * Secret Key Exchange
       * from M
       */
      KeyFactory         kf       =
        KeyFactory.getInstance("DH");
      X509EncodedKeySpec x509Spec =
        new X509EncodedKeySpec(userAfromM);
      PublicKey          pk       =
        kf.generatePublic(x509Spec);

      ka.doPhase(pk, true);

      /*
       * Distribute Public Key
       */
      byte secret[] = ka.generateSecret();

      /*
       * Decrypt message, thought to be from A
       * Actually from B
       */
      SecretKeyFactory skf       =
        SecretKeyFactory.getInstance("DES");
      DESKeySpec       desSpec   = new DESKeySpec(secret);
      SecretKey        secretKey = skf.generateSecret(desSpec);
      Cipher           c         =
        Cipher.getInstance("DES/ECB/PKCS5Padding");

      c.init(Cipher.DECRYPT_MODE, secretKey);

      while (ciphertext_from_M == null)
       {
        wait();
      }

      byte plaintext[] = c.doFinal(ciphertext_from_M);

      System.out.println("B->Got Cipher->"
                         + new String(plaintext));
    }
    catch (Exception e)
     {
      e.printStackTrace();
    }
  }

  /**
   * Method doUserM
   *
   *
   */
  public synchronized void doUserM()
   {

    try
     {
      System.out.println("M->Starting User....");

      /*
       * Wait for User A
       */
      while (userA == null)
       {
        wait();
      }

      System.out.println();
      System.out.println("M->A Public Key....");
      System.out.println(userA);
      System.out.println("M->A's P....");
      System.out.println(p);
      System.out.println();
      System.out.println("M->A's G....");
      System.out.println(g);

      /*
       * Generate a Key pair based on A
       */
      KeyPairGenerator kpg_from_A    =
        KeyPairGenerator.getInstance("DH");
      DHParameterSpec  dhSpec_from_A = new DHParameterSpec(p,
                                         g);

      kpg_from_A.initialize(dhSpec_from_A);
      System.out.println("M->Generating a Key from A....");

      KeyPair kp_from_A = kpg_from_A.generateKeyPair();

      userBfromM = kp_from_A.getPublic().getEncoded();

      System.out.println();
      System.out.println(
        "M->Sending fake B Public Key to A*********************");
      System.out.println(userBfromM);
      notifyAll();

      /*
       * Generate a 512 bit Prime to pass to B
       * with new  p and g
       */
      SecureRandom rnd = new SecureRandom();

      p = BigInteger.probablePrime(bitLength, rnd);
      g = BigInteger.probablePrime(bitLength, rnd);

      System.out.println();
      System.out
        .println("M->New p to B *********************");
      System.out.println(p);
      System.out.println();
      System.out
        .println("M->New g to B *********************");
      System.out.println(g);
      System.out.println();

      /*
       * Generate a Key pair based on B
       */
      KeyPairGenerator kpg_to_B    =
        KeyPairGenerator.getInstance("DH");
      DHParameterSpec  dhSpec_to_B = new DHParameterSpec(p, g);

      kpg_to_B.initialize(dhSpec_to_B);
      System.out.println("M->Generated a key to B....");

      KeyPair kp_to_B = kpg_to_B.generateKeyPair();

      userAfromM = kp_to_B.getPublic().getEncoded();

      System.out.println();
      System.out.println(
        "M->Sending fake A Public Key to B*********************");
      System.out.println(userAfromM);
      notifyAll();

      /*
       * Wait for B's distribution
       */
      while (userB == null)
       {
        wait();
      }

      System.out.println();
      System.out.println("M->B's Public Key....");
      System.out.println(userB);
      System.out.println();

      /*
       * Key Agreement between A and M
       */
      KeyAgreement ka_from_A = KeyAgreement.getInstance("DH");

      ka_from_A.init(kp_from_A.getPrivate());

      KeyFactory         kf_from_A       =
        KeyFactory.getInstance("DH");
      X509EncodedKeySpec x509Spec_from_A =
        new X509EncodedKeySpec(userA);
      PublicKey          pk_from_A       =
        kf_from_A.generatePublic(x509Spec_from_A);

      ka_from_A.doPhase(pk_from_A, true);

      byte secret_from_A[] = ka_from_A.generateSecret();

      /*
       * Getting cipher text from A
       */
      while (ciphertext_from_A == null)
       {
        wait();
      }

      /*
       * Decrypting A's Message
       */
      SecretKeyFactory skf_from_A     =
        SecretKeyFactory.getInstance("DES");
      DESKeySpec       desSpec_from_A =
        new DESKeySpec(secret_from_A);
      SecretKey        key_from_A     =
        skf_from_A.generateSecret(desSpec_from_A);
      Cipher           c_from_A       =
        Cipher.getInstance("DES/ECB/PKCS5Padding");

      c_from_A.init(Cipher.DECRYPT_MODE, key_from_A);

      byte plaintext[] = c_from_A.doFinal(ciphertext_from_A);

      System.out.println("M->Got Cipher from A->"
                         + new String(plaintext));

      /*
       * Key Agreement between B and M
       */
      KeyAgreement ka_to_B = KeyAgreement.getInstance("DH");

      ka_to_B.init(kp_to_B.getPrivate());

      KeyFactory         kf_to_B       =
        KeyFactory.getInstance("DH");
      X509EncodedKeySpec x509Spec_to_B =
        new X509EncodedKeySpec(userB);
      PublicKey          pk_to_B       =
        kf_to_B.generatePublic(x509Spec_to_B);

      ka_to_B.doPhase(pk_to_B, true);

      byte secret_to_B[] = ka_to_B.generateSecret();

      /*
       * M is sending B a cipher message
       */
      SecretKeyFactory skf_to_B     =
        SecretKeyFactory.getInstance("DES");
      DESKeySpec       desSpec_to_B =
        new DESKeySpec(secret_to_B);
      SecretKey        key_to_B     =
        skf_to_B.generateSecret(desSpec_to_B);
      Cipher           c_to_B       =
        Cipher.getInstance("DES/ECB/PKCS5Padding");

      c_to_B.init(Cipher.ENCRYPT_MODE, key_to_B);
      System.out.println();
      System.out.println(
        "M->Sending different Cipher to B->Are you sure that I am A?");

      ciphertext_from_M =
        c_to_B.doFinal("Are you sure that I am A?".getBytes());

      notifyAll();
    }
    catch (Exception e)
     {
      e.printStackTrace();
    }
  }
}
