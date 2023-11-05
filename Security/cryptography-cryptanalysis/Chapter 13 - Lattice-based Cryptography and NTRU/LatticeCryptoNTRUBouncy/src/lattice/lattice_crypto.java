package lattice;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import net.sf.ntru.encrypt.EncryptionKeyPair;

public class lattice_crypto {
	public static void main(String[] args) 
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
            System.out.println(Hex.encodeHexString(params.getEncoded()));
            
        }
    }
	
}
