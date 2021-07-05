package de.petranek.syncyoursecrets.swing.actions.file;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.petranek.syncyoursecrets.model.pw.PWList;
import de.petranek.syncyoursecrets.swing.model.EncryptedFile;
import de.petranek.syncyoursecrets.swing.model.Model;
import de.petranek.syncyoursecrets.util.SysXmlBaseException;
import de.petranek.syncyoursecrets.util.XmlSerializeTool;

public class SaveFileAction extends AbstractAction {
// de.petranek.syncyoursecrets.swing.actions.file.SaveFileAction
	/** The Constant logger. */
	static final Logger logger = LogManager.getLogger(SaveFileAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -7673152737606993541L;

	public SaveFileAction() {
		super();
		putValue(NAME, "Save");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			save();
		} catch (SysXmlBaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Save the current contents to a file and encrypt it.
	 *
	 * @param password the password
	 * @param file     the file name
	 * @throws SysXmlBaseException when the encryption goes wrong
	 *
	 */
	public void saveEncryptedFile(String password, File file) throws Exception {
		logger.debug("entering saveEncryptedFile");
		try {

			Model.getModel().getCurrentFile().setFilePw(password);
			Model.getModel().getCurrentFile().setOpenedFile(file);

			PWList list = Model.getModel().getPwList();

			Document doc = XmlSerializeTool.createDocument();

			Element root = list.toXml(doc);

			doc.appendChild(root);

			XmlSerializeTool.writeEncryptedDocument(password, doc, file);
			// this.modified = false;
		} catch (DOMException ex) {
			String msg = "Failed to generate XML document, this is a bug.";
			throw new Exception(msg, ex);
		}

		logger.debug("exiting saveEncryptedFile");
	}

	/**
	 * Save the current contents to the currently opened file and encrypt it with
	 * the password of that file.
	 * 
	 * @return name of the saved file
	 *
	 * @throws SysXmlBaseException when the save fails
	 * @throws SysGuiException     when no file is currently opened.
	 *
	 */
	public void save() throws SysXmlBaseException, Exception {

		Model.getModel().getPwEntryModel().onLeaveElement();

		EncryptedFile encryptedFile = Model.getModel().getCurrentFile();
		if (encryptedFile != null) {
			String filePw = encryptedFile.getFilePw();
			File openedFile = encryptedFile.getOpenedFile();

			if (openedFile != null && filePw != null) {
				saveEncryptedFile(filePw, openedFile);
				// this.modified = false;
				// return this.openedFile.getAbsolutePath();
			} else {
				throw new Exception("No file is currently opened, please select SaveAs.");
			}
		}
	}

}
