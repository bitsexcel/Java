package com.richware.chap09;

import java.security.*;

/**
 * Class RichMD5
 * Description: This is an example
 * implementation of the MD5
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
public class RichMD5
 {

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
  private static final int   S11       = 7;
  private static final int   S12       = 12;
  private static final int   S13       = 17;
  private static final int   S14       = 22;
  private static final int   S21       = 5;
  private static final int   S22       = 9;
  private static final int   S23       = 14;
  private static final int   S24       = 20;
  private static final int   S31       = 4;
  private static final int   S32       = 11;
  private static final int   S33       = 16;
  private static final int   S34       = 23;
  private static final int   S41       = 6;
  private static final int   S42       = 10;
  private static final int   S43       = 15;
  private static final int   S44       = 21;
  private long               count;

  /** 4 32-bit words (interim result) */
  private int[] context = new int[4];

  /** 512 bits work buffer = 16 x 32-bit words */
  private int[] x = new int[16];
  private byte  digestBits[];
  private byte  buffer[];

  /**
   * Constructor RichMD5
   *
   *
   */
  public RichMD5()
   {
    MD5Init();
  }

  protected void MD5Init()
   {

    // initial values of MD5 i.e. A, B, C, D
    context[0] = 0x67452301;
    context[1] = 0xEFCDAB89;
    context[2] = 0x98BADCFE;
    context[3] = 0x10325476;
    buffer     = new byte[64];
    count      = 0L;
    digestBits = new byte[16];

    for (int i = 0; i < digestBits.length; i++)
     {
      digestBits[i] = 0;
    }
  }

  /**
   * Method MD5Update
   *
   *
   * @param input
   * @param inputLen
   *
   */
  public void MD5Update(byte[] input, int inputLen)
   {

    int k = 0;
    int j = inputLen;

    while (j > 0)
     {
      int l = (int) (count >>> 3 & 63L);

      if ((l == 0) && (j > 64))
       {
        count += 512L;

        MD5Transform(input, k);

        j -= 64;
        k += 64;
      }
      else
       {
        count     += 8L;
        buffer[l] = input[k];

        if (l >= 63)
         {
          MD5Transform(buffer, 0);
        }

        k++;
        j--;
      }
    }
  }

  /**
   * Method MD5Final
   *
   *
   * @return
   *
   */
  public byte[] MD5Final()
   {

    byte abyte0[] = new byte[8];

    for (int i = 0; i < 8; i++)
     {
      abyte0[i] = (byte) (int) (count >>> i * 8 & 255L);
    }

    /* Pad out to 56 mod 64. */
    int index = (int) (count >> 3) & 0x3f;

    /*
     * Apply the padding
     */
    int  padLen   = (index >= 56)
                    ? 120 - index
                    : 56 - index;
    byte abyte1[] = new byte[padLen];

    abyte1[0] = -128;

    MD5Update(abyte1, abyte1.length);
    MD5Update(abyte0, abyte0.length);

    for (int j = 0; j < 4; j++)
     {
      for (int i1 = 0; i1 < 4; i1++)
       {
        digestBits[j * 4 + i1] = (byte) (context[j] >>> i1 * 8
                                         & 0xff);
      }
    }

    /* Store state in digest */
    byte abyte2[] = new byte[16];

    System.arraycopy(digestBits, 0, abyte2, 0, 16);
    MD5Init();

    return abyte2;
  }

  protected void MD5Transform(byte[] block, int offset)
   {

    /*
     * Decodes 64 bytes from
     * input block into an array of
     * 16 32-bit entities.
     * Decode function in reference
     */
    for (int i = 0; i < 16; i++)
     {
      x[i] = (block[offset++] & 0xFF)
             | (block[offset++] & 0xFF) << 8
             | (block[offset++] & 0xFF) << 16
             | (block[offset++] & 0xFF) << 24;
    }

    int a = context[0];
    int b = context[1];
    int c = context[2];
    int d = context[3];

    /* Round 1 */
    a = FF(a, b, c, d, x[0], S11, 0xd76aa478);   /* 1 */
    d = FF(d, a, b, c, x[1], S12, 0xe8c7b756);   /* 2 */
    c = FF(c, d, a, b, x[2], S13, 0x242070db);   /* 3 */
    b = FF(b, c, d, a, x[3], S14, 0xc1bdceee);   /* 4 */
    a = FF(a, b, c, d, x[4], S11, 0xf57c0faf);   /* 5 */
    d = FF(d, a, b, c, x[5], S12, 0x4787c62a);   /* 6 */
    c = FF(c, d, a, b, x[6], S13, 0xa8304613);   /* 7 */
    b = FF(b, c, d, a, x[7], S14, 0xfd469501);   /* 8 */
    a = FF(a, b, c, d, x[8], S11, 0x698098d8);   /* 9 */
    d = FF(d, a, b, c, x[9], S12, 0x8b44f7af);   /* 10 */
    c = FF(c, d, a, b, x[10], S13, 0xffff5bb1);  /* 11 */
    b = FF(b, c, d, a, x[11], S14, 0x895cd7be);  /* 12 */
    a = FF(a, b, c, d, x[12], S11, 0x6b901122);  /* 13 */
    d = FF(d, a, b, c, x[13], S12, 0xfd987193);  /* 14 */
    c = FF(c, d, a, b, x[14], S13, 0xa679438e);  /* 15 */
    b = FF(b, c, d, a, x[15], S14, 0x49b40821);  /* 16 */

    /* Round 2 */
    a = GG(a, b, c, d, x[1], S21, 0xf61e2562);   /* 17 */
    d = GG(d, a, b, c, x[6], S22, 0xc040b340);   /* 18 */
    c = GG(c, d, a, b, x[11], S23, 0x265e5a51);  /* 19 */
    b = GG(b, c, d, a, x[0], S24, 0xe9b6c7aa);   /* 20 */
    a = GG(a, b, c, d, x[5], S21, 0xd62f105d);   /* 21 */
    d = GG(d, a, b, c, x[10], S22, 0x2441453);   /* 22 */
    c = GG(c, d, a, b, x[15], S23, 0xd8a1e681);  /* 23 */
    b = GG(b, c, d, a, x[4], S24, 0xe7d3fbc8);   /* 24 */
    a = GG(a, b, c, d, x[9], S21, 0x21e1cde6);   /* 25 */
    d = GG(d, a, b, c, x[14], S22, 0xc33707d6);  /* 26 */
    c = GG(c, d, a, b, x[3], S23, 0xf4d50d87);   /* 27 */
    b = GG(b, c, d, a, x[8], S24, 0x455a14ed);   /* 28 */
    a = GG(a, b, c, d, x[13], S21, 0xa9e3e905);  /* 29 */
    d = GG(d, a, b, c, x[2], S22, 0xfcefa3f8);   /* 30 */
    c = GG(c, d, a, b, x[7], S23, 0x676f02d9);   /* 31 */
    b = GG(b, c, d, a, x[12], S24, 0x8d2a4c8a);  /* 32 */

    /* Round 3 */
    a = HH(a, b, c, d, x[5], S31, 0xfffa3942);   /* 33 */
    d = HH(d, a, b, c, x[8], S32, 0x8771f681);   /* 34 */
    c = HH(c, d, a, b, x[11], S33, 0x6d9d6122);  /* 35 */
    b = HH(b, c, d, a, x[14], S34, 0xfde5380c);  /* 36 */
    a = HH(a, b, c, d, x[1], S31, 0xa4beea44);   /* 37 */
    d = HH(d, a, b, c, x[4], S32, 0x4bdecfa9);   /* 38 */
    c = HH(c, d, a, b, x[7], S33, 0xf6bb4b60);   /* 39 */
    b = HH(b, c, d, a, x[10], S34, 0xbebfbc70);  /* 40 */
    a = HH(a, b, c, d, x[13], S31, 0x289b7ec6);  /* 41 */
    d = HH(d, a, b, c, x[0], S32, 0xeaa127fa);   /* 42 */
    c = HH(c, d, a, b, x[3], S33, 0xd4ef3085);   /* 43 */
    b = HH(b, c, d, a, x[6], S34, 0x4881d05);    /* 44 */
    a = HH(a, b, c, d, x[9], S31, 0xd9d4d039);   /* 45 */
    d = HH(d, a, b, c, x[12], S32, 0xe6db99e5);  /* 46 */
    c = HH(c, d, a, b, x[15], S33, 0x1fa27cf8);  /* 47 */
    b = HH(b, c, d, a, x[2], S34, 0xc4ac5665);   /* 48 */

    /* Round 4 */
    a = II(a, b, c, d, x[0], S41, 0xf4292244);  /* 49 */
    d = II(d, a, b, c, x[7], S42, 0x432aff97);  /* 50 */
    c = II(c, d, a, b, x[14], S43, 0xab9423a7);  /* 51 */
    b = II(b, c, d, a, x[5], S44, 0xfc93a039);  /* 52 */
    a = II(a, b, c, d, x[12], S41, 0x655b59c3);  /* 53 */
    d = II(d, a, b, c, x[3], S42, 0x8f0ccc92);  /* 54 */
    c = II(c, d, a, b, x[10], S43, 0xffeff47d);  /* 55 */
    b = II(b, c, d, a, x[1], S44, 0x85845dd1);  /* 56 */
    a = II(a, b, c, d, x[8], S41, 0x6fa87e4f);  /* 57 */
    d = II(d, a, b, c, x[15], S42, 0xfe2ce6e0);  /* 58 */
    c = II(c, d, a, b, x[6], S43, 0xa3014314);  /* 59 */
    b = II(b, c, d, a, x[13], S44, 0x4e0811a1);  /* 60 */
    a = II(a, b, c, d, x[4], S41, 0xf7537e82);  /* 61 */
    d = II(d, a, b, c, x[11], S42, 0xbd3af235);  /* 62 */
    c = II(c, d, a, b, x[2], S43, 0x2ad7d2bb);  /* 63 */
    b = II(b, c, d, a, x[9], S44, 0xeb86d391);  /* 64 */
    context[0] += a;
    context[1] += b;
    context[2] += c;
    context[3] += d;
  }

  private static int F(int x, int y, int z)
   {
    return (z ^ (x & (y ^ z)));
  }

  private static int G(int x, int y, int z)
   {
    return (y ^ (z & (x ^ y)));
  }

  private static int H(int x, int y, int z)
   {
    return (x ^ y ^ z);
  }

  private static int I(int x, int y, int z)
   {
    return (y ^ (x | ~z));
  }

  private static int FF(int a, int b, int c, int d, int k,
                        int s, int t)
   {

    a += k + t + F(b, c, d);
    a = (a << s | a >>> -s);

    return a + b;
  }

  private static int GG(int a, int b, int c, int d, int k,
                        int s, int t)
   {

    a += k + t + G(b, c, d);
    a = (a << s | a >>> -s);

    return a + b;
  }

  private static int HH(int a, int b, int c, int d, int k,
                        int s, int t)
   {

    a += k + t + H(b, c, d);
    a = (a << s | a >>> -s);

    return a + b;
  }

  private int II(int a, int b, int c, int d, int k, int s,
                 int t)
   {

    a += k + t + I(b, c, d);
    a = (a << s | a >>> -s);

    return a + b;
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
       * Create a new MD5 and test data
       */
      RichMD5 md5 = new RichMD5();
      /*
       * Create a JDK MD5 algorithm
       */
      MessageDigest md5MD = MessageDigest.getInstance("MD5");

      for (int index = 0; index < testData.length; index++)
       {
        System.out.println("");
        System.out.println("MD5 Digesting...");
        System.out.println(testData[index][0]);

        byte[] testBytes = testData[index][0].getBytes();

        /*
         * Update the digest with data
         * normally the data can be updated
         * at different times
         */
        System.out.println("Test Length :" + testBytes.length);
        md5.MD5Update(testBytes, testBytes.length);

        byte[] digest = md5.MD5Final();

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
        System.out.println("MD5 Digesting with JDK...");
        System.out.println(testData[index][0]);

        byte[] testBytes = testData[index][0].getBytes();

        /*
         * Update the digest with data
         * normally the data can be updated
         * at different times
         */
        System.out.println("Test Length :" + testBytes.length);
        md5MD.update(testBytes, 0, testBytes.length);

        byte[] digest = md5MD.digest();

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
