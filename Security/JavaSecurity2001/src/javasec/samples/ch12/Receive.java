package javasec.samples.ch12;

import java.io.*;
import java.security.*;
import javasec.samples.ch10.KeyStoreHandler;

public class Receive {
    public static void main(String args[]) {
        try {
            String data = null;
            byte signature[] = null;
            FileInputStream fis = new FileInputStream("test");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            try {
                data = (String) o;
            } catch (ClassCastException cce) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            o = ois.readObject();
            try {
                signature = (byte []) o;
            } catch (ClassCastException cce) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            System.out.println("Received message");
            System.out.println(data);

            KeyStoreHandler ksh = new KeyStoreHandler(null);
            KeyStore ks = ksh.getKeyStore();

            java.security.cert.Certificate c =
                                ks.getCertificate(args[0]);
            PublicKey pk = c.getPublicKey();
            Signature s = Signature.getInstance("MD5withRSA");
            s.initVerify(pk);
            s.update(data.getBytes());
            if (s.verify(signature)) {
                System.out.println("Message is valid");
            }
            else System.out.println("Message was corrupted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
