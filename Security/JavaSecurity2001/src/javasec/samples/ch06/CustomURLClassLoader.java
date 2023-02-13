package javasec.samples.ch06;

import java.net.*;
import java.security.*;

public class CustomURLClassLoader extends URLClassLoader {

    static URL urls[];

    static {
        try {
            urls = new URL[2];
            urls[0] = new URL("http://piccolo.East/~sdo/");
            urls[1] = new URL("file:/home/classes/LocalClasses.jar");
        } catch (Exception e) {
            throw new RuntimeException("Can't create URLs " + e);
        }
    };

    public CustomURLClassLoader() {
        super(urls);
    }

    public final synchronized Class loadClass(String name, boolean resolve)
                                 throws ClassNotFoundException {
        // First check if we have permission to access the package.
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            int i = name.lastIndexOf('.');
            if (i != -1) {
                sm.checkPackageAccess(name.substring(0, i));
            }
        }
        return super.loadClass(name, resolve);
    }

    protected Class findClass(final String name)
                    throws ClassNotFoundException {
        // First check if we have permission to access the package.
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            int i = name.lastIndexOf('.');
            if (i != -1) {
                sm.checkPackageDefinition(name.substring(0, i));
            }
        }
        return super.findClass(name);
    }

    protected PermissionCollection getPermissions(CodeSource codesource) {
        // Use all the standard permissions, plus allow the code to
        // exit the VM.
        PermissionCollection pc = super.getPermissions(codesource);
        pc.add(new RuntimePermission("exitVM"));
        return pc;
    }
}
