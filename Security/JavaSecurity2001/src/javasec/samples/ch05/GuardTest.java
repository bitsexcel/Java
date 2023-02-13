package javasec.samples.ch05;

import java.security.*;

// This is a dummy class; it would usually be the object
// that we want to protect access to (e.g. a real payroll
// request would fall into that category).
class XYZPayrollRequest {}

public class GuardTest {
    public static void main(String args[]) {
        // Protect the given object by requiring the given
        // permission.
        GuardedObject go = new GuardedObject(new XYZPayrollRequest(),
                         new XYZPayrollPermission("sdo", "view"));
        try {
            Object o = go.getObject();
            System.out.println("Got access to object");
        } catch (AccessControlException ace) {
            System.out.println("Can't access object");
        }
    }
}
