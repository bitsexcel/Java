package com.richware.chap21;

import java.io.*;
import java.net.*;

/**
* Class SocketClient
* Description: Demostrates a client connection to a Socket Server.
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
public class SocketClient extends Thread {
    protected Socket socket;
    /**
    * Port number is set to 2000 to avoid the protocols below 1024.
    */
    final static int PORT_NUM = 2000;

    /**
    True to only accept connections from the local machine.
    */
    final static boolean LOCAL_ONLY = true;

    /**
    * Constructor SocketClient
    * Description: Creates a new Socket to communicate with the Server.
    *
    */
    SocketClient() {}

    /**
    * Method main
    *
    *
    * @param args the standard arguments for a main driver.
    *
    * @throws IOException to propagate from the any IO exceptions for sockets.
    *
    */
    public static void main(String[] args) throws IOException {
       Socket socket = null;
       System.out.println("Starting SocketClient....");
       /**
       * If local, only receive connections from the local host
       */
       if (LOCAL_ONLY) {
          /**
          * Communicate with the local server at port 2000.
          */
          InetAddress address = InetAddress.getLocalHost();
          socket = new Socket(address, PORT_NUM);
       } else {
            /**
            * Communicate with the server on myserver1 at port 2000.
            */
            InetAddress address =
               InetAddress.getByName("myserver1.richware.com");
            socket = new Socket(address, PORT_NUM);
       }

       /**
       * The in receives the data from the client
       */
       InputStream in     = socket.getInputStream();
       OutputStream out = socket.getOutputStream();
       byte[]      buffer = new byte[1024];
       int         read;
       if ((read = in.read(buffer)) >= 0) {
          System.out.println("Socket Read:" + new String(buffer));
       }

        /**
         * The out sends the data to the client
         */
        System.out.println("Sending Data");
        out.write("Received Data\r\n".getBytes());
        /**
         * The out sends the data to the client
         */
        System.out.println("Sending Exit Server");
        out.write("EXIT".getBytes());

        /**
        * Close the socket
        */
        socket.close();
    }
}
