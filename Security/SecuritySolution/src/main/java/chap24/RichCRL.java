package com.richware.chap24;



import java.security.PublicKey;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.*;

import javax.security.auth.x500.X500Principal;

import sun.security.x509.*;


/**
 * Class RichCRL
 * Description: A custom demonstration of the Certificate Revocation List.
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
public class RichCRL
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
      System.out.println("Starting RichCRL....");

      /*
       * Pass in the argumkent of the keystore file
       * It will be openened in the same directoy as the application
       */
      if (args[0] == null)
       {
        System.out.println(
          "This application requires an input file for the location of the crl");
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
       * Import the certificate relocation list
       */
      RichCRL myCertificate  = new RichCRL();
      X509CRL newcertificate =
        myCertificate.importCertificate(localInputFile);

      System.out.println(
        "*********************CRL *************************");
      System.out.println(newcertificate);
      System.out.println("CRL->Version Number->"
                         + newcertificate.getVersion());
      System.out
        .println("CRL->Signature Algorithm Identifier->"
                 + newcertificate.getSigAlgName());
      System.out.println("CRL->Issuer Name->"
                         + newcertificate.getIssuerDN());
      System.out.println("CRL->ThisUpdate->"
                         + newcertificate.getThisUpdate());
      System.out.println("CRL->NextUpdate->"
                         + newcertificate.getNextUpdate());

      /*
       * Get the revoked Certificates
       */
      Set            setCRLEntries =
        newcertificate.getRevokedCertificates();
      X509CRLEntry[] newEntries    =
        new X509CRLEntry[setCRLEntries.size()];
      Iterator       iter          = setCRLEntries.iterator();
      int            current       = 0;

      while (iter.hasNext())
       {
        X509CRLEntry entry = (X509CRLEntry) iter.next();

        System.out.println(
          "***********CRL Entry No Extensions****************");
        System.out.println(entry);
        System.out.println("CRL->Entry->RevocationDate->"
                           + entry.getRevocationDate());
        System.out.println("CRL->Entry->SerialNumber->"
                           + entry.getSerialNumber());
        System.out.println("CRL->Entry->HasExtensions->"
                           + entry.hasExtensions());

        /*
         * Are there any extensions
         */
        if (entry.hasExtensions())
         {

          /*
           * Print the extension OIDs
           */
          Set nonCritSet = entry.getNonCriticalExtensionOIDs();

          if (nonCritSet != null)
           {
            for (Iterator i = nonCritSet.iterator();
                    i.hasNext(); )
             {
              String oid = (String) i.next();

              System.out.println("Extensions in Entry" + oid);
            }
          }
        }

        /*
         * Else create some extensions
         */
        else
         {

          /*
           * Create an CRL Extension class to contain individual extensions
           */
          CRLExtensions extensions = new CRLExtensions();

          /*
           * Create the CRL Reason Code Extension
           */
          CRLReasonCodeExtension reason =
            new CRLReasonCodeExtension(2);

          extensions.set("2.5.29.21", reason);

          //     System.out.println("CRL->Entry->New Reason Code***********");
          CRLReasonCodeExtension newreason =
            (CRLReasonCodeExtension) extensions
              .get("2.5.29.21");

          //     System.out.println(newreason);
          X509CRLEntryImpl x509crlentryimpl =
            new X509CRLEntryImpl(entry.getSerialNumber(),
                                 entry.getRevocationDate(),
                                 extensions);

          newEntries[current] =
            (X509CRLEntry) x509crlentryimpl;
        }

        current++;
      }

      /*
       * Create an X500Name from the X500 Principal
       */
      X500Principal currPrincipal =
        newcertificate.getIssuerX500Principal();
      X500Name      name          =
        new X500Name(currPrincipal.getEncoded());

      /*
       * Create an CRL Extension class to contain individual extensions and set it for the main CRL
       */
      CRLExtensions      crlExtensions = new CRLExtensions();
      CRLNumberExtension crlNumber     =
        new CRLNumberExtension(1);

      crlExtensions.set("2.5.29.20", crlNumber);

      /*
       * Create a new CRL with the extensions in the CRL Entries
       */
      X509CRLImpl newCRL =
        new X509CRLImpl(name, newcertificate.getThisUpdate(),
                        newcertificate.getNextUpdate(),
                        newEntries, crlExtensions);

      System.out.println(
        "*****************CRL with Extensions**************");
      System.out.println(newCRL);

      /*
       * Iterate through the CRL entries again showing the extensions
       */
      setCRLEntries = newCRL.getRevokedCertificates();
      iter          = setCRLEntries.iterator();

      /*
       * Loop through the entries
       */
      while (iter.hasNext())
       {
        X509CRLEntry entry = (X509CRLEntry) iter.next();

        System.out.println(
          "*******CRL Entry After Adding Reason Extension**********");
        System.out.println(entry);
        System.out.println("CRL->Entry->RevocationDate->"
                           + entry.getRevocationDate());
        System.out.println(entry.getSerialNumber());
        System.out.println("CRL->Entry->HasExtensions->"
                           + entry.hasExtensions());

        /*
         * the getExtensionValue will return a null becuase it is not part of the
         *  supported OIDs mentioned in the JavaDocs
         */
        System.out
          .println("CRL->Entry->Reason Code from method->"
                   + entry.getExtensionValue("2.5.29.21"));

        X509CRLEntryImpl x509crlentryimpl =
          new X509CRLEntryImpl(entry.getEncoded());
        Integer          reasonInt        =
          x509crlentryimpl.getReasonCode();

        /*
         * Print out the Reason Code
         */
        System.out
          .println("CRL->Entry->Reason Code->"
                   + RichCRL
                     .reasonToString(reasonInt.intValue()));

        /*
         * Print out the OIDs found
         */
        Set nonCritSet = entry.getNonCriticalExtensionOIDs();

        if (nonCritSet != null)
         {
          for (Iterator i = nonCritSet.iterator();
                  i.hasNext(); )
           {
            String oid = (String) i.next();

            System.out.println("CRL->Entry->OID->" + oid);
          }
        }
      }

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
  public X509CRL importCertificate(String filename)
   {

    X509CRL cert = null;

    try
     {
      CertificateFactory cf =
        CertificateFactory.getInstance("X.509");

      /*
       * Get the File I/O of the Certificate
       */
      FileInputStream fr = new FileInputStream(filename);

      /*
       *  Construct the certificate based on the import
       */
      cert = (X509CRL) cf.generateCRL(fr);

      fr.close();

      /*
       *  catches.
       */
    }
    catch (java.security.cert.CertificateException e)
     {
      e.printStackTrace();
    }
    catch (java.security.cert.CRLException e)
     {
      e.printStackTrace();
    }
    catch (java.io.IOException e)
     {
      e.printStackTrace();
    }

    return cert;
  }

  /**
   * Method reasonToString
   *
   *
   * @param i defining the reason for revocation
   *
   * @return the string that maps to the integer
   *
   */
  public static String reasonToString(int i)
   {

    switch (i)
     {

    case 0 :  // '\0'
      return "unspecified";

    case 1 :  // '\001'
      return "key compromise";

    case 2 :  // '\002'
      return "CA compromise";

    case 3 :  // '\003'
      return "affiliation changed";

    case 4 :  // '\004'
      return "superseded";

    case 5 :  // '\005'
      return "cessation of operation";

    case 6 :  // '\006'
      return "certificate hold";

    case 8 :  // '\b'
      return "remove from CRL";

    case 7 :  // '\007'
    default :
      return "unrecognized reason code";
    }
  }
}