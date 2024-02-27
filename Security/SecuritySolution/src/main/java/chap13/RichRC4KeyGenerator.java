package com.richware.chap13;

import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


 /**
 * Class RichRC4KeyGenerator
 * Description: This is a example of a
 * simple RC4 Stream Encryption for Key Generator
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
public final class RichRC4KeyGenerator extends KeyGeneratorSpi
 {

  private SecureRandom random_;
  private int          keysize_ = 256;

  /**
   * Constructor RichRC4KeyGenerator
   *
   *
   */
  public RichRC4KeyGenerator() {}

  /**
   * Method engineInit
   *
   *
   * @param securerandom
   *
   */
  protected void engineInit(SecureRandom securerandom)
   {
    random_ = securerandom;
  }

  /**
   * Method engineInit
   *
   *
   * @param algorithmparameterspec
   * @param securerandom
   *
   * @throws InvalidAlgorithmParameterException
   *
   */
  protected void engineInit(
          AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
            throws InvalidAlgorithmParameterException
   {
    throw new InvalidAlgorithmParameterException(
      "Blowfish key generation does not take any parameters");
  }

  /**
   * Method engineInit
   *
   *
   * @param i
   * @param securerandom
   *
   */
  protected void engineInit(int i, SecureRandom securerandom)
   {

    if ((i % 8 != 0) || (i < 32) || (i > 448))
     {
      throw new InvalidParameterException(
        "Keysize must be multiple of 8, and can only range from 32 to 448 (inclusive)");
    }
    else
     {
      keysize_ = i / 8;

      engineInit(securerandom);

      return;
    }
  }

  /**
   * Method engineGenerateKey
   *
   *
   * @return
   *
   */
  protected SecretKey engineGenerateKey()
   {

    if (random_ == null)
     {
      random_ = new SecureRandom();
    }

    byte temp[] = new byte[keysize_];

    random_.nextBytes(temp);

    return new SecretKeySpec(temp, "RC4");
  }
}
