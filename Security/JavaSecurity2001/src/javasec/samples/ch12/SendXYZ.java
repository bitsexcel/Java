package javasec.samples.ch12;

import java.io.*;
import java.security.*;
import javasec.samples.ch08.XYZProvider;
import javasec.samples.ch10.KeyStoreHandler;

public class SendXYZ {
    public static void main(String args[]) {
        String data;
        data = "This have I thought good to deliver thee, " +
                "that thou mightst not lose the dues of rejoicing " +
                "by being ignorant of what greatness is promised thee.";

        try {
            Security.addProvider(new XYZProvider());
            FileOutputStream fos = new FileOutputStream("test");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            KeyStoreHandler ksh = new KeyStoreHandler(null);
            KeyStore ks = ksh.getKeyStore();
            PrivateKey pk = (PrivateKey) ks.getKey(args[0],
                                               args[1].toCharArray());

            Signature s = Signature.getInstance("XYZwithSHA");
            s.initSign(pk);

            byte buf[] = data.getBytes();
            s.update(buf);
            oos.writeObject(data);
            oos.writeObject(s.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
