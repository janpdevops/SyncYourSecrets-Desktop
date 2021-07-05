package de.petranek.syncyoursecrets.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.petranek.syncyoursecrets.swing.actions.edit.AddEntryAction;
import de.petranek.syncyoursecrets.swing.actions.edit.DeleteEntryAction;
import de.petranek.syncyoursecrets.swing.actions.file.ExitAction;
import de.petranek.syncyoursecrets.swing.actions.file.OpenFileAction;
import de.petranek.syncyoursecrets.swing.actions.file.SaveFileAction;
import de.petranek.syncyoursecrets.swing.actions.file.SelectEntryListener;
import de.petranek.syncyoursecrets.swing.help.CheckEncryptionDialog;
import de.petranek.syncyoursecrets.swing.model.Model;

public class SyncYourSecrets {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final OpenFileAction openFileAction = new OpenFileAction();
	private JTextField textField_Name;
	private JTextField textField_User;
	private JTextField textField_Password;
	private JTextField textField_URL;
	private JTextField textField_EMail;
	private JTextArea textArea_Remark;
	private final SaveFileAction saveFileAction = new SaveFileAction();
	private final Action exitAction = new ExitAction();
	private final AddEntryAction addEntryAction = new AddEntryAction();
	private final DeleteEntryAction deleteEntryAction = new DeleteEntryAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SyncYourSecrets window = new SyncYourSecrets();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SyncYourSecrets() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New...");
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.setAction(openFileAction);
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAction(saveFileAction);
		mnFile.add(mntmSave);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAction(exitAction);
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem menuItem = mnEdit.add(addEntryAction);

		JMenuItem menuItem_1 = mnEdit.add(deleteEntryAction);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		JMenuItem mntmCheckEncryption = new JMenuItem("Check Encryption");
		mntmCheckEncryption.setAction(action);
		mnHelp.add(mntmCheckEncryption);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		SelectEntryListener seListener = new SelectEntryListener();
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(seListener);

		// frame.getContentPane().add(list, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(list);
		frame.getContentPane().add(scrollPane, BorderLayout.WEST);

		Model.getModel().setList(list);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		JLabel lblName = new JLabel("Name");
		panel.add(lblName, "2, 2, right, default");

		textField_Name = new JTextField();
		panel.add(textField_Name, "4, 2, fill, default");
		textField_Name.setColumns(10);

		JLabel lblUser = new JLabel("User");
		panel.add(lblUser, "2, 4, right, default");

		textField_User = new JTextField();
		panel.add(textField_User, "4, 4, fill, default");
		textField_User.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword, "2, 6, right, default");

		textField_Password = new JTextField();
		panel.add(textField_Password, "4, 6, fill, default");
		textField_Password.setColumns(10);

		JLabel lblUrl = new JLabel("URL");
		panel.add(lblUrl, "2, 8, right, default");

		textField_URL = new JTextField();
		panel.add(textField_URL, "4, 8, fill, default");
		textField_URL.setColumns(10);

		JLabel lblEmail = new JLabel("E-Mail");
		panel.add(lblEmail, "2, 10, right, default");

		textField_EMail = new JTextField();
		panel.add(textField_EMail, "4, 10, fill, default");
		textField_EMail.setColumns(10);

		JLabel lblRemark = new JLabel("Remark");
		panel.add(lblRemark, "2, 12");

		textArea_Remark = new JTextArea();
		panel.add(textArea_Remark, "2, 14, 3, 1, fill, fill");

		Model.getModel().getPwEntryModel().setTextField_Name(textField_Name);
		Model.getModel().getPwEntryModel().setTextField_User(textField_User);
		Model.getModel().getPwEntryModel().setTextField_Password(textField_Password);
		Model.getModel().getPwEntryModel().setTextField_URL(textField_URL);
		Model.getModel().getPwEntryModel().setTextField_EMail(textField_EMail);
		Model.getModel().getPwEntryModel().setTextArea_Remark(textArea_Remark);

		Model.getModel().setParentComponent(this.frame);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Check Encryption");
			putValue(SHORT_DESCRIPTION, "Verify your encryption setup");

		}

		public void actionPerformed(ActionEvent e) {
			CheckEncryptionDialog dialog = new CheckEncryptionDialog();
			dialog.setVisible(true);
		}
	}

}
