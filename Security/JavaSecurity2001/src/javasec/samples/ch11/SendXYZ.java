package javasec.samples.ch11;

import java.io.*;
import java.security.*;
import javasec.samples.ch08.XYZProvider;

public class SendXYZ {
    public static void main(String args[]) {
        try {
            Security.addProvider(new XYZProvider());
            FileOutputStream fos = new FileOutputStream("test.xyz");
            MessageDigest md = MessageDigest.getInstance("XYZ");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            String data = "This have I thought good to deliver thee, "+
                "that thou mightst not lose the dues of rejoicing " +
                "by being ignorant of what greatness is promised thee.";
            byte buf[] = data.getBytes();
            md.update(buf);
            oos.writeObject(data);
            oos.writeObject(md.digest());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
