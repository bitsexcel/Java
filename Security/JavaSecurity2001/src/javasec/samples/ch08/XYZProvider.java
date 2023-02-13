package javasec.samples.ch08;

import java.security.*;

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

    public static final synchronized void verifyForJCE() {
        throw new SecurityException("Can't verify for JCE");
    }
}
