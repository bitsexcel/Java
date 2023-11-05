package ch13.app;

import java.io.IOException;
import java.security.Security;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.crypto.ntru.NTRUSigningKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUSigningKeyPairGenerator;
import org.bouncycastle.pqc.crypto.ntru.NTRUSigningPrivateKeyParameters;
import org.bouncycastle.util.encoders.Hex;

import ch13.lattice.net.sf.ntru.encrypt.EncryptionKeyPair;

public class lattice_crypto {
	public static void main(String[] args) throws IOException 
	{	
		//We test if the Bouncy Castle is installed        
		Security.addProvider(new BouncyCastleProvider());
        // BC stands for Bouncy Castle
        if (Security.getProvider("BC") == null)
        {
            System.out.println("Bouncy Castle is not installed. For installation, follow the link: https://www.itcsolutions.eu/2011/08/22/how-to-use-bouncy-castle-cryptographic-api-in-netbeans-or-eclipse-for-java-jse-projects/");
        }
        else
        {
            System.out.println("Bouncy Castle is installed");
            System.out.println("Welcome to Apress. Good luck in learning new ways for implementing cryptography mechanisms");
            System.out.println("------------------------------------------------------------------------------------------");            
             
            
            NTRUSigningKeyPairGenerator ntruSigningKeyPairGenerator = new NTRUSigningKeyPairGenerator();
            NTRUSigningKeyGenerationParameters ntruSigningKeyGenerationParameters = NTRUSigningKeyGenerationParameters.TEST157;
            ntruSigningKeyPairGenerator.init(ntruSigningKeyGenerationParameters);
            AsymmetricCipherKeyPair asymmetricCipherKeyPair = ntruSigningKeyPairGenerator.generateKeyPair();
            NTRUSigningPrivateKeyParameters params = (NTRUSigningPrivateKeyParameters) asymmetricCipherKeyPair.getPrivate();
            System.out.println(Hex.toHexString(params.getEncoded()));
            
        }
    }
	
}
