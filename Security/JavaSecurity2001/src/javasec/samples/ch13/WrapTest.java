package javasec.samples.ch13;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class WrapTest {
    public static void main(String[] args) throws Exception {
        // This is the key we want to transmit to another party
        KeyGenerator kg = KeyGenerator.getInstance("DESede");
        Key sharedKey = kg.generateKey();

        // We'll transmit it with PBE encryption, which is good for
        // sending keys but not arbitrary data
        String password = "Come you spirits that tend on mortal thoughts";
        byte[] salt = { (byte) 0xc9, (byte) 0x36, (byte) 0x78, (byte) 0x99,
                (byte) 0x52, (byte) 0x3e, (byte) 0xea, (byte) 0xf2 };
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 20);
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey passwordKey = kf.generateSecret(keySpec);
        Cipher c = Cipher.getInstance("PBEWithMD5AndDES");
        c.init(Cipher.WRAP_MODE, passwordKey, paramSpec);
        byte[] wrappedKey = c.wrap(sharedKey);

        // Now we'll initialize a cipher with the DESede key and encrypt
        // some data with it
        c = Cipher.getInstance("DESede");
        c.init(Cipher.ENCRYPT_MODE, sharedKey);
        byte[] input = "Stand and unfold yourself".getBytes();
        byte[] encrypted = c.doFinal(input);

        // Now we'll try to read the wrapped key and the encrypted data
        // First, we have to unwrap the key
        c = Cipher.getInstance("PBEWithMD5AndDES");

        // We'll reuse the key and param spec from before, but generally
        // we'd have to recreate it from the password
        c.init(Cipher.UNWRAP_MODE, passwordKey, paramSpec);
        Key unwrappedKey = c.unwrap(wrappedKey, "DESede", Cipher.SECRET_KEY);

        // Now we can use the unwrapped key to decrypt the data
        c = Cipher.getInstance("DESede");
        c.init(Cipher.DECRYPT_MODE, unwrappedKey);
        String newData = new String(c.doFinal(encrypted));
        System.out.println("The string was " + newData);
    }
}
