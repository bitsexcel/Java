package javasec.samples.ch14;

import java.net.*;
import java.security.cert.X509Certificate;
import java.security.interfaces.*;
import java.security.*;
import java.util.*;

import javax.net.ssl.*;
import com.sun.net.ssl.*;

public class SSLKeyManager implements X509KeyManager {
    protected String alias;
    protected KeyStore ks;
    protected char[] pw;
    private String type;
    private String issuer;

    SSLKeyManager(KeyStore ks, String s, char[] pw) {
        this.ks = ks;
        alias = s;
        this.pw = pw;
        try {
            java.security.cert.Certificate c = ks.getCertificate(s);
            type = c.getPublicKey().getAlgorithm();
            issuer = ((X509Certificate) c).getIssuerDN().getName();
        } catch (Exception e) {
            throw new IllegalArgumentException(s + " has a bad key");
        }
    }

    public String chooseClientAlias(String type, Principal[] issuers) {
        if (!type.equals(this.type))
            return null;
        if (issuers == null)
            return alias;
        for (int i = 0; i < issuers.length; i++) { 
            if (issuer.equals(issuers[i].getName()))
                return alias;
        }
        return null;
    }

    public String chooseServerAlias(String type, Principal[] issuers) {
        return chooseClientAlias(type, issuers);
    }

    // Get the certificates -- make sure they are an X509Certificate
    // before copying them into the array.
    public X509Certificate[] getCertificateChain(String s) {
        try {
            java.security.cert.Certificate[] c =
                                ks.getCertificateChain(s);
            Vector c2 = new Vector(c.length);
            for (int i = 0; i < c.length; i++)
                c2.add(c[i]);
            return (X509Certificate[])
                    c2.toArray(new X509Certificate[0]);
        } catch (KeyStoreException kse) {
            return null;
        }
    }

    public String[] getClientAliases(String type, Principal[] p) {
        String[] s;
        String alias = chooseClientAlias(type, p);
        if (alias == null)
            s = new String[0];
        else {
            s = new String[1];
            s[0] = alias;
        }
        return s;
    }

    public String[] getServerAliases(String type, Principal[] p) {
        return getClientAliases(type, p);
    }

    public PrivateKey getPrivateKey(String alias) {
        try {
            return (PrivateKey) ks.getKey(alias, pw);
        } catch (Exception e) {
            return null;
        }
    }
}
