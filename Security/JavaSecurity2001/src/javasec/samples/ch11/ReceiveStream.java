import java.io.*;
import java.security.*;

public class ReceiveStream {
    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("test");
            MessageDigest md = MessageDigest.getInstance("SHA");
            DigestInputStream dis = new DigestInputStream(fis, md);
            ObjectInputStream ois = new ObjectInputStream(dis);
            Object o = ois.readObject();
            if (!(o instanceof String)) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            String data = (String) o;
            System.out.println("Got message " + data);
            dis.on(false);
            o = ois.readObject();
            if (!(o instanceof byte[])) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            byte origDigest[] = (byte []) o;
            if (MessageDigest.isEqual(md.digest(), origDigest))
                System.out.println("Message is valid");
            else System.out.println("Message was corrupted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
