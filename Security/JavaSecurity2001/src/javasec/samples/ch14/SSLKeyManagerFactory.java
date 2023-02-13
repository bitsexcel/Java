package javasec.samples.ch14;

import java.security.*;

import javax.net.ssl.*;
import com.sun.net.ssl.*;

public class SSLKeyManagerFactory extends KeyManagerFactorySpi {
    char[] pw;
    KeyStore ks;
    String alias;

    public SSLKeyManagerFactory() {
        alias = System.getProperty("xyz.aliasName");
        if (alias == null)
            throw new IllegalArgumentException("Must specify alias property");
    }

    protected KeyManager[] engineGetKeyManagers() {
        SSLKeyManager[] km = new SSLKeyManager[1];
        km[0] = new SSLKeyManager(ks, alias, pw);
        return km;
    }

    protected void engineInit(KeyStore ks, char[] pw) {
        this.ks = ks;
        this.pw = new char[pw.length];
        System.arraycopy(pw, 0, this.pw, 0, pw.length);
    }
}
