package com.richware.chap24;



import java.security.PublicKey;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Class RichCertificate
 * Description: A custom demostration of the certificate.
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
public class RichCertificate
 {

  /**
   * Method main
   * Description: The main driver to run the methods.
   *
   *
   * @param args (no arguments presently).
   *
   */
  public static void main(String args[])
   {

    try
     {
      System.out.println("Starting RichCertificate....");

      /*
       * Pass in the argumkent of the keystore file
       * It will be openened in the same directoy as the application
       */
      if (args[0] == null)
       {
        System.out.println(
          "This application requires an input file for the location of the certificate");
      }

      String localDirectory = System.getProperty("user.dir");

      System.out.println("Changing directory to Chapter 24");
      System.setProperty("user.dir",
                         localDirectory
                         + "\\com\\richware\\chap24\\");

      localDirectory = System.getProperty("user.dir");

      /*
       * Get the local keystore that contains a trusted certificate
       */
      String localInputFile = localDirectory + args[0];

      System.out.println(
        "Openining Chapter 24 plus the input file as an argument: "
        + localInputFile);

      /*
       * Import the certificate
       */
      RichCertificate myCertificate  = new RichCertificate();
      X509Certificate newcertificate =
        myCertificate.importCertificate(localInputFile);

      myCertificate.printVersion1(newcertificate);

      /*
       *  catches.
       */
    }
    catch (Exception e)
     {
      e.printStackTrace();
    }
  }

  /**
   * Method importCertificate
   * Description: Import the certificate.
   *
   * @param filename is the file to import.
   *
   * @return the certification.
   *
   */
  public X509Certificate importCertificate(String filename)
   {

    X509Certificate cert = null;

    try
     {
      CertificateFactory cf =
        CertificateFactory.getInstance("X509");

      /*
       * Get the File I/O of the Certificate
       */
      FileInputStream fr = new FileInputStream(filename);

      /*
       *  Construct the certificate based on the import
       */
      cert = (X509Certificate) cf.generateCertificate(fr);

      /*
       *  catches.
       */
    }
    catch (CertificateException e)
     {
      e.printStackTrace();
    }
    catch (FileNotFoundException e)
     {
      e.printStackTrace();
    }

    return cert;
  }

  /**
   * Method printVersion1
   * Description: Print version 1 information of the Certificate.
   *
   *
   * @param cert is the certification to read from.
   *
   */
  public void printVersion1(X509Certificate cert)
   {

    try
     {

      /*
       *  Get the information of the certificate.
       */
      System.out.println(
        "Certificate->Version Number*****************");
      System.out.println(cert.getVersion());
      System.out.println(
        "Certificate->Serial Number******************");
      System.out.println(cert.getSerialNumber());
      System.out.println(
        "Certificate->Signature Algorithm Identifier*");
      System.out.println(cert.getSigAlgName());
      System.out.println(
        "Certificate->Issuer Name********************");
      System.out.println(cert.getIssuerDN());
      System.out.println(
        "Certificate->Not Before Validity************");
      System.out.println(cert.getNotBefore());
      System.out.println(
        "Certificate->Not After Validity*************");
      System.out.println(cert.getNotAfter());
      System.out.println(
        "Certificate->Subject Name*******************");
      System.out.println(cert.getSubjectDN());
      System.out.println(
        "Certificate->Subject Public Key Information*");
      System.out.println(cert.getPublicKey());
      System.out.println(
        "Certificate->Signature**********************");
      System.out.println(cert.getSignature());

      /*
       *  catches.
       */
    }
    catch (Exception e)
     {
      e.printStackTrace();
    }
  }
}