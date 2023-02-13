package javasec.samples.ch14;

import java.io.*;
import java.net.*;

import javax.net.*;
import javax.net.ssl.*;
import javax.security.cert.*;

public class SSLClientVerifier {

    public static void main(String[] args) throws Exception {

        SocketFactory sf = SSLSocketFactory.getDefault();
        SSLSocket s = (SSLSocket) sf.createSocket(
                       args[0], Integer.parseInt(args[1]));

        SSLSession sess = s.getSession();
        String host = sess.getPeerHost();
        X509Certificate[] certs = sess.getPeerCertificateChain();
        String dn = certs[0].getSubjectDN().getName();
        X500Name name = new X500Name(dn);
        if (!host.equals(name.getCN()))
            System.out.println("Warning: Expected " + host +
                                " and got " + name.getCN());

        BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                        s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        System.out.println("Who is Sylvia?");
        pw.println("Who is Sylvia?");
        pw.flush();
        System.out.println(br.readLine());
        s.close();
    }
}
