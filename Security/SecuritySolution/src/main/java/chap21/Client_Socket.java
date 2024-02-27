package com.richware.chap21;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Class Client_Socket
 * Description: An example client
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
public class Client_Socket{
    public static void main(String args[]) {
      try {
        /*
         * Create a socket on the local machine using port 9000
         */
        System.out.println("Starting Client_Server....");
        Socket m_socket = new Socket(InetAddress.getLocalHost(), 9000);

        /*
         * Serialize the Credit Card Number, Example only, no security
         */
        OutputStream out = m_socket.getOutputStream();
        ObjectOutput cc = new ObjectOutputStream(out);
        
        System.out.println("Sending Credit Card Number....");
        cc.writeObject("My Credit Card Number");
        cc.writeObject("4444-4444-4444-4444");
        cc.flush(); 
        cc.close();
        /*
         *  Catches
         */
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
  }
}
