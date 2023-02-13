package javasec.samples.ch05;

import java.security.*;

public class PayrollApp {
    NetworkMonitor nm;
    public void init() {
        class doInit implements PrivilegedAction {
            public Object run() {
                nm = new NetworkMonitor();
                return nm;
            }
        }
        doInit di = new doInit();
        AccessController.doPrivileged(di);
    }
}
