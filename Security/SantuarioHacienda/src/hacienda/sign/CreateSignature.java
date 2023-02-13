package hacienda.sign;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;

public class CreateSignature {
	static org.apache.commons.logging.Log log = 
		       org.apache.commons.logging.LogFactory.getLog(CreateSignature.class.getName());
	static {
        org.apache.xml.security.Init.init();
    }

	public static void main(String unused[]) throws Exception {
		// TODO Auto-generated method stub
		ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "ds");
		 String keystoreType = "JKS";
	        String keystoreFile = "samples/data/keystore.jks";
	        String keystorePass = "xmlsecurity";
	        String privateKeyAlias = "test";
	        String privateKeyPass = "xmlsecurity";
	        String certificateAlias = "test";
	        File signatureFile = new File("build/signature.xml");

	        KeyStore ks = KeyStore.getInstance(keystoreType);
	        FileInputStream fis = new FileInputStream(keystoreFile);
	        
	        log.info("made");

	}

}
