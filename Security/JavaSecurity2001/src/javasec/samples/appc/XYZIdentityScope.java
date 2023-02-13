package javasec.samples.appc;

import java.security.*;
import java.util.*;

public class XYZIdentityScope extends IdentityScope {
    private transient IdentityScope publicScope;
    private transient IdentityScope privateScope;

    public XYZIdentityScope() throws KeyManagementException {
        super("XYZIdentityScope");
        privateScope = new XYZFileScope("/floppy/floppy0/private");
        publicScope = new XYZFileScope("/auto/shared/sharedScope");
        setSystemScope(this);
    }
    
    public int size() {
        return publicScope.size() + privateScope.size();
    }

    public Identity getIdentity(String name) {
        Identity id;
        id = privateScope.getIdentity(name);
        if (id == null)
            id = publicScope.getIdentity(name);
        return id;
    }

    public Identity getIdentity(PublicKey key) {
        Identity id;
        id = privateScope.getIdentity(key);
        if (id == null)
            id = publicScope.getIdentity(key);
        return id;
    }

    public void addIdentity(Identity identity)
                                    throws KeyManagementException {
        throw new KeyManagementException(
                    "This scope does not support adding identities");
    }

    public void removeIdentity(Identity identity)
                                    throws KeyManagementException {
        throw new KeyManagementException(
                    "This scope does not support removing identities");
    }

    class XYZIdentityScopeEnumerator implements Enumeration {
        private boolean donePrivate = false;
        Enumeration pubEnum = null, privEnum = null;

        XYZIdentityScopeEnumerator() {
            pubEnum = publicScope.identities();
            privEnum = privateScope.identities();
            if (!privEnum.hasMoreElements())
                donePrivate = true;
        }

        public boolean hasMoreElements() {
            return pubEnum.hasMoreElements() ||
                   privEnum.hasMoreElements();
        }

        public Object nextElement() {
            Object o = null;
            if (!donePrivate) {
                o = privEnum.nextElement();
                if (!privEnum.hasMoreElements())
                    donePrivate = true;
            }
            else o = pubEnum.nextElement();
            if (o == null)
                throw new NoSuchElementException(
                            "XYZIdentityScopeEnumerator");
            return o;
        }
    }

    public Enumeration identities() {
        return new XYZIdentityScopeEnumerator();
    }
}
