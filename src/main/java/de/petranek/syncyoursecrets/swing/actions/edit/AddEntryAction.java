package de.petranek.syncyoursecrets.swing.actions.edit;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import de.petranek.syncyoursecrets.model.pw.PWEntry;
import de.petranek.syncyoursecrets.swing.model.Model;
import de.petranek.syncyoursecrets.swing.model.PasswordEntryModel;
import de.petranek.syncyoursecrets.util.SysInvalidArgumentException;
import de.petranek.syncyoursecrets.xmlmapping.ElementDeletedException;

public class AddEntryAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 757085293992396956L;

	public AddEntryAction() {
		super();

		putValue(NAME, "Add Entry");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		PasswordEntryModel pwEntryModel = Model.getModel().getPwEntryModel();
		pwEntryModel.onLeaveElement();
		try {
			PWEntry entry;

			entry = new PWEntry(null);

			entry.setName("New Entry");

			entry.setPassword("");
			entry.setUser("");
			entry.setRemark("");
			entry.setUrl("");

			Model.getModel().getPwList().add(entry);
			Model.getModel().setPwList(Model.getModel().getPwList());

		} catch (ElementDeletedException e1) {
			JOptionPane.showMessageDialog(Model.getModel().getParentComponent(), e1.getMessage(),
					"Element already deleted", JOptionPane.ERROR_MESSAGE);

		} catch (SysInvalidArgumentException e2) {
			JOptionPane.showMessageDialog(Model.getModel().getParentComponent(), e2.getMessage(), "Invalid argument",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
