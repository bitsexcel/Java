package javasec.samples.ch06;

import java.net.*;
import java.security.*;
import java.io.*;

public class CustomSecureClassLoader extends SecureClassLoader {

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
        System.out.println("loading class " + name);
        return super.loadClass(name, resolve);
    }

    protected Class findClass(final String name)
                               throws ClassNotFoundException {
        // First check if we have permission to access the package.
        // You could remove these 7 lines to skip the optional step 4.
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            int i = name.lastIndexOf('.');
            if (i != -1) {
                sm.checkPackageDefinition(name.substring(0, i));
            }
        }
        // Now read in the bytes and define the class
        try {
            return (Class)
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction() {
                        public Object run() throws ClassNotFoundException {
                            byte[] buf = null;
                            try {
                                buf = readClassBytes(name);
                            } catch (Exception e) {
                                throw new ClassNotFoundException(name, e);
                            }
                            CodeSource cs = getCodeSource(name);
                            Class c = defineClass(name, buf,
                                               0, buf.length, cs);
		            System.out.println("defining class " + c);
		            return c;
                        }
                    }
                );
        } catch (java.security.PrivilegedActionException pae) {
            throw (ClassNotFoundException) pae.getException();
        }
    }

    protected byte[] readClassBytes(String name) {
        try {
            // This is the standard technique to read a class
            // from the file system; you must convert the . in 
            // the package name to the directory structure from
            // which you're reading.
            name = name.replace('.', File.separatorChar);
            // We read classes from the directoy foo (plus the
            // additional directories required by the package name).
            DataInputStream dis = new DataInputStream(
                       new FileInputStream("foo" +
                            File.separatorChar + name + ".class"));
            byte[] buf = new byte[dis.available()];
            dis.readFully(buf);
            return buf;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    protected CodeSource getCodeSource(String name) {
        try {
            return new CodeSource(new URL("file", "localhost", name), null);
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        return null;
    }

    protected PermissionCollection getPermissions(CodeSource codesource) {
        PermissionCollection pc = new Permissions();
        pc.add(new RuntimePermission("exitVM"));
        return pc;
    }
}
