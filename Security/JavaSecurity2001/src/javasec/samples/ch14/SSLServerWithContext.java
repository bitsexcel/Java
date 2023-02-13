package javasec.samples.ch14;

import java.io.*;
import java.net.*;
import java.security.*;

import javax.net.*;
import javax.net.ssl.*;
import javax.security.cert.*;
import com.sun.net.ssl.*;

public class SSLServerWithContext {

    public static void main(String[] args) throws Exception {
        SSLContext sc = SSLContext.getInstance("TLS");
        KeyManagerFactory kmf =
                        KeyManagerFactory.getInstance("SunX509");
        KeyStore ks = KeyStore.getInstance("jceks");
        char[] password = args[1].toCharArray();
        ks.load(new FileInputStream(args[0]), null);
        kmf.init(ks, password);
        sc.init(kmf.getKeyManagers(), null, null);

        ServerSocketFactory ssf = sc.getServerSocketFactory();
        ServerSocket ss = ssf.createServerSocket(9096);

        while (true) {
            new SSLSimpleServer(ss.accept()).start();
        }
    }
}
