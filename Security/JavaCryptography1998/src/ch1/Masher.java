package ch1;

import java.io.*;
import java.security.*;

//import sun.misc.*;	estar	daniel18
import java.util.Base64;

public class Masher{

public static void main(String[] args) {
  try{

    if( args.length != 1){
        System.out.println("Usage: Masher filename");
        return;
    }
    
    // Obtain a message digest object.
    MessageDigest md = MessageDigest.getInstance("MD5");
    
    // Calculate the digest for the given file.
    FileInputStream in = new FileInputStream(args[0]);
    byte[] buffer = new byte[8192];
    int length;
    while ((length = in.read(buffer)) != -1)
		//System.out.println(length);
      md.update(buffer, 0, length);
    byte[] raw = md.digest();

    // Print out the digest in base64.
    //BASE64Encoder encoder = new BASE64Encoder();
    //String base64 = encoder.encode(raw);
    String base64 = Base64.getEncoder().encodeToString(raw);
    System.out.println(base64);
  }
  catch(Exception ex){
    System.out.println("Exception: " +ex.getMessage());
  }
    
}
}