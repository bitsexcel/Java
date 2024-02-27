package com.richware.chap05;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import java.math.BigInteger;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PublicKey;

import java.util.Arrays;

import javax.crypto.spec.DHParameterSpec;

import sun.security.util.BigInt;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

/**
 * Class ECCPublicKey
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

class ECCPublicKey implements PublicKey, Serializable
 {

  /**
   * Constructor ECCPublicKey
   *
   *
   * @param biginteger
   * @param biginteger1
   *
   * @throws InvalidKeyException
   *
   */
  public ECCPublicKey(
          BigInteger biginteger, BigInteger biginteger1)
            throws InvalidKeyException
   {
    this(biginteger, biginteger1, null, 0);
  }

  /**
   * Constructor ECCPublicKey
   *
   *
   * @param biginteger
   * @param biginteger1
   * @param biginteger2
   * @param i
   *
   * @throws InvalidKeyException
   *
   */
  public ECCPublicKey(
          BigInteger biginteger, BigInteger biginteger1, BigInteger biginteger2, int i)
            throws InvalidKeyException
   {

    y = biginteger;
    p = biginteger1;
    g = biginteger2;
    l = i;

    try
     {
      key        =
        (new DerValue((byte) 2,
                      y.toByteArray())).toByteArray();
      encodedKey = getEncoded();
    }
    catch (IOException ioexception)
     {
      throw new InvalidKeyException(
        "Cannot produce ASN.1 encoding");
    }
  }

  /**
   * Constructor ECCPublicKey
   *
   *
   * @param abyte0
   *
   * @throws InvalidKeyException
   *
   */
  public ECCPublicKey(byte abyte0[]) throws InvalidKeyException
   {

    ByteArrayInputStream bytearrayinputstream =
      new ByteArrayInputStream(abyte0);

    try
     {
      DerValue dervalue = new DerValue(bytearrayinputstream);

      if (dervalue.tag != 48)
       {
        throw new InvalidKeyException("Invalid key format");
      }

      DerValue dervalue1 = dervalue.data.getDerValue();

      if (dervalue1.tag != 48)
       {
        throw new InvalidKeyException(
          "AlgId is not a SEQUENCE");
      }

      DerInputStream   derinputstream   =
        dervalue1.toDerInputStream();
      ObjectIdentifier objectidentifier =
        derinputstream.getOID();

      if (derinputstream.available() == 0)
       {
        throw new InvalidKeyException("Parameters missing");
      }

      DerValue dervalue2 = derinputstream.getDerValue();

      if (dervalue2.tag == 5)
       {
        throw new InvalidKeyException("Null parameters");
      }

      if (dervalue2.tag != 48)
       {
        throw new InvalidKeyException(
          "Parameters not a SEQUENCE");
      }

      dervalue2.data.reset();

      p = dervalue2.data.getInteger().toBigInteger();
      g = dervalue2.data.getInteger().toBigInteger();

      if (dervalue2.data.available() != 0)
       {
        l = dervalue2.data.getInteger().toInt();
      }

      if (dervalue2.data.available() != 0)
       {
        throw new InvalidKeyException("Extra parameter data");
      }

      key = dervalue.data.getBitString();

      parseKeyBits();

      if (dervalue.data.available() != 0)
       {
        throw new InvalidKeyException("Excess key data");
      }

      encodedKey = (byte[]) abyte0.clone();
    }
    catch (NumberFormatException numberformatexception)
     {
      throw new InvalidKeyException(
        "Private-value length too big");
    }
    catch (IOException ioexception)
     {
      throw new InvalidKeyException(ioexception.toString());
    }
  }

  /**
   * Method getFormat
   *
   *
   * @return
   *
   */
  public String getFormat()
   {
    return "X.509";
  }

  /**
   * Method getAlgorithm
   *
   *
   * @return
   *
   */
  public String getAlgorithm()
   {
    return "DH";
  }

  /**
   * Method getEncoded
   *
   *
   * @return
   *
   */
  public synchronized byte[] getEncoded()
   {

    if (encodedKey == null)
     {
      try
       {
        DerOutputStream deroutputstream =
          new DerOutputStream();

        deroutputstream.putOID(new ObjectIdentifier(DH_data));

        DerOutputStream deroutputstream1 =
          new DerOutputStream();

        deroutputstream1.putInteger(new BigInt(p));
        deroutputstream1.putInteger(new BigInt(g));

        if (l != 0)
         {
          deroutputstream1.putInteger(new BigInt(l));
        }

        DerValue dervalue =
          new DerValue((byte) 48,
                       deroutputstream1.toByteArray());

        deroutputstream.putDerValue(dervalue);

        DerOutputStream deroutputstream2 =
          new DerOutputStream();

        deroutputstream2.write((byte) 48, deroutputstream);
        deroutputstream2.putBitString(key);

        DerOutputStream deroutputstream3 =
          new DerOutputStream();

        deroutputstream3.write((byte) 48, deroutputstream2);

        encodedKey = deroutputstream3.toByteArray();
      }
      catch (IOException ioexception)
       {
        return null;
      }
    }

    return (byte[]) encodedKey.clone();
  }

  /**
   * Method getQY
   *
   *
   * @return
   *
   */
  public BigInteger getQY()
   {
    return y;
  }

  /**
   * Method getQX
   *
   *
   * @return
   *
   */
  public BigInteger getQX()
   {
    return y;
  }

  /**
   * Method getParams
   *
   *
   * @return
   *
   */
  public ECCParameterSpec getParams()
   {

    if (l != 0)
     {
      return new ECCParameterSpec(p, g, l);
    }
    else
     {
      return new ECCParameterSpec(p, g);
    }
  }

  /**
   * Method toString
   *
   *
   * @return
   *
   */
  public String toString()
   {

    StringBuffer stringbuffer = new StringBuffer(
      "SunJCE Diffie-Hellman Public Key:\ny:\n"
      + (new BigInt(y)).toString() + "\n" + "p:\n"
      + (new BigInt(p)).toString() + "\n" + "g:\n"
      + (new BigInt(g)).toString());

    if (l != 0)
     {
      stringbuffer.append("\nl:\n    " + l);
    }

    return stringbuffer.toString();
  }

  private void parseKeyBits() throws InvalidKeyException
   {

    try
     {
      DerInputStream derinputstream = new DerInputStream(key);

      y = derinputstream.getInteger().toBigInteger();
    }
    catch (IOException ioexception)
     {
      throw new InvalidKeyException(ioexception.toString());
    }
  }

  /**
   * Method hashCode
   *
   *
   * @return
   *
   */
  public int hashCode()
   {

    int  i        = 0;
    byte abyte0[] = getEncoded();

    for (int j = 1; j < abyte0.length; j++)
     {
      i += abyte0[j] * j;
    }

    return i;
  }

  /**
   * Method equals
   *
   *
   * @param obj
   *
   * @return
   *
   */
  public boolean equals(Object obj)
   {

    if (this == obj)
     {
      return true;
    }

    if (!(obj instanceof PublicKey))
     {
      return false;
    }
    else
     {
      byte abyte0[] = getEncoded();
      byte abyte1[] = ((PublicKey) obj).getEncoded();

      return Arrays.equals(abyte0, abyte1);
    }
  }

  static final long  serialVersionUID = 0x6a219571f5151fdfL;
  private BigInteger y;
  private byte       key[];
  private byte       encodedKey[];
  private BigInteger p;
  private BigInteger g;
  private int        l;
  private int        DH_data[] =
   {
    1, 2, 840, 0x1bb8d, 1, 3, 1
  };
}


/*--- Formatted in Sun Java Convention Style on Tue, Mar 5, '02 ---*/


/*------ Formatted by Jindent 3.24 Basic 1.0 --- http://www.jindent.de ------*/