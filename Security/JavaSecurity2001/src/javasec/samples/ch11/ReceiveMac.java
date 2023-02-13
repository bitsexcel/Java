package javasec.samples.ch11;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javasec.samples.ch10.KeyStoreHandler;

public class ReceiveMac {
    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("test");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            if (!(o instanceof String)) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            String data = (String) o;
            System.out.println("Got message " + data);
            o = ois.readObject();
            if (!(o instanceof byte[])) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            byte origDigest[] = (byte []) o;

	    Mac mac = Mac.getInstance("HmacSHA1");

	    KeyStoreHandler ksh = new KeyStoreHandler(null);
	    KeyStore ks = ksh.getKeyStore();
	    mac.init((SecretKey) ks.getKey(args[0], args[1].toCharArray()));
            mac.update(data.getBytes());
            if (MessageDigest.isEqual(mac.doFinal(), origDigest))
                System.out.println("Message is valid");
            else System.out.println("Message was corrupted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
