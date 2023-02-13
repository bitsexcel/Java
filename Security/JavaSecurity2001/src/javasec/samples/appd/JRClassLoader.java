package javasec.samples.appd;

import java.io.*;
import java.net.*;
import java.util.*;

public class JRClassLoader extends ClassLoader {

    private URL urlBase;
    private static URL trustedBase;
    private Hashtable trustedClasses;
    private static int groupNum;
    private ThreadGroup threadGroup;

    static {
        String s = System.getProperty("trustedBase");
        if (s != null)
            try {
                trustedBase = new URL(s);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                                 "Bad value for trustedBase " + s);
            }
    }

    public JRClassLoader(String base) {
        try {
            if (!(base.endsWith("/")))
                base = base + "/";
                urlBase = new URL(base);
        } catch (Exception e) {
            throw new IllegalArgumentException(base);
        }
        trustedClasses = new Hashtable(13);
    }

    protected Class loadClass(String name, boolean resolve) {
        Class c;
        SecurityManager sm = System.getSecurityManager();

        // Step 1 -- Check to make sure that we can access this class
        if (sm != null) {
        int i = name.lastIndexOf('.');
            if (i >= 0)
                sm.checkPackageAccess(name.substring(0, i));
        }

         // Step 2 -- Check for a previously loaded class
         c = findLoadedClass(name);
         if (c != null)
            return c;

        // Step 3 -- Check for system class first
        try {
            c = findSystemClass(name);
            return c;
        } catch (ClassNotFoundException cnfe) {
            // Not a system class, simply continue
        }

        // Step 4 -- Check to make sure that we can define this class
        if (sm != null) {
            int i = name.lastIndexOf('.');
            if (i >= 0)
                sm.checkPackageDefinition(name.substring(0, i));
        }

        // Step 5 -- Read in the class file
        byte data[] = lookupData(name);
        if (data == null)
             return null;

        // Step 6 and 7 -- Define the class from the data; this also
        //         passes the data through the byte code verifier
        c = defineClass(name, data, 0, data.length);
        if (trustedClasses.get(name) != null) {
            trustedClasses.remove(name);
            trustedClasses.put(c, name);
        }

        // Step 8 -- Resolve the internal references of the class
        if (resolve)
            resolveClass(c);

        return c;
    }

    byte[] lookupData(String n) {
        try {
            byte[] b = null;
            String urlName = n.replace('.', '/');

            if (trustedBase != null) {
                URL url = new URL(trustedBase, urlName + ".class");
                b = readData(url);
            }

            if (b == null) {
                URL url = new URL(urlBase, urlName + ".class");
                b = readData(url);
            }
            else trustedClasses.put(n, "dummy");

            return b;
        } catch (Exception e) {
            return null;
        }
    }

    byte[] readData(URL url) {
        try {
            InputStream is = url.openConnection().getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            boolean eof = false;
            while (!eof) {
                int i = bis.read();
                if (i == -1)
                    eof = true;
                else baos.write(i);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    boolean getTrusted(Class c) {
        return trustedClasses.get(c) != null;
    }

    private static synchronized int getGroupNum() {
        return groupNum++;
    }

    synchronized ThreadGroup getThreadGroup() {
        if (threadGroup == null)
            threadGroup = new ThreadGroup("JavaRunner ThreadGroup-"
                                          + getGroupNum());
        return threadGroup;
    }

    String getHost() {
        return urlBase.getHost();
    }
}
