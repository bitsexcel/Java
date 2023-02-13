package javasec.samples.ch12;

import java.io.*;
import java.security.*;
import java.security.cert.*;

public class Message implements Serializable {
    SignedObject object;
    transient java.security.cert.Certificate certificate;

    private void writeObject(ObjectOutputStream out)
                                    throws IOException {
        out.defaultWriteObject();
        try {
            out.writeObject(certificate.getEncoded());
        } catch (CertificateEncodingException cee) {
            throw new IOException("Can't serialize object " + cee);
        }
    }

    private void readObject(ObjectInputStream in)
                            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        try {
            byte b[] = (byte []) in.readObject();
            CertificateFactory cf = 
                        CertificateFactory.getInstance("X509");
            certificate = cf.generateCertificate(new 
                             ByteArrayInputStream(b));
        } catch (CertificateException ce) {
            throw new IOException("Can't de-serialize object " + ce);
        }
    }
}
