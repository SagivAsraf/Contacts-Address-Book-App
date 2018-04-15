package MyAddressBook;

/** This is my Listener - instead of ActionListener **/
public interface ISagivListener {

 // ----> Model Methods (by controller) <------ //	
	void getNextContact();
	
	void getPreviousContact();

	void getLastContact();

	void getFirstContact();

	void createContact(String firstName,String lastName,String phoneNumber);
	
	void updateContact(String firstName,String lastName,String phoneNumber);

	void Export(String typeOfFile, String firstName, String lastName, String phoneNumber);

	void Sort(String sortType, String fieldToSortBy, String AscOrDesc);
	
	void Load(String format, String fileName);
	
	void Show(String ascOrDesc);
	
//--->Get information about the Data Base.	
	
	boolean isNOTStartOfDB();

	boolean isNOTEndOfDB();
	
	boolean isDBEmpty();

	
 // ----> View Methods (by controller)<------ //	

	void controllerUpdateView(String operation ,String[] myContactsDetails);


	


	
}
