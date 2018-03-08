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

/** The class containing the Book for the 429Assign1 application */
// ======================================================================
public class Book extends EntityBase implements IView {

	// Variables
	private static final String myTableName = "Book";
	protected Properties dependencies;
	private String updateStatusMessage = "";

	// One argument constructor using the primary key of the book as a String
	// ----------------------------------------------------------------------
	public Book(String bookId) throws InvalidPrimaryKeyException {

		super(myTableName);

		setDependencies();
		// Select everything from the Book table where the id's match
		String query = "SELECT * FROM " + myTableName + " WHERE (bookId  = " + bookId + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		// You must get at least one Book
		if (allDataRetrieved != null) {

			int size = allDataRetrieved.size();

			// There should be EXACTLY one Book, more than one is an error
			if (size != 1) {

				throw new InvalidPrimaryKeyException("Multiple books matching id: " + bookId + " found.");
			}

			else {

				// Copy all the retrieved data into persistent state
				Properties retrievedBookdata = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedBookdata.propertyNames();
				while (allKeys.hasMoreElements() == true) {

					String nextKey = (String) allKeys.nextElement();
					String nextValue = retrievedBookdata.getProperty(nextKey);

					if (nextValue != null) {

						persistentState.setProperty(nextKey, nextValue);
					}
				}
			}
		}

		// If no books are found for the user, throw this exception
		else {
			throw new InvalidPrimaryKeyException("No books matching id " + bookId + " found.");
		}

	}

	// One argument constructor using the properties of the book
	// Can also be used to create a NEW Book (if the system it is a part of
	// allows for a new book to be set up)
	// ----------------------------------------------------------------------
	public Book(Properties props) {

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
			if (persistentState.getProperty("bookId") != null) {
				Properties whereClause = new Properties();
				whereClause.setProperty("bookId", persistentState.getProperty("bookId"));
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Book data for book id : " + persistentState.getProperty("bookId")
						+ " updated successfully in database!";
			} else {
				Integer bookId = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("bookId", "" + bookId.intValue());
				updateStatusMessage = "Book data for new book : " + persistentState.getProperty("bookId")
						+ "installed successfully in database!";
			}
		} catch (SQLException ex) {
			updateStatusMessage = "Error in installing book data in database!";
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
	public static int compare(Book a, Book b) {
		String aId = (String) a.getState("bookId");
		String bId = (String) b.getState("bookId");

		return aId.compareTo(bId);
	}

	// ----------------------------------------------------------------------
	public Vector<String> getEntryListView() {
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("bookId"));
		v.addElement(persistentState.getProperty("author"));
		v.addElement(persistentState.getProperty("title"));
		v.addElement(persistentState.getProperty("pubYear"));
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

	public static int compare(Patron a, Patron midSession) {
		// TODO Auto-generated method stub
		return 0;
	}
}
// ======================================================================