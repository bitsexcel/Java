package com.richware.chap19;



import java.security.*;

import java.util.*;

import javax.security.auth.*;
import javax.security.auth.login.*;


/**
 * Class JAASApp
 * Description: This is a Sample JAAS application
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
public class JAASApp
 {

  /**
   * Method main
   * Description: This is a Sample JAAS application
   * An example of running
   * java -Djava.security.manager 
   *   -Djava.security.auth.login.config =jaas.config 
   *   -Djava.security.policy=jaasapp.policy
   *
   * @param args none
   *
   */
  public static void main(String[] args)
   {

    LoginContext loginContext = null;

    try
     {
      loginContext =
        new LoginContext("RichJAAS",
                         new RichCallbackHandler());

      loginContext.login();
      System.out.println("\nLogin succeeded");
    }
    catch (LoginException le)
     {
      le.printStackTrace();
      System.out.println("\nLogin failed");
    }

    try
     {

      // Now we're logged in, so we can get the current subject.
      Subject subject = loginContext.getSubject();

      // Perform the example action as the authenticated subject.
      subject.doAs(subject, new JAASAction());
    }
    catch (Exception ex)
     {
      ex.printStackTrace();
    }
  }
} 
