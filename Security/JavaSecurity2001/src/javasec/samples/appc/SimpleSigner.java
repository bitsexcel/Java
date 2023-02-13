package javasec.samples.appc;

import java.security.*;

public class SimpleSigner extends Signer {
    public SimpleSigner(String name) throws KeyManagementException {
        super(name);
    }
}
