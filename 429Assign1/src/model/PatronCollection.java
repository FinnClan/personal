package model;

import java.util.Properties;
import java.util.Vector;

import impresario.IView;

public class PatronCollection extends EntityBase implements IView {

	// Variables
	private static final String myTableName = "Patron";
	private Vector<Patron> patronList;

	public PatronCollection() {

		super(myTableName);
		patronList = new Vector<Patron>();
	}

	// i. findPatronsOlderThan(String date)
	// ii. findPatronsYoungerThan(String date)
	// iii. findPatronsAtZipCode(String zip)
	// iv. findPatronsWithNameLike(String name)

	public void findPatronsOlderThan(String date) {

		String query = "SELECT * FROM Patron WHERE (dateOfBirth <" + date + ") ORDER BY name";

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
		}
	}

	public Vector findPatronsYoungerThan(String date) {

		String query = "SELECT * FROM Patron WHERE (dateOfBirth >" + date + ") ORDER BY name";

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
		}
		return patronList;
	}

	public Vector findPatronsAtZipCode(String zip) {

		String query = "SELECT * FROM Patron WHERE (zip like " + zip + ") ORDER BY zip";

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
		}
		return patronList;
	}

	public Vector findPatronsWithNameLike(String name) {

		String query = "SELECT * FROM Patron WHERE (name like %" + name + "%) ORDER BY name";

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
}
