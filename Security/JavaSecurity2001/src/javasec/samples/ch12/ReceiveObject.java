package javasec.samples.ch12;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import javasec.samples.ch10.KeyStoreHandler;

public class ReceiveObject {
    private static void verifySigner(java.security.cert.Certificate c,
                          String name) throws CertificateException {
        java.security.cert.Certificate issuerCert = null;
        X509Certificate sCert = null;
        KeyStore ks = null;

        try {
            KeyStoreHandler ksh = new KeyStoreHandler(null);
            ks = ksh.getKeyStore();
        } catch (Exception e) {
            throw new CertificateException("Invalid keystore");
        }

        try {
            String signer = ks.getCertificateAlias(c);
            if (signer !=null){
                System.out.println("We know the signer as " + signer);
                return;
            }
            for (Enumeration alias = ks.aliases(); 
                              alias.hasMoreElements();){
                String s = (String) alias.nextElement();
                try {
                    sCert = (X509Certificate) ks.getCertificate(s);
                } catch (Exception e) {
                    continue;
                }
                if (name.equals(sCert.getSubjectDN().getName())){
                    issuerCert = sCert;
                    break;
                }
            }
        } catch(KeyStoreException kse) {
            throw new CertificateException("Invalid keystore");
        }
        if (issuerCert == null) {
            throw new CertificateException("No such certificate");
        }
        try {
            c.verify(issuerCert.getPublicKey());
        } catch (Exception e) {
            throw new CertificateException(e.toString());
        }
    }

    private static void processCertificate(X509Certificate x509)
                                    throws CertificateParsingException {
        Principal p;
        p = x509.getSubjectDN();
        System.out.println("This message was signed by " +
                            p.getName());
        p = x509.getIssuerDN();
        System.out.println("This certificate was provided by " +
                            p.getName());
        try {
            verifySigner(x509, p.getName());
        } catch (CertificateException ce) {
            System.out.println("We don’t know the certificate signer");
        }
        try {
            x509.checkValidity();
        } catch (CertificateExpiredException cee) {
            System.out.println("That certificate is no longer valid");
        } catch (CertificateNotYetValidException cnyve) {
            System.out.println("That certificate is not yet valid");
        }
    }

    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("test.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            if (o instanceof Message) {
                Message m = (Message) o;
                System.out.println("Received message");
                processCertificate((X509Certificate) m.certificate);
                PublicKey pk = m.certificate.getPublicKey();
                if (m.object.verify(pk, Signature.getInstance("MD5withRSA"))) {
                    System.out.println("Message is valid");
                    System.out.println(m.object.getObject());
                }
                else System.out.println("Message signature is invalid");
            }
            else System.out.println("Message is corrupted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
