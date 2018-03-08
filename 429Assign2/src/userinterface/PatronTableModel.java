package userinterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

public class PatronTableModel {

	private final SimpleStringProperty name;
	private final SimpleStringProperty address;
	private final SimpleStringProperty city;
	private final SimpleStringProperty stateCode;
	private final SimpleStringProperty zip;
	private final SimpleStringProperty email;
	private final SimpleStringProperty dateOfBirth;
	private final SimpleStringProperty status;

	public PatronTableModel(Vector<String> patronData) {

		name = new SimpleStringProperty(patronData.elementAt(1));
		address = new SimpleStringProperty(patronData.elementAt(2));
		city = new SimpleStringProperty(patronData.elementAt(3));
		stateCode = new SimpleStringProperty(patronData.elementAt(4));
		zip = new SimpleStringProperty(patronData.elementAt(5));
		email = new SimpleStringProperty(patronData.elementAt(6));
		dateOfBirth = new SimpleStringProperty(patronData.elementAt(7));
		status = new SimpleStringProperty(patronData.elementAt(8));
	}

	public String getName() {
		return name.get();
	}

	public void setName(String fullName) {
		name.set(fullName);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String fullAddress) {
		address.set(fullAddress);
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String fullCity) {
		city.set(fullCity);
	}

	public String getStateCode() {
		return stateCode.get();
	}

	public void setStateCode(String fullStateCode) {
		stateCode.set(fullStateCode);
	}

	public String getZip() {
		return zip.get();
	}

	public void setZip(String fullZip) {
		zip.set(fullZip);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String fullEmail) {
		email.set(fullEmail);
	}

	public String getDateOfBirth() {
		return dateOfBirth.get();
	}

	public void setDateOfBirth(String fullDateOfBirth) {
		dateOfBirth.set(fullDateOfBirth);
	} 
	
	public String getStatus() {
		return status.get();
	}

	public void setStatus(String fullStatus) {
		status.set(fullStatus);
	} 
}
