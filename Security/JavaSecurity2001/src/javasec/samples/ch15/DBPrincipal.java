package javasec.samples.ch15;

import java.io.*;
import java.util.*;
import java.security.*;
import javax.security.auth.*;
import com.sun.security.auth.*;

public class DBPrincipal implements Principal, PrincipalComparator, Serializable {
    private String name;

    public DBPrincipal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean implies(Subject s) {
        Set set = s.getPrincipals(DBPrincipal.class);
        Iterator i = set.iterator();
        if (i.hasNext() && name.equals("DBA"))
            return true;
        try {
            while (true) {
                DBPrincipal p = (DBPrincipal) i.next();
                if (p.equals(this))
                    return true;
            }
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof DBPrincipal))
            return false;
        return ((DBPrincipal) o).name.equals(name);
    } 
}
