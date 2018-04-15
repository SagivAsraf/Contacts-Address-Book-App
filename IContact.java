package MyAddressBook;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public interface IContact {

	/**
	 * an object extending this method needs to add itself to the file using the
	 * FixedLengthStringIO class
	 * 
	 */
	void writeObject(RandomAccessFile randomAccessFile) throws IOException;

	/**
	 * an object extending this method needs to export itself to a file using a
	 * format.
	 * 
	 */
	void export(String format, File file) throws IOException;

	/**
	 * an object extending this method needs to 'stringify' all of the data it
	 * wants to show on a frame and return a String array of the String data
	 * about itself
	 * 
	 */
	String[] getUiData();

	/**
	 * an object extending this method needs to return the object size on file
	 * objectSize will always equal ----> = numOfFields*fieldSize*numOfBytes -
	 * numOfFields is handled by each child class
	 * 
	 */
	int getObjectSize();

}
