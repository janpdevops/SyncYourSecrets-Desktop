package de.petranek.syncyoursecrets.swing.actions.file;

import java.awt.event.ActionEvent;

import de.petranek.syncyoursecrets.util.SysXmlBaseException;

public class SaveAndExitAction extends SaveFileAction {

	public SaveAndExitAction() {
		super();

		putValue(NAME, "Save and exit");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			save();
			System.exit(0);
		} catch (SysXmlBaseException e1) {
			// TODO Dont' exit, but warn, if things go wrong.
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
