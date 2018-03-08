package userinterface;

import java.util.Vector;

import impresario.IModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.PatronCollection;
import model.Librarian;

public class SearchPatronView extends View {

	protected TableView<PatronTableModel> tableOfPatrons;

	private Button cancelButton;
	private Button submitButton;

	private TextField zip;

	private PatronCollection myPatronCollection;

	private MessageView statusLog;

	public SearchPatronView(IModel model) {

		super(model, "SearchPatronView");

		myPatronCollection = (PatronCollection) model;

		System.out.println("entered PatronCollectionView");

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());

		// container.getChildren().add(createStatusLog(" "));

		getChildren().add(container);

	}

	// Create the title container
	// -------------------------------------------------------------
	private Node createTitle() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);

		Text titleText = new Text(" Enter a ZIP Code");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		container.getChildren().add(titleText);

		return container;
	}

	// Create the main form contents
	// -------------------------------------------------------------
	private GridPane createFormContent() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// data entry fields
		// Author Label
		Label zipLabel = new Label("ZIP:");
		grid.add(zipLabel, 0, 0);

		// Author Text field
		zip = new TextField();
		zip.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		grid.add(zip, 1, 0);
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

	public void processAction(Event event) {

		myPatronCollection.processData(zip.getText()); 
		myPatronCollection.createPatronCollectionView();

	}

	public void processBackButton(Event event) {
		myPatronCollection.processBack();
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub

	}

}
