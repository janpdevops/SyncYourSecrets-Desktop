package de.petranek.syncyoursecrets.swing.file;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class PasswordDialog extends JDialog {

	private OnPasswordAction myAction;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private final Action action = new SwingAction();
	private JButton okButton;

	public String getPassword() {
		return String.valueOf(passwordField.getPassword());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PasswordDialog dialog = new PasswordDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PasswordDialog(OnPasswordAction onPasswordAction) {

		this();
		this.myAction = onPasswordAction;
		this.okButton.setAction(myAction);
		this.myAction.setPasswordDialog(this);

	}

	/**
	 * Create the dialog.
	 * 
	 * @param action
	 */
	public PasswordDialog() {
		setTitle("Enter password");
		setBounds(100, 100, 376, 120);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setAction(action);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Cancel");
			putValue(SHORT_DESCRIPTION, "Cancel");
		}

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
