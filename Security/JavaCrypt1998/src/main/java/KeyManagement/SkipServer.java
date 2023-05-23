/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeyManagement;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 *
 * @author jdaniel
 * C:\ java SkipServer 7999
 */
public class SkipServer {

    public static void main(String[] args) throws Exception {
        //args[0] = "7999";
        //int port = Integer.parseInt(args[0]);
        int port = 7999;

        // Create a Diffie-Hellman key pair.
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
        kpg.initialize(Skip.sDHParameterSpec);
        KeyPair keyPair = kpg.genKeyPair();
        // Wait for a connection.
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Listening on port " + port + "...");
        Socket s = ss.accept();
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());
        // Receive a public key.
        byte[] keyBytes = new byte[in.readInt()];
        in.readFully(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(keyBytes);
        PublicKey theirPublicKey = kf.generatePublic(x509Spec);
        // Send the public key.
        keyBytes = keyPair.getPublic().getEncoded();
        out.writeInt(keyBytes.length);
        out.write(keyBytes);
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
    }
}
