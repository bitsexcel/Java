package javasec.samples.ch13;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class CipherTest {
    public static void main(String args[]) {
        try {
            // First, we need a key; we'll just generate one
            // though we could look one up in the keystore or
            // obtain one via a key agreement algorithm
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
            Key key = kg.generateKey();
            
	    // Now we'll do the encryption. We'll also retrieve
	    // the initialization vector, which the engine will
	    // calculate for us.
            c.init(Cipher.ENCRYPT_MODE, key);
            byte input[] = "Stand and unfold yourself".getBytes();
            byte encrypted[] = c.doFinal(input);
            byte iv[] = c.getIV();

            // Now we'll do the decryption. The initialization
            // vector can be transmitted to the recipient with
            // the ciphertext, but the key must be transmitted
            // securely.
            IvParameterSpec dps = new IvParameterSpec(iv);
            c.init(Cipher.DECRYPT_MODE, key, dps);
            byte output[] = c.doFinal(encrypted);
            System.out.println("The string was ");
            System.out.println(new String(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
