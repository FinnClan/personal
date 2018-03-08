package userinterface;

import impresario.IModel;

public class ViewFactory {

	public static View createView(String viewName, IModel model) {
		if (viewName.equals("LibrarianView") == true) {
			return new LibrarianView(model);
		} else if (viewName.equals("InsertNewBookView") == true) {
			return new InsertNewBookView(model);
		} else if (viewName.equals("InsertNewPatronView") == true) {
			return new InsertNewPatronView(model);
		}

		// else if(viewName.equals("SearchBooksView") == true)
		// {
		// return new SearchBooksView(model);
		// }
		// else if(viewName.equals("SearchPatronsView") == true)
		// {
		// return new SearchPatronsView(model);
		// }

		else
			return null;
	}

}
