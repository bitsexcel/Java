package com.richware.chap05;



import java.security.*;
import java.security.spec.*;

import javax.crypto.spec.DHGenParameterSpec;
import javax.crypto.spec.DHParameterSpec;


/**
 * Class ECCParameterGenerator
 *
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
public final class ECCParameterGenerator
        extends AlgorithmParameterGeneratorSpi
 {

  /**
   * Constructor ECCParameterGenerator
   *
   *
   */
  public ECCParameterGenerator()
   {
    a = 1024;
  }

  protected void engineInit(int i, SecureRandom securerandom)
   {

    if ((i < 512) || (i > 1024) || (i % 64 != 0))
     {
      throw new InvalidParameterException(
        "Keysize must be multiple of 64, and can only range from 512 to 1024 (inclusive)");
    }
    else
     {
      a = i;
      c = securerandom;

      return;
    }
  }

  protected void engineInit(
          AlgorithmParameterSpec algorithmparameterspec, SecureRandom securerandom)
            throws InvalidAlgorithmParameterException
   {

    if (!(algorithmparameterspec
          instanceof DHGenParameterSpec))
     {
      throw new InvalidAlgorithmParameterException(
        "Inappropriate parameter type");
    }

    DHGenParameterSpec dhgenparameterspec =
      (DHGenParameterSpec) algorithmparameterspec;

    a = dhgenparameterspec.getPrimeSize();

    if ((a < 512) || (a > 1024) || (a % 64 != 0))
     {
      throw new InvalidAlgorithmParameterException(
        "Modulus size must be multiple of 64, and can only range from 512 to 1024 (inclusive)");
    }

    b = dhgenparameterspec.getExponentSize();

    if (b <= 0)
     {
      throw new InvalidAlgorithmParameterException(
        "Exponent size must be greater than zero");
    }

    if (b >= a)
     {
      throw new InvalidAlgorithmParameterException(
        "Exponent size must be less than modulus size");
    }
    else
     {
      return;
    }
  }

  protected AlgorithmParameters engineGenerateParameters()
   {

    AlgorithmParameters algorithmparameters = null;

    if (b == 0)
     {
      b = a - 1;
    }

    if (c == null)
     {
      c = new SecureRandom();
    }

    try
     {
      AlgorithmParameterGenerator algorithmparametergenerator =
        AlgorithmParameterGenerator.getInstance("DSA");

      algorithmparametergenerator.init(a);

      algorithmparameters =
        algorithmparametergenerator.generateParameters();

      DSAParameterSpec dsaparameterspec =
        (DSAParameterSpec) algorithmparameters
          .getParameterSpec(java.security.spec
            .DSAParameterSpec.class);
      DHParameterSpec  dhparameterspec;

      if (b > 0)
       {
        dhparameterspec =
          new DHParameterSpec(dsaparameterspec.getP(),
                              dsaparameterspec.getG(), b);
      }
      else
       {
        dhparameterspec =
          new DHParameterSpec(dsaparameterspec.getP(),
                              dsaparameterspec.getG());
      }

      algorithmparameters =
        AlgorithmParameters.getInstance("DH", "SunJCE");

      algorithmparameters.init(dhparameterspec);
    }
    catch (InvalidParameterSpecException invalidparameterspecexception)
     {
      throw new RuntimeException(invalidparameterspecexception
        .getMessage());
    }
    catch (NoSuchAlgorithmException nosuchalgorithmexception)
     {
      throw new RuntimeException(nosuchalgorithmexception
        .getMessage());
    }
    catch (NoSuchProviderException nosuchproviderexception)
     {
      throw new RuntimeException(nosuchproviderexception
        .getMessage());
    }

    return algorithmparameters;
  }

  private int          a;
  private int          b;
  private SecureRandom c;
}


/*--- Formatted in Sun Java Convention Style on Tue, Mar 5, '02 ---*/


/*------ Formatted by Jindent 3.24 Basic 1.0 --- http://www.jindent.de ------*/
