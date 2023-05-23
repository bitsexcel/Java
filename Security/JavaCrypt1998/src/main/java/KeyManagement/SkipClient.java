/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeyManagement;

/**
 *
 * @author jdaniel
 */
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
//import KeyManagement.Skip;
public class SkipClient {

    public static void main(String[] args) throws Exception {
        //args[0] = "127.0.0.1";
        //String host = args[0];
        String host = "127.0.0.1";
//        args[1] = "7999";
//        int port = Integer.parseInt(args[1]);
        int port = 7999;
        Skip skip = new Skip();
// Create a Diffie-Hellman key pair.
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
        kpg.initialize(skip.sDHParameterSpec);
        KeyPair keyPair = kpg.genKeyPair();
        // Open the network connection.
        Socket s = new Socket(host, port);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());
        // Send the public key.
        byte[] keyBytes = keyPair.getPublic().getEncoded();
        out.writeInt(keyBytes.length);
        out.write(keyBytes);
// Receive a public key.
        keyBytes = new byte[in.readInt()];
        in.readFully(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(keyBytes);
        PublicKey theirPublicKey = kf.generatePublic(x509Spec);
        // Generate the secret value.
        KeyAgreement ka = KeyAgreement.getInstance("DH");
        ka.init(keyPair.getPrivate());
        ka.doPhase(theirPublicKey, true);
        byte[] secret = ka.generateSecret();
// Clean up.
        out.close();
        in.close();
        // Print out the secret value
        //System.out.println(oreilly.jonathan.util.Base64.encode(secret));
        System.out.println(oreilly.jonathan.util.Base64.encode(secret));

    }
}
