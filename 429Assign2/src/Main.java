import event.Event;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import model.Librarian;

import userinterface.MainStageContainer;
import userinterface.WindowPosition;

/**
 * Brandon Helstrom
 * CSC 429 Assignment 2
 * Partner - James Finn
 * 
 */

/** The class containing the main program for the ATM application */
// ==============================================================
public class Main extends Application {

	private Librarian librarian;

	// Main frame of the application
	private Stage mainStage;

	@Override
	public void start(Stage primaryStage) {

		System.out.println("Library Application Version 1.0");
		System.out.println("Brandon Helstrom and James Finn");

		// Create the top-level container (main frame) and add contents to it.
		MainStageContainer.setStage(primaryStage, "Library Application Version 1.0");
		mainStage = MainStageContainer.getInstance();

		// Finish setting up the stage (ENABLE THE GUI TO BE CLOSED USING THE TOP RIGHT
		// 'X' IN THE WINDOW), and show it.
		mainStage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
			@Override
			public void handle(javafx.stage.WindowEvent event) {
				System.out.println("End of program");
				System.exit(0);
			}
		});

		try {
			librarian = new Librarian();
		} catch (Exception exc) {
			System.err.println("Could not create Librarian");
			new Event(Event.getLeafLevelClassName(this), "Library.<init>", "Unable to create Librarian object", Event.ERROR);
			exc.printStackTrace();
		}

		WindowPosition.placeCenter(mainStage);

		mainStage.show();

	}

	public static void main(String[] args) {

		launch(args);

	}

}
