package com.richware.chap23;



import java.io.*;

import java.net.*;

import java.security.*;

import javax.net.*;
import javax.net.ssl.*;

import javax.security.cert.*;


/**
 * Class SSLClient
 * This example demonstrates a JSSE client with context creation.
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
public class SSLClient
 {

  private final static int    port  = 25001;
  private final static String host  = "127.0.0.1";
  final static String         debug = "none";

  /**
   * Method main
   * This mathod is the main driver for the client.
   *
   * @param args none needed, later can put in host and port.
   *
   * @throws Exception if there is an issue.
   *
   */
  public static void main(String[] args) throws Exception
   {

    try
     {
      System.out.println("Starting SSLClient....");

      /*
       * Pass in the argument of the keystore file
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
       * Set the SSL internal debugger to all
       */
      System.out.println("Setting SSL debugging to :" + debug);
      System.setProperty("javax.net.debug", debug);

      /*
       * Get a TLS socket factory
       */
      SSLSocketFactory socketFactory =
        SSLClient.getClientSocketFactory("TLS",
                                         localInputFile);

      /*
       * Get the client socket instance
       */

      //    SSLSocket    socket    =
      //      (SSLSocket) socketFactory.createSocket("www.richware.com",
      //                   25001);
      SSLSocket sock =
        (SSLSocket) socketFactory.createSocket(host, port);

      /*
       * send http request
       *
       * Before any application data is sent or received, the
       * SSL socket will do SSL handshaking first to set up
       * the security attributes.
       *
       * The only way to tell there was an error is to call
       * PrintWriter.checkError().
       */
      System.out.println("Socket : " + sock);
      sock.startHandshake();
      System.out.println("Handshake finished");

      /*
       * Get the SSLSession and print some of the session info
       */
      SSLSession session = sock.getSession();

      System.out.println("Peer Host :"
                         + session.getPeerHost());
      System.out.println("Name of the cipher suite :"
                         + session.getCipherSuite());

      /*
       *  Get the certificate Chain
       */
      X509Certificate[] certificates =
        session.getPeerCertificateChain();

      /*
     * Print the distinguished Principal's name
     */
      System.out
        .println("DN :"
                 + certificates[0].getSubjectDN().getName());

      /*
       * Get the output stream
       */
      PrintWriter out = new PrintWriter(
        new BufferedWriter(
          new OutputStreamWriter(sock.getOutputStream())));

      /*
       * Send the Get method
       */
      out.println("GET HTTP/1.1");
      out.println();
      out.flush();

      /*
       * Make sure there were no issues
       */
      if (out.checkError())
       {
        System.out
          .println("SSLClient:  java.io.PrintWriter error");
      }

      /*
       * Read any responses
       */
      BufferedReader in =
        new BufferedReader(new InputStreamReader(sock
          .getInputStream()));
      String         inputLine;

      /*
       * Read until all input is read
       */
      while ((inputLine = in.readLine()) != null)
       {
        System.out.println(inputLine);
      }

      /*
       * Close the stream and connection
       */
      in.close();
      out.close();
      sock.close();
    }
    catch (Exception e)
     {
      e.printStackTrace();
    }
  }

  /**
   * Method getClientSocketFactory
   *
   *
   * @param type is the supported TLS type.
   * @param filename
   *
   * @return the SocketFactory created with the context.
   *
   */
  public static SSLSocketFactory getClientSocketFactory(
          String type, String filename)
   {

    if (type.equals("TLS"))
     {
      SSLSocketFactory factory = null;

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
         *  Initialize the SocketFactory with the SSLContext
         */
        factory = ctx.getSocketFactory();

        return factory;
      }
      catch (Exception e)
       {
        e.printStackTrace();
      }
    }
    else
     {
      return (SSLSocketFactory) SSLSocketFactory.getDefault();
    }

    return null;
  }
}