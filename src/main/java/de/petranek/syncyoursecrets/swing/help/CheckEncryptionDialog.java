package de.petranek.syncyoursecrets.swing.help;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class CheckEncryptionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CheckEncryptionDialog dialog = new CheckEncryptionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CheckEncryptionDialog() {
		setTitle("Checking encryption...");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			CheckEncryption check = new CheckEncryption();
			CheckEncryptionResult result = check.checkEncryption();
			contentPanel.setLayout(new CardLayout(0, 0));
			{
				JTextArea txtr = new JTextArea();
				txtr.setLineWrap(true);
				txtr.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				txtr.setText(result.getMessage());
				txtr.setWrapStyleWord(true);
				contentPanel.add(txtr, "name_13471693356629");
			}
			setTitle(result.getTitle());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setAction(action);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "OK");
			putValue(SHORT_DESCRIPTION, "Close this Dialog. You have few other choices anyhow");
		}

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
