package com.richware.chap19;



import java.io.*;

import java.security.*;

import javax.security.auth.*;
import javax.security.auth.callback.*;


/**
 * Class RichCallbackHandler
 * Description: This is a Sample Callback Handler
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
public class RichCallbackHandler implements CallbackHandler
 {

  /**
   * Constructor RichCallbackHandler
   *
   *
   */
  public RichCallbackHandler() {}

  /**
   * Method handle
   * Description: Retrieve or display the information requested in the
   * provided Callbacks.
   *
   *
   * @param callbacks - an array of Callback objects provided by an underlying
   * security service which contains the information requested to be retrieved
   * or displayed.
   * @throws IOException  - if an input or output error occurs.
   * @throws UnsupportedCallbackException - if the implementation of this
   * method does not support one or more of the Callbacks specified in the
   * callbacks parameter.
   *
   */
  public void handle(Callback[] callbacks)
          throws IOException, UnsupportedCallbackException
   {

    for (int i = 0; i < callbacks.length; i++)
     {
      if (callbacks[i] instanceof TextOutputCallback)
       {

        /*
         * Display the message according to the specified type
         */
        TextOutputCallback toc =
          (TextOutputCallback) callbacks[i];

        switch (toc.getMessageType())
         {

        case TextOutputCallback.INFORMATION :
          System.out.println(toc.getMessage());
          break;

        case TextOutputCallback.ERROR :
          System.out.println("ERROR: " + toc.getMessage());
          break;

        case TextOutputCallback.WARNING :
          System.out.println("WARNING: " + toc.getMessage());
          break;

        default :
          throw new IOException("Unsupported message type: "
                                + toc.getMessageType());
        }
      }
      else if (callbacks[i] instanceof NameCallback)
       {

        // Prompt the user for the username
        NameCallback nc = (NameCallback) callbacks[i];

        System.err.print(nc.getPrompt());
        System.err.flush();
        nc.setName(
          (new BufferedReader(
            new InputStreamReader(System.in))).readLine());
      }
      else if (callbacks[i] instanceof PasswordCallback)
       {

        /*
         * Prompt the user for the username
         */
        PasswordCallback pc = (PasswordCallback) callbacks[i];

        System.err.print(pc.getPrompt());
        System.err.flush();

        /*
         * Note: JAAS specifies that the password is a char[] rather than a String
         */
        String tmpPassword =
          (new BufferedReader(new InputStreamReader(System
            .in))).readLine();
        int    passLen     = tmpPassword.length();
        char[] password    = new char[passLen];

        for (int passIdx = 0; passIdx < passLen; passIdx++)
         {
          password[passIdx] = tmpPassword.charAt(passIdx);
        }

        pc.setPassword(password);
      }

      /*
       * Confirmation callbeack for KeyStore
       */
      else if (callbacks[i] instanceof ConfirmationCallback)
       {

        // Prompt the user for the username
        ConfirmationCallback nc =
          (ConfirmationCallback) callbacks[i];
      }
      else
       {
        throw new UnsupportedCallbackException(
          callbacks[i], "Unrecognized Callback");
      }
    }
  }
}
