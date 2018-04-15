//*
package MyAddressBook;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContactsManagerFrameView extends JFrame implements IFinalsInterface, IContactManagerView, IRegistable {

	private ArrayList<ISagivListener> listeners = new ArrayList<ISagivListener>();

	private JPanel pNorth;
	private JPanel pCenter;
	private JPanel pSouth;

	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField phoneNumberTextField;
	private JTextField filePathTextField;

	private JButton jbtCreateButton;
	private JButton jbtPrevious;
	private JButton jbtNext;
	private JButton jbtFirst;
	private JButton jbtLast;
	private JButton jbtUpdateButton;
	private JButton jbtEditContact;
	private JButton jbtExport;
	private JButton jbtLoadFile;
	private JButton jbtShow;
	private JButton jbtSort;

	private JComboBox<String> cbFiles;
	private JComboBox<String> cbTypeOfSort;
	private JComboBox<String> cbChooseWhatOrder;
	private JComboBox<String> cbdASCorDESCUp;
	private JComboBox<String> cbdASCorDESCDown;

	private JLabel phoneNumberLabelShow;
	private JLabel firstNameLabelShow;
	private JLabel lastNameLabelShow;

	private static final long serialVersionUID = 6981796460475688324L;

	public ContactsManagerFrameView() {
		setProperties();
	}

	/** This method management the components of the north panel **/
	private void addComponentsToPanelNorth() {

		GridLayout gl = new GridLayout(GRIDLAYOUT_ROWS, GRIDLAYOUT_COLS, GRID_SPACES, GRID_SPACES);
		this.pNorth.setLayout(gl);

		JLabel firstNameLabel = new JLabel("First Name: ");
		this.pNorth.add(firstNameLabel);

		firstNameTextField = new JTextField(TEXT_FIELD_SIZE);
		this.pNorth.add(firstNameTextField);

		JLabel lastNameLabel = new JLabel("Last Name: ");
		this.pNorth.add(lastNameLabel);

		lastNameTextField = new JTextField(TEXT_FIELD_SIZE);
		this.pNorth.add(lastNameTextField);

		JLabel phoneNumberLabel = new JLabel("Phone Number: ");
		this.pNorth.add(phoneNumberLabel);

		phoneNumberTextField = new JTextField(TEXT_FIELD_SIZE);
		this.pNorth.add(phoneNumberTextField);

		jbtCreateButton = new JButton("Create");
		this.pNorth.add(jbtCreateButton);

		jbtUpdateButton = new JButton("Update");
		this.pNorth.add(jbtUpdateButton);
		jbtUpdateButton.setEnabled(false);

	}

	/** This method management the components of the center panel **/

	private void addComponentsToPanelCenter() {

		setPanelUpOfCenter();
		setPanelDownOfCenter();

	}

	/** This method set panel up of first and last name **/

	private void setPanelUpOfCenter() {
		JPanel panelUp = new JPanel(new BorderLayout());
		this.pCenter.add(panelUp);

		JPanel panelUpLeftButton = new JPanel(new GridLayout());
		panelUp.add(panelUpLeftButton, BorderLayout.WEST);

		jbtPrevious = new JButton("   <   ");
		panelUpLeftButton.add(jbtPrevious);

		JPanel panelUpMiddleLabels = new JPanel(new GridLayout(2, 2, GRID_SPACES, GRID_SPACES));
		panelUp.add(panelUpMiddleLabels, BorderLayout.CENTER);

		JLabel firstNameLabel = new JLabel("First Name: ");
		panelUpMiddleLabels.add(firstNameLabel);

		firstNameLabelShow = new JLabel(IM_EMPTY);
		panelUpMiddleLabels.add(firstNameLabelShow);

		JLabel lastNameLabel = new JLabel("Last Name: ");
		panelUpMiddleLabels.add(lastNameLabel);

		lastNameLabelShow = new JLabel(IM_EMPTY);
		panelUpMiddleLabels.add(lastNameLabelShow);

		JPanel panelUpRightButton = new JPanel(new GridLayout());
		panelUp.add(panelUpRightButton, BorderLayout.EAST);

		jbtNext = new JButton("   >   ");

		panelUpRightButton.add(jbtNext);
	}

	/** This method set panel down of phone number and edit contact button **/

	private void setPanelDownOfCenter() {

		JPanel panelDown = new JPanel(new BorderLayout());
		this.pCenter.add(panelDown);

		JPanel panelDownLeftButton = new JPanel(new GridLayout());

		panelDown.add(panelDownLeftButton, BorderLayout.WEST);

		jbtFirst = new JButton("First");
		panelDownLeftButton.add(jbtFirst);

		JPanel panelDownMiddleLabels = new JPanel(new GridLayout(2, 2, GRID_SPACES, GRID_SPACES));
		panelDown.add(panelDownMiddleLabels, BorderLayout.CENTER);

		JLabel phoneNumberLabel = new JLabel("Phone Number: ");
		panelDownMiddleLabels.add(phoneNumberLabel);

		phoneNumberLabelShow = new JLabel(IM_EMPTY);
		panelDownMiddleLabels.add(phoneNumberLabelShow);

		JPanel editPanelButton = new JPanel(new BorderLayout());
		jbtEditContact = new JButton("Edit Contact");
		JLabel j = new JLabel("");

		editPanelButton.add(jbtEditContact, BorderLayout.SOUTH);
		editPanelButton.add(j);
		panelDownMiddleLabels.add(editPanelButton);

		JPanel panelDownRightButton = new JPanel(new GridLayout());
		panelDown.add(panelDownRightButton, BorderLayout.EAST);

		jbtLast = new JButton("Last");
		panelDownRightButton.add(jbtLast);

	}

	/** This method set the comboBoxfor the southPanel **/

	private void setComboForSouthPanel() {

		// String[] organize = { SFIELD, SCOUNT, REVERSE };
		cbTypeOfSort = new JComboBox<String>(SORT_TYPE_ARRAY);

		// String[] chooseWhatOrder = { FNAME_FIELD, LNAME_FIELD,
		// PHONE_NUM_FIELDE };

		cbChooseWhatOrder = new JComboBox<String>(WHAT_ORDER_ARRAY);

		// String[] aSCorDESC = { ASC, DESC };
		cbdASCorDESCUp = new JComboBox<String>(ASC_OR_DESC_ARRAY);
		cbdASCorDESCDown = new JComboBox<String>(ASC_OR_DESC_ARRAY);// same
																	// values

		// String[] filesTypes = { TXT_EXTENSION, OBJ_EXTENSION, BYTE_EXTENSION
		// };
		cbFiles = new JComboBox<String>(FILES_TYPES_ARRAY);
	}

	/** This method add components to the south Panel **/

	private void addComponentsToPanelSouth() {

		JPanel panelOfCombosAndButtonsDown = new JPanel(new GridLayout(2, 1, 5, 5));

		JPanel panelOfCombosAndButtonsDownUp = new JPanel(
				new GridLayout(1, 4, GRIDLAYOUT_BIG_SPACES, GRIDLAYOUT_BIG_SPACES));
		JPanel panelOfCombosAndButtonsDownDown = new JPanel(
				new GridLayout(1, 2, GRIDLAYOUT_BIG_SPACES, GRIDLAYOUT_BIG_SPACES));

		panelOfCombosAndButtonsDown.add(panelOfCombosAndButtonsDownUp);
		panelOfCombosAndButtonsDown.add(panelOfCombosAndButtonsDownDown);

		jbtShow = new JButton("Show");
		jbtSort = new JButton("Sort");

		setComboForSouthPanel();

		panelOfCombosAndButtonsDownUp.add(cbTypeOfSort);
		panelOfCombosAndButtonsDownUp.add(cbChooseWhatOrder);
		panelOfCombosAndButtonsDownUp.add(cbdASCorDESCUp);
		panelOfCombosAndButtonsDownUp.add(jbtSort);
		panelOfCombosAndButtonsDownDown.add(cbdASCorDESCDown);
		panelOfCombosAndButtonsDownDown.add(jbtShow);

		pSouth.add(panelOfCombosAndButtonsDown, BorderLayout.SOUTH);

		JPanel panelOfComboButtonAndTxtUp = new JPanel();

		JPanel comboAndButtonPanel = new JPanel(
				new GridLayout(1, 3, GRIDLAYOUT_BIG_SPACES / 2, GRIDLAYOUT_BIG_SPACES / 2));
		panelOfComboButtonAndTxtUp.add(comboAndButtonPanel, BorderLayout.CENTER);
		pSouth.add(panelOfComboButtonAndTxtUp, BorderLayout.NORTH);

		JPanel panelForCombo = new JPanel(new GridLayout(3, 1));
		panelForCombo.add(new JLabel(""));
		panelForCombo.add(cbFiles);
		panelForCombo.add(new JLabel(""));
		comboAndButtonPanel.add(panelForCombo);

		jbtExport = new JButton("Export");
		comboAndButtonPanel.add(jbtExport);

		JPanel textLabelAndButtonRightPanel = new JPanel(new GridLayout(3, 1, 7, 7));
		JLabel filePathLabel = new JLabel("File path:");
		textLabelAndButtonRightPanel.add(filePathLabel);

		filePathTextField = new JTextField(TEXT_FIELD_SIZE);
		textLabelAndButtonRightPanel.add(filePathTextField);

		jbtLoadFile = new JButton("Load file");
		textLabelAndButtonRightPanel.add(jbtLoadFile);
		comboAndButtonPanel.add(textLabelAndButtonRightPanel);

	}

	/** This method check if there is null fields **/

	public boolean checkTextFieldsFull() {
		if (firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("")
				|| phoneNumberTextField.getText().equals("")) {
			System.out.println(INSERT_ALL_VALUES);
			return false;
		}
		return true;
	}

	/** This method clean text fields and disable update button **/

	public void cleanTextsFieldsAndDisableUpdate() {

		firstNameTextField.setText("");
		lastNameTextField.setText("");
		phoneNumberTextField.setText("");
		jbtUpdateButton.setEnabled(false);

	}

	/** This method check if the string contains illegal length FOR ALL !!! **/

	private boolean isValidLengthForAll(String firstName, String lastName, String phoneNumber) {

		if (firstName.length() <= F_AND_L_NAME_SIZE && lastName.length() <= F_AND_L_NAME_SIZE
				&& phoneNumber.length() <= PHONE_NUM_SIZE) {
			return true;
		}
		System.out.println(INVALID_LENGTH);
		return false;

	}

	/**
	 * This method check if the string contain just digits (for phone number).
	 **/
	private boolean isDigitOnly(String str) {

		int l = str.length();
		for (int i = 0; i < l; i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				System.out.println(JUST_DIGITS);
				return false;
			}
		}
		return true;
	}

	/**
	 * This method present the data we want (of contact) on the center labels
	 **/
	private void presentData(String[] uiData) {

		if (uiData == null) {
			return;
		}
		else{
			firstNameLabelShow.setText(uiData[1]);
			lastNameLabelShow.setText(uiData[2]);
			phoneNumberLabelShow.setText(uiData[3]);
		}
		
	}

	/** This method create and set the main panels on the frame **/

	private void setGuiPannels() {

		this.setLayout(new BorderLayout());
		this.pNorth = new JPanel();
		this.getContentPane().add(this.pNorth, BorderLayout.NORTH);
		this.pCenter = new JPanel(new GridLayout(2, 1, GRID_SPACES, GRID_SPACES));
		this.getContentPane().add(this.pCenter, BorderLayout.CENTER);
		this.pSouth = new JPanel(new BorderLayout());
		this.getContentPane().add(this.pSouth, BorderLayout.SOUTH);

		addComponentsToPanelNorth();
		addComponentsToPanelCenter();
		addComponentsToPanelSouth();

	}

	/** This method initial the program **/

	public void init() {
		setGuiPannels();
		setListeners();

		// if (!(phoneNumberLabelShow.equals(IM_EMPTY))) { //IF TRUE = NOT EMPTY

		jbtFirst.doClick(); // cause i want to see the first contact when I
		// Am open a program!
		// }

		this.setVisible(true);
	}

	/** Register the listener to the listeners array **/
	@Override
	public void registerListener(ISagivListener iSagivListener) {
		listeners.add(iSagivListener);
	}

	// I preferred to work like this, cause I notice that i have 9 buttons,
	// and in my opinion it better to do it with ONE inner class than with
	// anonymous class for any button.
	/** Set all the listeners **/
	private void setListeners() {
		jbtNext.addActionListener(new Listener());
		jbtPrevious.addActionListener(new Listener());
		jbtFirst.addActionListener(new Listener());
		jbtCreateButton.addActionListener(new Listener());
		jbtLast.addActionListener(new Listener());
		jbtEditContact.addActionListener(new Listener());
		jbtUpdateButton.addActionListener(new Listener());
		jbtExport.addActionListener(new Listener());
		jbtLoadFile.addActionListener(new Listener());
		jbtSort.addActionListener(new Listener());
		jbtShow.addActionListener(new Listener());

	}

	/** Frame properties **/
	private void setProperties() {

		this.setSize(WIDTH_VIEW, HEIGHT_VIEW);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setLocation(FRAME_X_LOCATION, FRAME_Y_LOCATION);
		this.setTitle("HW3 Sagiv Contacts Book HW3");

	}

	/**
	 * This method put the values of the contact in the text fields after load
	 **/
	private void putValuesToTextFieldsAfterLoad(String fName, String lName, String pNum) {
		this.firstNameTextField.setText(fName);
		this.lastNameTextField.setText(lName);
		this.phoneNumberTextField.setText(pNum);
	}

	/** Get information from the view - transfer to the controller **/

	private void viewUpdateController(String operation) {
		for (ISagivListener listener : listeners) {
			switch (operation) {
			case NEXT_COMMAND:
				listener.getNextContact();
				break;
			case PREVIOUS_COMMAND:
				listener.getPreviousContact();
				break;
			case LAST_COMMAND:
				listener.getLastContact();
				break;
			case FIRST_COMMAND:
				listener.getFirstContact();
				break;
			case CREATE_COMMAND:
				listener.createContact(firstNameTextField.getText(), lastNameTextField.getText(),
						phoneNumberTextField.getText());
				break;
			case UPDATE_COMMAND:
				listener.updateContact(firstNameTextField.getText(), lastNameTextField.getText(),
						phoneNumberTextField.getText());
				break;
			case EXPORT_COMMAND:
				listener.Export(cbFiles.getSelectedItem() + "", firstNameLabelShow.getText(),
						lastNameLabelShow.getText(), phoneNumberLabelShow.getText());
				break;
			case SORT_COMMAND:
				listener.Sort(cbTypeOfSort.getSelectedItem() + "", cbChooseWhatOrder.getSelectedItem() + "",
						cbdASCorDESCUp.getSelectedItem() + "");
				break;
			case LOAD_COMMAND:
				listener.Load(cbFiles.getSelectedItem() + "", filePathTextField.getText());
				break;
			case SHOW_COMMAND:
				listener.Show(cbdASCorDESCDown.getSelectedItem() + "");
				break;

			}

		}
	}

	// UPDATE THE VIEW //
	@Override
	public void updateViewForNext(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForPrevious(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForFirst(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForLast(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForCreate(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForUpdate(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForLoad(String[] myContactsDetails) {
		putValuesToTextFieldsAfterLoad(myContactsDetails[1], myContactsDetails[2], myContactsDetails[3]);
	}

	/** Inner Class for listeners! **/
	class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbtCreateButton) {
				createContact();
			} else if (e.getSource() == jbtUpdateButton) {
				updateContact();
			} else if (e.getSource() == jbtEditContact) {
				editContact();
			} else if (e.getSource() == jbtPrevious) {
				previousContact();
			} else if (e.getSource() == jbtNext) {
				nextContact();
			} else if (e.getSource() == jbtFirst) {
				firstContact();
			} else if (e.getSource() == jbtLast) {
				lastContact();
			} else if (e.getSource() == jbtExport) {
				exportContact();
			} else if (e.getSource() == jbtLoadFile) {
				loadContactFromFile();
			} else if (e.getSource() == jbtSort) {
				doSort();
			} else if (e.getSource() == jbtShow) {
				doShow();
			}
		}

		/**
		 * This method show the contacts of the address book without any button
		 **/
		private void doShow() {
			viewUpdateController(SHOW_COMMAND);
		}

		/** This method sort the contacts of the address book **/
		private void doSort() {
			viewUpdateController(SORT_COMMAND);
			firstContact();
		}

		/** This method load contact From file **/
		private void loadContactFromFile() {
			viewUpdateController(LOAD_COMMAND);
		}

		/** This method export contact to file **/
		private void exportContact() {
			System.out.println("Pushed on export!!!!");
			cleanTextsFieldsAndDisableUpdate();
			viewUpdateController(EXPORT_COMMAND);
		}

		/** This method present the details of the Last contact **/
		private void lastContact() {
			viewUpdateController(LAST_COMMAND);
		}

		/** This method present the details of the FIRST contact **/

		private void firstContact() {
			viewUpdateController(FIRST_COMMAND);
		}

		/** This method present the details of the next contact **/
		private void nextContact() {
			viewUpdateController(NEXT_COMMAND);
		}

		/** This method present the details of the previous contact **/
		private void previousContact() {
			viewUpdateController(PREVIOUS_COMMAND);
		}

		/** This Method gives the option for update **/
		private void editContact() {

			// EDIT CONTACT is attached only to the view, so the controller
			// doesn't know about it. its internal method of the view!
			String str = IM_EMPTY;
			if (phoneNumberLabelShow.getText().compareTo(str) != 0) {
				// because phone number can include just numbers, except if the
				// file is empty!
				jbtUpdateButton.setEnabled(true);
				firstNameTextField.setText(firstNameLabelShow.getText().trim());
				lastNameTextField.setText(lastNameLabelShow.getText().trim());
				phoneNumberTextField.setText(phoneNumberLabelShow.getText().trim());
			} else {
				System.out.println(DBEmpty);
			}
		}

		/** This Method update exist Contact **/
		private void updateContact() {

			// check if valid length for all the fields & that the fields aren't
			// empty & that the phone number contain just digits
			if (checkTextFieldsFull() && isValidLengthForAll(firstNameTextField.getText(), lastNameTextField.getText(),
					phoneNumberTextField.getText()) && isDigitOnly(phoneNumberTextField.getText())) {
				viewUpdateController(UPDATE_COMMAND);
			}
		}

		/** This Method create new Contact **/
		public void createContact() {

			// check if valid length for all the fields & that the fields aren't
			// empty & that the phone number contain just digits
			if (checkTextFieldsFull() && isValidLengthForAll(firstNameTextField.getText(), lastNameTextField.getText(),
					phoneNumberTextField.getText()) && isDigitOnly(phoneNumberTextField.getText())) {

				viewUpdateController(CREATE_COMMAND);
			}
			return;
		}

	}// End Of inner class

}// End Of ContactManagerFrame
