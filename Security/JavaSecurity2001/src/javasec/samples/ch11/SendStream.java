package javasec.samples.ch11;

import java.io.*;
import java.security.*;

public class SendStream {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("test");
            MessageDigest md = MessageDigest.getInstance("SHA");
            DigestOutputStream dos = new DigestOutputStream(fos, md);
            ObjectOutputStream oos = new ObjectOutputStream(dos);
            String data = "This have I thought good to deliver thee, "+
                "that thou mightst not lose the dues of rejoicing " +
                "by being ignorant of what greatness is promised thee.";
            oos.writeObject(data);
            dos.on(false);
            oos.writeObject(md.digest());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
