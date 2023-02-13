package javasec.samples.ch09;

import java.security.*;
import java.security.interfaces.*;

public class PrintKey {
    public void printKey(Key k) {
        if (k instanceof DSAKey) {
            System.out.println("key is DSA");
            System.out.println("P value is " +
                            ((DSAKey) k).getParams().getP());
        }
        else System.out.println("key is not DSA");
    }

    public void printKey(DSAKey k) {
        if (k instanceof DSAPublicKey)
            System.out.println("Public key value is " +
                                            ((DSAPublicKey) k).getY());
        else if (k instanceof DSAPrivateKey)
            System.out.println("Private key value is " +
                                            ((DSAPrivateKey) k).getX());
        else System.out.println("Bad key implementation");
    }
}
