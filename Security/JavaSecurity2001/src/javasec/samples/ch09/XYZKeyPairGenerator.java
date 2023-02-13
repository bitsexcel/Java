package javasec.samples.ch09;

import java.security.*;
import javasec.samples.ch08.*;

public class XYZKeyPairGenerator extends KeyPairGenerator {
    SecureRandom random;

    public XYZKeyPairGenerator() {
        super("XYZ");
    }

    public void initialize(int strength, SecureRandom sr) {
        random = sr;
    }

    public KeyPair generateKeyPair() {
        int rotValue = random.nextInt() % 25;
        XYZKey pub = new XYZKey();
        XYZKey priv = new XYZKey();
        pub.rotValue = rotValue;
        priv.rotValue = -rotValue;
        KeyPair kp = new KeyPair(pub, priv);
        return kp;
    }

    public static void main(String[] args) throws Exception {
        Security.addProvider(new XYZProvider());
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("XYZ");
        kpg.initialize(0, new SecureRandom());
        KeyPair kp = kpg.generateKeyPair();
        System.out.println("Got key pair " + kp);
    }
}
