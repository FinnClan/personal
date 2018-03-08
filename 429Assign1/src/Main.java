import java.util.Properties;
import java.util.Scanner;

import model.Book;
import model.BookCollection;
import model.Patron;
import model.PatronCollection;

/** The class containing the main program for the ATM application */
// ==============================================================
public class Main {

	private static Scanner scan;
	private static int choice;

	public static void main(String[] args) {

		boolean flag = false;
		choice = 0;
		scan = new Scanner(System.in);

		System.out.println("Beginning of Program");
		String menu = "######################################################\n"
				+ "#1. Insert new book                                  #\n"
				+ "#2. Insert new patron                                #\n"
				+ "#3. Provide title, lookup books                      #\n"
				+ "#4. Provide year, lookup books                       #\n"
				+ "#5. Provide date, lookup patrons younger than date   #\n"
				+ "#6. Provide zip, lookup patrons for zipcode          #\n"
				+ "#7. Exit                                             #\n"
				+ "######################################################\n";

		while (!flag) {

			System.out.println("There are 7 choices in the menu below");
			System.out.println(menu);
			System.out.println("Please enter a choice: ");
			choice = scan.nextInt();
			scan.nextLine();
			
			switch (choice) {
			case 1:
				insertNewBook();
				break;

			case 2:
				insertNewPatron();
				break;

			case 3:
				findBookWithTitle();
				break;
			case 4:
				findBookWithYear();
				break;
			case 5:
				findPatronGivenDate();
				break;

			case 6:
				findPatronGivenZip();
				break;

			case 7:
				exit();

			default:
				System.out.println("Error please enter an integer between 1 and 7");
				break;
			}
		}

		System.out.println("End of Program");

	}

	public static void insertNewBook() {

		Properties p = new Properties();

		System.out.println("Please enter the author: ");
		String author = scan.nextLine();
		System.out.println("Author: " + author);
		p.setProperty("author", author);
		System.out.print("Please enter the title: ");
		String title = scan.nextLine();
		p.setProperty("title", title);
		System.out.print("Please enter the publication year: ");
		String pubYear = scan.nextLine();
		p.setProperty("pubYear", pubYear);
		System.out.print("Please enter the status: ");
		String status = scan.nextLine();
		p.setProperty("status", status);

		Book newBook = new Book(p);
		newBook.update();
		System.out.println("end of insert new book method");
	}

	public static void insertNewPatron() {

		Properties p = new Properties();
		System.out.print("Please enter the name: ");
		String name = scan.nextLine();
		p.setProperty("name", name);
		System.out.print("Please enter the address: ");
		String address = scan.nextLine();
		p.setProperty("address", address);
		System.out.print("Please enter the city: ");
		String city = scan.nextLine();
		p.setProperty("city", city);
		System.out.print("Please enter the State code: ");
		String stateCode = scan.nextLine();
		p.setProperty("stateCode", stateCode);
		System.out.print("Please enter the zip: ");
		String zip = scan.nextLine();
		p.setProperty("zip", zip);
		System.out.print("Please enter the email address: ");
		String email = scan.nextLine();
		p.setProperty("email", email);
		System.out.print("Please enter the date of birth: ");
		String dateOfBirth = scan.nextLine();
		p.setProperty("dateOfBirth", dateOfBirth);
		System.out.print("Please enter the status: ");
		String status = scan.nextLine();
		p.setProperty("status", status);
		System.out.println("end of insert new patron");

		Patron newPatron = new Patron(p);
		newPatron.update();
		System.out.println("end of insert new patron method");
	}

	public static void findBookWithTitle() {

		System.out.println("Please enter the title: ");
		String title = scan.nextLine();
		BookCollection bookCollection = new BookCollection();
		bookCollection.findBooksWithTitleLike(title);
		System.out.println(bookCollection.toString());
		System.out.println("end of find book with title method");
	}

	public static void findBookWithYear() {

		System.out.println("Please enter the year: ");
		String year = scan.nextLine();
		BookCollection bookCollection = new BookCollection();
		bookCollection.findBooksOlderThanDate(year);
		System.out.println(bookCollection.toString());
		System.out.println("end of find book with year method");

	}

	public static void findPatronGivenDate() {

		System.out.println("Please enter the date: ");
		String date = scan.nextLine();
		PatronCollection patronCollection = new PatronCollection();
		patronCollection.findPatronsYoungerThan(date);
		System.out.println(patronCollection.toString());
		System.out.println("end of find patron given date method");

	}

	public static void findPatronGivenZip() {
		System.out.println("Please enter a zip code: ");
		String zip = scan.nextLine();
		PatronCollection patronCollection = new PatronCollection();
		patronCollection.findPatronsAtZipCode(zip);
		System.out.println(patronCollection.toString());
		System.out.println("end of find patron given zip method");

	}

	public static void exit() {
		System.out.println("End of Program");
		System.exit(0);
	}
}
