package javasec.samples.ch11;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javasec.samples.ch10.KeyStoreHandler;

public class SendMac {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("test");
            Mac mac = Mac.getInstance("HmacSHA1");

            KeyStoreHandler ksh = new KeyStoreHandler(null);
            KeyStore ks = ksh.getKeyStore();
            mac.init((SecretKey) ks.getKey(args[0], args[1].toCharArray()));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            String data = "This have I thought good to deliver thee, "+
                "that thou mightst not lose the dues of rejoicing " +
                "by being ignorant of what greatness is promised thee.";
            byte buf[] = data.getBytes();
            mac.update(buf);
            oos.writeObject(data);
            oos.writeObject(mac.doFinal());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
