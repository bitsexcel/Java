package javasec.samples.ch09;

import java.security.*;
import java.security.spec.*;

public class XYZKeyFactory extends KeyFactorySpi {

    protected PublicKey engineGeneratePublic(KeySpec ks)
                                   throws InvalidKeySpecException {
        // Convert from the key spec to the key. In our case, the
        // spec holds the rot value, so we can just construct the
        // key
        if (!(ks instanceof XYZKeySpec))
            throw new InvalidKeySpecException("Expecting XYZKeySpec");
        XYZKey k = new XYZKey();
        k.rotValue = ((XYZKeySpec) ks).rotValue;
        return k;
    }

    protected PrivateKey engineGeneratePrivate(KeySpec ks)
                                   throws InvalidKeySpecException {
        if (!(ks instanceof XYZKeySpec))
            throw new InvalidKeySpecException("Expecting XYZKeySpec");
        XYZKey k = new XYZKey();
        k.rotValue = ((XYZKeySpec) ks).rotValue;
        return k;
    }

    protected KeySpec engineGetKeySpec(Key key, Class specClass)
                                   throws InvalidKeySpecException {
        if (specClass != XYZKeySpec.class)
            throw new InvalidKeySpecException("Expecting XYZKeySpec");
        if (!(key instanceof XYZKey))
            throw new IllegalArgumentException("Expecting XYZ Key");
        return new XYZKeySpec((XYZKey) key);
    }

    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        // Translate key formats by getting the encoded key and creating
        // the type of key we know about.
        XYZKey k = new XYZKey();
        if (k.getFormat() != key.getFormat())
            throw new InvalidKeyException("Expecting XYZ Special Format Key");
        byte[] b = key.getEncoded();
        k.rotValue = (b[3] << 24) | (b[2] << 16) | (b[1] << 8) | b[0];
        return k;
    }
}
