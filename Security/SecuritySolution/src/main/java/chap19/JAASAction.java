package com.richware.chap19;



import java.security.*;

import java.util.*;

import javax.security.auth.*;
import javax.security.auth.login.*;
import javax.security.auth.x500.*;

import java.io.*;


/**
 * Class JAASAction
 * Description: This is a Sample PrivilegedAction implementation
 * to read the secretinfo.txt file.
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
public class JAASAction implements PrivilegedAction
 {

  /**
   * Method run
   * Description: Run the printing of secret info
   *
   *
   * @return the running object
   *
   */
  public Object run()
   {

    try
     {
      printSecretText();

      return this;

      /*
       * Catches
       */
    }
    catch (Exception ex)
     {
      ex.printStackTrace();
    }

    return null;
  }

  /**
   * Method printSecretText
   * Description: Print the secret text from a file
   *
   *
   */
  public void printSecretText()
   {

    try
     {
      FileReader in   = new FileReader("secretinfo.txt");
      char[]     buff = new char[50];
      int        nch;

      while ((nch = in.read(buff, 0, buff.length)) != -1)
       {
        System.out.println(buff);
      }

      in.close();
    }
    catch (Exception ex)
     {
      ex.printStackTrace();
    }
  }
}
