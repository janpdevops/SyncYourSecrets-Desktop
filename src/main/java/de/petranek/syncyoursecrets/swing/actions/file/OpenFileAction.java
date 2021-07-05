package de.petranek.syncyoursecrets.swing.actions.file;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;



import de.petranek.syncyoursecrets.swing.file.OnPasswordAction;
import de.petranek.syncyoursecrets.swing.file.PasswordDialog;
import de.petranek.syncyoursecrets.swing.model.EncryptedFile;
import de.petranek.syncyoursecrets.swing.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenFileAction extends AbstractAction {

	public OpenFileAction() {
		putValue(NAME, "Open File...");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6731702178904791406L;
	/** The Constant logger. */
	static final Logger logger = LogManager.getLogger(OpenFileAction.class);

	@Override
	public void actionPerformed(ActionEvent e) {

		// Create a file chooser
		final JFileChooser fc = new JFileChooser();

		// In response to a button click:

		int returnVal = fc.showOpenDialog((Component) e.getSource());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			EncryptedFile ef = new EncryptedFile();
			ef.setOpenedFile(file);
			logger.debug("Opening: " + file.getName());
			Model.getModel().setCurrentFile(ef);

			OnPasswordAction action = new OnPasswordAction(ef);
			PasswordDialog pwDialog = new PasswordDialog(action);
			pwDialog.setVisible(true);

		} else {
			logger.info("Open command cancelled by user.");
		}

	}

}
