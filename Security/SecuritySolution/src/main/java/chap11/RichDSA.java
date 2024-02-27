package com.richware.chap11;

import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;

import java.security.spec.*;

import java.math.BigInteger;

import java.io.*;


/**
 * Class RichDSA
 * Description: This is an example
 * implementation of the DSA
 * algorithm.
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
public class RichDSA
 {

  /* the key length */
  private int l_ = 1024;

  /* the private key */
  private BigInteger x_;

  /* the public key */
  private BigInteger y_;

  /* the DSA parameters, see spec */
  /* h is used to generate g */
  private BigInteger h_;
  private BigInteger p_;
  private BigInteger q_;
  private BigInteger g_;

  /* q bit length */
  int qBitLength_ = 160;

  /* the SHA1 message digest */
  private MessageDigest md_;

  /* To debug or not to debug */
  private final boolean DEBUG = false;

  /* The certainty, that the generated numbers are prime. */
  private final int CERTAINTY = 80;

  /* The secure random generator */
  private SecureRandom secureRandom_;

  /* BigInteger Constants */
  private static final BigInteger ZERO =
    BigInteger.valueOf(0L);
  private static final BigInteger ONE  =
    BigInteger.valueOf(1L);
  private static final BigInteger TWO  =
    BigInteger.valueOf(2L);

  /**
   * Constructor RichDSA
   *
   *
   */
  public RichDSA()
   {
    initKeys();
  }

  /**
   * Method initKeys
   *
   *
   */
  public void initKeys()
   {

    BigInteger x, c, qMultTwo;

    try
     {
      md_ = MessageDigest.getInstance("SHA1");

      if (secureRandom_ == null)
       {
        secureRandom_ = new SecureRandom();
      }

      int     counter     = 0;
      boolean primesFound = false;

      while (!primesFound)
       {
        counter = 0;

        /*
         * Calculate Q
         */
        q_ = new BigInteger(qBitLength_, CERTAINTY,
                            secureRandom_);

        while ((counter < 4096) && (!primesFound))
         {

          /*
           * q must be a divisor of p
           * h ^ ((p-1) / q) mod p
           * and p being prime are a 
           * must. X will be shifted to try
           * another random and tested for prime.
           * See DSA Spec.
           * Appendix 2 and 3.
           */
          x        = reseedX();
          qMultTwo = q_.multiply(TWO);
          c        = x.mod(qMultTwo);

          /*
           * Calculate P
           */
          p_ = x.subtract(c.subtract(ONE));

          /*
           * If P is long enough and is prime,
           * use it
           */
          if (p_.bitLength() >= (l_))
           {
            if (DEBUG)
             {
              System.out.println("Counter :" + counter);
            }

            /*
             * P must be prime to quite
             */
            if (p_.isProbablePrime(CERTAINTY))
             {
              primesFound = true;
            }
          }

          counter++;
        }
      }

      /*
       * Calculate H
       */
      boolean hFound = false;

      while (!hFound)
       {
        h_ = new BigInteger(l_, secureRandom_);

        if ((h_.compareTo(ONE) > 0)
                || (h_.compareTo(p_.subtract(ONE)) < 0))
         {
          hFound = true;
        }
      }

      /*
       * Generate G, the generator
       */
      BigInteger pMinusOneOverQ = p_.subtract(ONE).divide(q_);
      boolean    gFound         = false;

      while (!gFound)
       {
        g_ = h_.modPow(pMinusOneOverQ, p_);

        if (g_.compareTo(ONE) > 0)
         {
          gFound = true;
        }
      }

      /*
       * Generate X, the private key
       */
      x_ = new BigInteger(qBitLength_ - 1, secureRandom_);

      while ((x_.compareTo(BigInteger.ZERO)) == 0)
       {
        x_ = new BigInteger(qBitLength_ - 1, secureRandom_);
      }

      /*
       * Generate Y, the public key
       */
      y_ = g_.modPow(x_, p_);

      /*
       * If DEBUG, print the results
       */
      if (DEBUG)
       {
        System.out.println();
        System.out.println("p_ :" + p_);
        System.out.println();
        System.out.println("q_ :" + q_);
        System.out.println();
        System.out.println("x_ :" + x_);
        System.out.println();
        System.out.println("y_ :" + y_);
        System.out.println();
        System.out.println("g_ :" + g_);
        System.out.println();
        System.out.println("h_ :" + h_);
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

  /**
   * Method reseedX
   * Description reseed X by shifting
   *
   * @return a new random X
   *
   */
  private BigInteger reseedX()
   {

    byte[] shiftBytes = new byte[(l_ / 8)];

    secureRandom_.nextBytes(shiftBytes);

    shiftBytes[0] = (byte) (shiftBytes[0] | 128);

    return (new BigInteger(1, shiftBytes));
  }

  /**
   * Method sign
   * Description: return the 
   * signature
   *
   *
   * @return mostly r and s
   *
   */
  public byte[] sign()
   {

    try
     {

      /*
       * Calculate the digest, number
       */
      byte[]     digest = md_.digest();
      BigInteger m      = new BigInteger(1, digest);

      /*
       * Generate k, a random number
       * 0 < k < q
       */
      BigInteger k = new BigInteger(qBitLength_ - 1,
                                    secureRandom_);

      while (k.compareTo(q_) >= 0)
       {
        k = new BigInteger(qBitLength_ - 1, secureRandom_);
      }

      /*
       * Inverse of K
       */
      BigInteger kInv = k.modInverse(q_);

      /*
       * r =  (g ^ k mod p) mod q
       */
      BigInteger r = (g_.modPow(k, p_)).mod(q_);

      /*
       * s =  (k ^ -1(SHA(M) +xr)) mod q
       */
      BigInteger s =
        kInv.multiply((m.add(x_.multiply(r)))).mod(q_);

      /*
       * If DEBUG, print the results
       */
      if (DEBUG)
       {
        System.out.println();
        System.out.println("sign:r :" + r);
        System.out.println();
        System.out.println("sign:s :" + s);
        System.out.println();
        System.out.println("sign:m :" + m);
      }

      /*
       * Put r and s in a buffer
       * with some magic numbers
       * to check for corruption
       */
      byte[] rdata = r.toByteArray();
      byte[] sdata = s.toByteArray();
      byte[] data  = new byte[6 + rdata.length + sdata.length];
      int    i     = 0;

      /*
       * Put first magic number
       */
      data[i++] = 0x40;

      /*
       * Put in length
       */
      data[i++] = (byte) (data.length - 2);

      /*
       * Put in Sepaerator
       */
      data[i++] = 0x02;

      /*
       * Put in r length
       */
      data[i++] = (byte) (rdata.length);

      /*
       * Put r in buffer
       */
      for (int j = 0; j < rdata.length; j++)
       {
        data[i++] = rdata[j];
      }

      /*
       * Put in sepaerator
       */
      data[i++] = 0x02;

      /*
       * Put in s length
       */
      data[i++] = (byte) (sdata.length);

      /*
       * Put s in buffer
       */
      for (int j = 0; j < sdata.length; j++)
       {
        data[i++] = sdata[j];
      }

      return data;
    }

    /*
     * Catches
     */
    catch (Exception ex)
     {
      ex.printStackTrace();
    }

    return null;
  }

  /**
   * Method update
   * Description : uppdate the hash
   *
   * @param b the bytes
   * @param offset the offset
   * @param len the length
   *
   * @throws SignatureException
   *
   */
  public void update(byte[] b, int offset, int len)
          throws SignatureException
   {

    md_.update(b, offset, len);

    if (DEBUG)
     {
      System.out.println();
      System.out.println("update:Length:" + len);
    }
  }

  /**
   * Method verify
   *
   *
   * @param data
   *
   * @return
   *
   */
  public boolean verify(byte[] data)
   {

    try
     {
      int i = 0;

      /*
       * Check for the first
       * magic number,
       * the length
       * and sepaerator
       */
      if ((data[i++] != 0x40)
              || (data[i++] != data.length - 2)
              || (data[i++] != 0x02))
       {
        throw new SignatureException(
          "Corrupted signature data");
      }

      /*
       * Check r length
       */
      byte len = data[i++];

      if (len > 21)
       {
        throw new SignatureException(
          "Corrupted signature data");
      }

      /*
       * Get the r buffer
       */
      byte[] rdata = new byte[len];

      for (int j = 0; j < len; j++)
       {
        rdata[j] = data[i++];
      }

      /*
       * Check sepaerator
       */
      if (data[i++] != 0x02)
       {
        throw new SignatureException(
          "Corrupted signature data");
      }

      /*
       * Check s length
       */
      len = data[i++];

      if (len > 21)
       {
        throw new SignatureException(
          "Corrupted signature data");
      }

      /*
       * Get the s buffer
       */
      byte[] sdata = new byte[len];

      for (int j = 0; j < len; j++)
       {
        sdata[j] = data[i++];
      }

      /*
       * Get r and s from Buffer
       */
      BigInteger r = new BigInteger(rdata);
      BigInteger s = new BigInteger(sdata);

      /*
       * reject the signature, if r or s >= q
       */
      if ((r.compareTo(q_) >= 0) || (s.compareTo(q_) >= 0))
       {
        return false;
      }

      /*
       * Get the hash value,number
       */
      byte[]     digest = md_.digest();
      BigInteger m      = new BigInteger(1, digest);

      /*
       * w = (S^-1) mod q
       */
      BigInteger w = s.modInverse(q_);

      /*
       * u1 = ((SHA(M)w) mod q
       */
      BigInteger u1 = m.multiply(w).mod(q_);

      /*
       * u2 = ((r)w) mod q
       */
      BigInteger u2 = r.multiply(w).mod(q_);

      if (DEBUG)
       {
        System.out.println();
        System.out.println("verify:r :" + r);
        System.out.println();
        System.out.println("verify:s :" + s);
        System.out.println();
        System.out.println("verify:m :" + m);
        System.out.println();
        System.out.println("verify:w :" + w);
        System.out.println();
        System.out.println("verify:u1 :" + u1);
        System.out.println();
        System.out.println("verify:u2 :" + u2);
        System.out.println();
        System.out.println("verify:g :" + g_);
        System.out.println();
        System.out.println("verify:p :" + p_);
        System.out.println();
        System.out.println("verify:q :" + q_);
        System.out.println();
        System.out.println("verify:y :" + y_);
      }

      BigInteger gu1 = g_.modPow(u1, p_);
      BigInteger yu2 = y_.modPow(u2, p_);

      /*
       * v = (((g)^ul (y) ^u2 ) mod p) mod q
       */
      BigInteger v = gu1.multiply(yu2).mod(p_).mod(q_);

      return v.equals(r);
    }

    /*
     * Catches
     */
    catch (Exception ex)
     {
      ex.printStackTrace();
    }

    return false;
  }

  /**
   * Method main
   * Description: This is a test driver
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
      String localFile  = localDirectory + args[0];
      System.out.println("Openining Chapter 11 plus the file as an argument: " + localFile); 
      System.out.println("Initializing Keys... This could take several minutes....");
    
      /*
       * Start the homegrown signer
       * it automatically
       * generates the keys
       */
      RichDSA dsa = new RichDSA();
      /*
       * Start the JDK version
       */

      /*
       * Generate the Keypair
       * get the private Key
       * get the public Key
       */
      KeyPairGenerator kpg =
        KeyPairGenerator.getInstance("DSA");
      SecureRandom     r   = new SecureRandom();

      kpg.initialize(1024, r);

      KeyPair    kp      = kpg.genKeyPair();
      PrivateKey privKey = kp.getPrivate();
      PublicKey  pubKey  = kp.getPublic();

      /*
       * Construct a sign
       * and verify Signature
       * If the same Signature
       * class is used for both
       * sign and verify in ths same context
       * might cause some problems
       */
      Signature dsaSign   =
        Signature.getInstance("SHA1withDSA", "SUN");
      Signature dsaVerify =
        Signature.getInstance("SHA1withDSA", "SUN");

      /*
       * Init a sign
       * with private Key
       * and verify with
       * a public Key
       */
      dsaSign.initSign(privKey);
      dsaVerify.initVerify(pubKey);

      /*
       * Open a File
       * and read the text
       */
      File                inputTextFile =
        new File(localFile);
      FileInputStream     fis           =
        new FileInputStream(inputTextFile);
      BufferedInputStream bis           =
        new BufferedInputStream(fis);
      byte[]              buff          =
        new byte[(int) inputTextFile.length()];
      int                 len;

      /*
       * Loop through the File
       * pass the date through
       * update method for
       * hashing
       */
      while (bis.available() != 0)
       {
        len = bis.read(buff);

        dsa.update(buff, 0, len);
        dsaSign.update(buff, 0, len);
      }

      /*
       * Close the file
       */
      bis.close();
      fis.close();

      /*
       * Get the signatures
       * the signature and public Key bytes
       * are normally written to file
       */
      byte[] text_signature = dsa.sign();
      byte[] jdk_signature  = dsaSign.sign();

      /*
       * Open a File
       * and read the text
       */
      inputTextFile = new File(localFile);
      fis           = new FileInputStream(inputTextFile);
      bis           = new BufferedInputStream(fis);
      buff          = new byte[(int) inputTextFile.length()];

      /*
       * Loop through the File
       * pass the date through
       * update method for
       * hashing
       */
      while (bis.available() != 0)
       {
        len = bis.read(buff);

        dsa.update(buff, 0, len);
        dsaVerify.update(buff, 0, len);
      }

      /*
       * Verify with hash
       * public key and
       * signatures
       */
      boolean verifies     = dsa.verify(text_signature);
      boolean jdk_verifies = dsaVerify.verify(jdk_signature);

      System.out.println("RichDSA Verify : " + verifies);
      System.out.println("JDK Verify     : " + jdk_verifies);

      /*
       * Close the file
       */
      bis.close();
      fis.close();
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
