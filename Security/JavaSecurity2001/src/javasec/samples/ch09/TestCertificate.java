package javasec.samples.ch09;

import java.security.*;
import java.security.cert.*;
import java.io.*;

public class TestCertificate {
    // Techniques to implement this method are shown in the next chapter.
    PublicKey getPublicKey(Principal p) {
        return null;
    }

    // Implementations of this method depend on the CA in use and are
    // left to the reader.
    InputStream lookupCRLFile(Principal p) {
        return null;
    }

    public java.security.cert.Certificate importCertificate(byte data[])
                              throws CertificateException {
        X509Certificate c = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            c = (X509Certificate) cf.generateCertificate(bais);
            Principal p = c.getIssuerDN();
            PublicKey pk = getPublicKey(p);
            c.verify(pk);
            InputStream crlFile = lookupCRLFile(p);
            cf = CertificateFactory.getInstance("X509CRL");
            X509CRL crl = (X509CRL) cf.generateCRL(crlFile);
            if (crl.isRevoked(c))
                    throw new CertificateException("Certificate revoked");
        } catch (NoSuchAlgorithmException nsae) {
            throw new CertificateException("Can't verify certificate");
        } catch (NoSuchProviderException nspe) {
            throw new CertificateException("Can't verify certificate");
        } catch (SignatureException se) {
            throw new CertificateException("Can't verify certificate");
        } catch (InvalidKeyException ike) {
            throw new CertificateException("Can't verify certificate");
        } catch (CRLException ce) {
            // treat as no crl
        }
        return c;
    }
}
