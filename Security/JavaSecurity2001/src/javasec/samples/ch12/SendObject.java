package javasec.samples.ch12;

import java.io.*;
import java.security.*;
import javasec.samples.ch10.KeyStoreHandler;

public class SendObject {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("test.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            KeyStoreHandler ksh = new KeyStoreHandler(null);
            KeyStore ks = ksh.getKeyStore();

            java.security.cert.Certificate certs[] =
                               ks.getCertificateChain(args[0]);
            PrivateKey pk = (PrivateKey) ks.getKey(args[0],
                                            args[1].toCharArray());
            Message m = new Message();
            m.object = new SignedObject(
              "This have I thought good to deliver thee, " +
              "that thou mightst not lose the dues of rejoicing " +
              "by being ignorant of what greatness is promised thee.",
                            pk, Signature.getInstance("MD5withRSA"));
            m.certificate = certs[0];
            oos.writeObject(m);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
