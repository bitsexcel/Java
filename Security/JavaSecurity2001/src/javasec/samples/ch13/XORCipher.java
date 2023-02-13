package javasec.samples.ch13;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javasec.samples.ch08.XYZProvider;
import javasec.samples.ch09.XORKey;

public class XORCipher extends CipherSpi {
    byte xorByte;

    public XORCipher() {
        XYZProvider.verifyForJCE();
    }

    protected void engineInit(int i, Key k, SecureRandom sr)
                        throws InvalidKeyException {
        if (!(k instanceof XORKey))
            throw new InvalidKeyException("XOR requires an XOR key");
        xorByte = k.getEncoded()[0];
    }

    protected void engineInit(int i, Key k, AlgorithmParameterSpec aps,
                    SecureRandom sr) throws InvalidKeyException,
                            InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException(
            "Algorithm parameters not supported in this class");
    }

    protected void engineInit(int i, Key k, AlgorithmParameters ap,
            SecureRandom sr) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException(
            "Algorithm parameters not supported in this class");
    }

    protected byte[] engineUpdate(byte in[], int off, int len) {
        return engineDoFinal(in, off, len);
    }

    protected int engineUpdate(byte in[], int inoff, int length,
                            byte out[], int outoff) {
        for (int i = 0; i < length; i++)
            out[outoff + i] = (byte) (in[inoff + i] ^ xorByte);
        return length;
    }

    protected byte[] engineDoFinal(byte in[], int off, int len) {
        byte out[] = new byte[len - off];
        engineUpdate(in, off, len, out, 0);
        return out;
    }

    protected int engineDoFinal(byte in[], int inoff, int len,
                                byte out[], int outoff) {
        return engineUpdate(in, inoff, len, out, outoff);
    }

    protected int engineGetBlockSize() {
        return 1;
    }

    protected byte[] engineGetIV() {
        return null;
    }

    protected int engineGetOutputSize(int sz) {
        return sz;
    }

    protected void engineSetMode(String s)
                        throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("Unsupported mode " + s);
    }

    protected void engineSetPadding(String s)
                        throws NoSuchPaddingException {
        throw new NoSuchPaddingException("Unsupported padding " + s);
    }

    protected AlgorithmParameters engineGetParameters() {
        return null;
    }
}
