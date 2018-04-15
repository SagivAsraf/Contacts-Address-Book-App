package MyAddressBook;

public interface IContactManagerModel {

	void getNext();
	
	void getPrev();

	void getFirstContact();

	void getLastContact();
	
	void createNewContact(String firstName, String lastName, String phoneNumber);

	void updateMyContact(String firstName, String lastName, String phoneNumber);

	void exportMyContact(String fileFormat, 
			String firstName, String lastName, String phoneNumber);
	
	void sortMyContacts(String sortType, String fieldToSortBy, String AscOrDesc);
	
	void loadMyContact(String format, String fileName);

	boolean isNOTStartOfDB();
	
	boolean isNOTEndOfDB();
	
	boolean isDBEmpty();
	
}
