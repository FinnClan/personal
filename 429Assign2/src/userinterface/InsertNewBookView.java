package userinterface;

import java.util.Enumeration;
import java.util.Properties;

import javax.swing.border.TitledBorder;

import impresario.IModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Book;

public class InsertNewBookView extends View {

	// GUI components
	// private TextField amount; // Make more private text fields
	private ComboBox<String> statusList;

	private TextField author;
	private TextField title;
	private TextField pubYear;

	private Button submitButton;
	private Button cancelButton;

	private Book myBook;

	private MessageView statusLog;

	public InsertNewBookView(IModel model) {
		super(model, "InsertNewBookView");

		myBook = (Book) model;

		// TODO Auto-generated constructor stub
		System.out.println("entered BookView");

		// create a container for showing the contents
		VBox container = new VBox(10);

		container.setPadding(new Insets(15, 5, 5, 5));

		// create a Node (Text) for showing the title
		container.getChildren().add(createTitle());

		// create a Node (GridPane) for showing data entry fields
		container.getChildren().add(createFormContents());

		container.getChildren().add(createStatusLog("                        "));

		getChildren().add(container);

		populateFields();

	}

	// Create the label (Text) for the title of the screen
	// -------------------------------------------------------------
	private Node createTitle() {

		Text titleText = new Text("       Insert a new Book          ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);

		return titleText;
	}

	// Create the main form contents
	// -------------------------------------------------------------
	private GridPane createFormContents() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// data entry fields
		// Author Label
		Label authorLabel = new Label("Author:");
		grid.add(authorLabel, 0, 0);

		// Author Text field
		author = new TextField();
		author.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(author, 1, 0);

		// Title Label
		Label titleLabel = new Label("Title:");
		grid.add(titleLabel, 0, 1);

		// Title Text field
		title = new TextField();
		title.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(title, 1, 1);

		// PubYear Label
		Label pubYearLabel = new Label("Publication Year:");
		grid.add(pubYearLabel, 0, 2);

		// PubYear Title
		pubYear = new TextField();
		title.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(pubYear, 1, 2);

		// Status Label
		Label statusLabel = new Label("Status:");
		grid.add(statusLabel, 0, 3);

		// Status ComboBox
		statusList = new ComboBox<String>();
		statusList.setMinSize(100, 20);
		grid.add(statusList, 1, 3);

		submitButton = new Button("Submit");
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});

		cancelButton = new Button("Back");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processBackButton(e);
			}
		});

		HBox btnContainer = new HBox(10);
		btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
		btnContainer.getChildren().add(submitButton);
		btnContainer.getChildren().add(cancelButton);
		grid.add(btnContainer, 1, 5);

		return grid;
	}

	// -----------------------------------------------------------------
	// Create the status log field
	// -------------------------------------------------------------
	private MessageView createStatusLog(String initialMessage) {

		statusLog = new MessageView(initialMessage);

		return statusLog;
	}

	// -----------------------------------------------------------------
	public void populateFields() {
		statusList.getItems().add(0, "Active");
		statusList.getItems().add(1, "Inactive");
//		statusList.getItems().add

		statusList.setValue(statusList.getItems().get(0));

	}

	// This method processes events generated from our GUI components.
	// Make the ActionListeners delegate to this method
	// -------------------------------------------------------------
	public void processAction(Event evt) {

		Properties props = new Properties();

		props.setProperty("author", author.getText());
		props.setProperty("title", title.getText());
		props.setProperty("pubYear", pubYear.getText());
		props.setProperty("status", statusList.getValue());

		/*
		 * DEBUG Enumeration keys = props.propertyNames(); while
		 * (keys.hasMoreElements()) { String key = (String)keys.nextElement();
		 * System.out.println(key + " = " + props.getProperty(key)); }
		 */
		myBook.processNewBook(props);
	}

	// --------------------------------------------------------------------
	public void processBackButton(Event evt) {
		myBook.processBack();
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub

	}

	public void displayMessage(String message) {

		statusLog.displayMessage(message);

	}

}
