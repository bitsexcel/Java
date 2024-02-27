package com.richware.chap17;


import java.security.*;

import java.io.*;

import java.net.*;

import org.ietf.jgss.*;


/**
 * Class RichGSSService
 * Description: A custom demonstration of
 * the acceptor/intiator of the GSS-API.
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
public class RichGSSService {

  /*
   * Port number for connection
   */
  public final static int localPort = 6000;

  /*
   * true if acceptor, false if initiator
   * this will distinguish the two in code
   */
  public boolean isAcceptor;

  /*
   * the Server name to connect
   */
  public final static String hostname = "localhost";

  /**
   * Method main
   * Description: The main driver to run the methods.
   *
   *
   * @param args (no arguments presently).
   *
   */
  public static void main(String args[]) {

    DataInputStream  inStream    = null;
    DataOutputStream outStream   = null;
    Socket           localsocket = null;

    try {

      RichGSSService service = new RichGSSService();
      if(args[0] != null){
         if(args[0].equalsIgnoreCase("Server")){
      	   service.isAcceptor = true;
      	   System.out.println("Starting Server");
      	 }  
      }

      /*
       * Establish the socket connection
       * For the server, the application is just collecting the
       * first socket connection
       */
      if (service.isAcceptor) {
        ServerSocket ss = new ServerSocket(service.localPort);

        localsocket = ss.accept();
      } else {
        localsocket = new Socket(service.hostname,
                                 service.localPort);
      }
      /*
       * Establish the streams from the socket connection
       */
      inStream  =
        new DataInputStream(localsocket.getInputStream());
      outStream =
        new DataOutputStream(localsocket.getOutputStream());

      System.out.println("Connected to server "
                         + localsocket.getInetAddress());
      /*
       * Create the GSS Manager instance
       */
      GSSManager manager = GSSManager.getInstance();
      /*
       * Create the security context
       */
      System.out.println("Creating Security Context....");
      /*
       * Create the context from the manager
       */
      GSSContext context =
        service.createContext(manager, service.hostname,
                              service.isAcceptor);
      if (context == null) {
        System.out.println("..creation failed");
      }
      /*
       * Loop until a security context is established 
       * through tokens
       */
      service.contextLoop(context, service.isAcceptor,
                          inStream, outStream);
 /*
  * If mutual authentication did not take place, then only the
  * client was authenticated to the server. Otherwise, both
  * client and server were authenticated to each other.
  */
if (context.getMutualAuthState()){
     System.out.println("Mutual authentication took place!");
 }    
      /*
       * In this example, the initiator writes the message
       * and the acceptor reads the message
       */
      if(service.isAcceptor){
       String message = service.readMessage(context,inStream);
       System.out.println("Message Read :" + message);
       }else{
       String message = "GSS Rules";
       System.out.println("Writing Message :" + message);
       service.writeMessage(context,message,outStream);
       }
      /*
       * Cleanup
       */
     context.dispose();
     localsocket.close();
      /*
       * Catches
       */
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method createContext
   * Purpose: to start the security context by 
   * defining the authentication mechanism
   * principals and credentials.
   *
   *
   * @param manager the GSSManager
   * @param hostname the hostname to connect 
   * @param isAcceptor is an acceptor or not
   *
   * @return the security context created
   *
   */
  public GSSContext createContext(GSSManager manager,
                                  String hostname,
                                  boolean isAcceptor) {

    GSSContext context = null;

    try {
      Oid krb5Mechanism         =
        new Oid("1.2.840.113554.1.2.2");
      Oid krb5PrincipalNameType =
        new Oid("1.2.840.113554.1.2.2.1");

      /*
       * Create a GSSName out of the server's name.
       * If a null is passed in the nametype, 
       * the underlying mechanism will try to 
       * parse it as the default syntax that it chooses.
       * If the krb5PrincipalNameType is passed in, 
       * then it will be parsing it in the kerberos 
       * name type.
       */
      GSSName serverName = manager.createName(hostname, null);

      /*
       * Create a security context for the initiator
       */
      if (!isAcceptor) {

        /*
         * Identify who the client wishes to be
         */
        GSSName userName =
          manager.createName("rich", GSSName.NT_USER_NAME);

        /*
         * Acquire credentials for the user
         */
        GSSCredential userCreds =
          manager
            .createCredential(userName, GSSCredential
              .DEFAULT_LIFETIME, krb5Mechanism, GSSCredential
              .INITIATE_ONLY);

        /*
         * Create a GSSContext for mutual authentication with
         * the server.
         *    - serverName is the GSSName 
         * that represents the server.
         *    - krb5Mechanism is the Oid for 
         * the kerberos 5 authentication mechanism.
         *    - the credentials for the user
         *    - DEFAULT_LIFETIME lets the mechanism 
         * decide how long the
         *      context can remain valid.
         * Note: If using a ull for the credentials, 
         * the GSS-API will use the default 
         * credentials. This means that the mechanism
         * will look among the credentials stored 
         * in the current Subject to find the 
         * right kind of credentials that it needs.
         */
        context =
          manager.createContext(serverName, krb5Mechanism,
                                userCreds,
                                GSSContext.DEFAULT_LIFETIME);

        /*
         * Set the desired optional features on 
         * the context. set the mutual authentication,
         * confidentiality and integrity
         */
        context.requestMutualAuth(true);
        context.requestConf(true); 
        context.requestInteg(true);
      /*
       * Create a security context for the acceptor
       */
      } else {

        /*
         * Acquire credentials for the server
         */
        GSSCredential serverCreds =
          manager
            .createCredential(serverName, GSSCredential
              .DEFAULT_LIFETIME, krb5Mechanism, GSSCredential
              .ACCEPT_ONLY);

        /*
         * Instantiate and initialize a security 
         * context that will wait for an 
         * establishment request token from the client
         */
        context = manager.createContext(serverCreds);
      }
      /*
       * Catches
       */
    } catch (org.ietf.jgss.GSSException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return context;
  }
/**
   * Method contextLoop
   * Purpose: to loop until the context is 
   * established from the tokens
   *
   *
   * @param context the intitialized token
   * @param isAcceptor is the acceptor or not
   * @param inStream the input stream
   * @param outStream the output stream
   *
   */
  public void contextLoop(GSSContext context,
                          boolean isAcceptor,
                          DataInputStream inStream,
                          DataOutputStream outStream) {

    byte[] token = null;
    try {

      while (!context.isEstablished()) {
      /*
       * Finish a security context for the acceptor
       * Read the token and accept it
       */
        if (isAcceptor) {
          token = new byte[inStream.readInt()];

          System.out
            .println("Will read input token of size "
                     + token.length
                     + " for processing by acceptSecContext");
          inStream.readFully(token);

          token = context.acceptSecContext(token, 0,
                                           token.length);
      /*
       * Else , finish a security context for the initiator
       * pass a empty token to initialize the context
       */
        } else {
   token = new byte[0];

          /* 
           * token is ignored on the first call
           */
          token = context.initSecContext(token, 0,
                                         token.length);
        }

        /*
         * Send a token to the peer if one was generated by
         * initSecContext
         */
        if (token != null) {
          System.out.println("Will send token of size "
                             + token.length
                             + " from initSecContext.");
          outStream.writeInt(token.length);
          outStream.write(token);
          outStream.flush();
        }

        /*
         * The initiator has an extra read of the token 
         * from the acceptor
         *
         */
        if (((!context.isEstablished()) && (!isAcceptor))) {
          token = new byte[inStream.readInt()];

          System.out
            .println("Will read input token of size "
                     + token.length
                     + " for processing by initSecContext");
          inStream.readFully(token);
        }
      }

      System.out.println("Context Established! ");
      System.out.println("Client is " + context.getSrcName());
      System.out.println("Server is " + context.getTargName());
      /*
       * Catches
       */
    } catch (org.ietf.jgss.GSSException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method readMessage
   * Purpose: reads a input stream from the socket
   *
   * @param context the current security context
   * @param inStream the input stream
   *
   * @return the string read from the input
   *
   */
  public String readMessage(GSSContext context,
                            DataInputStream inStream) {

    String str = null;

    try {

      /*
       * Create a MessageProp which unwrap will use to 
       * return information such as the 
       * Quality-of-Protection that was applied to 
       * the wrapped token, whether or not it was
       * encrypted, etc. Since the initial 
       * MessageProp values are ignored, 
       * just set them to the defaults of 0 and false.
       */
      MessageProp prop = new MessageProp(0, false);

      /*
       * Read the token. This uses the same token byte array
       * as that used during context establishment.
       */
      byte[] token = new byte[inStream.readInt()];

      System.out.println("Will read token of size "
                         + token.length);
      inStream.readFully(token);

      byte[] bytes = context.unwrap(token, 0, token.length,
                                    prop);

      str = new String(bytes);

      System.out.println("Received data \"" + str
                         + "\" of length " + str.length());
      System.out.println("Confidentiality applied: "
                         + prop.getPrivacy());

      /*
       * Catches
       */
    } catch (org.ietf.jgss.GSSException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return str;
  }

  /**
   * Method writeMessage
   * Purpose: writes the output stream to the socket
   *
   * @param context the current context
   * @param message the message to write to the output
   * @param outStream the output stream
   *
   */
  public void writeMessage(GSSContext context, String message,
                           DataOutputStream outStream) {

    try {
      byte[] messageBytes = message.getBytes();

      /*
       * The first MessageProp argument is 0 to request
       * the default Quality-of-Protection.
       * The second argument is true to request
       * privacy (encryption of the message).
       */
      MessageProp prop = new MessageProp(0, true);

      /*
       * Encrypt the data and send it across. 
       * Integrity protection is always applied,
       * irrespective of confidentiality (i.e., encryption).
       * You can use the same token (byte array) as that 
       * used when establishing the context.
       */
      byte[] token = context.wrap(messageBytes, 0,
                                  messageBytes.length, prop);

      System.out.println("Will send wrap token of size "
                         + token.length);
      outStream.writeInt(token.length);
      outStream.write(token);
      outStream.flush();

      /*
       * Catches
       */
    } catch (org.ietf.jgss.GSSException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

