package com.richware.chap05;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.DHGenParameterSpec;

/**
 * Class ECCKeyPairGenerator
 * Description: The generator for the
 * key pair for ECC 
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
public final class ECCKeyPairGenerator
        extends KeyPairGeneratorSpi
 {

  private BigInteger   a;
  private BigInteger   b;
  private BigInteger   c;
  private BigInteger   d;
  private int          e;
  private int          f;
  private SecureRandom g;

  /**
   * Constructor ECCKeyPairGenerator
   *
   *
   */
  public ECCKeyPairGenerator()
   {
    e = 1024;
  }

  /**
   * Method initialize
   * Description: Initialiazes the
   * key bit size and random generator
   *
   *
   * @param i
   * @param securerandom
   *
   */
  public void initialize(int i, SecureRandom securerandom)
   {

    if ((i < 512) || (i > 1024) || (i % 64 != 0))
     {
      throw new InvalidParameterException(
        "Keysize must be multiple of 64, and can only range from 512 to 1024 (inclusive)");
    }
    else
     {
      e = i;
      f = 0;
      g = securerandom;

      return;
    }
  }
  /**
   * Method initialize
   * Description: Initialiazes the
   * key bit size and keys based on the
   * algorithm spec
   *
   *
   * @param algorithmparameterspec
   * @param securerandom
   *
   * @throws InvalidAlgorithmParameterException
   *
   */
  public void initialize(
          AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
            throws InvalidAlgorithmParameterException
   {

    if (!(algorithmparameterspec instanceof ECCParameterSpec))
     {
      throw new InvalidAlgorithmParameterException(
        "Inappropriate parameter type");
    }

    c = ((ECCParameterSpec) algorithmparameterspec).getP();
    e = c.bitLength();

    if ((e < 512) || (e > 1024) || (e % 64 != 0))
     {
      throw new InvalidAlgorithmParameterException(
        "Prime size must be multiple of 64, and can only range from 512 to 1024 (inclusive)");
    }

    d = ((ECCParameterSpec) algorithmparameterspec).getG();
    f = ((ECCParameterSpec) algorithmparameterspec).getL();
    g = securerandom;

    if ((f != 0) && (f >= e))
     {
      throw new InvalidAlgorithmParameterException(
        "Exponent size must be less than modulus size");
    }
    else
     {
      return;
    }
  }
  /**
   * Method generateKeyPair
   * Description: Generates the key pair
   *
   *
   * @return the Key pair generated
   *
   */
  public KeyPair generateKeyPair()
   {

    KeyPair keypair = null;

    if (f == 0)
     {
      f = e - 1;
    }

    if (g == null)
     {
      g = new SecureRandom();
    }

    try
     {
      if ((c == null) || (d == null))
       {
        ECCGenParameterSpec   eccgenparameterspec   =
          new ECCGenParameterSpec(e);
        ECCParameterGenerator eccparametergenerator =
          new ECCParameterGenerator();

        eccparametergenerator.engineInit(eccgenparameterspec,
                                         null);

        AlgorithmParameters algorithmparameters =
          eccparametergenerator.engineGenerateParameters();
        ECCParameterSpec    ECCParameterSpec    =
          (ECCParameterSpec) algorithmparameters
            .getParameterSpec(ECCParameterSpec.class);

        c = ECCParameterSpec.getP();
        d = ECCParameterSpec.getG();
      }

      b = new BigInteger(f, g);
      a = d.modPow(b, c);

      ECCPublicKey  eccpublickey  = new ECCPublicKey(a, c, d,
                                      f);
      ECCPrivateKey eccprivatekey = new ECCPrivateKey(b, c, d,
                                      f);

      keypair = new KeyPair(eccpublickey, eccprivatekey);
    }
    /*
     * Catches
     */
    catch (InvalidAlgorithmParameterException invalidalgorithmparameterexception)
     {
      throw new RuntimeException(
        invalidalgorithmparameterexception.getMessage());
    }
    catch (InvalidParameterSpecException invalidparameterspecexception)
     {
      throw new RuntimeException(invalidparameterspecexception
        .getMessage());
    }
    catch (InvalidKeyException invalidkeyexception)
     {
      throw new RuntimeException(invalidkeyexception
        .getMessage());
    }

    return keypair;
  }
}
