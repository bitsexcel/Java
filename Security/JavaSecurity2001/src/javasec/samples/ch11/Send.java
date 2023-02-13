package javasec.samples.ch11;

import java.io.*;
import java.security.*;

public class Send {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("test");
            MessageDigest md = MessageDigest.getInstance("SHA");
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
