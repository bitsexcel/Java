package javasec.samples.appc;

import java.security.*;

public class SimpleIdentity extends Identity {
    public SimpleIdentity(String name) throws KeyManagementException {
        super(name);
    }
}
