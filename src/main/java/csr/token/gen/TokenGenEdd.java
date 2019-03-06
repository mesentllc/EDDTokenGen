package csr.token.gen;

import org.apache.log4j.Logger;

import com.fedex.security.client.ClientCipherProviderFactory;
import com.fedex.security.client.KeystoreCipherProviderImpl;
import com.fedex.security.client.PkcTokenGeneratorImpl;
import com.fedex.security.client.TokenGenerator;

public class TokenGenEdd {
	private static final Logger logger = Logger.getLogger(TokenGenEdd.class);
	private static final String ROOT_PATH = "/opt/fedex/ssm/app6998";
	private static final String CERT_FILE = ROOT_PATH + "/APP6998.p12";
	private static final String CLIENT_FILE = ROOT_PATH + "/client.properties";
	private static final String SECURITY_FILE = ROOT_PATH + "/../security.properties";

	// REMEMBER to change the EAI in the fp.properties as well as the paths -- this application uses it to build the token generator.
	static {
		KeystoreCipherProviderImpl.setAbsolutePathOfClientFile(CLIENT_FILE);
		KeystoreCipherProviderImpl.setAbsolutePathOfCert(CERT_FILE);
		ClientCipherProviderFactory.configure(KeystoreCipherProviderImpl.getInstance(SECURITY_FILE));
		TokenGenerator gen = PkcTokenGeneratorImpl.getInstance(SECURITY_FILE);
		gen.configure(CLIENT_FILE);
	}

	public static String getToken(String... params) {
		TokenGenerator tokenGen = PkcTokenGeneratorImpl.getInstance();
		String token;
		String serviceAppId = null;
		String clientUserId = null;

		if (params == null) {
			logger.error("Need to supply at least the service app id.");
			return null;
		}
		if (params.length > 0) {
			serviceAppId = params[0];
		}
		if (params.length > 1) {
			clientUserId = params[1];
		}
		if (clientUserId != null && clientUserId.length() > 0) {
			token = tokenGen.getToken(serviceAppId, clientUserId);
		}
		else {
			token = tokenGen.getToken(serviceAppId);
		}
		return token;
	}

	public static void main(String args[]) {
		logger.info("EddDrsBillingFlagsService Token: " + getToken("EddDrsBillingFlagsService"));
		logger.info("EddUtilityService Token: " + getToken("EddUtilityService"));
		logger.info("943415_cds Token: " + getToken("943415_cds"));
	}
}
