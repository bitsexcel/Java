package javasec.samples.appd;

import java.security.*;
import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import java.util.*;

public class JavaRunnerManager extends SecurityManager {

    private void checkTrustedDepth(String err, int depth) {
        int clDepth = classLoaderDepth();
        if (clDepth == 0 || clDepth > depth + 1)
            return;

        ClassLoader cl = currentClassLoader();
        if (cl != null) {
            JRClassLoader jcl;
            try {
                jcl = (JRClassLoader) cl;
            } catch (ClassCastException cce) {
                // This can’t happen unless our own classes are out of sync
                throw new SecurityException("Unknown class loader");
            }
            if (!jcl.getTrusted(currentLoadedClass()))
                throw new SecurityException(err);
        }
    }

    public synchronized boolean getInCheck() {
        return super.getInCheck();
    }

    public synchronized void checkConnect(String host, int port) {
        ClassLoader loader = currentClassLoader();
        String remoteHost;

        if (loader == null)
            return;
        if (!(loader instanceof JRClassLoader)) {
            System.err.println("Class loader out of sync");
            return;
        }

        JRClassLoader cl = (JRClassLoader) loader;
        remoteHost = cl.getHost();

        if (host.equals(remoteHost))
            return;

        try {
            inCheck = true;
            InetAddress hostAddr = InetAddress.getByName(host);
            InetAddress remoteAddr = InetAddress.getByName(remoteHost);
            inCheck = false;
            if (hostAddr.equals(remoteAddr))
                return;
        } catch (UnknownHostException uhe) {
            inCheck = false;
        }
        throw new SecurityException(
                   "Can’t connect from " + remoteHost + " to " + host);
    }

    public void checkConnect(String host, int port, Object context) { 
        checkConnect(host, port);
    }

    public void checkListen(int port) { 
        if (inClassLoader() && port < 1024 & port > -1)
            throw new SecurityException(
                          "Can’t listen on privileged port");
    }

    public void checkAccept(String host, int port) {
        checkListen(port);
    }

    public void checkMulticast(InetAddress maddr) { }
    public void checkMulticast(InetAddress maddr, byte ttl) { }

    public void checkPackageAccess(String pkg) { }
    public void checkPackageDefinition(String pkg) {
        if (!pkg.startsWith("java.") && !pkg.startsWith("sun."))
            return;
        if (inClassLoader())
            throw new SecurityException(
                              "Can’t define sun/java classes");
    }

    public void checkAccess(Thread t) {
        ThreadGroup current = Thread.currentThread().getThreadGroup();
        if (!current.parentOf(t.getThreadGroup()))
            throw new SecurityException(
                              "Can’t modify outside of group");
    }

    public void checkAccess(ThreadGroup tg) {
        ThreadGroup current = Thread.currentThread().getThreadGroup();
        if (!current.parentOf(tg))
            throw new SecurityException(
                              "Can’t modify outside of group");
    }

    public void checkRead(String s) {
        checkTrustedDepth("checkread", 2);
    }

    public void checkRead(FileDescriptor fd) { 
        if (!inClassLoader())
            return;
        if (!fd.valid() || !inClass("java.net.SocketInputStream"))
            throw new SecurityException("checkRead fd");
    }

    public void checkRead(String file, Object context) { 
        checkRead(file);
    }

    public void checkWrite(FileDescriptor fd) { 
        if (!inClassLoader())
            return;
        if (!fd.valid() || !inClass("java.net.SocketOutputStream"))
            throw new SecurityException("checkWrite fd");
    }

    public void checkWrite(String s) {
        checkTrustedDepth("checkwrite", 2);
    }

    public void checkDelete(String file) { 
        checkRead(file);
    }

    public void checkCreateClassLoader() { 
        if (inClassLoader())
            throw new SecurityException("createClassLoader");
    } 

    public void checkExec(String cmd) { 
        checkTrustedDepth("checkExec", 2);
    }

    public void checkExit(int status) { 
        checkTrustedDepth("checkExit", 2);
    }

    public void checkLink(String lib) { 
        checkTrustedDepth("checkExit", 3);
    }
    public void checkPropertiesAccess() { }
    public void checkPropertyAccess(String key) { }
    public void checkPropertyAccess(String key, String def) { }

    public boolean checkTopLevelWindow(Object window) { 
        try {
            checkTrustedDepth("top", 3);
        } catch (SecurityException se) {
            return false;
        }
        return true;
    }

    public void checkPrintJobAccess() { 
        checkTrustedDepth("printjob", 2);
    }

    public void checkSystemClipboardAccess() {
        checkTrustedDepth("clipboard", 2);
    }

    public void checkAwtEventQueueAccess() { 
        checkTrustedDepth("eventqueue", 2);
    }

    public void checkSetFactory() { 
        checkTrustedDepth("setfactory", 2);
    }

    public void checkMemberAccess(Class clazz, int which) { 
        checkTrustedDepth("member access", 2);
    }

    public void checkSecurityAccess(String provider) {
        checkTrustedDepth("security access", 2);
    }

    public ThreadGroup getThreadGroup() {
        ClassLoader cl = currentClassLoader();
        if (cl == null || !(cl instanceof JRClassLoader))
            return super.getThreadGroup();
        JRClassLoader jcl = (JRClassLoader) cl;
        return jcl.getThreadGroup();
    }
}        
