package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Personne {
    private SimpleIntegerProperty ID= new SimpleIntegerProperty();
    private SimpleStringProperty Email = new SimpleStringProperty() ;
    private SimpleStringProperty  Name= new SimpleStringProperty();
    private SimpleStringProperty  PhoneNum= new SimpleStringProperty();
	public int getID() {
		return ID.get();
	}
	public void setID(int iD) {
		ID.set(iD);
	}
	public SimpleIntegerProperty getIDProperty() {
		return ID;
	}
	public SimpleStringProperty getEmailProperty() {
		return Email;
	}
	public String getEmail() {
		return Email.get();
	}
	public void setEmail(String email) {
		Email.set(email);
	}
	public String getName() {
		return Name.get();
	}
	public Personne() {
		super();
	}
	public Personne(String email, String name, String phoneNum) {
		super();
		Email.set(email); 
		Name.set(name);
		PhoneNum.set(phoneNum);
	}
	public void setName(String name) {
		Name.set(name);
	}
	public SimpleStringProperty getNameProperty() {
		return Name;
	}
	public String getPhoneNum() {
		return PhoneNum.get();
	}
	public void setPhoneNum(String phoneNum) {
		PhoneNum.set(phoneNum);
	}
	public SimpleStringProperty getPhoneNumProperty() {
		return PhoneNum;
	}
    
}
