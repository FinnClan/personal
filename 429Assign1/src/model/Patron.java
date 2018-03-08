// Specify the package
package model;

// System imports
import java.util.Properties;
import java.util.Vector;
import java.sql.SQLException;
import java.util.Enumeration;

// Project imports
import exception.InvalidPrimaryKeyException;
import impresario.IView;

/**
 * 	Brandon Helstrom
 * 	1.14.2018
 * 	Used style of the Account class from the ATM System provided from CSC 429
 */

/** The class containing the Patron for the 429Assign1 application */
// ======================================================================
public class Patron extends EntityBase implements IView {

	// Variables
	private static final String myTableName = "Patron";
	protected Properties dependencies;
	private String updateStatusMessage = "";

	// One argument constructor using the primary key of the patron as a String
	// ----------------------------------------------------------------------
	public Patron(String patronId) throws InvalidPrimaryKeyException {

		super(myTableName);

		setDependencies();
		// Select everything from the Patron table where the id's match
		String query = "SELECT * FROM " + myTableName + " WHERE (patronId  = " + patronId + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		// You must get at least one Patron
		if (allDataRetrieved != null) {

			int size = allDataRetrieved.size();

			// There should be EXACTLY one Patron, more than one is an error
			if (size != 1) {

				throw new InvalidPrimaryKeyException("Multiple patrons matching id: " + patronId + " found.");
			}

			else {

				// Copy all the retrieved data into persistent state
				Properties retrievedPatrondata = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedPatrondata.propertyNames();
				while (allKeys.hasMoreElements() == true) {

					String nextKey = (String) allKeys.nextElement();
					String nextValue = retrievedPatrondata.getProperty(nextKey);

					if (nextValue != null) {

						persistentState.setProperty(nextKey, nextValue);
					}
				}
			}
		}

		// If no patrons are found for the user, throw this exception
		else {
			throw new InvalidPrimaryKeyException("No patrons matching id " + patronId + " found.");
		}
	}

	// One argument constructor using the properties of the patron
	// Can also be used to create a NEW Patron (if the system it is a part of
	// allows for a new patron to be set up)
	// ----------------------------------------------------------------------
	public Patron(Properties props) {

		super(myTableName);
		setDependencies();
		persistentState = new Properties();
		Enumeration allKeys = props.propertyNames();
		while (allKeys.hasMoreElements() == true) {

			String nextKey = (String) allKeys.nextElement();
			String nextValue = props.getProperty(nextKey);

			if (nextValue != null) {
				persistentState.setProperty(nextKey, nextValue);
			}
		}
	}

	// ----------------------------------------------------------------------
	public void update() {
		updateStateInDatabase();
	}

	// ----------------------------------------------------------------------
	private void updateStateInDatabase() {
		try {
			if (persistentState.getProperty("patronId") != null) {
				Properties whereClause = new Properties();
				whereClause.setProperty("patronId", persistentState.getProperty("patronId"));
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Patron data for patron id : " + persistentState.getProperty("patronId")
						+ " updated successfully in database!";
			} else {
				Integer patronId = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("patronId", "" + patronId.intValue());
				updateStatusMessage = "Patron data for new patron : " + persistentState.getProperty("patronId")
						+ "installed successfully in database!";
			}
		} catch (SQLException ex) {
			updateStatusMessage = "Error in installing patron data in database!";
		}
		// DEBUG System.out.println("updateStateInDatabase " + updateStatusMessage);

	}

	// ----------------------------------------------------------------------
	private void setDependencies() {

		dependencies = new Properties();
		myRegistry.setDependencies(dependencies);
	}

	// ----------------------------------------------------------------------
	@Override
	public void updateState(String key, Object value) {

		stateChangeRequest(key, value);
	}

	// ----------------------------------------------------------------------
	@Override
	public Object getState(String key) {

		if (key.equals("UpdateStatusMessage") == true)
			return updateStatusMessage;

		return persistentState.getProperty(key);
	}

	// ----------------------------------------------------------------------
	@Override
	public void stateChangeRequest(String key, Object value) {

		myRegistry.updateSubscribers(key, this);
	}
	// ----------------------------------------------------------------------
	public static int compare(Patron a, Patron b) {
		String aId = (String) a.getState("patronId");
		String bId = (String) b.getState("patronId");

		return aId.compareTo(bId);
	}

	// ----------------------------------------------------------------------
	public Vector<String> getEntryListView() {
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("patronId"));
		v.addElement(persistentState.getProperty("name"));
		v.addElement(persistentState.getProperty("address"));
		v.addElement(persistentState.getProperty("city"));
		v.addElement(persistentState.getProperty("stateCode"));
		v.addElement(persistentState.getProperty("zip"));
		v.addElement(persistentState.getProperty("email"));
		v.addElement(persistentState.getProperty("dateOfBirth"));
		v.addElement(persistentState.getProperty("status"));

		return v;
	}
	// ----------------------------------------------------------------------
	@Override
	protected void initializeSchema(String tableName) {

		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}
	}
}
// ======================================================================