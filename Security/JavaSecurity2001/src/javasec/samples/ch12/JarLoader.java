package javasec.samples.ch12;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.jar.*;

public class JarLoader extends SecureClassLoader {
    private URL urlBase;    
    public boolean printLoadMessages = true;

    // These hold the classes and certificates that we read from the jar file
    Hashtable classArrays;
    Hashtable classIds;

    public JarLoader(String base, ClassLoader parent) {
        super(parent);
        try {
            // The base is the URL from which we read jar files; e.g.
            // http://piccolo/sdo.jar is read from the base http://piccolo/
            // But we can also read classes from the base directly.
            if (!(base.endsWith("/")))
                base = base + "/";
            urlBase = new URL(base);
            classArrays = new Hashtable();
            classIds = new Hashtable();
        } catch (Exception e) {
            throw new IllegalArgumentException(base);
        }
    }

    // Completely read the input stream and convert it into a byte array
    private byte[] getClassBytes(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        boolean eof = false;
        while (!eof) {
            try {
                int i = bis.read();
                if (i == -1)
                    eof = true;
                else baos.write(i);
            } catch (IOException e) {
                return null;
            }
        }
        return baos.toByteArray();
    }

    // Find the class. This is based on the steps of our previous example
    protected Class findClass(final String name) throws ClassNotFoundException {
        try {
            return (Class)
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction() {
                        public Object run() throws ClassNotFoundException {
                            Object o = doFindClass(name);
                            if (o == null)
                                throw new ClassNotFoundException(name);
			    return o;
			}
                    }
                );
        } catch (PrivilegedActionException pae) {
            throw (ClassNotFoundException) pae.getException();
        }
    }

    private Class doFindClass(String name) {
        String urlName = name.replace('.', '/');
        byte buf[];
        Class cl;

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            int i = name.lastIndexOf('.');
            if (i >= 0)
                sm.checkPackageDefinition(name.substring(0, i));
        }

        // See if the class is in a jar file we've read
        buf = (byte[]) classArrays.get(urlName);
        if (buf != null) {
            // It was -- get the certificates (if any) and define the
            // codesource and class with them.
            java.security.cert.Certificate[] ids =
                (java.security.cert.Certificate[]) classIds.get(urlName);
            CodeSource cs = new CodeSource(urlBase, ids);
            cl = defineClass(name, buf, 0, buf.length, cs);
            return cl;
        }

        // The class wasn't in a jar file -- see if we can load it
        // directly. Since it's being loaded directly, it won't have
        // a signature.
        try {
            URL url = new URL(urlBase, urlName + ".class");
            if (printLoadMessages)
                System.out.println("Loading " + url);
            InputStream is = url.openConnection().getInputStream();
            buf = getClassBytes(is);
            CodeSource cs = new CodeSource(urlBase, null);
            cl = defineClass(name, buf, 0, buf.length, cs);
            return cl;
        } catch (Exception e) {
            System.out.println("Can't load " + name + ": " + e);
            return null;
        }
    }

    // Read a jar file, storing its classes into the classArray and
    // the certificates of its signatures into the classIds
    public void readJarFile(String name) {
        URL jarUrl = null;
        JarInputStream jis;
        JarEntry je;

        try {
            jarUrl = new URL(urlBase, name);
        } catch (MalformedURLException mue) {
            System.out.println("Unknown jar file " + name);
            return;
        }
        if (printLoadMessages)
            System.out.println("Loading jar file " + jarUrl);

        try {
            jis = new JarInputStream(
                        jarUrl.openConnection().getInputStream());
        } catch (IOException ioe) {
            System.out.println("Can't open jar file " + jarUrl);
            return;
        }

        try {
            while ((je = jis.getNextJarEntry()) != null) {
                String jarName = je.getName();
                if (jarName.endsWith(".class"))
                    loadClassBytes(jis, jarName, je);
                // else ignore it; it could be an image or audio file
                // Really, these type of entries need to be saved for
                // the resource methods; we leave that extension to
                // the reader.
                jis.closeEntry();
            }
        } catch (IOException ioe) {
            System.out.println("Badly formatted jar file");
        }
    }

    private void loadClassBytes(JarInputStream jis,
                                    String jarName, JarEntry je) {
        if (printLoadMessages)
            System.out.println("\t" + jarName);
        BufferedInputStream jarBuf = new BufferedInputStream(jis);
        ByteArrayOutputStream jarOut = new ByteArrayOutputStream();
        int b;
        try {
            while ((b = jarBuf.read()) != -1)
                jarOut.write(b);
            String className = jarName.substring(0, jarName.length() - 6);
            classArrays.put(className, jarOut.toByteArray());
            // This returns the certificates from the signature file
            // only if this class was signed.
            java.security.cert.Certificate c[] = je.getCertificates();
            if (c == null)
                c = new java.security.cert.Certificate[0];
            classIds.put(className, c);
        } catch (IOException ioe) {
            System.out.println("Error reading entry " + jarName);
        }
    }
}
