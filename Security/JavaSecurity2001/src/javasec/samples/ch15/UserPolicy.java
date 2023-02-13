package javasec.samples.ch15;

import java.util.*;
import java.io.*;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Principal;
import javax.security.auth.Subject;
import javax.security.auth.Policy;

public class UserPolicy extends Policy {

    private Policy deferredPolicy;

    public UserPolicy(Policy p) {
        deferredPolicy = p;
    }

    public PermissionCollection getPermissions(Subject s,
                                               CodeSource cs) {
        PermissionCollection pc = deferredPolicy.getPermissions(s, cs);
        if (s == null)
            return pc;      // No subject means no specific permissions
        Set principals = s.getPrincipals();
        Iterator i = principals.iterator();
        while (i.hasNext()) {
            Principal p = (Principal) i.next();
            FilePermission fp = new FilePermission(File.separator +
                                    "files" + File.separator +
                                    p.getName() + File.separator + "-",
                                                  "read,write,delete");
            pc.add(fp);
        }
        return pc;
    }

    public void refresh() {
        deferredPolicy.refresh();
    }
}
