package javasec.samples.ch04;

import java.io.*;

public class Cat {
    public static void main(String args[]) {
        try {
            String s;
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            while ((s = br.readLine()) != null)
                System.out.println(s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
