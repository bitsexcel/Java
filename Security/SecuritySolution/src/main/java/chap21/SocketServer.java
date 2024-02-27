package com.richware.chap21;

import java.io.*;
import java.net.*;

/** 
 * Class SocketServer
 * Description: Demostrates a listener for connections.
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
public class SocketServer extends Thread {

 protected Socket socket;

 /**
 * Port number is set to 2000 to avoid the protocols below 1024.
 */
 final static int PORT_NUM = 2000;

 /**
 * True to only accept connections from the local machine.
 */
 final static boolean LOCAL_ONLY = false;

 /**
 * Constructor SocketServer
 * Description: Creates a new Server for Sockets.
 *
 * @param socket, the client socket to run.
 */
 SocketServer(Socket socket) {
   this.socket = socket;
 }

 /**
* Method run
* Description: A thread to send some data and read a response.
 *
 */
 public void run() {
    try {
       /**
       * For exiting the server
       */
       boolean isExit = false; 	
       /**
       * The in receives the data from the client
       */
       InputStream in = socket.getInputStream();
       /**
       * The out sends the data to the client
       */
       OutputStream out = socket.getOutputStream();
       System.out.println("Sending Some Data...");
       out.write("Send Some Data\r\n".getBytes());
       byte[] buffer = new byte[1024];

       int    read;
       while (((read = in.read(buffer)) >= 0) && !isExit) {
       	  String readString = new String(buffer);
          System.out.println("Read Data:" + new String(buffer));
          /**
           * Reset the contents of the buffer
           */
          for(int i = 0; i < 1024; i++){
             buffer[i] = 0;
          }	
          if(readString.equalsIgnoreCase("Exit")){
            isExit = true;
          }	
          System.out.println("Exiting Client Connection");
       }
      } catch (IOException ex) {
          ex.printStackTrace();
      } finally {
         try {
            socket.close();
         } catch (IOException ignored) {}
      }
 }

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
    ServerSocket server = null;
    System.out.println("Starting SocketServer....");

    /**
    * If local, only receive connections from the local host
    */
    if (LOCAL_ONLY) {
       InetAddress address = InetAddress.getLocalHost();
       server = new ServerSocket(PORT_NUM, 10, address);
    } else {
       server = new ServerSocket(PORT_NUM);
    }
    System.out.println("SocketServer Listening....");
    while (true) {
       System.out.println("Waiting for Connection....");
       /**
       * Accept a new client
       */
       Socket client = server.accept();
       /**
       * Start the current class;
       */
       SocketServer socketServer = new SocketServer(client);
       /**
       * Start the Thread
       */
       socketServer.start();
      }
    }
}
