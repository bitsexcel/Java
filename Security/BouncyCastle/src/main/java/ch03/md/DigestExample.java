package ch03.md;

import static ch03.md.JcaUtils.computeDigest;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

/**
 * A simple example of using a MessageDigest.
 */
public class DigestExample
{
    public static void main(String[] args)
        throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());
        System.out.println(
            Hex.toHexString(
                computeDigest("SHA-256", Strings.toByteArray("Hello World!"))));
    }
}
