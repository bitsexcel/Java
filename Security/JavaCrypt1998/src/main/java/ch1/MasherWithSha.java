package ch1;

import java.io.*;
import java.security.*;

//import sun.misc.*;	estar	daniel18
import java.util.Base64;

public class MasherWithSha{

public static void main(String[] args) {
  try{

    if( args.length != 1){
        System.out.println("Usage: Masher filename");
        return;
    }
    
    // Obtain a message digest object.
    MessageDigest mdmd5 = MessageDigest.getInstance("MD5");
    MessageDigest mdsha1 = MessageDigest.getInstance("SHA-1");
    MessageDigest mdsha256 = MessageDigest.getInstance("SHA-256");    

    // Calculate the digest for the given file.
    FileInputStream in = new FileInputStream(args[0]);
    //byte[] buffer = new byte[8192];
    byte[] buffer = new byte[1024];
    int length;
    while ((length = in.read(buffer)) != -1){
      mdmd5.update(buffer, 0, length);
      mdsha1.update(buffer, 0, length);
      mdsha256.update(buffer, 0, length);

    }
    byte[] rawmd5 = mdmd5.digest();
    byte[] rawsha1 = mdsha1.digest();
    byte[] rawsha256 = mdsha256.digest();

    // Print out the digest in base64.
    //BASE64Encoder encoder = new BASE64Encoder();
    //String base64 = encoder.encode(raw);
    String base64md5 = Base64.getEncoder().encodeToString(rawmd5);
    String base64sha1 = Base64.getEncoder().encodeToString(rawsha1);
    String base64sha256 = Base64.getEncoder().encodeToString(rawsha256);
    System.out.println("MD5: " +base64md5);
    System.out.println("SHA1: " +base64sha1);
    System.out.println("SHA256: " +base64sha256);
  }
  catch(Exception ex){
    System.out.println("Exception: " +ex.getMessage());
  }
    
}
}