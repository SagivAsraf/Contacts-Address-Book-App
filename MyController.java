package MyAddressBook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;


public class MyController implements ISagivListener, IFinalsInterface {

	private ArrayList<IContactManagerView> views = new ArrayList<IContactManagerView>();
	private IContactManagerModel myModel;

	private boolean firstTimeGetIn;
	private Timer myTimerShow;

	public void setModel(ContactsManagerModel cm) {
		this.myModel = cm;
		registerIfPossible(this.myModel);

	}

	public void addView(IContactManagerView view) {
		this.views.add(view);
		registerIfPossible(view);
		
	}

	/**
	 * This method register the controller listeners to the view/model! to
	 * communicate
	 **/

	public void registerIfPossible(Object o) {
		if (o instanceof IRegistable) {
			IRegistable registrable = (IRegistable) o;
			registrable.registerListener(this);
		}
	}

	// --- **---Transfer info from the view to the model---** //

	@Override
	public void getNextContact() {
		this.myModel.getNext();
	}

	@Override
	public void getPreviousContact() {
		this.myModel.getPrev();
	}

	@Override
	public void getLastContact() {
		this.myModel.getLastContact();
	}

	@Override
	public void getFirstContact() {
		this.myModel.getFirstContact();
	}

	@Override
	public void createContact(String firstName, String lastName, String phoneNumber) {
		this.myModel.createNewContact(firstName, lastName, phoneNumber);
	}

	@Override
	public void updateContact(String firstName, String lastName, String phoneNumber) {
		this.myModel.updateMyContact(firstName, lastName, phoneNumber);
	}

	@Override
	public void Export(String typeOfFile, String firstName, String lastName, String phoneNumber) {
		this.myModel.exportMyContact(typeOfFile, firstName, lastName, phoneNumber);
	}

	@Override
	public void Sort(String sortType, String fieldToSortBy, String AscOrDesc) {
		this.myModel.sortMyContacts(sortType, fieldToSortBy, AscOrDesc);
	}

	@Override
	public void Load(String format, String fileName) {
		this.myModel.loadMyContact(format, fileName);
	}

	// Ask the model if we don't on the START of our DataBase (our case = file)

	@Override
	public boolean isNOTStartOfDB() {
		return this.myModel.isNOTStartOfDB();
	}

	// Ask the model if we don't on the END of our DataBase (our case = file)

	@Override
	public boolean isNOTEndOfDB() {
		return this.myModel.isNOTEndOfDB();
	}

	// Ask the model if the DB is empty (our case db = file)

	@Override
	public boolean isDBEmpty() {
		return this.myModel.isDBEmpty();
	}

	// ** -TIMER METOD - Show contacts - ** //

	@Override
	public void Show(String ascOrDesc) {

		this.firstTimeGetIn = true; // for next ride
		this.myTimerShow = new Timer(TIMERSPEED, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (ascOrDesc.equals(ASC)) {
					doAscShow();
				} else {
					doDescShow();
				}
			}

			private void doAscShow() {
				if (firstTimeGetIn) {
					getFirstContact();
					firstTimeGetIn = false;
				} else {
					if (isNOTEndOfDB()) {
						getNextContact();
					} else {
						myTimerShow.stop();
					}
				}
			}

			private void doDescShow() {
				if (firstTimeGetIn) {
					getLastContact();
					firstTimeGetIn = false;
				} else {
					if (isNOTStartOfDB()) {
						getPreviousContact();
					} else {
						myTimerShow.stop();
					}
				}
			}
		});

		startTimer();
	}

	/**
	 * This method start the time just if we have a full data base (not empty)
	 **/
	private void startTimer() {
		if (isDBEmpty()) {
			return;
		} else {
			this.myTimerShow.start();
		}
	}

	// **---Transfer info from the model to the view ---** //

	@Override
	public void controllerUpdateView(String operation, String[] myContactsDetails) {

		for (IContactManagerView view : views) {
			switch (operation) {
			case FIRST_COMMAND:
				view.updateViewForFirst(myContactsDetails);
				break;
			case LAST_COMMAND:
				view.updateViewForLast(myContactsDetails);
				break;
			case NEXT_COMMAND:
				view.updateViewForNext(myContactsDetails);
				break;
			case PREVIOUS_COMMAND:
				view.updateViewForPrevious(myContactsDetails);
				break;
			case CREATE_COMMAND:
				view.updateViewForCreate(myContactsDetails);
				break;
			case UPDATE_COMMAND:
				view.updateViewForUpdate(myContactsDetails);
				break;
			case LOAD_COMMAND:
				view.updateViewForLoad(myContactsDetails);
				break;

			}

		}
	}

}
