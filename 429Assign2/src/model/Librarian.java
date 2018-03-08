package model;

import java.util.Hashtable;
import java.util.Properties;

import event.Event;
import impresario.IModel;
import impresario.IView;
import impresario.ModelRegistry;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;

public class Librarian implements IView, IModel {

	// Local Variables
	private Properties dependencies;
	private ModelRegistry myRegistry;

	// GUI Components
	private Hashtable<String, Scene> myViews;
	private Stage myStage;

	public Librarian() {

		myStage = MainStageContainer.getInstance();
		myViews = new Hashtable<String, Scene>();

		myRegistry = new ModelRegistry("Librarian");
		if (myRegistry == null) {
			new Event(Event.getLeafLevelClassName(this), "Librarian", "Could not instantiate Registry", Event.ERROR);
		}

		setDependencies();

		createAndShowLibrarianView();

	}

	private void setDependencies() {

		dependencies = new Properties();

		dependencies.setProperty("InsertNewBook", "InputNewBookError");
		dependencies.setProperty("InsertNewPatron", "InputNewPatronError");
		dependencies.setProperty("SearchBooks", "SearchBooksError");
		dependencies.setProperty("SearchPatrons", "SearchPatronsError");
		// dependencies.setProperty("Done", "DoneError");

		myRegistry.setDependencies(dependencies);
	}

	public void createNewBook() {
		// Create an empty book object
		// System.out.println("Entered createNewBook method in Librarian");
		Book book = new Book();
		book.setLibrarian(this);
		book.createDataEntryView();
	}

	public void createNewPatron() {

		Patron patron = new Patron();
		patron.setLibrarian(this);
		patron.createDataEntryView();

	}

	public void createNewSearchBookView() {

		BookCollection bookCollection = new BookCollection();
		bookCollection.setLibrarian(this);
		bookCollection.createDataEntryView();

	}

	public void createNewSearchPatronView() {

		PatronCollection patronCollection = new PatronCollection();
		patronCollection.setLibrarian(this);
		patronCollection.createDataEntryView();

	}
	
	public void createBookCollectionView() {
		
		BookCollection bookCollection = new BookCollection();
		bookCollection.setLibrarian(this);
		bookCollection.createBookCollectionView();
	}

	// public void createNewBookCollection() {
	//
	// BookCollection bookCollection = new BookCollection();
	// bookCollection.setLibrarian(this);
	// bookCollection.createDataEntryView();
	//
	// }

	// public void createNewPatronCollection() {
	//
	// PatronCollection patronCollection = new PatronCollection();
	// patronCollection.setLibrarian(this);
	// patronCollection.createDataEntryView();
	//
	// }

	// -------------------------------------------------------------------------
	public void goBackToLibrarianView() {
		createAndShowLibrarianView();
	}

	// -------------------------------------------------------------------------
	private void createAndShowLibrarianView() {

		Scene currentScene = (Scene) myViews.get("LibrarianView");

		if (currentScene == null) {
			// create our initial view
			View newView = ViewFactory.createView("LibrarianView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("LibrarianView", currentScene);
		}

		swapToView(currentScene);

	}

	@Override
	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		if (key.equals("Done") == true) {

			System.out.println("End of program");
			System.exit(0);

		} else if (key.equals("InsertNewBook")) {
			Scene newScene = (Scene) myViews.get("InsertNewBook");
			swapToView(newScene);
		}
		// else if ((key.equals("InsertNewBook") == true) ||
		// (key.equals("InsertNewPatron") == true)
		// || (key.equals("SearchBooks") == true) || (key.equals("SearchPatrons") ==
		// true)) {
		//
		// String transType = key;
		//
		// }

		myRegistry.updateSubscribers(key, this);

	}

	public void swapToView(Scene newScene) {

		if (newScene == null) {
			System.out.println("Librarian.swapToView(): Missing view for display");
			new Event(Event.getLeafLevelClassName(this), "swapToView", "Missing view for display ", Event.ERROR);
			return;
		}

		myStage.setScene(newScene);
		myStage.sizeToScene();

		// Place in center
		WindowPosition.placeCenter(myStage);

	}

	@Override
	public Object getState(String key) {
		// DEBUG
		System.out.println("Librarian | getState: " + key);
		return null;
	}

	@Override
	/** Register objects to receive state updates. */
	// ----------------------------------------------------------
	public void subscribe(String key, IView subscriber) {
		// DEBUG: System.out.println("Cager[" + myTableName + "].subscribe");
		// forward to our registry
		myRegistry.subscribe(key, subscriber);
	}

	@Override
	/** Unregister previously registered objects. */
	// ----------------------------------------------------------
	public void unSubscribe(String key, IView subscriber) {
		// DEBUG: System.out.println("Cager.unSubscribe");
		// forward to our registry
		myRegistry.unSubscribe(key, subscriber);
	}

	@Override
	/** Called via the IView relationship */
	// ----------------------------------------------------------
	public void updateState(String key, Object value) {
		// DEBUG System.out.println("Teller.updateState: key: " + key);
		stateChangeRequest(key, value);
	}

}
