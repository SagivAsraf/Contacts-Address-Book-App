package MyAddressBook;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Random;


public class Contact implements IContact, IFinalsInterface {

	private String id;
	private String firstName;
	private String lastName;
	private String phoneNumber;

	/** Constructor without id = to example for create new contact **/

	public Contact(String firstName, String lastName, String phoneNumber) {
		this.id = getRandomId();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	/** This method return an id - that is a number that contain 9 digits **/
	// Andrey suggest me to do the ID in this way.
	// the chance that we will get 2 same ids is almost zero...
	private String getRandomId() {
		Random random = new Random();
		return String.format("%09d", random.nextInt(BILLION));
	}

	/** Copy Constructor **/ // I know that I can just compare between the
								// adresses of the objects, but I prefer to do a
								// new object by copyConstructor
	public Contact(Contact con) {
		this.setId(con.getId());
		this.setFirstName(con.getFirstName());
		this.setLastName(con.getLastName());
		this.setPhoneNumber(con.getPhoneNumber());
	}

	/** Constructor with id = to example for UPDATE EXIST contact **/

	public Contact(String id, String firstName, String lastName, String phoneNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void writeObject(RandomAccessFile randomAccessFile) throws IOException {

		writeContact(randomAccessFile);
		System.out.println("add:");
		System.out.println(this.toString()); // just for myself

	}

	public void updateObject(RandomAccessFile randomAccessFile, long position) throws IOException {
		if (randomAccessFile.getFilePointer() > 0) {
			randomAccessFile.seek(position - RECORD_SIZE);
		}

		writeContact(randomAccessFile);

		System.out.println("Update:");
		System.out.println(this.toString()); // just for myself

	}

	/** Helper method that help to write contact on file **/
	// I did it cause I used it twice - the same code in update and write
	// object.
	private void writeContact(RandomAccessFile randomAccessFile) throws IOException {

		FixedLengthStringIO.writeFixedLengthString(id, ID_SIZE, randomAccessFile);
		FixedLengthStringIO.writeFixedLengthString(this.firstName, F_AND_L_NAME_SIZE, randomAccessFile);
		FixedLengthStringIO.writeFixedLengthString(this.lastName, F_AND_L_NAME_SIZE, randomAccessFile);
		FixedLengthStringIO.writeFixedLengthString(this.phoneNumber, PHONE_NUM_SIZE, randomAccessFile);
	}

	/** Compare with two contacts **/ // Andrey said its OK to do compare in
										// contact

	public boolean compareTo(Contact c) {
		if (c != null) {
			if (this.getFirstName().trim().equals(c.getFirstName().trim())
					&& this.getLastName().trim().equals(c.getLastName().trim())
					&& this.getPhoneNumber().trim().equals(c.getPhoneNumber().trim())
					&& this.getId().trim().equals(c.getId().trim()) 
			) {
				return true;
			}
			return false;
		}
		return false;
	}

	/** This method export contact to a file **/
	@Override
	public void export(String format, File file) throws IOException {

		switch (format) {

		case TXT_EXTENSION:
			PrintWriter pw = new PrintWriter(file);
			pw.println(this.id);
			pw.println(this.firstName);
			pw.println(this.lastName);
			pw.println(this.phoneNumber);
			pw.close();
			break;

		case OBJ_EXTENSION:
			ObjectOutputStream outputObject = new ObjectOutputStream(new FileOutputStream(file));
			outputObject.writeUTF(this.id + "");
			outputObject.writeUTF(this.firstName);
			outputObject.writeUTF(this.lastName);
			outputObject.writeUTF(this.phoneNumber);

			outputObject.close();
			break;
		case BYTE_EXTENSION:
			DataOutputStream outputData = new DataOutputStream(new FileOutputStream(file));
			outputData.writeUTF(this.id + "");
			outputData.writeUTF(this.firstName);
			outputData.writeUTF(this.lastName);
			outputData.writeUTF(this.phoneNumber);

			outputData.close();

			break;
		default:
			break;
		}

		System.out.println("Export:");
		System.out.println(this.toString()); // just for myself

	}

	/** This method "put" the contact into array of strings **/

	@Override
	public String[] getUiData() {
		String[] str = new String[4];
		str[0] = this.id + "";
		str[1] = this.firstName;
		str[2] = this.lastName;
		str[3] = this.phoneNumber;

		return str;
	}

	/** This method give us the object size **/ // *** I just used at
												// RECORD_SIZE as a constant.
	@Override
	public int getObjectSize() {
		return RECORD_SIZE;
	}

	/** This method return the id of the contact **/
	public String getId() {
		return id;
	}

	/** This method set the id of the contact **/
	public void setId(String id) {
		this.id = id;
	}

	/** This method return the FirstName of the contact **/

	public String getFirstName() {
		return firstName;
	}

	/** This method set the first name of the contact **/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** This method return the last name of the contact **/

	public String getLastName() {
		return lastName;
	}

	/** This method set the last name of the contact **/

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** This method return the phone number of the contact **/

	public String getPhoneNumber() {
		return phoneNumber;
	}

	/** This method set the phone number of the contact **/

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// its for my checks. (the toString) we don't required to do this.
	public String toString() {
		return "ID: " + this.id // *static variable*/
				+ "\nFirstName: " + this.firstName + "\nLastName: " + this.lastName + "\nPhoneNumber: "
				+ this.phoneNumber + "\n";
	}
}
