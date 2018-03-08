package userinterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

public class BookTableModel {

	// private final SimpleStringProperty bookId;
	private final SimpleStringProperty author;
	private final SimpleStringProperty title;
	private final SimpleStringProperty pubYear;
	private final SimpleStringProperty status;

	public BookTableModel(Vector<String> bookData) {

		// // bookId = new SimpleStringProperty(bookData.elementAt(0));
		author = new SimpleStringProperty(bookData.elementAt(0));
		title = new SimpleStringProperty(bookData.elementAt(1));
		pubYear = new SimpleStringProperty(bookData.elementAt(2));
		status = new SimpleStringProperty(bookData.elementAt(3));
	}

	// public String getBookId() {
	// return bookId.get();
	// }
	//
	// public void setBookId(String id) {
	// bookId.set(id);
	// }

	public String getAuthor() {
		return author.get();
	}

	public void setAuthor(String authorName) {
		author.set(authorName);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String titleName) {
		title.set(titleName);
	}

	public String getPubYear() {
		return pubYear.get();
	}

	public void setPubYear(String publYear) {
		pubYear.set(publYear);
	}

	public String getStatus() {
		return status.get();
	}

	public void setStatus(String stat) {
		status.set(stat);
	}

}
