package de.petranek.syncyoursecrets.swing.actions.edit;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import de.petranek.syncyoursecrets.model.pw.PWList;
import de.petranek.syncyoursecrets.swing.model.Model;
import de.petranek.syncyoursecrets.swing.model.PasswordEntryModel;

public class DeleteEntryAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5166142400294214554L;

	@Override
	public void actionPerformed(ActionEvent e) {
		PasswordEntryModel pwEntryModel = Model.getModel().getPwEntryModel();
		// pwEntryModel.onLeaveElement();

		try {

			PWList pwList = Model.getModel().getPwList();
			pwList.remove(Model.getModel().getCurrentPwEntry());

			Model.getModel().setCurrentPwEntry(null);
			Model.getModel().setPwList(pwList);

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(Model.getModel().getParentComponent(), e2.getMessage(), "Delete failed",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public DeleteEntryAction() {
		super();
		putValue(NAME, "Delete Entry");
	}

}
