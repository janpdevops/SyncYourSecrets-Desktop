package de.petranek.syncyoursecrets.swing.file;

import java.awt.event.ActionEvent;


import javax.swing.AbstractAction;



import de.petranek.syncyoursecrets.swing.model.EncryptedFile;
import de.petranek.syncyoursecrets.swing.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OnPasswordAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4056859217519731258L;

	/** The Constant logger. */
	static final Logger logger = LogManager.getLogger(OnPasswordAction.class);

	private PasswordDialog passwordDialog;

	public OnPasswordAction(EncryptedFile encryptedFile) {
		Model.getModel().setCurrentFile(encryptedFile);
		putValue(NAME, "OK");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getPasswordDialog() != null) {
			EncryptedFile encryptedFile = Model.getModel().getCurrentFile();
			encryptedFile.setFilePw(getPasswordDialog().getPassword());

			encryptedFile.loadEncryptedFile();
			getPasswordDialog().dispose();
		} else {
			logger.error("Callback to Password dialog was null");
		}

	}

	public PasswordDialog getPasswordDialog() {
		return passwordDialog;
	}

	public void setPasswordDialog(PasswordDialog passwordDialog) {
		this.passwordDialog = passwordDialog;
	}

}
