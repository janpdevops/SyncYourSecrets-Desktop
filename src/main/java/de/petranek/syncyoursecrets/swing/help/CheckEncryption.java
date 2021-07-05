package de.petranek.syncyoursecrets.swing.help;



import de.petranek.syncyoursecrets.util.EnryptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckEncryption {

	/** The Constant logger. */
	static final Logger logger = LogManager.getLogger(CheckEncryption.class);

	/**
	 * Check encryption.
	 * 
	 * @return the encryption test result
	 */
	public CheckEncryptionResult checkEncryption() {
		CheckEncryptionResult result = new CheckEncryptionResult();

		// by default, the message is OK = information
		// result.setMessageStyle(SWT.ICON_WORKING);

		String simplePw = "abc";
		String plaintext = "plaintext";
		String message;
		try {
			logger.info("Starting weak encryption test");

			String cypher = EnryptionUtil.encryptString(plaintext, simplePw);
			String decrypted = EnryptionUtil.decryptString(cypher, simplePw);

			logger.debug("Weak encryption test: plaintext [" + plaintext + "] decrypted message [" + decrypted + "]");

			if (plaintext.equals(decrypted)) {
				message = "Weak encryption test OK\n";
				logger.info(message);
				result.append(message);

			} else {
				result.setTitle("Weak encryption test ERROR");

				message = "Weak encryption failed\n " + " Encryption operation has garbled test data.\n"
						+ "This is a serious issue, do not use SyncYourSecrets\n";
				result.append(message);
				logger.fatal(message);
				// result.setMessageStyle(SWT.ICON_ERROR);
				return result; // return, further tests are futile
			}

		} catch (Exception e) {
			result.setTitle("Weak encryption test ERROR");

			message = "Weak encryption failed\n " + " An unexpected error occured, while encryption / decrypting "
					+ "with a weak password. \n " + "This is a serious issue, do not use SyncYourSecrets\n";
			result.append(message);
			logger.fatal(message);
			logger.debug("Weak encryption failed: ", e);

			// result.setMessageStyle(SWT.ICON_ERROR);
			return result;

		}

		String hardPw = "abcdefghijklmnopqrstuvwxyz";
		logger.info("Starting strong encryption test");
		try {
			String cypher = EnryptionUtil.encryptString(plaintext, hardPw);
			String decrypted = EnryptionUtil.decryptString(cypher, hardPw);

			logger.debug("Strong encryption test: plaintext [" + plaintext + "] decrypted message [" + decrypted + "]");

			if (plaintext.equals(decrypted)) {
				message = "Strong encryption test OK\n" + "Encryption seems OK\n";
				result.setTitle("All encryption tests OK");

				result.append(message);
				logger.info(message);
			} else {
				result.setTitle("Strong encryption test ERROR\n");

				message = "Strong encryption failed\n " + " An unexpected error occured, while encryption / decrypting "
						+ "with a strong password. \n " + "This is a serious issue, do not use SyncYourSecrets";
				result.append(message);
				logger.fatal(message);
				// result.setMessageStyle(SWT.ICON_ERROR);

			}

		} catch (Exception e) {
			result.setTitle("Strong encryption test failed");

			message = "Strong encryption test failed\n" + "If the weak encryption has been successful, "
					+ "you may want to install JCE with strong cryptography support"
					+ " in your Java Runtime Environment.";
			result.append(message);
			logger.warn(message);
			// result.setMessageStyle(1);
		}

		return result;
	}
}
