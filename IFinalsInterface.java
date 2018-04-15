package MyAddressBook;

import javafx.scene.paint.Color;

public interface IFinalsInterface {

	static final long serialVersionUID = 1L;

	public enum myColorEnum {

		BLACK_COLOR_DEFAULT(Color.BLACK), 
		GREEN(Color.GREEN),
		RED(Color.RED), 
		BLUE(Color.BLUE);

		private Color myLabelsColor;

		myColorEnum(Color color) {
			this.myLabelsColor = color;
		}
		
		// myColorEnum getCurrentEnum

		Color getColorEnum(){
			return this.myLabelsColor;
		}

	}

	final static String ADDRESS_BOOK_NAME = "contactsSagiv2.dat";
	final static int BILLION = 1000000000;

	final static int FRAME_X_LOCATION = 220;
	final static int FRAME_Y_LOCATION = 150;

	final static int HEIGHT_VIEW = 700;
	final static int WIDTH_VIEW = 600;

	final static int HEIGHT_FX = 600;
	final static int WIDTH_FX = 550;

	final static int ID_SIZE = 9; // maximum of people 999999999.
	final static int F_AND_L_NAME_SIZE = 12;
	final static int PHONE_NUM_SIZE = 10;

	final static int BITS_IN_CHAR = 2;
	final static int TIMERSPEED = 1000;

	final static int FX_TIMER_SPEED = 1000;

	
	final static int RECORD_SIZE = (ID_SIZE + F_AND_L_NAME_SIZE * 2 + PHONE_NUM_SIZE) * BITS_IN_CHAR;

	final static int TEXT_FIELD_SIZE = 15;
	final static int GRIDLAYOUT_BIG_SPACES = 40;

	final static int GRID_SPACES = 12;
	final static int GRIDLAYOUT_ROWS = 4;
	final static int GRIDLAYOUT_COLS = 2;

	final static String OBJ_EXTENSION = "obj.dat";
	final static String TXT_EXTENSION = "txt";
	final static String BYTE_EXTENSION = "byte.dat";

	final static String FILE_NOT_FOUND = "File not found,please insert another" + " ID or another extension";
	final static String JUST_DIGITS = "Please insert just digits to phone number!";
	final static String INSERT_ALL_VALUES = "Please insert values for all the textfields!";
	final static String IM_EMPTY = "IM EMPTY";
	final static String DBEmpty = "Data Base is empty. please insert at least one contact";
	final static String PROBLEMFILE = "Problem to create new file";
	final static String INVALID_LENGTH = "One of the text fields contains a lot of chars! please try less";

	final static String ILLEGAL_ACTION = "You trying to do illegal action!!!";

	final static String ASC = "asc";
	final static String DESC = "desc";
	final static String SFIELD = "Sort - Field";
	final static String SCOUNT = "Sort - Count";
	final static String REVERSE = "Reverse";

	final static String NEXT_COMMAND = "Next";
	final static String PREVIOUS_COMMAND = "Previous";
	final static String LAST_COMMAND = "Last";
	final static String FIRST_COMMAND = "First";

	final static String CREATE_COMMAND = "Create";
	final static String UPDATE_COMMAND = "Update";
	final static String EXPORT_COMMAND = "Export";
	final static String SORT_COMMAND = "Sort";
	final static String LOAD_COMMAND = "Load";
	final static String SHOW_COMMAND = "Show";

	final static String FNAME_FIELD = "First Name Field";
	final static String LNAME_FIELD = "Last Name Field";
	final static String PHONE_NUM_FIELDE = "Phone Number Field";

	final static String[] ASC_OR_DESC_ARRAY = { ASC, DESC };

	final static String[] FILES_TYPES_ARRAY = { TXT_EXTENSION, OBJ_EXTENSION, BYTE_EXTENSION };

	final static String[] WHAT_ORDER_ARRAY = { FNAME_FIELD, LNAME_FIELD, PHONE_NUM_FIELDE };

	final static String[] SORT_TYPE_ARRAY = { SFIELD, SCOUNT, REVERSE };

}
