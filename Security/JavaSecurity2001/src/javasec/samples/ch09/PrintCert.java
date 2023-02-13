package javasec.samples.ch09;

import java.security.cert.*;
import java.io.*;

public class PrintCert {
    public static void main(String args[]) {
        try {
            FileInputStream fr = new FileInputStream("sdo.cer");
            CertificateFactory cf =                         
                       CertificateFactory.getInstance("X509");
            X509Certificate c = (X509Certificate) 
                                cf.generateCertificate(fr);
            System.out.println("Read in the following certificate:");
            System.out.println("\tCertificate for: " +
                                     c.getSubjectDN());
            System.out.println("\tCertificate issued by: " +
                                     c.getIssuerDN());
            System.out.println("\tThe certificate is valid from " +
                        c.getNotBefore() + " to " + c.getNotAfter());
            System.out.println("\tCertificate SN# " +
                                     c.getSerialNumber());
            System.out.println("\tGenerated with " +
                                     c.getSigAlgName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
