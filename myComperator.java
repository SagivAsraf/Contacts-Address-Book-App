package MyAddressBook;

import java.util.Comparator;


public class myComperator implements Comparator<IContact>, IFinalsInterface {

	private String ascOrDesc;
	private String whichField;

	public myComperator(String ascOrDesc, String whichField) {
		this.ascOrDesc = ascOrDesc;
		this.whichField = whichField;
	}

	@Override
	public int compare(IContact o1, IContact o2) {

		String firstContact;
		String secondContact;

		switch (this.whichField) {
		case FNAME_FIELD:
			firstContact = ((Contact) (o1)).getFirstName();
			secondContact = ((Contact) (o2)).getFirstName();
			if (this.ascOrDesc.equals(ASC)) {
				return getAscSort(firstContact, secondContact);
			} else {
				return getDescSort(firstContact, secondContact);
			}

		case LNAME_FIELD:
			firstContact = ((Contact) (o1)).getLastName();
			secondContact = ((Contact) (o2)).getLastName();
			if (this.ascOrDesc.equals(ASC)) {
				return getAscSort(firstContact, secondContact);
			} else {
				return getDescSort(firstContact, secondContact);
			}

		case PHONE_NUM_FIELDE:
			firstContact = ((Contact) (o1)).getPhoneNumber();
			secondContact = ((Contact) (o2)).getPhoneNumber();

			if (this.ascOrDesc.equals(ASC)) {
				return getAscSort(firstContact, secondContact);
			} else {
				return getDescSort(firstContact, secondContact);
			}
		}
		return 0; // will never happen cause in the comboBox there is ^ that 3
					// options...
	}

	/** This method do compare in ascending order **/
	private int getAscSort(String firstContact, String secondContact) {
		if (firstContact.compareTo(secondContact) > 0) {
			return 1;
		} else if (firstContact.compareTo(secondContact) < 0)
			return -1;

		else
			return 0;
	}

	/** This method do compare in descending order **/
	private int getDescSort(String firstContact, String secondContact) {
		if (firstContact.compareTo(secondContact) > 0) {
			return -1;
		} else if (firstContact.compareTo(secondContact) < 0)
			return 1;
		else
			return 0;
	}

}
