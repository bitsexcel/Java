package com.richware.chap23;



import java.io.*;

import java.net.*;

import java.security.*;

import javax.net.*;
import javax.net.ssl.*;

import javax.security.cert.*;


/**
 * Class SSLServer
 * This example demonstrates a JSSE server with context creation.
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
public class SSLServer extends Thread
 {

  private final static int port = 25001;
  Socket                   sock;
  String                   docroot = "C:\\";
  final static String      debug   = "none";

  /**
   * Method main
   * The main driver for the server.
   *
   * @param args none needed
   *
   * @throws Exception if there is an issue.
   *
   */
  public static void main(String[] args) throws Exception
   {

    try
     {
      System.out.println("Starting SSLServer....");

      /*
       * Pass in the argumkent of the keystore file
       * It will be openened in the same directoy as the application
       */
      if (args[0] == null)
       {
        System.out.println(
          "This application requires an input file for the location of the keystore");
      }

      String localDirectory = System.getProperty("user.dir");

      System.out.println("Changing directory to Chapter 23");
      System.setProperty("user.dir",
                         localDirectory
                         + "\\com\\richware\\chap23\\");

      localDirectory = System.getProperty("user.dir");

      /*
       * Get the local keystore that contains a trusted certificate
       */
      String localInputFile = localDirectory + args[0];

      System.out.println(
        "Openining Chapter 23 plus the input file as an argument: "
        + localInputFile);

      /*
       * Set the SSL internal debugger to none
       * Set it to all for all connections
       */
      System.out.println("Setting SSL debugging to :" + debug);
      System.setProperty("javax.net.debug", debug);

      ServerSocketFactory ssf =
        SSLServer.getServerSocketFactory("TLS",
                                         localInputFile);
      ServerSocket        ss  = ssf.createServerSocket(port);

      ((SSLServerSocket) ss).setNeedClientAuth(true);

      /*
       * Keep accepting Socket Connections and starting them on a thread
       */
      while (true)
       {
        System.out
          .println("Waiting for client connection....");
        new SSLServer(ss.accept()).start();
      }
    }
    catch (Exception e)
     {
      e.printStackTrace();
    }
  }

  /**
   * Method getServerSocketFactory
   *
   *
   * @param type is the support of TLS.
   * @param filename
   *
   * @return the ServerSocketfactory created wuth the context.
   *
   */
  public static ServerSocketFactory getServerSocketFactory(
          String type, String filename)
   {

    if (type.equals("TLS"))
     {
      SSLServerSocketFactory ssf = null;

      System.out.println("Starting TLS Exchange....");

      try
       {

        /*
         * set up key manager to do server authentication
         */
        SSLContext          ctx;
        KeyManagerFactory   kmf;
        TrustManagerFactory tmf;
        KeyStore            ks;

        /*
         *  passphrase is the password for the store
         */
        char[] passphrase = "passphrase".toCharArray();

        /*
         *  Use TLS
         */
        ctx = SSLContext.getInstance("TLS");

        /*
         *  Get an instance of the X509
         */
        kmf = KeyManagerFactory.getInstance("SunX509");

        /*
         *  Get an instance of the X509
         */
        tmf = TrustManagerFactory.getInstance("SunX509");

        /*
         *  Get the default Java KeyStore
         */
        ks = KeyStore.getInstance("JKS");

        /*
         *  Open the keystore that is a file called testkeys and the password
         */
        ks.load(new FileInputStream(filename), passphrase);

        /*
         *  Initialize the KeyManager with the KeyStore
         */
        kmf.init(ks, passphrase);
        tmf.init(ks);
        System.out.println("Opened KeyStore");

        /*
         *  Initialize the SSLContext with the keyManager
         */
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(),
                 null);

        /*
         *  Initialize the ServerSocketFactory with the SSLContext
         */
        ssf = ctx.getServerSocketFactory();

        return ssf;
      }
      catch (Exception e)
       {
        e.printStackTrace();
      }
    }
    else
     {
      return ServerSocketFactory.getDefault();
    }

    return null;
  }

  /**
   * Constructor SSLServer
   * Initializes the client socket when acceptance.
   *
   * @param s the client socket.
   *
   */
  public SSLServer(Socket s)
   {
    sock = s;
  }

  /**
   * Method run
   * This method is a thread that is accepting an input socket
   *
   */
  public void run()
   {

    try
     {
      System.out.println("Received client connection....");

      /*
       *  Get the Input and Output Socket Streams
       */
      PrintWriter    out = new PrintWriter(
        new BufferedWriter(
          new OutputStreamWriter(sock.getOutputStream())));
      BufferedReader in  =
        new BufferedReader(new InputStreamReader(sock
          .getInputStream()));
      String         inputLine;

      /*
       * Read until all input is read
       */
      if ((inputLine = in.readLine()) != null)
       {
        System.out.println(inputLine);
      }

      out.println("Post HTTP/1.1");
      out.flush();

      /*
       * Close the stream and connection
       */
      in.close();
      out.close();
    }
    /*
     * Print any errors
     */
    catch (IOException ex)
     {

      System.out.println("Error: " + ex.getMessage());
      ex.printStackTrace();
    }
    finally
     {
      try
       {
        System.out.println("Closing Client Socket......");

        /*
         * Close the Socket Connection
         */
        sock.close();
      }
      catch (IOException e) {}
    }
  }
}
