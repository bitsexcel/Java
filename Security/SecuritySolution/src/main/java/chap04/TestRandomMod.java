package chap04; 
import java.util.*;

import java.math.*;

import java.security.*;


/**
 * Class TestRandomMod
 * Description: This is an example of a
 * a random modular exponent
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
public class TestRandomMod
 {

  /**
   * Method main
   * Description: Main Driver
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
       * bitLength - bitLength of the returned BigInteger.
       * certainty - a measure of the uncertainty
       * that the caller is willing to tolerate.
       * The probability that the new BigInteger
       * represents a prime number will exceed (1 - 1/2certainty).
       * The execution time of this constructor is proportional
       * to the value of this parameter.
       * rnd - source of random bits used to
       * select candidates to be tested for primality.
       */
      int          bitLength = 512;  // 512 bits
      SecureRandom rnd       = new SecureRandom();
      int          certainty = 90;   // 1 - 1/2(90) certainty

      System.out.println("BitLength : " + bitLength);
      System.out
        .println("Selecting Prime Numbers..............");

      BigInteger mod = new BigInteger(bitLength, certainty,
                                      rnd);

      
      /* probablePrime
       * Returns a positive BigInteger
       * that is probably prime, with the
       * specified bitLength.
       * The probability that a BigInteger
       * returned by this method
       * is composite does not exceed 2-100.
       * Parameters:
       * bitLength - bitLength of the returned BigInteger.
       * rnd - source of random bits
       * used to select candidates to be
       * tested for primality.
       */
      BigInteger exponent = BigInteger.probablePrime(bitLength,
                              rnd);
      BigInteger n        = BigInteger.probablePrime(bitLength,
                              rnd);

      /* modPow
       * Returns a BigInteger whose
       * value is (thisexponent mod m).
       *(Unlike pow, this method permits negative exponents.)
       */
      BigInteger result = n.modPow(exponent, mod);

      System.out
        .println("Number ^ Exponent MOD Modulus = Result");
      System.out.println("Number*****************");
      System.out.println(n);
      System.out.println("Exponent***************");
      System.out.println(exponent);
      System.out.println("Modulus****************");
      System.out.println(mod);
      System.out.println("Result*****************");
      System.out.println(result);
    }
    catch (Exception ex)
     {
      ex.printStackTrace();
    }
  }
}
