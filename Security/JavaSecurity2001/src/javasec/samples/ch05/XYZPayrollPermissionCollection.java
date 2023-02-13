package javasec.samples.ch05;

import java.util.*;
import java.security.*;
import java.util.*;

public class XYZPayrollPermissionCollection extends PermissionCollection {
    private Hashtable permissions;
    // We keep track of whether the * name has been added to make
    // the implies method simpler.
    private boolean addedAdmin;
    private int adminMask;

    XYZPayrollPermissionCollection() {
        permissions = new Hashtable();
        addedAdmin = false;
    }

    public void add(Permission p) {
        // Required test
        if (isReadOnly())
            throw new IllegalArgumentException("Read only collection");

        // This is a homogenous collection, as are all 
        // PermissionCollections that you'll implement.
        if (!(p instanceof XYZPayrollPermission))
            throw new IllegalArgumentException("Wrong permission type");

        XYZPayrollPermission xyz = (XYZPayrollPermission) p;
        String name = xyz.getName();
        XYZPayrollPermission other = 
                       (XYZPayrollPermission) permissions.get(name);
        if (other != null)
            xyz = merge(xyz, other);
        // An administrative permission. The administrative permission
        // may have only view or only update or both, and multiple
        // admin permissions may be added, so the masks are OR-ed
        // together.
        if (name.equals("*")) {
            addedAdmin = true;
            adminMask = xyz.mask | adminMask;
        }
        permissions.put(name, xyz);
    }

    public Enumeration elements() {
        return permissions.elements();
    }

    public boolean implies(Permission p) {
        if (!(p instanceof XYZPayrollPermission))
            return false;
        XYZPayrollPermission xyz = (XYZPayrollPermission) p;
        // If the admin name is present, then all names are implied;
        // we need check only the permissions.
        if (addedAdmin && (adminMask & xyz.mask) == xyz.mask)
            return true;
        // Otherwise, we can just see if the given individual is
        // in our table and if so, see if the permissions match.
        Permission inTable = (Permission) permissions.get(xyz.getName());
        if (inTable == null)
            return false;
        return inTable.implies(xyz);
    }

    // This is called when the same name is added twice to the
    // permissions; we merge the action lists and only store the
    // name once.
    private XYZPayrollPermission merge(XYZPayrollPermission a,
                                       XYZPayrollPermission b) {
        String aAction = a.getActions();
        if (aAction.equals(""))
            return b;
        String bAction = b.getActions();
        if (bAction.equals(""))
            return a;
        return new XYZPayrollPermission(a.getName(), aAction + "," + bAction);
    }
}
