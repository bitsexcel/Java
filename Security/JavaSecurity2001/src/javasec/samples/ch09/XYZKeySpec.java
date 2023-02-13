package javasec.samples.ch09;

import java.security.spec.*;

public class XYZKeySpec implements KeySpec {
    int rotValue;

    public XYZKeySpec(XYZKey k) {
        rotValue = k.rotValue;
    }
}
