//A+ date:
package MyAddressBook;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application implements IFinalsInterface {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		ContactsManagerModel cmm = null;

		try {
			cmm = new ContactsManagerModel(ADDRESS_BOOK_NAME);
			ContactsManagerFrameView cmfv = new ContactsManagerFrameView();
			ContactManagerViewFX cmvfx = new ContactManagerViewFX();
			MyController mc = new MyController();
			mc.setModel(cmm);
			mc.addView(cmvfx);
			mc.addView(cmfv);
			cmfv.init();
			cmvfx.init(primaryStage);
			

		} catch (FileNotFoundException e) {
			System.out.println(FILE_NOT_FOUND);
		} catch (IOException e) {
			System.out.println(PROBLEMFILE);
		}

	}

}
