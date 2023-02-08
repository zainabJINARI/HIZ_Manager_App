package Models;




import javafx.beans.property.SimpleStringProperty;

public class Client extends Personne {
   
	private SimpleStringProperty CIN= new SimpleStringProperty();
   

	public Client() {
		super();
	}

	public String getCIN() {
		return CIN.get();
	}

	public void setCIN(String cIN) {
		CIN.set(cIN);
	}
	public SimpleStringProperty getCINProperty() {
		return CIN;
	}

	public Client(String cIN, String email, String name, String phoneNum) {
		super(email,name,phoneNum);
		CIN.set(cIN);
	}
    
}
