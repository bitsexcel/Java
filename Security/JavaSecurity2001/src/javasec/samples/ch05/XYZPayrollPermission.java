package javasec.samples.ch05;

import java.security.*;
import java.util.*;

public class XYZPayrollPermission extends Permission {

    protected int mask;
    static private int VIEW = 0x01;
    static private int UPDATE = 0x02;

    public XYZPayrollPermission(String name) {
        // Our permission must always have an action, so we
        // choose a default one here.
        this(name, "view");
    }

    public XYZPayrollPermission(String name, String action) {
        // Our superclass, however, does not support actions
        // so we don't provide one to that.
        super(name);
        parse(action);
    }

    private void parse(String action) {
        // Look in the action string for the words view and
        // update, separated by white space or by a comma
        StringTokenizer st = new StringTokenizer(action, ",\t ");

        mask = 0;
        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            if (tok.equals("view"))
                mask |= VIEW;
            else if (tok.equals("update"))
                mask |= UPDATE;
            else throw new IllegalArgumentException(
                                    "Unknown action " + tok);
        }
    }

    public boolean implies(Permission permission) {
        if (!(permission instanceof XYZPayrollPermission))
            return false;
        
        XYZPayrollPermission p = (XYZPayrollPermission) permission;
        String name = getName();
        // The name must be either the wildcard *, which signifies
        // all possible names, or the name must match our name
        if (!name.equals("*") && !name.equals(p.getName()))
            return false;
        // Similarly, the requested actions must all match actions
        // that we've been constructed with.
        if ((mask & p.mask) != p.mask)
            return false;
        // Only if both the action and name match do we return true.
        return true;
    }

    public boolean equals(Object o) {
        if (!(o instanceof XYZPayrollPermission))
            return false;
        
        // For equality, we check the name and action mask.
        // We must provide a method definition like this, since
        // the security system expects us to do a deep check for
        // equality rather than relying on object reference
        // equality.
        XYZPayrollPermission p = (XYZPayrollPermission) o;
        return ((p.getName().equals(getName())) && (p.mask == mask));
    }

    public int hashCode() {
        // We must always provide a hash code for permissions,
        // because the hashes must match if the permissions compare
        // as equals. The default implementation of this method
        // wouldn't provide that.
        return getName().hashCode() ^ mask;
    }

    public String getActions() {
        // This method must return the same string, no matter how
        // the action list was passed to the constructor.
        if (mask == 0)
            return "";
        else if (mask == VIEW)
            return "view";
        else if (mask == UPDATE)
            return "update";
        else if (mask == (VIEW | UPDATE))
            return "view, update";
        else throw new IllegalArgumentException("Unknown mask");
    }

    public PermissionCollection newPermissionsCollection() {
        // More about this in a later example.
        return new XYZPayrollPermissionCollection();
    }

    public static void main(String[] args) {
        XYZPayrollPermission p1 = new XYZPayrollPermission("sdo", "view");
        XYZPayrollPermission p2 = new XYZPayrollPermission(args[0], args[1]);
        System.out.println("P1 is " + p1);
        System.out.println("P2 is " + p2);
        System.out.println("P1 -> P2 is " + p1.implies(p2));
        System.out.println("P2 -> P1 is " + p2.implies(p1));
    }
}
