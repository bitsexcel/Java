package com.richware.chap13;

import java.io.*;

import java.math.BigInteger;

import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;

import javax.crypto.*;

import sun.misc.*;


/**
 * Class RichRC4Cipher
 * Description: This is a example of a
 * simple RC4 Stream Encryption
 * RC4 (TM) was designed by Ron Rivest, and was previously a trade secret of
 * RSA Data Security, Inc. The algorithm is now in the public domain. The name
 * "RC4" is a trademark of RSA Data Security, Inc.
 *
 * Copyright:    Copyright (c) 2002
 * Company:      HungryMinds
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
public final class RichRC4Cipher extends CipherSpi
 {

  private SecureRandom random_;
  private int          opmode_;
  private Key          key_;
  private int          KEYSIZE = 128;

  /** Contents of the current set S-box. */
  private final int[] sBox = new int[256];

  /**
   * The two indices for the S-box computation referred to as i and j
   * in Schneier.
   */
  private int x, y;

  /**
   * The block size of this cipher. Being a stream cipher this value
   * is 1!
   */
  private static final int BLOCK_SIZE = 1;

  /**
   * Constructor RichRC4Cipher
   *
   *
   */
  public RichRC4Cipher() {}

  /**
   * Method engineDoFinal
   *
   *
   * @param input
   * @param inputOffset
   * @param inputLen
   *
   * @return
   *
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   *
   */
  protected byte[] engineDoFinal(
          byte[] input, int inputOffset, int inputLen)
            throws IllegalBlockSizeException,
                   BadPaddingException
   {

    byte[] output = engineUpdate(input, inputOffset, inputLen);

    return output;
  }

  /**
   * Method engineDoFinal
   *
   *
   * @param input
   * @param inputOffset
   * @param inputLen
   * @param output
   * @param outputOffset
   *
   * @return
   *
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   * @throws ShortBufferException
   *
   */
  protected int engineDoFinal(
          byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset)
            throws ShortBufferException,
                   IllegalBlockSizeException,
                   BadPaddingException
   {

    byte[] buffer;

    buffer = engineDoFinal(input, inputOffset, inputLen);

    if (output.length - outputOffset < buffer.length)
     {
      throw new ShortBufferException(
        "Output longer than buffer");
    }

    System.arraycopy(buffer, 0, output, outputOffset,
                     buffer.length);

    return buffer.length;
  }

  /**
   * Method engineGetBlockSize
   *
   *
   * @return
   *
   */
  protected int engineGetBlockSize()
   {
    return BLOCK_SIZE;
  }

  /**
   * Method engineGetIV
   *
   *
   * @return
   *
   */
  protected byte[] engineGetIV()
   {
    return null;  // If not supported
  }

  /**
   * Method engineGetKeySize
   *
   *
   * @param _key
   *
   * @return
   *
   * @throws InvalidKeyException
   *
   */
  protected int engineGetKeySize(Key _key)
          throws InvalidKeyException
   {

    /*
     * Get the key size based on bit length
     */
    if (_key instanceof SecretKey)
     {
      byte[] k = _key.getEncoded();

      return k.length;
    }

    throw new InvalidKeyException(
      "Unsupported RC4 key, must be a Secret Key!");
  }

  /**
   * Method engineGetOutputSize
   *
   *
   * @param inputLen
   *
   * @return
   *
   */
  protected int engineGetOutputSize(int inputLen)
   {
    return inputLen;
  }

  /**
   * Method engineGetParameters
   *
   *
   * @return
   *
   */
  protected AlgorithmParameters engineGetParameters()
   {
    return null;
  }

  /**
   * Method engineInit
   *
   *
   * @param opmode
   * @param _key
   * @param params
   * @param _random
   *
   * @throws InvalidAlgorithmParameterException
   * @throws InvalidKeyException
   *
   */
  protected void engineInit(
          int opmode, Key _key, AlgorithmParameterSpec params, SecureRandom _random)
            throws InvalidKeyException,
                   InvalidAlgorithmParameterException
   {

    // Check for valid key
    if (!(_key instanceof SecretKey))
     {
      throw new InvalidKeyException("Unsupported RC4 Key!");
    }

    prepare_key(_key);

    random_ = _random;

    // Check for valid types of opmode
    if ((opmode == Cipher.DECRYPT_MODE)
            || (opmode == Cipher.ENCRYPT_MODE)
            || (opmode == Cipher.UNWRAP_MODE)
            || (opmode == Cipher.WRAP_MODE))
     {
      opmode_ = opmode;
    }
    else
     {
      throw new InvalidKeyException("Unsupported opmode!");
    }

    key_ = _key;
  }

  /**
   * Method engineInit
   *
   *
   * @param opmode
   * @param _key
   * @param params
   * @param _random
   *
   * @throws InvalidAlgorithmParameterException
   * @throws InvalidKeyException
   *
   */
  protected void engineInit(
          int opmode, Key _key, AlgorithmParameters params, SecureRandom _random)
            throws InvalidKeyException,
                   InvalidAlgorithmParameterException
   {

    /*
     * Note _key is used instead of key, becuase Key is a class.
     * Random is also a class.
     */
    engineInit(opmode, _key, (AlgorithmParameterSpec) null,
               _random);
  }

  /**
   * Method engineInit
   *
   *
   * @param opmode
   * @param _key
   * @param _random
   *
   * @throws InvalidKeyException
   *
   */
  protected void engineInit(
          int opmode, Key _key, SecureRandom _random)
            throws InvalidKeyException
   {

    try
     {
      engineInit(opmode, _key, (AlgorithmParameterSpec) null,
                 _random);
    }
    catch (InvalidAlgorithmParameterException ex)
     {
      throw new InvalidKeyException(ex.getMessage());
    }
  }

  /**
   * Method engineSetMode
   *
   *
   * @param mode
   *
   * @throws NoSuchAlgorithmException
   *
   */
  protected void engineSetMode(String mode)
          throws NoSuchAlgorithmException
   {
    throw new NoSuchAlgorithmException(
      "Stream Cipher, doesn't specify a mode");
  }

  /**
   * Method engineSetPadding
   *
   *
   * @param s
   *
   * @throws NoSuchPaddingException
   *
   */
  protected void engineSetPadding(String s)
          throws NoSuchPaddingException
   {
    throw new NoSuchPaddingException(
      "Padding not used iin stream cipher");
  }

  /**
   * Method engineUpdate
   *
   *
   * @param input
   * @param inputOffset
   * @param inputLen
   *
   * @return
   *
   */
  protected byte[] engineUpdate(byte[] input, int inputOffset,
                             int inputLen)
   {

    try
     {
      byte[] tmp =
        new byte[this.engineGetOutputSize(inputLen)];

      engineUpdate(input, inputOffset, inputLen, tmp, 0);

      return tmp;
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
   * Method engineUpdate
   *
   *
   * @param input
   * @param inputOffset
   * @param inputLen
   * @param output
   * @param outputOffset
   *
   * @return
   *
   * @throws ShortBufferException
   *
   */
  protected int engineUpdate(
          byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset)
            throws ShortBufferException
   {

    int bufSize = output.length - outputOffset;

    if (bufSize < inputLen)
     {
      throw new ShortBufferException(
        "Output longer than buffer");
    }

    rc4(input, inputOffset, inputLen, output, outputOffset);

    return inputLen;
  }

  /**
   * Method rc4
   * Description: performs encryption
   * and decryption
   *
   * @param in the in buffer
   * @param inOffset any in offest
   * @param inLen the length of the in buffer
   * @param out the out buffer
   * @param outOffset any out offset
   *
   */
  protected void rc4(byte[] in, int inOffset, int inLen,
                  byte[] out, int outOffset)
   {

    /*
     * The byte is XORed with the plaintext to produce the ciphertext
     * The byte is XORed with the ciphertext to produce the plaintext
     * The algorithm is symmetric, meaning this function will work for both
     * encryption and decryption
     */
    int xorIndex, temp;

    for (int i = 0; i < inLen; i++)
     {
      x                = (x + 1) & 0xFF;
      y                = (sBox[x] + y) & 0xFF;
      temp             = sBox[x];
      sBox[x]          = sBox[y];
      sBox[y]          = temp;
      xorIndex         = (sBox[x] + sBox[y]) & 0xFF;
      out[outOffset++] = (byte) (in[inOffset++]
                                 ^ sBox[xorIndex]);
    }
  }

  /**
   * Method prepare_key
   * Description: initializes the key
   *
   *
   * @param key the key that will set the S-box.
   *
   * @throws InvalidKeyException
   *
   */
  protected void prepare_key(Key key) throws InvalidKeyException
   {

    /*
     * Fill the S-box with the key
     * Key Setup
     */
    byte[] userkey = key.getEncoded();

    if (userkey == null)
     {
      throw new InvalidKeyException("Null user key");
    }

    int len = userkey.length;

    if (len == 0)
     {
      throw new InvalidKeyException(
        "Invalid user key length");
    }

    /*
     * Reset x and y
     */
    x = y = 0;

    for (int index = 0; index < 256; index++)
     {
      sBox[index] = index;
    }

    int index1 = 0, index2 = 0, temp;

    for (int counter = 0; counter < 256; counter++)
     {
      index2 =
        ((userkey[index1] & 0xFF) + sBox[counter] + index2)
        & 0xFF;

      /*
       * Swap the byte
       */
      temp          = sBox[counter];
      sBox[counter] = sBox[index2];
      sBox[index2]  = temp;
      index1        = (index1 + 1) % len;
    }
  }
}
