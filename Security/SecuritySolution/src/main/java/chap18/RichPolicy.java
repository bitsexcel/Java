package com.richware.chap18;
import java.security.*;

import java.io.*;


/**
 * Class RichGuard
 * Description: A custom demonstration of
 * printing out default policies.
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
public class RichPolicy {

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
      System.out.println("Starting RichPolicy.....");
      /*
       * Get the Policy
       */
       Policy localPolicy = Policy.getPolicy();
      /*
       * Get the CodeSource
       * Shown here is an empyt CodeSource
       */
       CodeSource codesource = new CodeSource(null,null);
      /*
       * Get the Permission Collection
       * from the CodeSource
       */
       PermissionCollection permissioncollection = localPolicy.getPermissions(codesource);
      /*
       * Get the current ClassLoader
       */
       ClassLoader loader = ClassLoader.getSystemClassLoader();
      /*
       * Get the ProtectionDomain
       * from the CodeSource & Permission Collection
       */
       ProtectionDomain protectiondomain = new ProtectionDomain(codesource, permissioncollection);
      /*
       * Get the current Security Manager
       */
       SecurityManager sm = System.getSecurityManager();
       System.out.println("********Security Manager**********");
       System.out.println(sm);
       System.out.println("********CodeSource**********");
       System.out.println(codesource);
       System.out.println("********ClassLoader*********");
       System.out.println(loader);
       System.out.println("********Protection Domain********");
       System.out.println(protectiondomain);
       System.out.println("********Permissions*********");
       System.out.println(permissioncollection);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}