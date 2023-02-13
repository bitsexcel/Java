package javasec.samples.ch11;

import java.io.*;
import java.security.*;
import javasec.samples.ch08.XYZProvider;

public class ReceiveXYZ {
    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("test.xyz");
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
            Security.addProvider(new XYZProvider());
            MessageDigest md = MessageDigest.getInstance("XYZ");
            md.update(data.getBytes());
            if (MessageDigest.isEqual(md.digest(), origDigest))
                System.out.println("Message is valid");
            else System.out.println("Message was corrupted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
