package chapter15;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;

import org.bouncycastle.pqc.jcajce.interfaces.StateAwareSignature;
import org.bouncycastle.pqc.jcajce.interfaces.XMSSPrivateKey;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

import static chapter15.XMSSUtils.generateXMSSKeyPair;

/**
 * Example of use and effect of a private key running out of usages with XMSS.
 */
public class XMSSExceptionExample
{
    public static void main(String[] args)
        throws GeneralSecurityException
    {
        byte[] msg = Strings.toByteArray("hello, world!");

        KeyPair kp = generateXMSSKeyPair();
        XMSSPrivateKey privKey = (XMSSPrivateKey)kp.getPrivate();

        Signature xmssSig = Signature.getInstance("XMSS", "BCPQC");

        // this uses extractKeyShard() to get a single use key.
        XMSSPrivateKey sigKey = privKey.extractKeyShard(1);
        //Uncomment this line back in to make the example complete
//        sigKey = privKey.extractKeyShard(2);

        xmssSig.initSign(sigKey);

        xmssSig.update(msg, 0, msg.length);

        byte[] s1 = xmssSig.sign();

        // a 0 value here tells us the key can no longer be used.
        System.out.println("xmssSig usages remaining: "
                                     + sigKey.getUsagesRemaining());
        
        xmssSig.update(msg, 0, msg.length);

        //this line will only work if extractKeyShard() was called with more than 1.
        byte[] s2 = xmssSig.sign();

    }
}
