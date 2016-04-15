import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.util.Calendar;

import org.spongycastle.jce.provider.BouncyCastleProvider;

//import com.andium.java.components.*;
import com.andium.java.components.exceptions.X509CertBuilderConfigurationException;
import com.andium.java.components.security.DistinguishedNameBuilder;
import com.andium.java.components.security.ECKeys;
import com.andium.java.components.security.Sha256Hash;
import com.andium.java.components.security.X509Cert;
import com.andium.java.components.security.X509CertBuilder;
import com.andium.java.components.security.X509CertSigningRequest;


public class test {
	
	  private static void setUp() throws Exception {
	       // SecureRandom secureRandom = new SecureRandom();

	        BouncyCastleProvider prov = new org.spongycastle.jce.provider.BouncyCastleProvider();
	        Security.addProvider(prov);
	    }

	 

    
	public static void main(String [ ] args) throws Exception
	{
	    setUp();
	    String pem_string;
	    
        
        //Generate CA Certificate 
	    
        try {
            ECKeys keys = new ECKeys();     //pub
            ECKeys certKeys = new ECKeys(); //priv
            
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, 3);

            
            X509CertBuilder certBuilder = new X509CertBuilder(
                    DistinguishedNameBuilder.createURNDistinguishedName("caMQTTBokerID", "MQTT.broker"),
                    new X509CertSigningRequest(DistinguishedNameBuilder.createURNDistinguishedName("testID", "subjectDN"), certKeys),
                    c.getTime()
            );
            
            certBuilder.setCertificateKeys(certKeys);
            certBuilder.setSigningKeys(keys);
            
            certBuilder.setCanSignOtherCerts(true); //CA this cert can sign csr's
            X509Cert cert = certBuilder.build();
            cert.printCertificateToConsole();
            cert.checkValidity();
            cert.getCertificate();
            cert.writeCertificateToDERFile("cacert");
            cert.writeCertificateToPEMFile("cacert");
            cert.verify(keys.getPublicKeyObject(keys));
           // cert.verifyCertificate(cert.getCertificate(), cert.getCertificate(), cert.getCertificate());
            System.out.println(keys.getPublicKeyObject(keys));
            
            
            //create a cert signing request *should be generated by android node*
            X509CertSigningRequest csr = new X509CertSigningRequest(
                    DistinguishedNameBuilder.createURNDistinguishedName("android-test-csr","254c1afd-7bfb-4792-a7d7-6717017fa484"),
                    new ECKeys()
            );
            csr.getPEMObject();
            System.out.println("CSR string" + csr.getPEMString());
            
            
            //build a client cert
            ECKeys client_keys = new ECKeys();  //public key
            ECKeys client_certKeys = new ECKeys();
            
            
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, 3);

            
            X509CertBuilder client_certBuilder = new X509CertBuilder(
                    DistinguishedNameBuilder.createURNDistinguishedName("androidMQTTid", "MQTT.client"),
                    new X509CertSigningRequest(DistinguishedNameBuilder.createURNDistinguishedName("testID", "subjectDN"), certKeys),
                    cal.getTime()
            );
            
            client_certBuilder.setCertificateKeys(client_certKeys);
            client_certBuilder.setSigningKeys(client_keys);
            
            client_certBuilder.setCanSignOtherCerts(false); //CA this is just a node cert signed by broker
            X509Cert client_cert = client_certBuilder.build();
            client_cert.printCertificateToConsole();
            client_cert.checkValidity();
            client_cert.getCertificate();
            client_cert.writeCertificateToPEMFile("clientcert");
           // cert.verifyCertificate(cert.getCertificate(), cert.getCertificate(), cert.getCertificate());
            System.out.println(client_keys.getPublicKeyObject(client_keys));
            
            
                                  
                      
            client_cert.verify(client_keys.getPublicKeyObject(client_keys));
            
            

        } catch (X509CertBuilderConfigurationException f){
            f.printStackTrace();
            fail();
        } catch (IOException f){
            f.printStackTrace();
            fail();
        } catch (CertificateEncodingException f){
            f.printStackTrace();
            fail();
        }
      
		
		
		
	}
	
	
}
