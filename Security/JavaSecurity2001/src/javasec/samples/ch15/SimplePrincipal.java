package javasec.samples.ch15;

import java.security.*;
import java.io.*;

public class SimplePrincipal implements Principal, Serializable {
    private String name;

    public SimplePrincipal(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        if (!(o instanceof SimplePrincipal))
            return false;
        return ((SimplePrincipal) o).name.equals(name);
    }
}
