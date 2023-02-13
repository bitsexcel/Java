package javasec.samples.ch10;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class StoreKey {
    public static void main(String[] args) throws Exception {
        KeyStoreHandler ksh = new KeyStoreHandler(args[1].toCharArray());
        KeyStore ks = ksh.getKeyStore();

        KeyGenerator kg = KeyGenerator.getInstance("DES");
        SecretKey sk = kg.generateKey();
        ks.setKeyEntry(args[0], sk, args[2].toCharArray(), null);
        ksh.store();
    }
}
