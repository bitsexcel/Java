package javasec.samples.appc;

import java.security.*;

public class NewEmployee {
    public static void main(String args[]) {
        try {
            IdentityScope is = IdentityScope.getSystemScope();
            Signer origSigner = (Signer) is.getIdentity(args[0]);

            System.out.println(
                        "Please insert the floppy for " + args[0]);
            System.out.print("Press enter when ready:  ");
            System.in.read();
            XYZFileScope privateScope =
                        new XYZFileScope("/floppy/floppy0/private");
            XYZSigner newSigner = new XYZSigner(args[0], privateScope);
            KeyPair kp = new KeyPair(origSigner.getPublicKey(),
                                      origSigner.getPrivateKey());
            newSigner.setKeyPair(kp);
            newSigner.setInfo(origSigner.getInfo());
            Certificate certs[] = origSigner.certificates();
            for (int i = 0; i < certs.length; i++)
                newSigner.addCertificate(certs[i]);
            newSigner.setTrust(Integer.parseInt(args[1]));
            privateScope.save();

            XYZFileScope sharedScope =
                        new XYZFileScope("/auto/shared/sharedScope");
            XYZIdentity newId = new XYZIdentity(args[0], sharedScope);
            newId.setPublicKey(origSigner.getPublicKey());
            newId.setInfo(origSigner.getInfo());
            certs = origSigner.certificates();
            for (int i = 0; i < certs.length; i++)
                newId.addCertificate(certs[i]);
            newId.setTrust(Integer.parseInt(args[1]));
            sharedScope.save();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
