package com.richware.chap13;

import java.security.*;


/**
 * Class RichProvider
 * Description: This is an example to
 * load a provider for ciphers.
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
public final class RichProvider extends java.security.Provider
 {

  private static final String NAME    = "RichWare";
  private static final double VERSION = 1.0;
  private static final String INFO    = "RichWare Ciphers";

  /**
   * Constructor RichProvider
   *
   *
   */
  public RichProvider()
   {

    super(NAME, VERSION, INFO);

    /*
     * Need to execute as a PrivileigedAction
     * for security reasons.
     */
    AccessController.doPrivileged(new PrivilegedAction()
     {

      public Object run()
       {

        put("Cipher.RSA", "com.richware.chap12.RichRSACipher");
        put("Cipher.RC4", "com.richware.chap13.RichRC4Cipher");
        put("KeyGenerator.RC4", "com.richware.chap13.RichRC4KeyGenerator");

        return null;
      }
    });
  }
}
