package model;

import java.util.Properties;
import java.util.Vector;

import impresario.IView;
import javafx.scene.Scene;
import userinterface.BookCollectionView;
import userinterface.MainStageContainer;
import userinterface.SearchBookView;

public class BookCollection extends EntityBase implements IView {

	// Variables
	private static final String myTableName = "Book";
	private Vector<Book> bookList;

	private BookCollectionView bookCollectionView;
	private SearchBookView searchBookView;

	private Librarian myLibrarian;

	public BookCollection() {

		super(myTableName);
		bookList = new Vector<Book>();
		persistentState = new Properties();
		myStage = MainStageContainer.getInstance();

	}

	@Override
	public String toString() {
		String retVal = "";
		for (int cnt = 0; cnt < bookList.size(); cnt++) {
			retVal += (bookList.elementAt(cnt)).toString() + " |\n";
		}
		return retVal;
	}

	public Vector findBooksOlderThanDate(String year) {

		String query = "SELECT * FROM " + myTableName + " WHERE (pubYear <= '" + year + "') ORDER BY title";
		System.out.println(query);

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
			System.out.println("Error: Could not find book older than given year.");
		}
		return bookList;

	}

	public Vector findBooksNewerThanDate(String year) {

		String query = "SELECT * FROM " + myTableName + " WHERE (pubYear > '" + year + "' ) ORDER BY title";
		System.out.println(query);
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
			System.out.println("Error: Could not find books newer than the given year.");
		}
		return bookList;

	}

	public Vector findBooksWithTitleLike(String title) {

		String query = "Select * FROM " + myTableName + " WHERE (title LIKE '%" + title + "%') ORDER BY author";
		System.out.println(query);
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
			System.out.println("Error: Could not find book with a title like the searched title.");
		}
		
		System.out.println(toString()); // DEBUG
		return bookList;

	}

	public Vector findBooksWithAuthorLike(String author) {

		String query = "Select * FROM " + myTableName + " WHERE (author LIKE '%" + author + "%') ORDER BY author";
		System.out.println(query);
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
			System.out.println("Error: Could not find a book with an author matching the search.");
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

	public void setLibrarian(Librarian lib) {
		myLibrarian = lib;
	}
	
	public void createDataEntryView() {

		searchBookView = new SearchBookView(this);
		Scene searchBookCollectionScene = new Scene(searchBookView);
		swapToView(searchBookCollectionScene);

	}

	public void createBookCollectionView() {

		bookCollectionView = new BookCollectionView(this);
		Scene bookCollectionScene = new Scene(bookCollectionView);
		swapToView(bookCollectionScene);
	}

	public void processData(String title) {
		findBooksWithTitleLike(title);
	}

	public void processBack() {
		myLibrarian.goBackToLibrarianView();
	}

	public void processNewBookCollection(String titleName) {

		findBooksWithTitleLike(titleName);

	}

	public Vector<Book> getBookList() {
		return this.bookList;
	}
	

}
