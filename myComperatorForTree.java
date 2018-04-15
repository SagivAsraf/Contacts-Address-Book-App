package MyAddressBook;

import java.util.Comparator;


public class myComperatorForTree implements Comparator<Integer>, IFinalsInterface {

	private String ascOrDesc;

	public myComperatorForTree(String ascOrDesc){
		this.ascOrDesc = ascOrDesc;
	}
	@Override
	public int compare(Integer o1, Integer o2) {
		if(ascOrDesc.equals(ASC)){
			if (o1 - o2 >= 0)
				return 1;
			else {
				return -1;
			}
		}
		else{
			if (o1 - o2 >= 0)
				return -1;
			else {
				return 1;
			}
		}
	}

	
	
}
