package MyAddressBook;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class ContactsManagerModel implements IFinalsInterface, IContactManagerModel, IRegistable {

	private ArrayList<ISagivListener> listeners = new ArrayList<ISagivListener>();

	private RandomAccessFile raf;
	private FileListIterator<IContact> myFileListIterator;
	private List<IContact> myListOfContacts;
	private ListIterator<IContact> myListIterator;
	private TreeSet<IContact> myFieldTreeSet;

	private IContact currentContact;

	public ContactsManagerModel(String fileName) throws IOException {

		this.raf = new RandomAccessFile(fileName, "rw");
		this.myFileListIterator = new FileListIterator<>(this.raf);

		if (!isDBEmpty()) { // if I start my program with file that already
							// exist - I set the currentContact
			currentContact = this.myFileListIterator.next();
		}

	}

	/** This method create new contact **/
	public void createNewContact(String firstName, String lastName, String phoneNumber) {

		currentContact = new Contact(firstName, lastName, phoneNumber);
		this.myFileListIterator.add(currentContact);

		modelUpadateController(CREATE_COMMAND, currentContact.getUiData());

	}

	/** This method update exist contact **/
	public void updateMyContact(String firstName, String lastName, String phoneNumber) {

		String id = ((Contact) (currentContact)).getId();
		currentContact = new Contact(id, firstName, lastName, phoneNumber);

		this.myFileListIterator.set(currentContact);

		modelUpadateController(UPDATE_COMMAND, currentContact.getUiData());

	}

	/** Return The Previous Contact Details **/
	@Override
	public void getPrev() {

		if (!isDBEmpty()) {
			IContact myContact = this.myFileListIterator.previous();

			if (((Contact) (currentContact)).compareTo(((Contact) myContact))) {
				System.out.println("got inside - they equal go previous again");
				myContact = this.myFileListIterator.previous();
				try {
					// if i go twice previous, the seek is wrong. so i fix it
					// here. (seek wrong = update the wrong contact.
					if (this.raf.getFilePointer() != 0) {
						// cause 0 = i am in the start of the file.
						this.raf.seek(this.raf.getFilePointer() + RECORD_SIZE);
					}
				} catch (IOException e) {
					System.out.println("Cant get the prev contact");
				}
			}
			if (myContact != null) {
				// currentContact = myContact; //prefer with copy constructor
				currentContact = new Contact((Contact) myContact);
				modelUpadateController(PREVIOUS_COMMAND, myContact.getUiData());
			}
		}
	}

	/** Return The Next Contact Details **/
	@Override
	public void getNext() {
		if (!isDBEmpty()) {

			IContact myContact = this.myFileListIterator.next();
			if (((Contact) (currentContact)).compareTo(((Contact) myContact))) {
				System.out.println("got inside - they equal go next again");
				myContact = this.myFileListIterator.next();
			}
			if (myContact != null) {
				currentContact = new Contact((Contact) myContact);
				// update all listeners (if there are any) that something have
				// been changed
				modelUpadateController(NEXT_COMMAND, myContact.getUiData());
			}
		}
	}

	@Override
	public void getFirstContact() {

		if (!isDBEmpty()) {
			while ((this.myFileListIterator.hasPrevious())) {
				currentContact = this.myFileListIterator.previous();
			}
			if (currentContact != null) {
				modelUpadateController(FIRST_COMMAND, currentContact.getUiData());
			}
		}
	}

	@Override
	public void getLastContact() {
		if (!isDBEmpty()) {
			while ((this.myFileListIterator.hasNext())) {
				currentContact = this.myFileListIterator.next();
			}
			if (currentContact != null) {
				modelUpadateController(LAST_COMMAND, currentContact.getUiData());
			}
		}
	}

	/**
	 * This method export the contact that we see on the center labels,by format
	 * that we got in the Combo box
	 **/
	public void exportMyContact(String fileFormat, String firstName, String lastName, String phoneNumber) {

		if (!isDBEmpty()) {
			Contact myNewContactToExport = null;
			try {
				String id = ((Contact) currentContact).getId();
				myNewContactToExport = new Contact(id, firstName, lastName, phoneNumber);
				myNewContactToExport.export(fileFormat, new File("" + myNewContactToExport.getId() + "." + fileFormat));
				modelUpadateController(EXPORT_COMMAND, currentContact.getUiData());

			} catch (IOException e) {
				System.out.println("Can't export contact");
			}
		} else
			return;
	}

	/**
	 * This method load contact from file, by format that we got in the combo
	 * box
	 **/

	public void loadMyContact(String format, String fileName) {

		if (isDBEmpty()) {
			return;
		} else {
			File f = new File(fileName + "." + format);
			String[] contactDetails = new String[4];
			switch (format) {
			case TXT_EXTENSION:
				Scanner s;
				System.out.println("Im here text");
				try {
					s = new Scanner(f);
					contactDetails[0] = s.nextLine();
					contactDetails[1] = s.nextLine();
					contactDetails[2] = s.nextLine();
					contactDetails[3] = s.nextLine();

					s.close();
				} catch (FileNotFoundException e) {
					System.out.println(FILE_NOT_FOUND);
				}
				break;

			case OBJ_EXTENSION:
				System.out.println("Im here obj");

				ObjectInputStream input;
				try {
					input = new ObjectInputStream(new FileInputStream(f));

					contactDetails[0] = input.readUTF();
					contactDetails[1] = input.readUTF();
					contactDetails[2] = input.readUTF();
					contactDetails[3] = input.readUTF();

					input.close();

				} catch (FileNotFoundException e) {
					System.out.println(FILE_NOT_FOUND);
				} catch (IOException e) {
					System.out.println("Problem to read from obj file");
				}

				break;

			case BYTE_EXTENSION:

				try {
					System.out.println("im here byte");
					@SuppressWarnings("resource")
					DataInputStream inputData = new DataInputStream(new FileInputStream(f));

					try {
						contactDetails[0] = inputData.readUTF();
						contactDetails[1] = inputData.readUTF();
						contactDetails[2] = inputData.readUTF();
						contactDetails[3] = inputData.readUTF();

					} catch (IOException e) {
						System.out.println("Problem to read from byte file");
					}

				} catch (FileNotFoundException e) {
					System.out.println(FILE_NOT_FOUND);
				}
				break;
			}

			modelUpadateController(LOAD_COMMAND, contactDetails);

		}
	}

	/** This method sort the contacts in the address book **/
	public void sortMyContacts(String sortType, String fieldToSortBy, String AscOrDesc) {
		if (!isDBEmpty()) {
			WriteFileToList();

			switch (sortType) {
			case "Sort - Field":
				sortSwitchByField(fieldToSortBy, AscOrDesc);
				sortSwitchField();

				break;
			case "Sort - Count":
				sortSwitchByCount(fieldToSortBy, AscOrDesc);
				break;
			case "Reverse":
				sortSwitchReverse();
				break;
			}

			modelUpadateController(SORT_COMMAND, currentContact.getUiData());

		}
		return;
	}

	/** Tell the controller what has been done and who was changed **/

	private void modelUpadateController(String operation, String[] myContact) {
		for (ISagivListener listener : listeners) {
			switch (operation) {
			case NEXT_COMMAND:
				listener.controllerUpdateView(NEXT_COMMAND, myContact);
				break;

			case PREVIOUS_COMMAND:
				listener.controllerUpdateView(PREVIOUS_COMMAND, myContact);
				break;

			case FIRST_COMMAND:
				listener.controllerUpdateView(FIRST_COMMAND, myContact);
				break;

			case LAST_COMMAND:
				listener.controllerUpdateView(LAST_COMMAND, myContact);
				break;

			case CREATE_COMMAND:
				listener.controllerUpdateView(CREATE_COMMAND, myContact);
				break;

			case UPDATE_COMMAND:
				listener.controllerUpdateView(UPDATE_COMMAND, myContact);
				break;

			case LOAD_COMMAND:
				listener.controllerUpdateView(LOAD_COMMAND, myContact);
				break;

			case EXPORT_COMMAND:
				System.out.println("I doing export to: " + myContact.toString());
				break;

			case SORT_COMMAND:
				System.out.println("I doing sort with MVC");
				// I decided don't "controllerUpdateView" cause sort is a
				// function that
				// affect just
				// THE MODEL! that sort the file. but, after I am doing sort, I
				// always present the firstContact, and then, the view will
				// update!
				break;

			}
		}
	}

	/** This method sortByCount **/
	private void sortSwitchByCount(String fieldToSortBy, final String AscOrDesc) {

		Map<ContactFieldCounterDecorator, Integer> myMap = new TreeMap<ContactFieldCounterDecorator, Integer>();

		myListIterator = myListOfContacts.listIterator(0);

		while (myListIterator.hasNext()) {
			Contact myContact = (Contact) myListIterator.next();
			ContactFieldCounterDecorator myContactDecorator = new ContactFieldCounterDecorator(myContact,
					fieldToSortBy);

			Integer count = myMap.get(myContactDecorator);
			if (count == null) {
				myMap.put(myContactDecorator, 1);
			} else {
				myMap.put(myContactDecorator, count + 1);
			}

		}

		Map<Integer, Contact> myTreeMap = new TreeMap<Integer, Contact>((new myComperatorForTree(AscOrDesc)));

		for (Entry<ContactFieldCounterDecorator, Integer> e : myMap.entrySet()) {
			myTreeMap.put(e.getValue(), e.getKey().getContact());
		}

		for (Entry<Integer, Contact> e : myTreeMap.entrySet()) {
			System.out.println(e.getKey() + "  <--- KEY  " + (Contact) (e.getValue()));
		}

		showSwitchedCount(fieldToSortBy, myTreeMap);

	}

	/** This method show the new field after sorting **/

	private void showSwitchedCount(String fieldToSortBy, Map<Integer, Contact> myTreeMap) {
		System.out.println("I chose sort Count! Field: " + fieldToSortBy);
		try {
			// I want to "remove" the file, cause I want that after
			// I sorted the list, I will not have duplications.
			this.getRaf().setLength(0);
			this.getRaf().seek(0);

			for (Entry<Integer, Contact> e : myTreeMap.entrySet()) {
				e.getValue().writeObject(this.getRaf());
			}

		} catch (IOException e1) {
			System.out.println("Cant show the sort");
		}
	}

	/**
	 * This method reverse all the contact and write them to the file afte
	 * reversing
	 **/
	private void sortSwitchReverse() {

		System.out.println("I chose Reverse: ");
		myListIterator = myListOfContacts.listIterator(myListOfContacts.size());

		try {
			this.getRaf().seek(0);
			while (myListIterator.hasPrevious()) {
				((Contact) myListIterator.previous()).writeObject(this.getRaf());
			}
		} catch (IOException e) {
			System.out.println("Can't write the list to the file");
		}
	}

	/** This method show the new field after sorting **/
	private void sortSwitchField() {
		System.out.println("I chose sort Switch Field: ");
		try {
			// I want to "remove" the file, cause I want that after
			// I sorted the list, I will not have duplications.
			this.getRaf().setLength(0);
			this.getRaf().seek(0);

			for (IContact iContact : myFieldTreeSet) {
				((Contact) iContact).writeObject(this.getRaf());
			}
		} catch (IOException e1) {
			System.out.println("Cant show the sort");
		}

	}

	/** This method sortByField **/
	private void sortSwitchByField(String fieldToSortBy, String AscOrDesc) {
		myFieldTreeSet = new TreeSet<IContact>(new myComperator(AscOrDesc, fieldToSortBy));
		writeListToTree();
	}

	/** This method write the file to a List **/
	private void WriteFileToList() {
		myListOfContacts = new ArrayList<IContact>();
		try {
			this.getRaf().seek(0);
			while (isNOTEndOfDB()) {
				myListOfContacts.add(myFileListIterator.next());
			}
			this.getRaf().seek(0);
		} catch (IOException e) {
			System.out.println("Can't write to the list from the file");
		}
	}

	private void writeListToTree() {
		myListIterator = myListOfContacts.listIterator(0);
		while (myListIterator.hasNext()) {
			myFieldTreeSet.add(((Contact) myListIterator.next()));
		}
		System.out.println(myFieldTreeSet);
	}

	/** This method help to understand if file is empty or not **/
	@Override
	public boolean isDBEmpty() {

		try {
			if (this.getRaf().length() <= 0) {
				System.out.println(DBEmpty);
				return true;
			}
		} catch (IOException e) {
			System.out.println("Problem to check if file is empty");
		}
		return false;
	}

	/** This method check if I am NOT the FIRST contact - start of file **/
	@Override
	public boolean isNOTStartOfDB() {
		return this.myFileListIterator.hasPrevious();
	}

	/** This method help to understand if I am NOT at the end of file **/
	public boolean isNOTEndOfDB() {
		return this.myFileListIterator.hasNext();
	}

	/** Return raf **/
	public RandomAccessFile getRaf() {
		return this.raf;
	}

	/** Add the listener to the listeners array **/
	@Override
	public void registerListener(ISagivListener iSagivListener) {
		listeners.add(iSagivListener);
	}

}
