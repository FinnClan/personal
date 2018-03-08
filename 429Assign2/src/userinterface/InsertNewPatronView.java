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
import model.Patron;

public class InsertNewPatronView extends View {

	// GUI components
	// private TextField amount; // Make more private text fields
	private ComboBox<String> statusList;

	private TextField name;
	private TextField address;
	private TextField city;
	private TextField stateCode;
	private TextField zip;
	private TextField email;
	private TextField dateOfBirth;

	private Button submitButton;
	private Button cancelButton;

	private Patron myPatron;

	private MessageView statusLog;

	public InsertNewPatronView(IModel model) {
		super(model, "InsertNewPatronView");

		myPatron = (Patron) model;

		// TODO Auto-generated constructor stub
		System.out.println("entered PatronView");

		// create a container for showing the contents
		VBox container = new VBox(10);

		container.setPadding(new Insets(15, 5, 5, 5));

		// create a Node (Text) for showing the address
		container.getChildren().add(createTitle());

		// create a Node (GridPane) for showing data entry fields
		container.getChildren().add(createFormContents());

		container.getChildren().add(createStatusLog("                        "));

		getChildren().add(container);

		populateFields();

	}

	// Create the label (Text) for the address of the screen
	// -------------------------------------------------------------
	private Node createTitle() {

		Text addressText = new Text("       Insert Patron's Information          ");
		addressText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		addressText.setTextAlignment(TextAlignment.CENTER);
		addressText.setFill(Color.DARKGREEN);

		return addressText;
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
		// name Label
		Label nameLabel = new Label("Name:");
		grid.add(nameLabel, 0, 0);

		// name Text field
		name = new TextField();
		name.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(name, 1, 0);
		// ---------------------------------------------------------------------------
		// address Label
		Label addressLabel = new Label("Address:");
		grid.add(addressLabel, 0, 1);

		// address Text field
		address = new TextField();
		address.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(address, 1, 1);
		// ---------------------------------------------------------------------------
		// city Label
		Label cityLabel = new Label("City:");
		grid.add(cityLabel, 0, 2);

		// city address
		city = new TextField();
		address.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(city, 1, 2);

		// ---------------------------------------------------------------------------
		// address Label
		Label stateCodeLabel = new Label("State Code:");
		grid.add(stateCodeLabel, 0, 3);

		// address Text field
		stateCode = new TextField();
		stateCode.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(stateCode, 1, 3);
		// ---------------------------------------------------------------------------
		// zip Label
		Label zipLabel = new Label("ZIP:");
		grid.add(zipLabel, 0, 4);

		// address Text field
		zip = new TextField();
		zip.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(zip, 1, 4);
		// ---------------------------------------------------------------------------
		// email Label
		Label emailLabel = new Label("Email Address:");
		grid.add(emailLabel, 0, 5);

		// address Text field
		email = new TextField();
		email.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(email, 1, 5);
		// ---------------------------------------------------------------------------
		
		// dateOfBirth Label
		Label birthLabel = new Label("Date of Birth:");
		grid.add(birthLabel, 0, 6);

		// address Text field
		dateOfBirth = new TextField();
		dateOfBirth.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(dateOfBirth, 1, 6);
		// ---------------------------------------------------------------------------

		// Status Label
		Label statusLabel = new Label("Status:");
		grid.add(statusLabel, 0, 8);

		// Status ComboBox
		statusList = new ComboBox<String>();
		statusList.setMinSize(100, 20);
		grid.add(statusList, 1, 8);

		submitButton = new Button("Submit");
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		// ---------------------------------------------------------------------------
		
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
		grid.add(btnContainer, 1, 11);

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

		statusList.setValue(statusList.getItems().get(0));

	}

	// This method processes events generated from our GUI components.
	// Make the ActionListeners delegate to this method
	// -------------------------------------------------------------
	public void processAction(Event evt) {
		// DEBUG: System.out.println("TellerView.actionPerformed()");

		// clearErrorMessage();
		//
		// String useridEntered = nameName.getText();
		//
		// if ((useridEntered == null) || (useridEntered.length() == 0)) {
		// displayErrorMessage("Please enter a user id!");
		// userid.requestFocus();
		// } else {
		// String passwordEntered = password.getText();
		// processUserIDAndPassword(useridEntered, passwordEntered);
		// }
		Properties props = new Properties();

		props.setProperty("name", name.getText());
		props.setProperty("address", address.getText());
		props.setProperty("city", city.getText());
		props.setProperty("stateCode", stateCode.getText());
		props.setProperty("address", address.getText());
		props.setProperty("zip", zip.getText());
		props.setProperty("email", email.getText());
		props.setProperty("dateOfBirth", dateOfBirth.getText());
		props.setProperty("status", statusList.getValue());
		// need to fill rest of stuff

		/*
		 * DEBUG Enumeration keys = props.propertyNames(); while
		 * (keys.hasMoreElements()) { String key = (String)keys.nextElement();
		 * System.out.println(key + " = " + props.getProperty(key)); }
		 */
		myPatron.processNewPatron(props);
	}

	// --------------------------------------------------------------------
	public void processBackButton(Event evt) {
		myPatron.processBack();
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub

	}

	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		statusLog.displayMessage(message);

	}

}
