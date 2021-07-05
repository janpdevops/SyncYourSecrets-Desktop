package de.petranek.syncyoursecrets.swing.model;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;


import de.petranek.syncyoursecrets.model.pw.PWEntry;
import de.petranek.syncyoursecrets.xmlmapping.ElementDeletedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordEntryModel {

	/** The Constant logger. */
	static final Logger logger = LogManager.getLogger(PasswordEntryModel.class);

	private PWEntry pwEntry;

	private JTextField textField_Name;
	private JTextField textField_User;
	private JTextField textField_Password;
	private JTextField textField_URL;
	private JTextField textField_EMail;
	private JTextArea textArea_Remark;

	public JTextField getTextField_Name() {
		return textField_Name;
	}

	public JTextField getTextField_User() {
		return textField_User;
	}

	public JTextField getTextField_Password() {
		return textField_Password;
	}

	public JTextField getTextField_URL() {
		return textField_URL;
	}

	public JTextField getTextField_EMail() {
		return textField_EMail;
	}

	public JTextArea getTextArea_Remark() {
		return textArea_Remark;
	}

	public PWEntry getPwEntry() {
		return pwEntry;
	}

	public void setPwEntry(PWEntry pwEntry) {
		this.pwEntry = pwEntry;
		try {
			getTextField_Name().setText(pwEntry.getName());
			getTextField_User().setText(pwEntry.getUser());
			getTextField_Password().setText(pwEntry.getPassword());
			getTextField_URL().setText(pwEntry.getUrl());
			getTextField_EMail().setText(pwEntry.getEmail());
			getTextArea_Remark().setText(pwEntry.getRemark());
		} catch (Exception e) {
			logger.error(e);
		}

	}

	public boolean hasPendingChanges() {
		if (getPwEntry() == null) {
			logger.debug("pwEntry not set yet");
			return false;
		}

		try {
			return nameHasChanged() || userHasChanged() || urlHasChanged() || emailHasChanged() || passwordHasChanged()
					|| remarkHasChanged();
		} catch (ElementDeletedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void onLeaveElement() {

		if (getPwEntry() == null) {
			logger.debug("pwEntry not set yet");
			return;
		}
		try {

			if (nameHasChanged()) {
				getPwEntry().setName(getTextField_Name().getText());
				logger.debug("Set property" + PWEntry.NAME);
				Model.getModel().setHasChanged();
			}

			if (userHasChanged()) {
				getPwEntry().setUser(getTextField_User().getText());
				logger.debug("Set property" + PWEntry.USER);
				Model.getModel().setHasChanged();
			}

			if (urlHasChanged()) {
				getPwEntry().setUrl(getTextField_URL().getText());
				logger.debug("Set property" + PWEntry.URL);
				Model.getModel().setHasChanged();
			}

			if (emailHasChanged()) {
				getPwEntry().setEmail(getTextField_EMail().getText());
				logger.debug("Set property" + PWEntry.EMAIL);
				Model.getModel().setHasChanged();
			}

			if (passwordHasChanged()) {
				getPwEntry().setPassword(getTextField_Password().getText());
				logger.debug("Set property" + PWEntry.PASSWORD);
				Model.getModel().setHasChanged();
			}

			if (remarkHasChanged()) {
				getPwEntry().setRemark(getTextArea_Remark().getText());
				logger.debug("Set property" + PWEntry.REMARK);
				Model.getModel().setHasChanged();
			}

		} catch (ElementDeletedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean remarkHasChanged() throws ElementDeletedException {
		return !StringUtils.equals(getPwEntry().getRemark(), getTextArea_Remark().getText());
	}

	private boolean passwordHasChanged() throws ElementDeletedException {
		return !StringUtils.equals(getPwEntry().getPassword(), getTextField_Password().getText());
	}

	private boolean emailHasChanged() throws ElementDeletedException {
		return !StringUtils.equals(getPwEntry().getEmail(), getTextField_EMail().getText());
	}

	private boolean urlHasChanged() throws ElementDeletedException {
		return !StringUtils.equals(getPwEntry().getUrl(), getTextField_URL().getText());
	}

	private boolean userHasChanged() throws ElementDeletedException {
		return !StringUtils.equals(getPwEntry().getUser(), getTextField_User().getText());
	}

	private boolean nameHasChanged() {
		return !StringUtils.equals(getPwEntry().getName(), getTextField_Name().getText());
	}

	public void setTextField_Name(JTextField textField_Name) {
		this.textField_Name = textField_Name;
	}

	public void setTextField_User(JTextField textField_User) {
		this.textField_User = textField_User;
	}

	public void setTextField_Password(JTextField textField_Password) {
		this.textField_Password = textField_Password;
	}

	public void setTextField_URL(JTextField textField_URL) {
		this.textField_URL = textField_URL;
	}

	public void setTextField_EMail(JTextField textField_EMail) {
		this.textField_EMail = textField_EMail;
	}

	public void setTextArea_Remark(JTextArea textArea_Remark) {
		this.textArea_Remark = textArea_Remark;
	}

}
