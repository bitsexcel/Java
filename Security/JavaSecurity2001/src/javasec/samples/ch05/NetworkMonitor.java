package javasec.samples.ch05;

import java.net.*;
import java.io.*;
import java.security.*;

public class NetworkMonitor {
    public NetworkMonitor() {
        try {
            // This class is used by the doPrivilged() method to
            // open a socket
            class doSocket implements PrivilegedExceptionAction {
                public Object run() throws UnknownHostException,
                                     IOException {
                    return new Socket("net.xyz.com", 4000);
                }
            };
            doSocket ds = new doSocket();
            Socket s = (Socket) AccessController.doPrivileged(ds);
        } catch (PrivilegedActionException pae) {
            Exception e = pae.getException();
            if (e instanceof UnknownHostException) {
                // process host exception
            }
            else if (e instanceof IOException) {
                // process IOException
            }
            else {
                // e must be a runtime exception
                throw (RuntimeException) e;
            }
        }
    }
}
