package com.richware.chap25;

import java.security.cert.CertPath;
import java.security.cert.CertificateFactory;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidator;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Date;
import java.security.cert.X509CRLSelector;
import java.security.cert.PKIXParameters;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.util.Collection;
import java.util.Collections;
import java.security.cert.LDAPCertStoreParameters;
import java.security.cert.CertStore;
import java.security.cert.X509CertSelector;
import java.math.BigInteger;
import java.util.List;
import java.io.FileInputStream;
import java.util.Arrays; 
/**
 * Class RichPath
 * Description: A custom demonstration of the path verification
 * for a certificate.
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
public class RichPath {

  private static final boolean _DEBUG = true;

  /**
   * Method main
   * Description: The main driver to run the methods.
   *
   *
   * @param args (no arguments presently).
   *
   */
  public static void main(String args[]) {

    try {
      System.out.println("Starting RichPath....");

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

      System.out.println("Changing directory to Chapter 25");
      System.setProperty("user.dir",
                         localDirectory
                         + "\\com\\richware\\chap25\\");

      localDirectory = System.getProperty("user.dir");

      /*
       * Get the local keystore that contains a trusted certificate
       */
      String localInputFile = localDirectory + args[0];

      System.out.println(
        "Openining Chapter 25 plus the input file as an argument: "
        + localInputFile);

      /*
       * Import the certificate path keystore
       */
      RichPath myPath  = new RichPath();
      CertPath newpath =
        myPath.importCertPath(localInputFile,"password", "rich2");
      /*
       *   catches.
       */
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method importCertPath
   * Description: Import the certificate path from the 
   * keystore.
   *
   * @param filename of the keystore to import.
   * @param password
   * @param chain
   *
   * @return the certification path.
   *
   */
  public CertPath importCertPath(String filename,
                                 String password,
                                 String chain) {

    CertPath certPath = null;

    try {

      /*
       * instantiate a CertificateFactory for X.509
       */
      CertificateFactory cf =
        CertificateFactory.getInstance("X.509");

      /*
       * instantiate a KeyStore with type JKS
       */
      KeyStore ks = KeyStore.getInstance("JKS");

      /*
       * load the contents of the KeyStore
       */
      ks.load(new FileInputStream(filename),
              password.toCharArray());

      /*
       * fetch certificate chain stored 
       */
      Certificate[] certArray = ks.getCertificateChain(chain);

      /*
       * convert chain to a List
       */
      List certList = Arrays.asList(certArray);

      /*
       * extract the certification path from the List of 
       * Certificates
       */
      certPath = cf.generateCertPath(certList);
      System.out.println(" CertPath :" + certPath);

      /*
       *   catches.
       */
    } catch (java.security.cert.CertificateException e) {
      e.printStackTrace();
    } catch (java.security.KeyStoreException e) {
      e.printStackTrace();
    } catch (java.security.NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }

    return certPath;
  }
}
