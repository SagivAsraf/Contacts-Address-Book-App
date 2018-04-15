package MyAddressBook;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ListIterator;


public class FileListIterator<T extends IContact> implements ListIterator<T>, IFinalsInterface {

	private RandomAccessFile raf;

	public FileListIterator(RandomAccessFile raf) throws IOException {
		this.raf = raf;
		this.raf.seek(0);
	}

	@SuppressWarnings("unchecked")
	public T initContactWorkaround(String id, String firstName, String lastName, String phoneNumber) {
		return (T) new Contact(id, firstName, lastName, phoneNumber);
	}

	/** This method return the next contact in the address book **/
	@Override
	public T next() {
		if (this.hasNext()) {
			try {
				String id = FixedLengthStringIO.readFixedLengthString(ID_SIZE, this.raf);
				// System.out.println("True id: " + id);
				String firstName = FixedLengthStringIO.readFixedLengthString(F_AND_L_NAME_SIZE, this.raf);
				String lastName = FixedLengthStringIO.readFixedLengthString(F_AND_L_NAME_SIZE, this.raf);
				String phoneNum = FixedLengthStringIO.readFixedLengthString(PHONE_NUM_SIZE, this.raf);
				return initContactWorkaround(id, firstName, lastName,
						phoneNum);
			} catch (IOException e) {
				System.out.println("Can't get next Contact");
			}
		}
		return null;
	}

	/** This method return the previous contact in the address book **/

	@Override
	public T previous() {

		try {
			if (this.hasPrevious()) {
				this.raf.seek(this.raf.getFilePointer() - RECORD_SIZE);
				String id = FixedLengthStringIO.readFixedLengthString(ID_SIZE, this.raf);
				String firstName = FixedLengthStringIO.readFixedLengthString(F_AND_L_NAME_SIZE, this.raf);
				String lastName = FixedLengthStringIO.readFixedLengthString(F_AND_L_NAME_SIZE, this.raf);
				String phoneNum = FixedLengthStringIO.readFixedLengthString(PHONE_NUM_SIZE, this.raf);
				
				this.raf.seek(this.raf.getFilePointer() - RECORD_SIZE); // put
																		// the
																		// seek
																		// one
																		// contact
																		// backward
				return initContactWorkaround(id, firstName, lastName, phoneNum);

			}
		} catch (IOException e) {
			System.out.println("Can't get previous Contact");
		}
		return null;
	}

	/** This method check if we have next contact in the address book **/
	@Override
	public boolean hasNext() {
		try {
			if (this.raf.length() > this.raf.getFilePointer()) {
				return true;
			}
		} catch (IOException e) {
			System.out.println("Problem to check if we are on the end of file");
		}
		System.out.println("You are on the last contact!!!");
		return false;
	}

	/** This method check if we have previous contact in the address book **/
	@Override
	public boolean hasPrevious() {
		try {
			if (this.raf.getFilePointer() - RECORD_SIZE < 0) {
				System.out.println("You are on the first contact!!!");
				return false;
			}
		} catch (IOException e) {
			System.out.println("Problem to check if we are on the start of file");
		}
		return true;
	}

	/** This method add contact to the address book **/
	public void add(T e) {
		try {
			this.raf.seek(this.raf.length());
			e.writeObject(this.raf);
		} catch (IOException ex) {
			System.out.println("Cant add to the file");
		}
	}

	/** This method update contact in the address book **/
	@Override
	public void set(T e) {
		try {
			((Contact) e).updateObject(this.raf, this.raf.getFilePointer());
			// if(this.raf.getFilePointer() != 0){
			// this.raf.seek(this.raf.getFilePointer() - RECORD_SIZE);
			// }
		} catch (IOException e1) {
			System.out.println("Cant update this contact");
		}
	}

	@Override // Andrey say not to put into action
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Andrey say not to put into action
	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Andrey say not to put into action
	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
