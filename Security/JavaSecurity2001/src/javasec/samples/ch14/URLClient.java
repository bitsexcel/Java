package javasec.samples.ch14;

import java.io.*;
import java.net.*;

public class URLClient {
    public static void main(String[] args) throws Exception {
        URL u = new URL(args[0]);
        URLConnection uc = u.openConnection();
        BufferedReader br = new BufferedReader(
                   new InputStreamReader(uc.getInputStream()));
        String s = br.readLine();
        while (s != null) {
            System.out.println(s);
            s = br.readLine();
        }
    }
}
