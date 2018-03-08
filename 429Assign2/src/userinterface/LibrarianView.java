package userinterface;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;

import impresario.IModel;

import model.Librarian;

public class LibrarianView extends View {

	// Local Variables
	// GUI components
	private Button insertNewBookButton;
	private Button insertNewPatronButton;
	private Button searchBooksButton;
	private Button searchPatronsButton;

	private Button doneButton;

	private Librarian myLibrarian;

	public LibrarianView(IModel librarian) {
		super(librarian, "LibrarianView");

		myLibrarian = (Librarian) librarian;

		// create a container for showing the contents
		VBox container = new VBox(10);

		container.setPadding(new Insets(15, 5, 5, 5));

		// create a Node (Text) for showing the title
		container.getChildren().add(createTitle());

		// create a Node (GridPane) for showing data entry fields
		container.getChildren().add(createFormContents());

		getChildren().add(container);

		// STEP 0: Be sure you tell your model what keys you are interested in
		myModel.subscribe("LoginError", this);

	}

	// Create the label (Text) for the title of the screen
	// -------------------------------------------------------------
	private Node createTitle() {

		Text titleText = new Text("       LIBRARY SYSTEM          ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);

		return titleText;
	}

	// Create the navigation buttons
	// -------------------------------------------------------------
	private VBox createFormContents() {

		VBox container = new VBox(15);

		// create the buttons, listen for events, add them to the container
		HBox dCont = new HBox(10);
		dCont.setAlignment(Pos.CENTER);
		insertNewBookButton = new Button("Insert New Book");
		insertNewBookButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		insertNewBookButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// System.out.println("Clicked on InsertNewBook button"); // DEBUG
				myLibrarian.createNewBook();
			}
		});
		dCont.getChildren().add(insertNewBookButton);

		container.getChildren().add(dCont);

		HBox wCont = new HBox(10);
		wCont.setAlignment(Pos.CENTER);
		insertNewPatronButton = new Button("Insert New Patron");
		insertNewPatronButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		insertNewPatronButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				myLibrarian.createNewPatron();
			}

		});
		wCont.getChildren().add(insertNewPatronButton);

		container.getChildren().add(wCont);

		HBox lCont = new HBox(10);
		lCont.setAlignment(Pos.CENTER);
		searchPatronsButton = new Button("Search Books");
		searchPatronsButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		searchPatronsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// System.out.println("Clicked on SearchBooks button");
				myLibrarian.createNewSearchBookView();

			}
		});
		lCont.getChildren().add(searchPatronsButton);

		container.getChildren().add(lCont);

		HBox gCont = new HBox(10);
		gCont.setAlignment(Pos.CENTER);
		searchPatronsButton = new Button("Search Patrons");
		searchPatronsButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		searchPatronsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				System.out.println("Clicked on SearchPatrons button");
				myLibrarian.createNewSearchPatronView();
			}
		});
		gCont.getChildren().add(searchPatronsButton);

		container.getChildren().add(gCont);

		HBox doneCont = new HBox(10);
		doneCont.setAlignment(Pos.CENTER);
		doneButton = new Button("Done");
		doneButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		doneButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				myModel.stateChangeRequest("Done", null);
			}
		});
		doneCont.getChildren().add(doneButton);

		container.getChildren().add(doneCont);

		return container;
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub

	}

}
