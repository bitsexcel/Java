package javasec.samples.appc;

import java.security.*;

public class XYZSigner extends Signer {
    private int trustLevel;

    public XYZSigner(String name, IdentityScope scope)
                                    throws KeyManagementException {
        super(name, scope);
        scope.addIdentity(this);
    }

    public void setPublicKey(PublicKey key)
                                    throws KeyManagementException {
        IdentityScope scope = getScope();
        if (scope != null) {
            Identity i = getScope().getIdentity(key);
            if (i != null && !equals(i))
                throw new KeyManagementException(
                                    "Duplicate public key");
        }
        super.setPublicKey(key);
    }
 
    public void addCertificate(Certificate cert)
                                    throws KeyManagementException {
        IdentityScope scope = getScope();
        if (scope != null) {
            Identity i = getScope().getIdentity(cert.getPublicKey());
            if (i != null && !equals(i))
                throw new KeyManagementException(
                                    "Duplicate public key");
        }
        super.addCertificate(cert);
    }    

    public int getTrust() {
        return trustLevel;
    }

    void setTrust(int x) {
        if (x < 0 || x > 10)
            throw new IllegalArgumentException("Invalid trust level");
        trustLevel = x;
    }

    public String toString() {
        return super.toString() + " trust level: " + trustLevel;
    }
}
