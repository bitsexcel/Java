package com.richware.chap10;

import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;

import java.security.spec.*;

import java.math.BigInteger;


/**
 * Class RichMAC
 * Description: This is an example
 * implementation of the MAC
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
public class RichMAC
 {

  /**
   * The algorithm used for the HMac. Usually SHA1 or MD5
   */

  /* The algorithm name */
  private String      algorithm_;
  /* inner pad */
  private static byte ipad_ = 0x36;
  /* outer pad */
  private static byte opad_ = 0x5c;
  /* Digest Block Length */
  private static int  B_    = 64;

  /* The Length */
  protected int L_;

  /* inner pad */
  private byte[] ipad_key = new byte[64];

  /* outer pad */
  private byte[] opad_key = new byte[64];

  /* The MAC key */
  private byte[] macKey = new byte[64];

  /* The Message Digest */
  protected MessageDigest        md;
  public final static String[][] testData =
   {

    //    data string, md hex
    { "", "D41D8CD98F00B204E9800998ECF8427E" },     // A.5 1
    { "a", "0CC175B9C0F1B6A831C399E269772661" },    // A.5 2
    { "aa", "4124BC0A9335C27F086F24BA207A4912" },
    { "abc", "900150983CD24FB0D6963F7D28E17F72" },  // A.5 3
    { "aaa", "47BCE5C74F589F4867DBD57E9CA9F808" },
    { "bbb", "08F8E0260C64418510CEFB2B06EEE5CD" },
    { "ccc", "9DF62E693988EB4E1E1444ECE0578579" },
    { "message digest", "F96B697D7CB7938D525A2F31AAF161D0" },  // A.5 4
    { "abcdefg", "7AC66C0F148DE9519B8BD264312C4D64" },
    { "abcdefghijk", "92B9CCCC0B98C3A0B8D0DF25A421C0E3" },
     {                                              // A.5 5
      "abcdefghijklmnopqrstuvwxyz",
      "C3FCD3D76192E4007DFB496CCA67E13B"
    },
     {                                              // A.5 6
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
      "D174AB98D277D9F5A5611C2C9F419D9F"
    },
     {                                              // A.5 7
      "12345678901234567890123456789012345678901234567890123456789012345678901234567890",
      "57EDF4A22BE3C955AC49DA2E2107B67A"
    },
  };
  public static final char[] hexDigits =
   {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
    'C', 'D', 'E', 'F'
  };

  /**
   * Constructor RichMAC
   *
   *
   * @param algorithm
   *
   * @throws NoSuchAlgorithmException
   *
   */
  public RichMAC(String algorithm)
          throws NoSuchAlgorithmException
   {

    algorithm_ = algorithm;
    md         = MessageDigest.getInstance(algorithm_);
    L_         = md.getDigestLength();
  }

  /**
   * Method doFinal
   * Description : does the final calculations
   * and returns the digest
   *
   *
   * @return the digest
   *
   */
  public byte[] doFinal()
   {

    byte[] hash1;
    byte[] hmac;

    /* Get the original digest */
    hash1 = md.digest();

    md.reset();
    /* Update with the outer pad */
    md.update(opad_key);
    /* Update with the original hash */
    md.update(hash1);

    hmac = md.digest();

    return hmac;
  }


  /**
   * Method init
   * Description : does the initial calculations
   * with the secret key
   *
   *
   * @param key the Secret key
   *
   * @throws InvalidAlgorithmParameterException
   * @throws InvalidKeyException
   *
   */
  public final void init(Key key)
          throws InvalidKeyException,
                 InvalidAlgorithmParameterException
   {
      init(key, null);
  }

  /**
   * Method init
   * Description : does the initial calculations
   * with the secret key and algorithm spec
   *
   *
   * @param key the secret key
   * @param params the key parameters
   *
   * @throws InvalidAlgorithmParameterException
   * @throws InvalidKeyException
   *
   */
  public void init(Key key, AlgorithmParameterSpec params)
          throws InvalidKeyException,
                 InvalidAlgorithmParameterException
   {
    byte[]           keyBytes;

    /*
     * convert the secret key into a bytearray.
     */
    if (key instanceof SecretKey)
     {
      keyBytes = key.getEncoded();
    }
    else
     {
      throw new InvalidKeyException();
    }

    /*
     * If the key is greater than B, 
     * the key has to be hashed.
     * If the key is less than B
     * the key must be extended with zeros.
     */
    int n = B_ - keyBytes.length;

    /*
     * If the key is less than B, 
     * fill up to B size with zeros
     */
    if (n > 0)
     {
      System.arraycopy(keyBytes, 0, macKey, 0,
                       keyBytes.length);

      for (int i = 0; i < n; i++)
       {
        macKey[keyBytes.length + i] = 0;
      }
    }
    /*
     * if the key size is greater than B, 
     * the key has to be hashed and the 
     * digest is retrieved
     */
    else
     {
      if (n < 0)
       {
        md.update(keyBytes);

        macKey = md.digest();
      }
    }

    /* Copies the MAC Key into the ipad_key */
    System.arraycopy(macKey, 0, ipad_key, 0, B_);
    /* Copies the MAC Key into the opad_key */
    System.arraycopy(macKey, 0, opad_key, 0, B_);

    for (int j = 0; j < B_; j++)
     {
      /* XOR the ipad_key with the ipad */
      ipad_key[j] = (byte) (ipad_key[j] ^ ipad_);
      /* XOR the opad_key with the opad */
      opad_key[j] = (byte) (opad_key[j] ^ opad_);
    }

    /* Digest the ipad_key */
    md.update(ipad_key);
  }

  /**
   * Method reset
   * Description : Resets the values for a new digest
   *
   *
   */
  public void reset()
   {
    md.reset();
  }

  /**
   * Method update
   * Description : Updates the input data
   *
   *
   * @param input the input data
   *
   */
  public void update(byte[] input)
   {

    md.update(input, 0, input.length);
  }

  /**
   * Method update
   * Description : Updates the input data
   * with the offste and data length.
   *
   * @param input the input bytes
   * @param offset the offset of the input
   * @param len the length of the input
   *
   */
  public void update(byte[] input, int offset, int len)
   {
    md.update(input, offset, len);
  }

  /**
   * Method update
   * Description : Updates the input data
   * with the offste and data length.
   *
   *
   * @param input byte
   *
   */
  public void update(byte input)
   {

    md.update(input);
  }

  /**
   * Method getMacLength
   *
   *
   * @return the length of message digest
   *
   */
  public int getMacLength()
   {
    return L_;
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

      /*
       * Create a password for the file
       */
      char[] password = new char[4];

      password[0] = 't';
      password[1] = 'e';
      password[2] = 's';
      password[3] = 't';

      /*
       * Create a new local MD5 and test data
       */
      RichMAC local_m = new RichMAC("MD5");

      /*
       * Test the JDK version
       */
      javax.crypto.Mac m =
        javax.crypto.Mac.getInstance("HmacMD5");

      /*
       * Build the key
       */

      // Use the char array to create a PBEKeySpec
      PBEKeySpec keySpec = new PBEKeySpec(password);

      // Create a SecretKeyFactory for the PBE key
      SecretKeyFactory keyFactory =
        SecretKeyFactory.getInstance("PBEWithMD5AndDES");

      // Generate the key from the key spec
      SecretKey srKey = keyFactory.generateSecret(keySpec);

      // Convert the key to a byte buffer
      byte[] keyBuffer = srKey.getEncoded();

      m.init(srKey);
      local_m.init(srKey);

      byte[] testBytes = testData[1][0].getBytes();

      /*
       * Update the digest with data
       * normally the data can be updated
       * at different times
       */
      System.out.println("Test Length :" + testBytes.length);
      System.out.println("Test String :" + testData[1][0]);
      m.update(testBytes);
      local_m.update(testBytes);

      byte[] digest  = m.doFinal();
      byte[] digest2 = local_m.doFinal();
      char[] buf     = new char[digest.length * 2];
      char[] buf2    = new char[digest2.length * 2];
      int    j       = 0;
      int    k;

      for (int i = 0; i < digest.length; i++)
       {
        k        = digest[i];
        buf[j++] = hexDigits[(k >>> 4) & 0x0F];
        buf[j++] = hexDigits[k & 0x0F];
      }

      String buffer = new String(buf);

      System.out.println("JDK New Digest       :" + buffer);

      j = 0;

      for (int i = 0; i < digest2.length; i++)
       {
        k        = digest2[i];
        buf[j++] = hexDigits[(k >>> 4) & 0x0F];
        buf[j++] = hexDigits[k & 0x0F];
      }

      String buffer2 = new String(buf);

      System.out.println("Local New Digest     :" + buffer2);
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
