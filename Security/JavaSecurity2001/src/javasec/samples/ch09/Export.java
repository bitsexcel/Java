package javasec.samples.ch09;

import java.security.*;
import java.security.spec.*;
import java.io.*;

public class Export {
    public static void main(String args[]) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
            kpg.initialize(512, new SecureRandom());
            KeyPair kp = kpg.generateKeyPair();
            Class spec = Class.forName(
                            "java.security.spec.DSAPrivateKeySpec");
            KeyFactory kf = KeyFactory.getInstance("DSA");
            DSAPrivateKeySpec ks = (DSAPrivateKeySpec)
                                    kf.getKeySpec(kp.getPrivate(), spec);
            FileOutputStream fos = new FileOutputStream("exportedKey");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ks.getX());
            oos.writeObject(ks.getP());
            oos.writeObject(ks.getQ());
            oos.writeObject(ks.getG());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
