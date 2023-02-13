package javasec.samples.ch11;

import java.io.*;
import java.security.*;

public class SendSelfMac {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("test");
            MessageDigest md = MessageDigest.getInstance("SHA");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            String data = "This have I thought good to deliver thee, "+
                "that thou mightst not lose the dues of rejoicing " +
                "by being ignorant of what greatness is promised thee.";
	    String passphrase = "Sleep no more";
            byte buf[] = data.getBytes();
            byte pass[] = passphrase.getBytes();
            md.update(pass);
            md.update(buf);
            byte digest1[] = md.digest();
            md.update(pass);
            md.update(digest1);
            oos.writeObject(data);
            oos.writeObject(md.digest());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
