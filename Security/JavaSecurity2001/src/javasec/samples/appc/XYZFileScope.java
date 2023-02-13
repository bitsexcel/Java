package javasec.samples.appc;

import java.io.*;
import java.security.*;
import java.util.*;

public class XYZFileScope extends IdentityScope {
    private Hashtable ids;
    private static String fname;

    public XYZFileScope(String fname) throws KeyManagementException {
        super("XYZFileScope");
        this.fname = fname;
        try {
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ids = (Hashtable) ois.readObject();
        } catch (FileNotFoundException fnfe) {
            ids = new Hashtable();
        } catch (Exception e) {
            throw new KeyManagementException(
                        "Can’t load identity database " + fname);
        }
    }
    
    public int size() {
        return ids.size();
    }

    public Identity getIdentity(PublicKey key) {
        if (key == null)
            return null;
        Identity id;
        for (Enumeration e = ids.elements(); e.hasMoreElements(); ) {
            id = (Identity) e.nextElement();
            PublicKey k = id.getPublicKey();
            if (k != null && k.equals(key))
                return id;
        }
        return null;
    }

    /*public Identity getIdentity(String name) {
        Identity id;
        id = (Identity) ids.get(name);
        return id;
    }*/

    public Identity getIdentity(String name) {
        Identity id;
        id = (Identity) ids.get(name);
        if (id instanceof Signer) {
            SecurityManager sec = System.getSecurityManager();
            if (sec != null)
                sec.checkSecurityAccess("get.signer");
        }
        return id;
    }

    public void addIdentity(Identity identity)
                                throws KeyManagementException {
        String name = identity.getName();
        if (getIdentity(name) != null)
            throw new KeyManagementException(
                            name + " already in identity scope");

        PublicKey k = identity.getPublicKey();
        if (getIdentity(k) != null)
            throw new KeyManagementException(
                            name + " already in identity scope");
        ids.put(name, identity);
    }

    public void removeIdentity(Identity identity)
                                    throws KeyManagementException {
        String name = identity.getName();
        if (ids.get(name) == null)
            throw new KeyManagementException(
                            name + " isn’t in the identity scope");
        ids.remove(name);
    }

    public Enumeration identities() {
        return ids.elements();
    }

    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ids);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Can’t save id database");
        }
    }
}
