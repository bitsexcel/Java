package javasec.samples.appe;

import java.net.*;
import java.io.*;
import java.util.jar.*;
import java.util.*;
import java.security.cert.*;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Provider;

public class XYZProvider extends Provider {

    public XYZProvider() {
        super("XYZ", 1.0, "XYZ Security Provider v1.0");
        // These are examples we'll demonstrate throughout the next
        // chapters.
        put("KeyGenerator.XOR", "javasec.samples.ch09.XORKeyGenerator");
        put("KeyPairGenerator.XYZ", "javasec.samples.ch09.XYZKeyPairGenerator");
        put("KeyFactory.XYZ", "javasec.samples.ch09.XYZKeyFactory");
        put("MessageDigest.XYZ", "javasec.samples.ch11.XYZMessageDigest");
        put("Signature.XYZwithSHA", "javasec.samples.ch12.XYZSignature");
        put("Cipher.XOR", "javasec.samples.ch13.XORCipher");
        put("KeyManagerFactory.XYZ", "javasec.samples.ch14.SSLKeyManagerFactory");

        // Now include any aliases
        put("Alg.Alias.MessageDigest.SHA-1", "SHA");
    }

    private static boolean verifiedJCE = false;

    private static X509Certificate[] trustedCerts;

    static byte[][] embeddedCerts = {
        /*
        { // Embed first certificate here
        },
        { // Embed second certificate here... and so on
        }
        */
    };

    static {
        // Note: This code is incomplete until you obtain the trusted
        // root certificate from Sun (and, optionally, IBM). When you
        // apply for your JCE signing certificate, they will send you
        // the root certificate. Then follow these steps:
        //    1) Import it into a keystore
        //    2) Write a program to read the certificate out of the keystore
        //    3) Get the encoded byte array from the certificate
        //    4) Dump out the byte array to a print writer file like this:
        //         for (int i = 0; i < bytearray.length; i++)
        //             pw.print(bytearray[i] + ", ");
        //    5) Convert that file into a byte array definition, and put
        //         it into the embeddedCerts array above

        try {
            trustedCerts = new X509Certificate[embeddedCerts.length];
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            for (int i = 0; i < trustedCerts.length; i++) {
                ByteArrayInputStream bais =
                        new ByteArrayInputStream(embeddedCerts[i]);
                trustedCerts[i] = (X509Certificate) cf.generateCertificate(bais);
            }
        } catch (Exception e) {
            throw new SecurityException("Can't initialize certs " + e);
        }
    }

    public static final synchronized void verifyForJCE() {
        // If the JCE has already been verified, just return.
        if (verifiedJCE)
            return;
        
        verify(javax.crypto.Cipher.class);
        verify(XYZProvider.class);
        verifiedJCE = true;
    }

    private static void verify(Class c) {
        // Verify that the class C comes from a jar file signed by a
        // trusted entity.

        // Find out the URL for the class
        final URL u = getURL(c);
        if (u == null)
        throw new SecurityException("Can't find valid signed class " + c);

        // Read the JAR file
        JarFile jf;
        try {
            jf = (JarFile)AccessController.doPrivileged(
                new PrivilegedExceptionAction() {
                    public Object run() throws Exception {
                        return ((JarURLConnection)
                                   u.openConnection()).getJarFile();
                    }
                }
            );
        } catch (PrivilegedActionException pae) {
            throw new SecurityException("Cannot authenticate JCE " + pae);
        }

        try {
            verifySingleJarFile(jf);
        } catch (Exception e) {
            throw new SecurityException("Cannot authenticate JCE " + e);
        }
    }

    private static void verifySingleJarFile(JarFile jf)
                        throws IOException, CertificateException {
        Vector entriesVec = new Vector();

        // Ensure there is a manifest file
        Manifest man = jf.getManifest();
        if (man == null)
            throw new SecurityException("The JCE framework is not signed");

        // Ensure all the entries' signatures verify correctly
        byte[] buffer = new byte[8192];
        Enumeration entries = jf.entries();
        while (entries.hasMoreElements()) {
            JarEntry je = (JarEntry)entries.nextElement();
            entriesVec.addElement(je);
            InputStream is = jf.getInputStream(je);
            int n;
            while ((n = is.read(buffer, 0, buffer.length)) != -1) {
                // we just read. This will throw a SecurityException
                // if  a signature/digest check fails.
            }
            is.close();
        }
        jf.close();

        // Get the list of signer certificates 
        Enumeration e = entriesVec.elements();
        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();
            if (je.isDirectory())
                continue;
            // Every file must be signed - except files in META-INF
            Certificate[] certs = je.getCertificates();
            if ((certs == null) || (certs.length == 0)) {
                if (!je.getName().startsWith("META-INF"))
                    throw new SecurityException("Unsigned JCE classes!");
                else ;
            } else {
                // Check whether the file is signed as expected.
                // The framework may be signed by multiple signers.
                // At least one of the signers must be a trusted signer.
                // First, determine the roots of the certificate chains
                Certificate[] chainRoots = getChainRoots(certs);
                boolean signedAsExpected = false;
                for (int i = 0; i < chainRoots.length; i++) {
                    if (isTrusted((X509Certificate)chainRoots[i])) {
                        signedAsExpected = true;
                        break;
                    }
                }
                if (!signedAsExpected)
                    throw new SecurityException("The JCE framework " +
                                     "is not signed by a trusted signer");
            }
        }
    }

    private static boolean isTrusted(X509Certificate cert) {
        // Return true only if either of the following is true:
        // 1) the cert is in the trustedCerts.
        // 2) the cert is issued by a trusted CA.
        // Check whether the cert is in the trustedCerts
        for (int i = 0; i < trustedCerts.length; i++) {
            // If the cert has the same SubjectDN as a trusted CA,
            // check whether the two certs are the same.
            if (cert.getSubjectDN().equals(
                                    trustedCerts[i].getSubjectDN())) {
                if (cert.equals(trustedCerts[i]))
                    return true;
            }
        }

        // Check whether the cert is issued by a trusted CA.
        // Signature verification is expensive. So we check 
        // whether the cert is issued by one of the trusted CAs
        // only if the above loop failed.
        for (int i = 0; i < trustedCerts.length; i++) {
            // If the issuer of the cert has the same name as 
            // a trusted CA, check whether that trusted CA 
            // actually issued the cert.
            if (cert.getIssuerDN().equals(trustedCerts[i].getSubjectDN())) {
                try {
                    cert.verify(trustedCerts[i].getPublicKey());
                    return true;
                } catch (Exception e) {
                    // Do nothing.
                }
            }        
         }
         return false;
     }    

     private static Certificate[] getChainRoots(Certificate[] certs) {
         Vector result = new Vector(3);
         // choose a Vector size that seems reasonable
         for (int i = 0; i < certs.length - 1; i++) {
             if (!((X509Certificate)certs[i + 1]).getSubjectDN().equals(
                 ((X509Certificate)certs[i]).getIssuerDN())) {
                 // We've reached the end of a chain
                 result.addElement(certs[i]);
             }
         }
         // The final entry in the certs array is always 
         // a "root" certificate
         result.addElement(certs[certs.length - 1]);
         Certificate[] ret = new Certificate[result.size()];
         result.copyInto(ret);

         return ret;
    }

    /*
     * Returns the URL of the JAR file containing the specified class.
     */
     private static URL getURL(Class c) {
         final Class cc = c;
         return (URL)AccessController.doPrivileged(
             new PrivilegedAction() {
                 public Object run() {
                     CodeSource s1 = cc.getProtectionDomain().getCodeSource();
                     return s1.getLocation();
                 }
              }
          );
      }
}
