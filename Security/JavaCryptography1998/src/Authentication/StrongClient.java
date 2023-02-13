package Authentication;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Date;

public class StrongClient {
  public void sendAuthentication(String user, PrivateKey key,
      OutputStream outStream) throws IOException, NoSuchAlgorithmException,
      InvalidKeyException, SignatureException {
    DataOutputStream out = new DataOutputStream(outStream);
    long t = (new Date()).getTime();
    double q = Math.random();

    Signature s = Signature.getInstance("DSA");
    s.initSign(key);
    s.update(Protection.makeBytes(t, q));
    byte[] signature = s.sign();

    out.writeUTF(user);
    out.writeLong(t);
    out.writeDouble(q);
    out.writeInt(signature.length);
    out.write(signature);
    out.flush();
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 5) {
      System.out.println(
          "Usage: StrongClient host keystore storepass alias keypass");
      return;
    }
    
    String host = args[0];
    String keystorefile = args[1];
    //String storepass = args[2];
    char[] storepass = args[2].toCharArray();
    String alias = args[3];
    //** edited String keypass = args[4];
    char[] keypass = args[4].toCharArray();
    
    int port = 7999;
    Socket s = new Socket(host, port);

    StrongClient client = new StrongClient();
    //KeyStore keystore = KeyStore.getInstance();
    KeyStore keystore = KeyStore.getInstance("JKS");
    //keystore.load(new FileInputStream(keystorefile), storepass);
    keystore.load(new FileInputStream(keystorefile), storepass);
    //PrivateKey key = keystore.getPrivateKey(alias, keypass);
    PrivateKey key = (PrivateKey)keystore.getKey(alias, keypass);
    client.sendAuthentication(alias, key, s.getOutputStream());

    s.close();
  }
}