package de.petranek.syncyoursecrets.swing.model;

import java.io.File;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.petranek.syncyoursecrets.model.pw.PWList;
import de.petranek.syncyoursecrets.util.SysXmlBaseException;
import de.petranek.syncyoursecrets.util.XmlSerializeTool;

public class EncryptedFile {

	/** The Constant logger. */
	static final Logger logger = LogManager.getLogger(EncryptedFile.class);

	private String filePw;

	private PWList pwList;

	public PWList getPwList() {
		return pwList;
	}

	public void setPwList(PWList pwList) {
		this.pwList = pwList;
	}

	public String getFilePw() {
		return filePw;
	}

	public void setFilePw(String filePw) {
		this.filePw = filePw;
	}

	public File getOpenedFile() {
		return openedFile;
	}

	public void setOpenedFile(File openedFile) {
		this.openedFile = openedFile;
	}

	private File openedFile;

	/**
	 * Load an encrypted file.
	 *
	 *
	 */
	public void loadEncryptedFile() {

		logger.debug("entering loadEncryptedFile for file " + getOpenedFile().getAbsolutePath());
		try {

			Document doc = XmlSerializeTool.readEncryptedFile(getFilePw(), getOpenedFile());

			Element root = doc.getDocumentElement();
			PWList pwList = new PWList(root, null);
			Model.getModel().setPwList(pwList);

		} catch (SysXmlBaseException ex) {
			String message = "Load " + getOpenedFile().getAbsolutePath() + " failed\n" + ex.getMessage();

			Model.getModel().showErrorMessage("Cannot open File", message);
			logger.warn(message);
		}
		logger.debug("exiting loadEncryptedFile");
	}
}
