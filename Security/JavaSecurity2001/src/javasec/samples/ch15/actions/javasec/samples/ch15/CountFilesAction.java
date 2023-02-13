package javasec.samples.ch15;

import java.io.*;
import java.security.*;

class CountFilesAction implements PrivilegedAction {
    public Object run() {
        File f = new File(File.separatorChar + "files");
        File[] fArray = f.listFiles();
        return new Integer(fArray.length);
    }
}
