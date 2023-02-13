package javasec.samples.appd;

import java.io.*;

public class TestSecurityManager {
    public static void main(String args[]) throws Exception {
        System.setSecurityManager(new MySecurityManager());
        FileReader f = new FileReader("TestSecurityManager.java");
    }
}
