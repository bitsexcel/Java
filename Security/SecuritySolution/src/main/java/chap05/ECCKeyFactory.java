package chap05;

import java.security.*;
import java.security.spec.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

/**
 * Class ECCKeyFactory
 * Description: The factory class for 
 * building and retrieving 
 * keys based on spec.
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
public final class ECCKeyFactory extends KeyFactorySpi
 {

  /**
   * Constructor ECCKeyFactory
   *
   *
   */
  public ECCKeyFactory() {}

  /*
   * Method engineGeneratePublic
   * Description: Builds puvblic key
   * from spec info
   *
   *
   */
  protected PublicKey engineGeneratePublic(KeySpec keyspec)
          throws InvalidKeySpecException
   {

    try
     {
      if (keyspec instanceof ECCPublicKeySpec)
       {
        ECCPublicKeySpec eccpublickeyspec =
          (ECCPublicKeySpec) keyspec;

        return new ECCPublicKey(eccpublickeyspec.getQY(),
                                eccpublickeyspec.getQX());
      }
      else
       {
        throw new InvalidKeySpecException(
          "Inappropriate key specification");
      }
    }
    /*
     * Catches
     */
    catch (InvalidKeyException invalidkeyexception)
     {
      throw new InvalidKeySpecException(
        "Inappropriate key specification");
    }
  }

  /*
   * Method engineGeneratePrivate
   * Description: Builds private key
   * from spec info
   *
   *
   */
  protected PrivateKey engineGeneratePrivate(KeySpec keyspec)
          throws InvalidKeySpecException
   {

    try
     {
      if (keyspec instanceof DHPrivateKeySpec)
       {
        ECCPrivateKeySpec eccprivatekeyspec =
          (ECCPrivateKeySpec) keyspec;

        return new ECCPrivateKey(eccprivatekeyspec.getD());
      }
      else
       {
        throw new InvalidKeySpecException(
          "Inappropriate key specification");
      }
    }
    /*
     * Catches
     */
    catch (InvalidKeyException invalidkeyexception)
     {
      throw new InvalidKeySpecException(
        "Inappropriate key specification");
    }
  }

  /*
   * Method engineGetKeySpec
   * Description: Gets the spec info
   * based on key type
   *
   *
   */
  protected KeySpec engineGetKeySpec(Key key, Class class1)
          throws InvalidKeySpecException
   {

    try
     {
      if (key instanceof ECCPublicKey)
       {
        Class class2 = Class
          .forName("ECCPublicKeySpec");

        if (class2.isAssignableFrom(class1))
         {
          ECCPublicKey     eccpublickey     =
            (ECCPublicKey) key;
          ECCParameterSpec eccparameterspec =
            eccpublickey.getParams();

          return new ECCPublicKeySpec(eccpublickey.getQY(),
                                      eccparameterspec.getP());
        }
        else
         {
          throw new InvalidKeySpecException(
            "Inappropriate key specification");
        }
      }

      if (key instanceof ECCPrivateKey)
       {
        Class class3 = Class
          .forName("ECCPrivateKeySpec");

        if (class3.isAssignableFrom(class1))
         {
          ECCPrivateKey    eccprivatekey     =
            (ECCPrivateKey) key;
          ECCParameterSpec eccparameterspec1 =
            eccprivatekey.getParams();

          return new ECCPrivateKeySpec(eccprivatekey.getD());
        }
        else
         {
          throw new InvalidKeySpecException(
            "Inappropriate key specification");
        }
      }
      else
       {
        throw new InvalidKeySpecException(
          "Inappropriate key type");
      }
    }
    /*
     * Catches
     */
    catch (ClassNotFoundException classnotfoundexception)
     {
      throw new InvalidKeySpecException(
        "Unsupported key specification: "
        + classnotfoundexception.getMessage());
    }
  }

  protected Key engineTranslateKey(Key key)
          throws InvalidKeyException
   {

    try
     {
      if (key instanceof ECCPublicKey)
       {
        if (key instanceof ECCPublicKey)
         {
          return key;
        }
        else
         {
          ECCPublicKeySpec eccpublickeyspec =
            (ECCPublicKeySpec) engineGetKeySpec(key, ECCPublicKeySpec.class);

          return engineGeneratePublic(eccpublickeyspec);
        }
      }

      if (key instanceof ECCPrivateKey)
       {
        if (key instanceof ECCPrivateKey)
         {
          return key;
        }
        else
         {
          ECCPrivateKeySpec eccprivatekeyspec =
            (ECCPrivateKeySpec) engineGetKeySpec(key, ECCPrivateKeySpec.class);

          return engineGeneratePrivate(eccprivatekeyspec);
        }
      }
      else
       {
        throw new InvalidKeyException("Wrong algorithm type");
      }
    }
    /*
     * Catches
     */
    catch (InvalidKeySpecException invalidkeyspecexception)
     {
      throw new InvalidKeyException("Cannot translate key");
    }
  }
}
