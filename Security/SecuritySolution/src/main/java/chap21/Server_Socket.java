package com.richware.chap21;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Class Server_Socket
 * Description: An example server
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
public class Server_Socket {

    /**
     * The main driver, no args used.
     */
    public static void main(String args[]) {
      System.out.println("Starting Server_Socket....");
      ServerSocket  m_server = null;
      Socket  m_socket = null;
      try {
      /*
       * Listen to port 9000.
         */      
      m_server = new ServerSocket(9000);
      /*
       * This will wait for a connection is made from the client.
       * It returns the client socket.
         */      
      m_socket = m_server.accept();
      InputStream in = m_socket.getInputStream();
      ObjectInput cc = new ObjectInputStream(in);
      String m_header = (String) cc.readObject();
      String m_number = (String) cc.readObject();
      cc.close();
      
      // print out what wehas been  just received
      System.out.println(m_header);
      System.out.println(m_number);
      /*
       * Catches
       */
      } catch (Exception e) {
               e.printStackTrace();
        System.exit(1);
      }
    }
}
