package javasec.samples.ch14;

import java.io.*;
import java.net.*;
import java.security.*;

import javax.net.*;
import javax.net.ssl.*;

public class SSLSimpleServer extends Thread {

    public static void main(String[] args) throws Exception {
        ServerSocketFactory ssf = SSLServerSocketFactory.getDefault();
        ServerSocket ss = ssf.createServerSocket(9096);

        System.out.println("Ready...");
        while (true) {
            new SSLSimpleServer(ss.accept()).start();
        }
    }

    private Socket sock;
    
    public SSLSimpleServer(Socket s) {
        sock = s;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                        sock.getInputStream()));
            PrintWriter pw = new PrintWriter(sock.getOutputStream());

            String data = br.readLine();
            pw.println("What is she?");
            pw.close();
            sock.close();
        } catch (IOException ioe) {
            // Client disconnected
        }
    }
}
