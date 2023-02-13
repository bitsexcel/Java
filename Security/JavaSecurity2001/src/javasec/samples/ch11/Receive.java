package javasec.samples.ch11;

import java.io.*;
import java.security.*;

public class Receive {
    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("test");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            if (!(o instanceof String)) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            String data = (String) o;
            System.out.println("Got message " + data);
            o = ois.readObject();
            if (!(o instanceof byte[])) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            byte origDigest[] = (byte []) o;
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(data.getBytes());
            if (MessageDigest.isEqual(md.digest(), origDigest))
                System.out.println("Message is valid");
            else System.out.println("Message was corrupted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
