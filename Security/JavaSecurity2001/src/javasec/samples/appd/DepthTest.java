package javasec.samples.appd;

import java.applet.*;
import java.math.*;

public class DepthTest extends Applet {
    native void evilInterface();
    
    public void init() {
        doMath();
        infiltrate();
    }
    
    public void infiltrate() {
        try {
            System.loadLibrary("evilLibrary");
            evilInterface();
        } catch (Exception e) {}
    }
    
    public void doMath() {
        BigInteger bi = new BigInteger("100");
        bi = bi.add(new BigInteger("100"));
        System.out.println("answer is " + bi);
    }
}
