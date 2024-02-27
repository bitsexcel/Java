package com.richware.chap05;



import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.math.BigInteger;

import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.spec.DHParameterSpec;

import sun.security.util.*;


/**
 * Class ECCParameters
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
public final class ECCParameters extends AlgorithmParametersSpi
 {

  /**
   * Constructor ECCParameters
   *
   *
   */
  public ECCParameters() {}

  protected void engineInit(
          AlgorithmParameterSpec algorithmparameterspec)
            throws InvalidParameterSpecException
   {

    if (!(algorithmparameterspec instanceof DHParameterSpec))
     {
      throw new InvalidParameterSpecException(
        "Inappropriate parameter specification");
    }
    else
     {
      a = ((DHParameterSpec) algorithmparameterspec).getP();
      b = ((DHParameterSpec) algorithmparameterspec).getG();
      c = ((DHParameterSpec) algorithmparameterspec).getL();

      return;
    }
  }

  protected void engineInit(byte abyte0[]) throws IOException
   {

    try
     {
      DerValue dervalue = new DerValue(abyte0);

      if (dervalue.tag != 48)
       {
        throw new IOException("DH params parsing error");
      }

      dervalue.data.reset();

      a = dervalue.data.getInteger().toBigInteger();
      b = dervalue.data.getInteger().toBigInteger();

      if (dervalue.data.available() != 0)
       {
        c = dervalue.data.getInteger().toInt();
      }

      if (dervalue.data.available() != 0)
       {
        throw new IOException(
          "DH parameter parsing error: Extra data");
      }
    }
    catch (NumberFormatException numberformatexception)
     {
      throw new IOException("Private-value length too big");
    }
  }

  protected void engineInit(byte abyte0[], String s)
          throws IOException
   {
    engineInit(abyte0);
  }

  protected AlgorithmParameterSpec engineGetParameterSpec(
          Class class1) throws InvalidParameterSpecException
   {

    try
     {
      Class class2 =
        Class.forName("javax.crypto.spec.DHParameterSpec");

      if (class2.isAssignableFrom(class1))
       {
        return new DHParameterSpec(a, b, c);
      }
      else
       {
        throw new InvalidParameterSpecException(
          "Inappropriate parameter Specification");
      }
    }
    catch (ClassNotFoundException classnotfoundexception)
     {
      throw new InvalidParameterSpecException(
        "Unsupported parameter specification: "
        + classnotfoundexception.getMessage());
    }
  }

  protected byte[] engineGetEncoded() throws IOException
   {

    DerOutputStream deroutputstream  = new DerOutputStream();
    DerOutputStream deroutputstream1 = new DerOutputStream();

    deroutputstream1.putInteger(new BigInt(a));
    deroutputstream1.putInteger(new BigInt(b));

    if (c > 0)
     {
      deroutputstream1
        .putInteger(new BigInt(BigInteger.valueOf(c)));
    }

    deroutputstream.write((byte) 48, deroutputstream1);

    return deroutputstream.toByteArray();
  }

  protected byte[] engineGetEncoded(String s)
          throws IOException
   {
    return engineGetEncoded();
  }

  protected String engineToString()
   {

    StringBuffer stringbuffer = new StringBuffer(
      "SunJCE Diffie-Hellman Parameters:\np:\n"
      + (new BigInt(a)).toString() + "\n" + "g:\n"
      + (new BigInt(b)).toString());

    if (c != 0)
     {
      stringbuffer.append("\nl:\n    " + c);
    }

    return stringbuffer.toString();
  }

  protected BigInteger a;
  protected BigInteger b;
  private int          c;
}


/*--- Formatted in Sun Java Convention Style on Tue, Mar 5, '02 ---*/


/*------ Formatted by Jindent 3.24 Basic 1.0 --- http://www.jindent.de ------*/
