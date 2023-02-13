package javasec.samples.appc;

import java.security.*;
import java.util.*;

public class ListIdentityScope {
    public static void main(String args[]) {
        try {
            IdentityScope is = IdentityScope.getSystemScope();
            System.out.println(is);
            Enumeration e = is.identities();
            while (e.hasMoreElements()) {
                Identity id = (Identity) e.nextElement();
                System.out.println(id);
            }
        } catch (Exception ex) {}
    }
}
