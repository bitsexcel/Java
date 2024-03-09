package chap05;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.util.Arrays;

import sun.security.util.BigInt;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;


/**
 * Class ECCPrivateKey
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
class ECCPrivateKey implements PrivateKey, Serializable
 {

  /**
   * Constructor ECCPrivateKey
   *
   *
   * @param d
   *
   * @throws InvalidKeyException
   *
   */
  public ECCPrivateKey(BigInteger d) throws InvalidKeyException
   {
    this(d, null, null, 0);
  }

  /**
   * Constructor ECCPrivateKey
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
  public ECCPrivateKey(
          BigInteger biginteger, BigInteger biginteger1, BigInteger biginteger2, int i)
            throws InvalidKeyException
   {

    x = biginteger;
    p = biginteger1;
    g = biginteger2;
    l = i;

    try
     {
      key        =
        (new DerValue((byte) 2,
                      x.toByteArray())).toByteArray();
      encodedKey = getEncoded();
    }
    catch (IOException ioexception)
     {
      InvalidKeyException invalidkeyexception =
        new InvalidKeyException(
          "Cannot produce ASN.1 encoding");

      invalidkeyexception.initCause(ioexception);

      throw invalidkeyexception;
    }
  }

  /**
   * Constructor ECCPrivateKey
   *
   *
   * @param abyte0
   *
   * @throws InvalidKeyException
   *
   */
  public ECCPrivateKey(byte abyte0[])
          throws InvalidKeyException
   {

    ByteArrayInputStream bytearrayinputstream =
      new ByteArrayInputStream(abyte0);

    try
     {
      DerValue dervalue = new DerValue(bytearrayinputstream);

      if (dervalue.tag != 48)
       {
        throw new InvalidKeyException("Key not a SEQUENCE");
      }

      BigInteger biginteger =
        dervalue.data.getInteger().toBigInteger();

      if (!biginteger.equals(PKCS8_VERSION))
       {
        throw new IOException("version mismatch: (supported: "
                              + PKCS8_VERSION + ", parsed: "
                              + biginteger);
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

      key = dervalue.data.getOctetString();

      parseKeyBits();

      encodedKey = (byte[]) abyte0.clone();
    }
    catch (NumberFormatException numberformatexception)
     {
      InvalidKeyException invalidkeyexception =
        new InvalidKeyException(
          "Private-value length too big");

      invalidkeyexception.initCause(numberformatexception);

      throw invalidkeyexception;
    }
    catch (IOException ioexception)
     {
      InvalidKeyException invalidkeyexception1 =
        new InvalidKeyException(ioexception.getMessage());

      invalidkeyexception1.initCause(ioexception);

      throw invalidkeyexception1;
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
    return "PKCS#8";
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

        deroutputstream
          .putInteger(new BigInt(PKCS8_VERSION.toByteArray()));

        DerOutputStream deroutputstream1 =
          new DerOutputStream();

        deroutputstream1.putOID(new ObjectIdentifier(DH_data));

        DerOutputStream deroutputstream2 =
          new DerOutputStream();

        deroutputstream2.putInteger(new BigInt(p));
        deroutputstream2.putInteger(new BigInt(g));

        if (l != 0)
         {
          deroutputstream2.putInteger(new BigInt(l));
        }

        DerValue dervalue =
          new DerValue((byte) 48,
                       deroutputstream2.toByteArray());

        deroutputstream1.putDerValue(dervalue);
        deroutputstream.write((byte) 48, deroutputstream1);
        deroutputstream.putOctetString(key);

        DerOutputStream deroutputstream3 =
          new DerOutputStream();

        deroutputstream3.write((byte) 48, deroutputstream);

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
   * Method getD
   *
   *
   * @return
   *
   */
  public BigInteger getD()
   {
    return x;
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
      "SunJCE Diffie-Hellman Private Key:\nx:\n"
      + (new BigInt(x)).toString() + "\n" + "p:\n"
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

      x = derinputstream.getInteger().toBigInteger();
    }
    catch (IOException ioexception)
     {
      InvalidKeyException invalidkeyexception =
        new InvalidKeyException(ioexception.getMessage());

      invalidkeyexception.initCause(ioexception);

      throw invalidkeyexception;
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

    if (!(obj instanceof PrivateKey))
     {
      return false;
    }
    else
     {
      byte abyte0[] = getEncoded();
      byte abyte1[] = ((PrivateKey) obj).getEncoded();

      return Arrays.equals(abyte0, abyte1);
    }
  }

  static final long               serialVersionUID =
    0x68fdf9c8d4775c16L;
  private static final BigInteger PKCS8_VERSION    =
    BigInteger.valueOf(0L);
  private BigInteger              x;
  private byte                    key[];
  private byte                    encodedKey[];
  private BigInteger              p;
  private BigInteger              g;
  private int                     l;
  private int                     DH_data[] =
   {
    1, 2, 840, 0x1bb8d, 1, 3, 1
  };
}


/*--- Formatted in Sun Java Convention Style on Tue, Mar 5, '02 ---*/


/*------ Formatted by Jindent 3.24 Basic 1.0 --- http://www.jindent.de ------*/
