package MyAddressBook;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ContactManagerViewFX implements IContactManagerView, IRegistable, IFinalsInterface {

	private ArrayList<ISagivListener> listeners = new ArrayList<ISagivListener>();

	private Timeline timerColor;

	private myColorEnum colorEnum = myColorEnum.BLACK_COLOR_DEFAULT;

	private BorderPane mainPane;

	private GridPane paneOfTop;
	private GridPane paneOfCener;
	private GridPane paneOfBottom;

	private Button btCreate;
	private Button btUpdate;
	private Button btPrevious;
	private Button btNext;
	private Button btFirst;
	private Button btLast;
	private Button btEdit;
	private Button btExport;
	private Button btLoad;
	private Button btSort;
	private Button btShow;

	private TextField firstNameTextField;
	private TextField lastNameTextField;
	private TextField phoneNumberTextField;
	private TextField filePathTextField;

	private Label firstNameLabelShow;
	private Label lastNameLabelShow;
	private Label phoneNumberLabelShow;

	private ComboBox<String> cbFilesExtension;
	private ComboBox<String> cbSortType;
	private ComboBox<String> cbFieldSortBy;
	private ComboBox<String> cbAscOrDesc;
	private ComboBox<String> cbAscOrDesc2;

	public ContactManagerViewFX() {
		setGuiPanes();
		setHandlerEventsForButtons();
		setFontForLabels();
		setTimer();
	}

	/**This method set the font for the labels **/
	private void setFontForLabels() {
		Font labelsFont = Font.font("Arial", FontWeight.BOLD, 14);
		this.firstNameLabelShow.setFont(labelsFont);
		this.lastNameLabelShow.setFont(labelsFont);
		this.phoneNumberLabelShow.setFont(labelsFont);

	}

	/** This method set the timer of the colors **/
	private void setTimer() {

		EventHandler<ActionEvent> eventHandler = e -> {
			setColorsToLabels();
		};

		timerColor = new Timeline(new KeyFrame(Duration.millis(FX_TIMER_SPEED), eventHandler));
		timerColor.setCycleCount(Timeline.INDEFINITE);
		timerColor.play();
	}

	/** This method initial my java FX view. and set general properties **/
	public void init(Stage primaryStage) {


		primaryStage.setTitle("HW4 --> JFX ---> Sagiv Asraf");
		primaryStage.setAlwaysOnTop(true);
		Scene scene = new Scene(this.mainPane, WIDTH_FX, HEIGHT_FX);
		primaryStage.setScene(scene);
		primaryStage.setX(FRAME_X_LOCATION + WIDTH_VIEW);
		primaryStage.setY(FRAME_Y_LOCATION); // TO SHOW NEXT TO THE VIEW
		primaryStage.show();

		this.colorEnum = myColorEnum.BLACK_COLOR_DEFAULT;
	}

	/** Set all the view panes **/
	private void setGuiPanes() {
		this.mainPane = new BorderPane();
		setPaneOfTop();
		setPaneOfCenter();
		setPaneOfBottom();

	}

	/** Set the top pane **/

	private void setPaneOfTop() {

		this.paneOfTop = new GridPane();
		this.paneOfTop.setAlignment(Pos.CENTER);
		this.paneOfTop.setPadding(new Insets(GRID_SPACES, GRID_SPACES, GRID_SPACES, GRID_SPACES));
		this.paneOfTop.setHgap(5);
		this.paneOfTop.setVgap(5);

		this.paneOfTop.add(new Label("First Name:"), 0, 0);

		this.firstNameTextField = new TextField();
		this.paneOfTop.add(firstNameTextField, 1, 0);
		this.paneOfTop.add(new Label("Last Name:"), 0, 1);
		this.lastNameTextField = new TextField();
		this.paneOfTop.add(lastNameTextField, 1, 1);
		this.phoneNumberTextField = new TextField();
		this.paneOfTop.add(new Label("Phone Number:"), 0, 2);
		this.paneOfTop.add(phoneNumberTextField, 1, 2);

		this.btUpdate = new Button("Update");
		this.paneOfTop.add(btUpdate, 1, 3);
		GridPane.setHalignment(btUpdate, HPos.RIGHT);

		this.btCreate = new Button("Create");
		this.paneOfTop.add(btCreate, 0, 3);
		GridPane.setHalignment(btCreate, HPos.LEFT);

		this.mainPane.setTop(paneOfTop);
	}

	/** Set the center pane **/

	private void setPaneOfCenter() {
		this.paneOfCener = new GridPane();

		setPaneOfCenterUp();
		setPaneOfCenterDown();
		setPaneOfCenterDownDown();
		this.mainPane.setCenter(paneOfCener);
	}

	/** Helper method to Set the center(UP) pane **/

	private void setPaneOfCenterUp() {

		BorderPane paneOfCenterUp = new BorderPane();

		GridPane buttonsLeftPane = new GridPane();
		this.btPrevious = new Button("<");
		buttonsLeftPane.add(btPrevious, 0, 0);
		this.btFirst = new Button("First");
		buttonsLeftPane.add(btFirst, 0, 1);
		buttonsLeftPane.setPadding(new Insets(20));

		paneOfCenterUp.setLeft(buttonsLeftPane);

		GridPane paneOfLabels = new GridPane();

		paneOfLabels.add(new Label("First Name:"), 1, 0);
		paneOfLabels.add(new Label("Last Name:"), 1, 1);
		paneOfLabels.add(new Label("Phone Number:"), 1, 2);
		this.btEdit = new Button("Edit");
		paneOfLabels.add(btEdit, 1, 3);
		this.firstNameLabelShow = new Label(IM_EMPTY);

		paneOfLabels.add(firstNameLabelShow, 2, 0);
		this.lastNameLabelShow = new Label(IM_EMPTY);
		paneOfLabels.add(lastNameLabelShow, 2, 1);
		this.phoneNumberLabelShow = new Label(IM_EMPTY);
		paneOfLabels.add(phoneNumberLabelShow, 2, 2);
		paneOfLabels.setHgap(40);
		paneOfLabels.setVgap(3);

		paneOfLabels.setPadding(new Insets(20));
		paneOfCenterUp.setCenter(paneOfLabels);

		GridPane buttonsRightPane = new GridPane();

		this.btNext = new Button(">");
		buttonsRightPane.add(btNext, 1, 0);

		this.btLast = new Button("Last");
		buttonsRightPane.add(btLast, 1, 1);
		buttonsRightPane.setPadding(new Insets(20, 20, 20, 80));

		paneOfCenterUp.setRight(buttonsRightPane);

		this.paneOfCener.add(paneOfCenterUp, 0, 0);

	}

	/** Helper method to Set the center(Down) pane **/

	private void setPaneOfCenterDown() {

		BorderPane paneOfCenterDown = new BorderPane();
		GridPane paneForComboAndButton = new GridPane();

		setPaneForComboAndButton(paneForComboAndButton);
		paneOfCenterDown.setLeft(paneForComboAndButton);
		GridPane paneForLabelTextAndButton = new GridPane();
		setPaneForLabelTextAndButton(paneForLabelTextAndButton);

		paneOfCenterDown.setRight(paneForLabelTextAndButton);
		this.paneOfCener.add(paneOfCenterDown, 0, 1);

	}

	/** Helper method to set pane for label,text field and button **/

	private void setPaneForLabelTextAndButton(GridPane paneForLabelTextAndButton) {

		this.btLoad = new Button("Load");
		this.filePathTextField = new TextField();
		paneForLabelTextAndButton.add(new Label("File path: "), 0, 0);
		paneForLabelTextAndButton.add(filePathTextField, 0, 1);
		paneForLabelTextAndButton.add(btLoad, 0, 2);
		GridPane.setHalignment(btLoad, HPos.RIGHT);
		paneForLabelTextAndButton.setVgap(5);

		paneForLabelTextAndButton.setPadding(new Insets(GRID_SPACES));

	}

	/** Helper method to set pane for combo box and button **/

	private void setPaneForComboAndButton(GridPane paneForComboAndButton) {

		ObservableList<String> itemsForFileTypeCombo = FXCollections.observableArrayList(FILES_TYPES_ARRAY);

		this.cbFilesExtension = new ComboBox<String>(itemsForFileTypeCombo);
		this.cbFilesExtension.setValue(TXT_EXTENSION);
		this.btExport = new Button("Export");
		paneForComboAndButton.add(new Label(), 0, 0); // for design

		paneForComboAndButton.add(cbFilesExtension, 0, 1);
		paneForComboAndButton.add(btExport, 1, 1);
		paneForComboAndButton.setHgap(5);
		paneForComboAndButton.setPadding(new Insets(GRID_SPACES));
	}

	/** Helper method to Set the center(Down-Down) pane **/

	private void setPaneOfCenterDownDown() {

		GridPane paneOfCenterDownDown = new GridPane();

		paneOfCenterDownDown.setPadding(new Insets(20));
		paneOfCenterDownDown.setHgap(20);
		set3ComboBoxAndButton();
		paneOfCenterDownDown.add(this.cbSortType, 0, 0);
		paneOfCenterDownDown.add(this.cbFieldSortBy, 1, 0);
		paneOfCenterDownDown.add(this.cbAscOrDesc, 2, 0);
		paneOfCenterDownDown.add(this.btSort, 3, 0);
		this.paneOfCener.add(paneOfCenterDownDown, 0, 2);

	}

	/** Helper method to set 3 combo boxes and one button **/

	private void set3ComboBoxAndButton() {

		ObservableList<String> itemsForSortType = FXCollections.observableArrayList(SORT_TYPE_ARRAY);

		this.cbSortType = new ComboBox<String>(itemsForSortType);
		this.cbSortType.setValue(SFIELD);

		ObservableList<String> itemsForWhatOrderByCombo = FXCollections.observableArrayList(WHAT_ORDER_ARRAY);

		this.cbFieldSortBy = new ComboBox<String>(itemsForWhatOrderByCombo);
		this.cbFieldSortBy.setValue(FNAME_FIELD);

		ObservableList<String> itemsForAscOrDescCombo = FXCollections.observableArrayList(ASC_OR_DESC_ARRAY);

		this.cbAscOrDesc = new ComboBox<String>(itemsForAscOrDescCombo);
		this.cbAscOrDesc.setValue(ASC);
		this.btSort = new Button("Sort");

	}

	/** Set the bottom pane **/
	private void setPaneOfBottom() {
		this.paneOfBottom = new GridPane();

		ObservableList<String> itemsForAscOrDescCombo = FXCollections.observableArrayList(ASC_OR_DESC_ARRAY);

		this.cbAscOrDesc2 = new ComboBox<String>(itemsForAscOrDescCombo);
		this.cbAscOrDesc2.setValue(ASC);
		this.btShow = new Button("Show");

		paneOfBottom.add(cbAscOrDesc2, 0, 0);
		paneOfBottom.add(btShow, 1, 0);
		paneOfBottom.setPadding(new Insets(20));
		paneOfBottom.setHgap(80);

		this.mainPane.setBottom(paneOfBottom);

	}

	/** Register the listener to the listeners array **/

	@Override
	public void registerListener(ISagivListener iSagivListener) {
		listeners.add(iSagivListener);
		// btFirst.fire(); // cause i have to do it just after the listener is
		// register to the array
		System.out.println("herE//");
	}

	/** Set all the handlers for the button **/
	private void setHandlerEventsForButtons() {
		btNext.setOnAction(e -> {
			nextContact();
		});

		btPrevious.setOnAction(e -> {
			prevContact();
		});

		btLast.setOnAction(e -> {
			lastContact();
		});

		btFirst.setOnAction(e -> {
			firstContact();
		});

		btCreate.setOnAction(e -> {
			createContact();
		});

		btEdit.setOnAction(e -> {
			editContact();
		});

		btUpdate.setOnAction(e -> {
			updateContact();
		});

		btExport.setOnAction(e -> {
			exportContact();
		});

		btSort.setOnAction(e -> {
			doSort();
		});
		btLoad.setOnAction(e -> {
			loadContact();
		});
		btShow.setOnAction(e -> {
			doShow();
		});
	}

	/** This method load contact From file **/

	private void loadContact() {
		viewFxUpdateController(LOAD_COMMAND);
	}

	/**
	 * This method show the contacts of the address book without any button
	 **/
	private void doShow() {
		viewFxUpdateController(SHOW_COMMAND);
	}

	/** This method sort the contacts of the address book **/
	private void doSort() {

		viewFxUpdateController(SORT_COMMAND);
		firstContact();

	}

	/** This method export contact to file **/

	private void exportContact() {
		viewFxUpdateController(EXPORT_COMMAND);
		cleanTextsFieldsAndDisableUpdate();
	}

	/** This Method update exist Contact **/

	private void updateContact() {
		// check if valid length for all the fields & that the fields aren't
		// empty & that the phone number contain just digits
		if (checkTextFieldsFull() && isValidLengthForAll(firstNameTextField.getText(), lastNameTextField.getText(),
				phoneNumberTextField.getText()) && isDigitOnly(phoneNumberTextField.getText())) {
			viewFxUpdateController(UPDATE_COMMAND);

		}

	}

	/** This Method create new Contact **/

	private void createContact() {
		// check if valid length for all the fields & that the fields aren't
		// empty & that the phone number contain just digits

		if (checkTextFieldsFull() && isValidLengthForAll(firstNameTextField.getText(), lastNameTextField.getText(),
				phoneNumberTextField.getText()) && isDigitOnly(phoneNumberTextField.getText())) {

			viewFxUpdateController(CREATE_COMMAND);
		}
		return;
	}

	/** This method present the details of the FIRST contact **/

	private void firstContact() {
		viewFxUpdateController(FIRST_COMMAND);

	}

	/** This method present the details of the next contact **/

	private void nextContact() {
		viewFxUpdateController(NEXT_COMMAND);
	}

	/** This method present the details of the previous contact **/

	private void prevContact() {
		viewFxUpdateController(PREVIOUS_COMMAND);
	}

	/** This method present the details of the Last contact **/

	private void lastContact() {
		viewFxUpdateController(LAST_COMMAND);
	}

	/** This Method gives the option for update **/
	private void editContact() {

		// EDIT CONTACT is attached only to the view, so the controller
		// doesn't know about it. its internal method of the view!
		String str = IM_EMPTY;
		if (phoneNumberLabelShow.getText().compareTo(str) != 0) {
			// because phone number can include just numbers, except if the
			// file is empty!
			btUpdate.setDisable(false);
			firstNameTextField.setText(firstNameLabelShow.getText().trim());
			lastNameTextField.setText(lastNameLabelShow.getText().trim());
			phoneNumberTextField.setText(phoneNumberLabelShow.getText().trim());
		} else {
			System.out.println(DBEmpty);
		}
	}

	private void presentData(final String[] contactDetails) {
		if (contactDetails == null) {
			return;
		} else {



			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					firstNameLabelShow.setText(contactDetails[1]);
					lastNameLabelShow.setText(contactDetails[2]);
					phoneNumberLabelShow.setText(contactDetails[3]);
				}

			});

		}

	}

	/** set the labels color **/
	private void setColorsToLabels() {
		firstNameLabelShow.setTextFill(colorEnum.getColorEnum());
		lastNameLabelShow.setTextFill(colorEnum.getColorEnum());
		phoneNumberLabelShow.setTextFill(colorEnum.getColorEnum());
	}

	/** This method clean text fields and disable update button**/

	private void cleanTextsFieldsAndDisableUpdate() {
		this.btUpdate.setDisable(true);
		this.firstNameTextField.setText("");
		this.lastNameTextField.setText("");
		this.phoneNumberTextField.setText("");
	}

	/** This method check if there is null fields **/

	private boolean checkTextFieldsFull() {
		if (firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("")
				|| phoneNumberTextField.getText().equals("")) {
			System.out.println(INSERT_ALL_VALUES);
			return false;
		}
		return true;
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

	/** Get information from the view (FX) - transfer to the controller **/

	private void viewFxUpdateController(String operation) {

		for (ISagivListener listener : listeners) {
			switch (operation) {

			case NEXT_COMMAND:
				listener.getNextContact();
				break;
			case FIRST_COMMAND:
				listener.getFirstContact();
				break;
			case PREVIOUS_COMMAND:
				listener.getPreviousContact();
				break;
			case LAST_COMMAND:
				listener.getLastContact();
				break;
			case CREATE_COMMAND:
				listener.createContact(this.firstNameTextField.getText() + "", this.lastNameTextField.getText() + "",
						this.phoneNumberTextField.getText() + "");
				break;
			case UPDATE_COMMAND:
				listener.updateContact(this.firstNameTextField.getText() + "", this.lastNameTextField.getText() + "",
						this.phoneNumberTextField.getText() + "");
				break;
			case EXPORT_COMMAND:
				listener.Export(cbFilesExtension.getValue(), firstNameLabelShow.getText(), lastNameLabelShow.getText(),
						phoneNumberLabelShow.getText());
				break;
			case SORT_COMMAND:
				listener.Sort(cbSortType.getValue(), cbFieldSortBy.getValue(), cbAscOrDesc.getValue());
				break;
			case SHOW_COMMAND:
				listener.Show(cbAscOrDesc2.getValue());
				break;
			case LOAD_COMMAND:
				listener.Load(cbFilesExtension.getValue(), filePathTextField.getText());
				break;
			}
		}
	}

	@Override
	public void updateViewForNext(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		this.colorEnum = myColorEnum.GREEN;
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForPrevious(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		this.colorEnum = myColorEnum.RED;
		presentData(myContactsDetails);

	}

	@Override
	public void updateViewForFirst(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		this.colorEnum = myColorEnum.RED;
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForLast(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		this.colorEnum = myColorEnum.GREEN;
		presentData(myContactsDetails);
	}

	@Override
	public void updateViewForCreate(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		this.colorEnum = myColorEnum.BLUE;
		presentData(myContactsDetails);

	}

	@Override
	public void updateViewForUpdate(String[] myContactsDetails) {
		cleanTextsFieldsAndDisableUpdate();
		this.colorEnum = myColorEnum.BLUE;
		presentData(myContactsDetails);

	}

	@Override
	public void updateViewForLoad(String[] myContactsDetails) {
		loadContactToTextFields(myContactsDetails);
	}

	private void loadContactToTextFields(String[] myContactsDetails) {
		this.firstNameTextField.setText(myContactsDetails[1]);
		this.lastNameTextField.setText(myContactsDetails[2]);
		this.phoneNumberTextField.setText(myContactsDetails[3]);
	}

}
