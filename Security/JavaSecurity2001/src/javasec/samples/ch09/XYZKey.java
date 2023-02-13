package javasec.samples.ch09;

import java.security.*;

public class XYZKey implements Key, PublicKey, PrivateKey {
    int rotValue;

    public String getAlgorithm() {
        return "XYZ";
    }

    public String getFormat() {
        return "XYZ Special Format";
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
