package com.richware.chap18;
import java.security.*;

import java.io.*;


/**
 * Class RichGuard
 * Description: A custom demonstration of
 * guarding an object. This code is the requestor.
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
public class RichGuard {

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
      /*
       * Combine the userdirectory + package name + input file
       * to find the file and where it's location should be
       */
      System.out.println("Starting RichGuard....");
      String localDirectory  = System.getProperty("user.dir");
      System.out.println("Changing directory to Chapter 11"); 
      System.setProperty("user.dir",localDirectory + "\\com\\richware\\chap18\\");
      localDirectory  = System.getProperty("user.dir");
      String localInputFile  = localDirectory + args[0];
      System.out.println("Openining Chapter 14 plus the input file as an argument: " + localInputFile); 

      /*
       * Create the protected object
       * as a FileInputStream object
       */
      FileInputStream protectedObject =
        new FileInputStream(localInputFile);

      /*
       * Create the guard object
       * as a FilePermssion for
       * what is needed to access the object.
       */
      FilePermission guard = new FilePermission(localInputFile,
                                                "read");

      /*
       * Create the guarded object
       * which is an association between the
       * requestor, guard and protected object
       */
      GuardedObject guardedObject =
        new GuardedObject(protectedObject, guard);

      /*
       * get the object so this is
       * the requestor object
       * this will call the checkGuard
       * and Premission.checkPermission()
       */
      Object o = guardedObject.getObject();

      System.out.println("Got access to object");

      /*
       *   catches.
       */
    } catch (AccessControlException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}