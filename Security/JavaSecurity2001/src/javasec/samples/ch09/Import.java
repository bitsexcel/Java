package javasec.samples.ch09;

import java.security.*;
import java.security.spec.*;
import java.io.*;
import java.math.*;

public class Import {
    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("exportedKey");
            ObjectInputStream ois = new ObjectInputStream(fis);
            DSAPrivateKeySpec ks = new DSAPrivateKeySpec(
                        (BigInteger) ois.readObject(),
                        (BigInteger) ois.readObject(),
                        (BigInteger) ois.readObject(),
                        (BigInteger) ois.readObject());
            KeyFactory kf = KeyFactory.getInstance("DSA");
            PrivateKey pk = kf.generatePrivate(ks);
            System.out.println("Got private key");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
