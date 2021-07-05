package de.petranek.syncyoursecrets.swing.actions.file;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.petranek.syncyoursecrets.swing.model.Model;

public class ExitAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5473377512022342913L;

	public ExitAction() {
		super();
		putValue(NAME, "Exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Model.getModel().getPwEntryModel().onLeaveElement();
		// TODO Auto-generated method stub
		if (Model.getModel().isHasChanged()) {

			SaviourOfUnsavedChangesDialog dialog = new SaviourOfUnsavedChangesDialog();
			dialog.setVisible(true);
		} else {
			System.exit(0);
		}
	}

}
