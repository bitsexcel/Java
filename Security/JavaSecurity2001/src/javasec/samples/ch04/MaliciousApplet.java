package javasec.samples.ch04;

import java.applet.*;

public class MaliciousApplet extends Applet {
    public void init() {
        try {
            Runtime.getRuntime().exec("rmdir foo");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String args[]) {
        MaliciousApplet a = new MaliciousApplet();
        a.init();
    }
}
