package com.richware.chap09;

import java.security.*;


/**
 * Class RichSHA
 * Description: This is an example
 * implementation of the SHA-1
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
public class RichSHA
 {

  /* Temoporary Buffer */
  public int W_[];

  /* counter for the bytes */
  private long count_ = 0L;

  /* Initial Register Values */
  private int[] H_;

  /* Input buffer */
  private byte[] INPUT_;

  /*
   * The round constants
   */
  private final int round1_kt = 0x5a827999;
  private final int round2_kt = 0x6ed9eba1;
  private final int round3_kt = 0x8f1bbcdc;
  private final int round4_kt = 0xca62c1d6;

  /*
   * Trusted digests
   */
  public final static String[][] testData =
   {
    { "", "da39a3ee5e6b4b0d3255bfef95601890afd80709" },
    { "1", "356a192b7913b04c54574d18c28d46e6395428ab" },
    { "a", "86f7e437faa5a7fce15d1ddcb9eaeaea377667b8" },
    { "abc", "a9993e364706816aba3e25717850c26c9cd0d89d" },
    { "abcdefghijklmnopqrstuvwxyz",
      "32d10c7b8cf96570ca04ce37f2a19d84240d3a89" },
    { "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq",
      "84983E441C3BD26EBAAE4AA1F95129E5E54670F1" },  

    { "Anyone got any SHA-1 test data?",
      "09b9e9c04a84ce274942048acf3a6f2ff4a8a39c" },  
      { "Of cabbages and kings",
        "5f093d74a9cb1f2f14537bcf3a8a1ffd59b038a2" } 
  };

  /*
   * For hex conversion
   */
  public static final char[] hexDigits =
   {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
    'C', 'D', 'E', 'F'
  };

  /**
   * Constructor RichSHA
   * Description: The Main constructor
   *
   *
   */
  public RichSHA()
   {

    W_     = new int[80];
    H_     = new int[5];
    INPUT_ = new byte[64];

    initSHA();
  }

  /**
   * Method: initSHA
   * Description: Initialize the variables
   * for SHA
   *
   *
   */
  protected void initSHA()
   {

    // initial values of SHA i.e. A, B, C, D
    H_[0] = 0x67452301;
    H_[1] = 0xefcdab89;
    H_[2] = 0x98badcfe;
    H_[3] = 0x10325476;
    H_[4] = 0xc3d2e1f0;

    for (int i = 0; i < 80; i++)
     {
      W_[i] = 0;
    }

    for (int i = 0; i < 64; i++)
     {
      INPUT_[i] = 0;
    }

    count_ = 0L;
  }

  /**
   * Method updateSHA
   * Description: Updates the SHA values
   * with a byte array
   *
   *
   * @param data the data to add
   * @param offset offset to add data
   * @param len length of bytes
   *
   */
  public void updateSHA(byte data[], int offset, int len)
   {

    if ((offset < 0) || (len < 0)
            || (offset + len > data.length))
     {
      throw new ArrayIndexOutOfBoundsException();
    }

    /*
     * SAVE Input and compute
     * when the block is done
     */
    for (int index = 0; index < len; index++)
     {
      INPUT_[(int) count_ & 63] = data[offset + index];

      if ((int) (count_ & 63) == 63)
       {
        computeSHA();
      }

      count_++;
    }
  }

  /**
   * Method digestSHA
   * Description: Calculates final digest
   *
   *
   * @return the byte array with final digest
   *
   */
  public byte[] digestSHA()
   {

    byte digest[] = new byte[20];

    try
     {
      pad();

      for (int i = 0; i < 5; i++)
       {
        digest[4 * i]     = (byte) ((H_[i] >>> 24) & 255);
        digest[4 * i + 1] = (byte) ((H_[i] >>> 16) & 255);
        digest[4 * i + 2] = (byte) ((H_[i] >>> 8) & 255);
        digest[4 * i + 3] = (byte) (H_[i] & 255);
      }

      initSHA();
    }

    /*
     * Catches
     */
    catch (Exception ex)
     {
      ex.printStackTrace();
    }

    return digest;
  }

  /**
   * Method computeSHA
   * Description: The SHA algorithm
   *
   *
   *
   */
  private void computeSHA()
   {

    /* step a */
    for (int k1 = 0; k1 < 16; k1++)
     {
      W_[k1] =
        (((((int) INPUT_[4 * k1] & 255) << 8) + ((int) INPUT_[4 * k1 + 1] & 255) << 8) + ((int) INPUT_[4 * k1 + 2] & 255) << 8)
        + ((int) INPUT_[4 * k1 + 3] & 255);
    }

    /* step b */

    /*
     * 32 bit Word values being derived
     * from the 512-bit Message
     */
    for (int k2 = 16; k2 <= 79; k2++)
     {
      int i = W_[k2 - 3] ^ W_[k2 - 8] ^ W_[k2 - 14]
              ^ W_[k2 - 16];

      W_[k2] = i << 1 | i >>> 31;
    }

    int a    = H_[0];
    int b    = H_[1];
    int c    = H_[2];
    int d    = H_[3];
    int e    = H_[4];
    int temp = 0;

    for (int index = 0; index < 80; index++)
     {

      /*
       * Round 1
       */
      if (index < 20)
       {
        temp = (a << 5 | a >>> 27) + (b & c | ~b & d) + e
               + W_[index] + round1_kt;
      }

      /*
       * Round 2
       */
      else if (index < 40)
       {
        temp = (a << 5 | a >>> 27) + (b ^ c ^ d) + e
               + W_[index] + round2_kt;
      }

      /*
       * Round 3
       */
      else if (index < 60)
       {
        temp = (a << 5 | a >>> 27) + (b & c | b & d | c & d)
               + e + W_[index] + round3_kt;
      }

      /*
       * Round 4
       */
      else if (index < 80)
       {
        temp = (a << 5 | a >>> 27) + (b ^ c ^ d) + e
               + W_[index] + round4_kt;
      }

      /*
       * All Rounds
       */
      e = d;
      d = c;
      c = b << 30 | b >>> 2;
      b = a;
      a = temp;
    }

    /*
     * Add the values back
     * to the registers
     */
    H_[0] += a;
    H_[1] += b;
    H_[2] += c;
    H_[3] += d;
    H_[4] += e;
  }

  /**
   * Method pad
   * Description: Pads the bytes
   */
  private void pad()
   {

    int  i;
    long bitlength;

    bitlength                 = count_ << 3;
    INPUT_[(int) count_ & 63] = (byte) 128;

    count_++;

    if ((int) (count_ & 63) >= 56)
     {
      for (i = ((int) count_ & 63); i < 64; i++)
       {
        INPUT_[i] = 0;

        count_++;
      }

      computeSHA();
    }

    for (i = ((int) count_ & 63); i < 56; i++)
     {
      INPUT_[i] = 0;
    }

    INPUT_[56] = (byte) ((bitlength >>> 56) & 255);
    INPUT_[57] = (byte) ((bitlength >>> 48) & 255);
    INPUT_[58] = (byte) ((bitlength >>> 40) & 255);
    INPUT_[59] = (byte) ((bitlength >>> 32) & 255);
    INPUT_[60] = (byte) ((bitlength >>> 24) & 255);
    INPUT_[61] = (byte) ((bitlength >>> 16) & 255);
    INPUT_[62] = (byte) ((bitlength >>> 8) & 255);
    INPUT_[63] = (byte) (bitlength & 255);

    computeSHA();
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
       * Create a new local SHA1 and test data
       */
      RichSHA sha = new RichSHA();

      /*
       * Create a JDK SHA aalgorithm
       */
      MessageDigest shaMD = MessageDigest.getInstance("SHA1");

      /*
       * Loop through the test data
       */
      for (int index = 0; index < testData.length; index++)
       {
        System.out.println("");
        System.out.println("SHA1 Digesting...");
        System.out.println(testData[index][0]);

        byte[] testBytes = testData[index][0].getBytes();

        /*
         * Update the digest with data
         * normally the data can be updated
         * at different times
         */
        System.out.println("Test Length :" + testBytes.length);
        sha.updateSHA(testBytes, 0, testBytes.length);

        byte[] digest = sha.digestSHA();

        System.out.println("Trusted Digest :"
                           + testData[index][1]);

        byte[] testDigest = testData[0][1].getBytes();
        char[] buf        = new char[digest.length * 2];
        int    j          = 0;
        int    k;

        for (int i = 0; i < digest.length; i++)
         {
          k        = digest[i];
          buf[j++] = hexDigits[(k >>> 4) & 0x0F];
          buf[j++] = hexDigits[k & 0x0F];
        }

        String buffer = new String(buf);

        System.out.println("New Digest     :" + buffer);
      }

      /*
       *  Test the JDK Version
       */
      for (int index = 0; index < testData.length; index++)
       {
        System.out.println("");
        System.out.println("SHA1 Digesting with JDK...");
        System.out.println(testData[index][0]);

        byte[] testBytes = testData[index][0].getBytes();

        /*
         * Update the digest with data
         * normally the data can be updated
         * at different times
         */
        System.out.println("Test Length :" + testBytes.length);
        shaMD.update(testBytes, 0, testBytes.length);

        byte[] digest = shaMD.digest();

        System.out.println("Trusted Digest :"
                           + testData[index][1]);

        byte[] testDigest = testData[0][1].getBytes();
        char[] buf        = new char[digest.length * 2];
        int    j          = 0;
        int    k;

        for (int i = 0; i < digest.length; i++)
         {
          k        = digest[i];
          buf[j++] = hexDigits[(k >>> 4) & 0x0F];
          buf[j++] = hexDigits[k & 0x0F];
        }

        String buffer = new String(buf);

        System.out.println("New Digest     :" + buffer);
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
