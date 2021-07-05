package de.petranek.syncyoursecrets.swing.actions.file;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class SaviourOfUnsavedChangesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final Action exitNosave = new ExitNoSaveAction();

	private final SaveFileAction saveAndExitAction = new SaveAndExitAction();
	private final Action cancel = new CancelAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SaviourOfUnsavedChangesDialog dialog = new SaviourOfUnsavedChangesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SaviourOfUnsavedChangesDialog() {
		setTitle("Pending Changes");
		setBounds(100, 100, 345, 109);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JTextArea txtrThereAreUnsaved = new JTextArea();
			txtrThereAreUnsaved.setLineWrap(true);
			txtrThereAreUnsaved.setWrapStyleWord(true);
			txtrThereAreUnsaved.setText("There are unsaved changes in this file. Do you want to exit anyway?");
			contentPanel.add(txtrThereAreUnsaved);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton button = new JButton("New button");
				button.setAction(saveAndExitAction);
				buttonPane.add(button);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setAction(exitNosave);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setAction(cancel);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class ExitNoSaveAction extends AbstractAction {
		public ExitNoSaveAction() {

			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit without saving, any changes will be lost");
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class CancelAction extends AbstractAction {
		public CancelAction() {
			putValue(NAME, "Cancel");
			putValue(SHORT_DESCRIPTION, "Cancel, return to the application, as if you never clicked on exit");
		}

		public void actionPerformed(ActionEvent e) {
			SaviourOfUnsavedChangesDialog.this.dispose();

		}
	}
}
