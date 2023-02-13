package javasec.samples.ch12;

import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;

public class XYZSignature extends Signature implements Cloneable {
    private DSAPublicKey pub;
    private DSAPrivateKey priv;
    private MessageDigest md;

    public XYZSignature() throws NoSuchAlgorithmException {
        super("XYZSignature");
        md = MessageDigest.getInstance("SHA");
    }

    public void engineInitVerify(PublicKey publicKey)
                                    throws InvalidKeyException {
        try {
            pub = (DSAPublicKey) publicKey;
        } catch (ClassCastException cce) {
            throw new InvalidKeyException("Wrong public key type");
        }
    }

    public void engineInitSign(PrivateKey privateKey)
                                    throws InvalidKeyException {
        try {
            priv = (DSAPrivateKey) privateKey;
        } catch (ClassCastException cce) {
            throw new InvalidKeyException("Wrong private key type");
        }
    }

    public void engineUpdate(byte b) throws SignatureException {
        try {
            md.update(b);
        } catch (NullPointerException npe) {
            throw new SignatureException("No SHA digest found");
        }
    }

    public void engineUpdate(byte b[], int offset, int length)
                                    throws SignatureException {
        try {
            md.update(b, offset, length);
        } catch (NullPointerException npe) {
            throw new SignatureException("No SHA digest found");
        }
    }

    public byte[] engineSign() throws SignatureException {
        byte b[] = null;
        try {
            b = md.digest();
        } catch (NullPointerException npe) {
            throw new SignatureException("No SHA digest found");
        }
        return crypt(b, priv);
    }

    public boolean engineVerify(byte[] sigBytes)
                                    throws SignatureException {
        byte b[] = null;
        try {
            b = md.digest();
        } catch (NullPointerException npe) {
            throw new SignatureException("No SHA digest found");
        }
        byte sig[] = crypt(sigBytes, pub);
        return MessageDigest.isEqual(sig, b);
    }

    public void engineSetParameter(String param, Object value) {
        throw new InvalidParameterException("No parameters");
    }

    public void engineSetParameter(AlgorithmParameterSpec aps) {
        throw new InvalidParameterException("No parameters");
    }

    public Object engineGetParameter(String param) {
        throw new InvalidParameterException("No parameters");
    }

    public void engineReset() {
    }

    private byte[] crypt(byte s[], DSAKey key) {
        DSAParams p = key.getParams();
        int rotValue = p.getP().intValue();
        byte d[] = rot(s, (byte) rotValue);
        return d;
    }

    private byte[] rot(byte in[], byte rotValue) {
        byte out[] = new byte[in.length];
        for (int i = 0; i < in.length; i++) {
            out[i] = (byte) (in[i] ^ rotValue);
        }
        return out;
    }
}
