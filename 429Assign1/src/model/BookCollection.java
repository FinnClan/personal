// Specify the package
package model;

import java.util.Properties;
import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.Select;

import database.Persistable;
import exception.InvalidPrimaryKeyException;
import impresario.IView;
import sun.text.resources.cldr.om.FormatData_om;

/**
 * 	Brandon Helstrom
 * 	1.14.2018
 * 	Used style of the AccountCollection class from the ATM System provided from CSC 429
 */

/** The class containing the BookCollection for the 429Assign1 application */
// ======================================================================
public class BookCollection extends EntityBase implements IView {

	// Variables
	private static final String myTableName = "Book";
	private Vector<Book> bookList;
	// private Persistable persistable;
	// bookList = new Vector(); // new Vector<Book>();

	public BookCollection() {

		super(myTableName);
		bookList = new Vector<Book>();
	}

	public Vector findBooksOlderThanDate(String year) {

		// query for this method
		// "SELECT * FROM " + myTableName + " WHERE (pubYear < " + year + " ) ORDER BY
		// title"

		String query = "SELECT * FROM " + myTableName + " WHERE (pubYear < " + year + " ) ORDER BY title)";

		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {
			bookList = new Vector<Book>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextBookData = (Properties) allDataRetrieved.elementAt(cnt);

				Book book = new Book(nextBookData);

				if (book != null) {
					addBook(book);
				}
			}

		}

		else {
			System.out.println("Error here");
			// throw new InvalidPrimaryKeyException("Error here: ...");
			// "No book for customer : " + accountHolderId + ". Name : " +
			// cust.getState("Name"));
		}
		return bookList;

	}

	public Vector findBooksNewerThanDate(String year) {

		// query for this method
		// "SELECT * FROM " + myTableName + " WHERE (pubYear > " + year + " ) ORDER BY
		// title"
		String query = "SELECT * FROM " + myTableName + " WHERE (pubYear > " + year + " ) ORDER BY title)";

		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {
			bookList = new Vector<Book>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextBookData = (Properties) allDataRetrieved.elementAt(cnt);

				Book book = new Book(nextBookData);

				if (book != null) {
					addBook(book);
				}
			}

		}

		else {
			System.out.println("Error here");
			// throw new InvalidPrimaryKeyException("Error here: ...");
			// "No book for customer : " + accountHolderId + ". Name : " +
			// cust.getState("Name"));
		}
		return bookList;

	}

	public Vector findBooksWithTitleLike(String title) {

		// query for this method
		// "SELECT * FROM " + myTableName + " WHERE title > " + year + " ORDER BY
		// title"

		String query = "Select * FROM " + myTableName + " WHERE (title LIKE %" + title + "%) ORDER BY title";

		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {
			bookList = new Vector<Book>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextBookData = (Properties) allDataRetrieved.elementAt(cnt);

				Book book = new Book(nextBookData);

				if (book != null) {
					addBook(book);
				}
			}

		}

		else {
			System.out.println("Error here");
			// throw new InvalidPrimaryKeyException("Error here: ...");
			// "No book for customer : " + accountHolderId + ". Name : " +
			// cust.getState("Name"));
		}
		return bookList;

	}

	public Vector findBooksWithAuthorLike(String author) {

		String query = "Select * FROM " + myTableName + " WHERE (author LIKE %" + author + "%) ORDER BY author";

		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {
			bookList = new Vector<Book>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextBookData = (Properties) allDataRetrieved.elementAt(cnt);

				Book book = new Book(nextBookData);

				if (book != null) {
					addBook(book);
				}
			}

		}

		else {
			System.out.println("Error here");
			// throw new InvalidPrimaryKeyException("Error here: ...");
			// "No book for customer : " + accountHolderId + ". Name : " +
			// cust.getState("Name"));
		}
		return bookList;

	}

	// ----------------------------------------------------------------------------------
	private void addBook(Book a) {
		// accounts.add(a);
		int index = findIndexToAdd(a);
		bookList.insertElementAt(a, index); // To build up a collection sorted on some key
	}

	// ----------------------------------------------------------------------------------
	private int findIndexToAdd(Book a) {
		// users.add(u);
		int low = 0;
		int high = bookList.size() - 1;
		int middle;

		while (low <= high) {
			middle = (low + high) / 2;

			Book midSession = bookList.elementAt(middle);

			int result = Book.compare(a, midSession);

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

	// ----------------------------------------------------------------------
	@Override
	public void updateState(String key, Object value) {

		stateChangeRequest(key, value);
	}

	// ----------------------------------------------------------------------
	@Override
	public Object getState(String key) {

		if (key.equals("bookId"))
			return bookList;
		else if (key.equals("bookList"))
			return this;
		return null;
	}

	// ----------------------------------------------------------------------
	@Override
	public void stateChangeRequest(String key, Object value) {

		myRegistry.updateSubscribers(key, this);
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
