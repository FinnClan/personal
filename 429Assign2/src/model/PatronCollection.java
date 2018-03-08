package model;

import java.util.Properties;
import java.util.Vector;

import impresario.IView;
import javafx.scene.Scene;
import userinterface.BookCollectionView;
import userinterface.MainStageContainer;
import userinterface.PatronCollectionView;
import userinterface.SearchPatronView;

public class PatronCollection extends EntityBase implements IView {

	// Variables
	private static final String myTableName = "Patron";
	private Vector<Patron> patronList;

	private PatronCollectionView patronCollectionView;
	private SearchPatronView searchPatronView;

	private Librarian myLibrarian;

	public PatronCollection() {

		super(myTableName);
		patronList = new Vector<Patron>();
		persistentState = new Properties();
		myStage = MainStageContainer.getInstance();
	}

	@Override
	public String toString() {
		String retVal = "";
		for (int cnt = 0; cnt < patronList.size(); cnt++) {
			retVal += (patronList.elementAt(cnt)).toString() + " |\n";
		}
		return retVal;
	}

	public void findPatronsOlderThan(String date) {

		String query = "SELECT * FROM Patron WHERE (dateOfBirth <'" + date + "') ORDER BY name";
		System.out.println(query);
		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {
			patronList = new Vector<Patron>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextPatronData = (Properties) allDataRetrieved.elementAt(cnt);

				Patron patron = new Patron(nextPatronData);

				if (patron != null) {
					addPatron(patron);
				}
			}
		} else {
			System.out.println("Error: Could not find patron older than given date: " + date);
		}
	}

	public Vector findPatronsYoungerThan(String date) {

		String query = "SELECT * FROM Patron WHERE (dateOfBirth >'" + date + "') ORDER BY name";
		System.out.println(query);
		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {
			patronList = new Vector<Patron>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextPatronData = (Properties) allDataRetrieved.elementAt(cnt);

				Patron patron = new Patron(nextPatronData);

				if (patron != null) {
					addPatron(patron);
				}
			}
		} else {
			System.out.println("Error: Could not find a patron younger than the given date: " + date);
		}
		return patronList;
	}

	public Vector findPatronsAtZipCode(String zip) {

		String query = "SELECT * FROM " + myTableName + " WHERE zip=" + zip + " ORDER BY name";
		System.out.println(query);
		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {
			patronList = new Vector<Patron>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextPatronData = (Properties) allDataRetrieved.elementAt(cnt);

				Patron patron = new Patron(nextPatronData);

				if (patron != null) {
					addPatron(patron);
				}
			}
		} else {
			System.out.println("Error: Could not find a patron with the given zip code: " + zip);
		}
		
		System.out.println(toString());
		return patronList;
	}

	public Vector findPatronsWithNameLike(String name) {

		String query = "SELECT * FROM Patron WHERE (name like '%" + name + "%') ORDER BY name";
		System.out.println(query);
		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {
			patronList = new Vector<Patron>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextPatronData = (Properties) allDataRetrieved.elementAt(cnt);

				Patron patron = new Patron(nextPatronData);

				if (patron != null) {
					addPatron(patron);
				}
			}
		} else {
			System.out.println("Error: Could not find patron with a similar name to the given name: " + name);
		}
		return patronList;

	}

	private void addPatron(Patron a) {
		// accounts.add(a);
		int index = findIndexToAdd(a);
		patronList.insertElementAt(a, index); // To build up a collection sorted on some key
	}

	private int findIndexToAdd(Patron a) {
		// users.add(u);
		int low = 0;
		int high = patronList.size() - 1;
		int middle;

		while (low <= high) {
			middle = (low + high) / 2;

			Patron midSession = patronList.elementAt(middle);

			int result = Patron.compare(a, midSession);

			if (result == 0) {
				return middle;
			} else if (result < 0) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}

		}
		return low;

	}

	@Override
	public void updateState(String key, Object value) {

		stateChangeRequest(key, value);
	}

	@Override
	public Object getState(String key) {

		if (key.equals("patronId"))
			return patronList;
		else if (key.equals("patronList"))
			return this;
		return null;
	}

	@Override
	public void stateChangeRequest(String key, Object value) {

		myRegistry.updateSubscribers(key, this);

	}

	@Override
	protected void initializeSchema(String tableName) {

		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}

	}

	public void setLibrarian(Librarian lib) {
		myLibrarian = lib;
	}

	public void processBack() {
		myLibrarian.goBackToLibrarianView();

	}

	public void createDataEntryView() {

		searchPatronView = new SearchPatronView(this);
		Scene searchPatronCollectionScene = new Scene(searchPatronView);
		swapToView(searchPatronCollectionScene);

	}
	
	public void createPatronCollectionView() {

		patronCollectionView = new PatronCollectionView(this);
		Scene patronCollectionScene = new Scene(patronCollectionView);
		swapToView(patronCollectionScene);
	}

	public void processData(String zip) {
		findPatronsAtZipCode(zip);
	}


	public Vector<Patron> getPatronList() {
		return this.patronList;
	}
}
