package javasec.samples.ch05;

import java.security.*;
import java.util.*;

public class MyPolicy extends Policy {

    // This inner class defines a simple set of permissions:
    // either everything is allowed (the implies() method always
    // returns true, and the collection contains an AllPermission
    // object) or everything is prohibited (the implies()
    // method always returns false and the collection is empty).
    static class SimplePermissions extends PermissionCollection {
        boolean allow;
        Permissions perms;

        SimplePermissions(boolean b) {
            allow = b;
            perms = new Permissions();
            if (allow)
                perms.add(new AllPermission());
        }

        public void add(Permission p) {
            if (isReadOnly())
                throw new SecurityException(
                             "Can’t add to this collection");
            perms.add(p);
        }

        public Enumeration elements() {
            return perms.elements();
        }

        public boolean implies(Permission p) {
            return allow;
        }
    }

    // We never change the policy
    public void refresh() {}

    //If the code was loaded from a file, return a collection
    // that implies all permissions. Otherwise, return an
    // empty collection (one that implies no permissions).
    public PermissionCollection getPermissions(CodeSource cs) {
        if (cs.getLocation().getProtocol().equals("file"))
            return new SimplePermissions(true);
        return new SimplePermissions(false);
    }
}
