package Models;

import javafx.beans.property.SimpleStringProperty;

public class Provider extends Personne{
  
  
	private SimpleStringProperty levelOfProvider = new SimpleStringProperty();

	public SimpleStringProperty getLevelOfProviderProperty() {
		return levelOfProvider;
	}
	public String getLevelOfProvider() {
		return levelOfProvider.get();
	}

	public void setLevelOfProvider(String levelOfProvider) {
		this.levelOfProvider.set(levelOfProvider); 
	}
	public Provider(String name, String email , String PhoneNum,String levelOfProvider) {
		super(email,name,PhoneNum);
		this.levelOfProvider.set(levelOfProvider);;
	}
	public Provider() {
		super();
	}

	
	




   
   
}
