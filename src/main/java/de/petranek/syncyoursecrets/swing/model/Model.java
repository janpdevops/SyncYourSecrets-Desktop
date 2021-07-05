package de.petranek.syncyoursecrets.swing.model;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JList;
import javax.swing.JOptionPane;

import de.petranek.syncyoursecrets.model.pw.PWEntry;
import de.petranek.syncyoursecrets.model.pw.PWList;
import de.petranek.syncyoursecrets.xmlmapping.MappingElement;

public class Model {

	static Model model = new Model();

	private boolean hasChanged = false;

	public static Model getModel() {
		return model;
	}

	private EncryptedFile currentFile;
	private PWList pwList;
	private JList list;

	private PasswordEntryModel pwEntryModel = new PasswordEntryModel();

	private PWEntry currentPwEntry;

	private Component parentComponent;

	public JList getList() {
		return list;
	}

	public EncryptedFile getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(EncryptedFile currentFile) {
		this.currentFile = currentFile;
	}

	public PWList getPwList() {
		return pwList;
	}

	public void setPwList(PWList pwList) {
		this.pwList = pwList;

		MappingElement[] array = pwList.toArray();
		Arrays.sort(array, new MappingElementNameComparator());
		getList().setListData(array);

	}

	public void setList(JList list) {
		this.list = list;

	}

	public PWEntry getCurrentPwEntry() {
		return getPwEntryModel().getPwEntry();
	}

	public void setCurrentPwEntry(PWEntry currentPwEntry) {
		getPwEntryModel().setPwEntry(currentPwEntry);

	}

	public PasswordEntryModel getPwEntryModel() {
		return pwEntryModel;
	}

	public boolean isHasChanged() {
		return hasChanged;
	}

	public void setHasChanged() {
		this.hasChanged = true;
	}

	public void wasSaved() {
		this.hasChanged = false;
	}

	public void showErrorMessage(String title, String message) {
		JOptionPane.showMessageDialog(getParentComponent(), message, title, JOptionPane.ERROR_MESSAGE);
	}

	public Component getParentComponent() {
		return parentComponent;
	}

	public void setParentComponent(Component parentComponent) {
		this.parentComponent = parentComponent;
	}

}
