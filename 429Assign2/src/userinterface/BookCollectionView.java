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
import model.Book;
import model.BookCollection;

public class BookCollectionView extends View {

	protected TableView<BookTableModel> tableOfBooks;

	private Button cancelButton;
//	private Button submitButton;

	private BookCollection myBookCollection;

	private MessageView statusLog;

	public BookCollectionView(IModel model) {

		super(model, "BookCollectionView");

		myBookCollection = (BookCollection) model;

		System.out.println("entered BookCollectionView");

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

		ObservableList<BookTableModel> tableData = FXCollections.observableArrayList();
		
		
		try {
			
			Vector entryList = (Vector) myBookCollection.getBookList();
			Enumeration entries = entryList.elements();
			
			while (entries.hasMoreElements()) {
				
				Book nextBook = (Book)entries.nextElement();
				Vector<String> view = nextBook.getEntryListView();
				
				BookTableModel nextTableRowData = new BookTableModel(view);
				tableData.add(nextTableRowData);
				
			}
			tableOfBooks.setItems(tableData);
			
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

		tableOfBooks = new TableView<BookTableModel>();
		tableOfBooks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		TableColumn authorColumn = new TableColumn("Author");
		authorColumn.setMinWidth(150);
		authorColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("author"));

		TableColumn titleColumn = new TableColumn("Title");
		titleColumn.setMinWidth(300);
		titleColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("title"));

		TableColumn pubYearColumn = new TableColumn("Year");
		pubYearColumn.setMinWidth(100);
		pubYearColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("pubYear"));

		TableColumn statusColumn = new TableColumn("Status");
		statusColumn.setMinWidth(100);
		statusColumn.setCellValueFactory(new PropertyValueFactory<BookTableModel, String>("status"));

		tableOfBooks.getColumns().addAll(authorColumn, titleColumn, pubYearColumn, statusColumn);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(500, 300);
		scrollPane.setContent(tableOfBooks);

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

		// System.out.println("BookCollectionView: processAction");
		//
		// System.out.println();
		// System.out.println(" " + myBookCollection);
		//
		// String searchTitle = title.getText();
		//
		// myBookCollection.processNewBookCollection(searchTitle);

	}

	public void processBackButton(Event event) {
		myBookCollection.processBack();
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub

	}

}
