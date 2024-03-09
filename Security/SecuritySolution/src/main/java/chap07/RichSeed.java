package chap07;

import java.security.*;

import java.io.*;


/**
 * Class RichSeed
 * Description: A custom demonstration of
 * SecureRandom.
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
public class RichSeed {

  /**
   * Method main
   * Description: The main driver to run the methods.
   *
   *
   * @param args (no arguments presently).
   *
   */
  public static void main(String args[]) {

    try {
      System.out.println("Seeding...This may take a minute...");
      
      SecureRandom ran = SecureRandom.getInstance("SHA1PRNG",
                                                  "SUN");

      ran.setSeed(101L);
      ran.setSeed(101L);

      byte[] seeds = ran.getSeed(24);

      for (int i = 0; i < seeds.length; i++) {
        System.out.println("Seed["+ i + "]:" + seeds[i]);
      }

      /*
       * Create the Serialized object
       */
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
