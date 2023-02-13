package javasec.samples.ch09;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javasec.samples.ch08.XYZProvider;

public class XORKeyGenerator extends KeyGeneratorSpi {
    SecureRandom sr;

    public XORKeyGenerator() {
        XYZProvider.verifyForJCE();
    }

    public void engineInit(SecureRandom sr) {
        this.sr = sr;
    }

    public void engineInit(int len, SecureRandom sr) {
        if (len != 32)
            throw new IllegalArgumentException("Only support 32 bit keys");
        this.sr = sr;
    }

    public void engineInit(AlgorithmParameterSpec aps, SecureRandom sr) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("Not supported");
    }

    public SecretKey engineGenerateKey() {
        if (sr == null)
            sr = new SecureRandom();

        byte b[] = new byte[1];
        sr.nextBytes(b);
        return new XORKey(b[0]);
    }
}
