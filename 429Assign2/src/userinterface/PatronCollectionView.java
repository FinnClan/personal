package userinterface;

import java.util.Enumeration;
import java.util.Properties;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Patron;
import model.PatronCollection;

public class PatronCollectionView extends View {

	protected TableView<PatronTableModel> tableOfPatrons;

	private Button cancelButton;
//	private Button submitButton;
	

	private PatronCollection myPatronCollection;

	private MessageView statusLog;

	public PatronCollectionView(IModel model) {

		super(model, "PatronCollectionView");

		myPatronCollection = (PatronCollection) model;

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());

		// container.getChildren().add(createStatusLog(" "));

		getChildren().add(container);
		
		populateFields();

	}

	protected void populateFields() {
		getEntryTableModelValues();

	}

	protected void getEntryTableModelValues() {

		ObservableList<PatronTableModel> tableData = FXCollections.observableArrayList();
		
		try {
			
			Vector entryList = (Vector) myPatronCollection.getPatronList();
			Enumeration entries = entryList.elements();
			
			while (entries.hasMoreElements()) {
				
				Patron nextPatron = (Patron)entries.nextElement();
				Vector<String> view = nextPatron.getEntryListView();
				System.out.print("Patron string:");
				for (int cnt = 0; cnt < view.size(); cnt++)
					System.out.print(" |" + view.get(cnt));
				System.out.println();
				PatronTableModel nextTableRowData = new PatronTableModel(view);
				System.out.println("Brandon was here -- table model created");
				tableData.add(nextTableRowData);
				
			}
			tableOfPatrons.setItems(tableData);
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	// Create the title container
	// -------------------------------------------------------------
	private Node createTitle() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);

		Text titleText = new Text(" Search Results");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		container.getChildren().add(titleText);

		return container;
	}

	// Create the main form contents
	// -------------------------------------------------------------
	private VBox createFormContent() {

		VBox vBox = new VBox(10);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		tableOfPatrons = new TableView<PatronTableModel>();
		tableOfPatrons.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		TableColumn nameColumn = new TableColumn("Name");
		nameColumn.setMinWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<PatronTableModel, String>("name"));

		TableColumn addressColumn = new TableColumn("Address");
		addressColumn.setMinWidth(300);
		addressColumn.setCellValueFactory(new PropertyValueFactory<PatronTableModel, String>("address"));

		TableColumn cityColumn = new TableColumn("City");
		cityColumn.setMinWidth(100);
		cityColumn.setCellValueFactory(new PropertyValueFactory<PatronTableModel, String>("city"));
		
		TableColumn stateColumn = new TableColumn("State");
		stateColumn.setMinWidth(100);
		stateColumn.setCellValueFactory(new PropertyValueFactory<PatronTableModel, String>("stateCode"));

		TableColumn zipColumn = new TableColumn("ZIP");
		zipColumn.setMinWidth(100);
		zipColumn.setCellValueFactory(new PropertyValueFactory<PatronTableModel, String>("zip"));

		TableColumn emailColumn = new TableColumn("Email");
		emailColumn.setMinWidth(100);
		emailColumn.setCellValueFactory(new PropertyValueFactory<PatronTableModel, String>("email"));

		TableColumn birthColumn = new TableColumn("Birthdate");
		birthColumn.setMinWidth(100);
		birthColumn.setCellValueFactory(new PropertyValueFactory<PatronTableModel, String>("dateOfBirth"));
		
		TableColumn statusColumn = new TableColumn("Status");
		statusColumn.setMinWidth(100);
		statusColumn.setCellValueFactory(new PropertyValueFactory<PatronTableModel, String>("status"));
		
		tableOfPatrons.getColumns().addAll(nameColumn, addressColumn, cityColumn, stateColumn, zipColumn, emailColumn, birthColumn, statusColumn);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(500, 300);
		scrollPane.setContent(tableOfPatrons);

		// submitButton = new Button("Submit");
		// submitButton.setOnAction(new EventHandler<ActionEvent>() {
		//
		// @Override
		// public void handle(ActionEvent e) {
		// processAction(e);
		// }
		// });
		//
		cancelButton = new Button("Back");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processBackButton(e);
			}
		});

		HBox btnContainer = new HBox(10);
		btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
		// btnContainer.getChildren().add(submitButton);
		btnContainer.getChildren().add(cancelButton);
		grid.add(btnContainer, 1, 5); // need to adjust accordingly

		vBox.getChildren().add(grid);
		vBox.getChildren().add(scrollPane);
		vBox.getChildren().add(btnContainer);

		return vBox;
	}

	public void processAction(Event event) {

		// System.out.println("PatronCollectionView: processAction");
		//
		// System.out.println();
		// System.out.println(" " + myPatronCollection);
		//
		// String searchTitle = title.getText();
		//
		// myPatronCollection.processNewPatronCollection(searchTitle);

	}

	public void processBackButton(Event event) {
		myPatronCollection.processBack();
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub

	}

}
