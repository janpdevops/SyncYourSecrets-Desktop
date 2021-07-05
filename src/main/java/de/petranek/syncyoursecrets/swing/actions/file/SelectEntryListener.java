package de.petranek.syncyoursecrets.swing.actions.file;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



import de.petranek.syncyoursecrets.model.pw.PWEntry;
import de.petranek.syncyoursecrets.swing.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SelectEntryListener implements ListSelectionListener {

	/** The Constant logger. */
	static final Logger logger = LogManager.getLogger(SelectEntryListener.class);

	@Override
	public void valueChanged(ListSelectionEvent e) {

		Model.getModel().getPwEntryModel().onLeaveElement();
		logger.debug(e.getSource());
		JList source = (JList) e.getSource();
		logger.debug("selected " + source.getSelectedValue());

		Object o = source.getSelectedValue();

		if (o instanceof PWEntry) {
			PWEntry pwEntry = (PWEntry) o;
			Model.getModel().setCurrentPwEntry(pwEntry);
		}

	}

}
