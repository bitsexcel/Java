package javasec.samples.ch05;

import java.applet.*;
import java.net.*;
import java.security.*;

public class AccessTest extends Applet {
    public void init() {
        SocketPermission sp = new SocketPermission(
                            getParameter("host") + ":6000", "connect");
        try {
            AccessController.checkPermission(sp);
            System.out.println("Ok to open socket");
        } catch (AccessControlException ace) {
            System.out.println(ace);
        }
    }
}
