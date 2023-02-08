package Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PartyRoom {
      
	private SimpleIntegerProperty ID = new SimpleIntegerProperty();
	private SimpleStringProperty Address= new SimpleStringProperty();
	private SimpleStringProperty name= new SimpleStringProperty();
	private SimpleDoubleProperty budgetRoom= new SimpleDoubleProperty();
	
	

	
	public String getAddress() {
		return Address.get();
	}

	public void setAddress(String adr) {
		Address.set(adr);
	}
	public SimpleStringProperty getAddressProperty() {
		return Address;
	}
	
	
	
	public Double getBudgetRoom() {
		return budgetRoom.get();
	}
	public void setBudgetRoom(Double budget) {
		budgetRoom.set(budget);
	}
	public SimpleDoubleProperty getBudgetRoomProperty() {
		return budgetRoom;
	}
	
	
	
	
	public String getName() {
		return name.get();
	}
	public void setName(String nme) {
		name.set(nme);
	}
	public SimpleStringProperty getNameProperty() {
		return name;
	}
	
	public int getID() {
		return ID.get();
	}
	public void setID(int iD) {
		ID.set(iD);
	}
	public SimpleIntegerProperty getIDProperty() {
		return ID;
	}
	
	
	
	public PartyRoom(String nme,String adr, Double budget) {
		name.set(nme);
		Address.set(adr);
		budgetRoom.set(budget);
		
		
	}
	public PartyRoom() {
		super();
	}
	
	
	
}