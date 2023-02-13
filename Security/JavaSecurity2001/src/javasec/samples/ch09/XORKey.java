package javasec.samples.ch09;

import javax.crypto.*;

public class XORKey implements SecretKey {
    int rotValue;

    XORKey(int value) {
        rotValue = value;
    }

    public String getAlgorithm() {
        return "XOR";
    }

    public String getFormat() {
        return "XOR Special Format";
    }

    public byte[] getEncoded() {
        byte b[] = new byte[4];
        b[3] = (byte) ((rotValue << 24) & 0xff);
        b[2] = (byte) ((rotValue << 16) & 0xff);
        b[1] = (byte) ((rotValue <<  8) & 0xff);
        b[0] = (byte) ((rotValue <<  0) & 0xff);
        return b;
    }
}
