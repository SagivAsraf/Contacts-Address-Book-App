package MyAddressBook;

public interface IContactManagerView {

	//I decided to do this at separate methods, cause I want that my code will be 
	//as much as possible flexible to changes. so even that update view for next, is 
	//equal to update view for previous, I did it separate, cause maybe in the future,
	//the purpose of this method will change. and the I just have to change one method (that changed!)
	
	void updateViewForNext(String [] myContactsDetails);
	
	void updateViewForPrevious(String [] myContactsDetails);

	void updateViewForFirst(String [] myContactsDetails);
	
	void updateViewForLast(String [] myContactsDetails);

	void updateViewForCreate(String [] myContactsDetails);
	
	void updateViewForUpdate(String [] myContactsDetails);

	void updateViewForLoad(String [] myContactsDetails);


}
