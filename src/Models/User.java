package Models;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User extends Personne implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8608283269779295105L;
	
	
	private SimpleStringProperty HintPassword  = new SimpleStringProperty() ;
    private SimpleStringProperty Password = new SimpleStringProperty() ;
    private SimpleStringProperty Username = new SimpleStringProperty() ;
    ArrayList<Integer> taskList =new ArrayList<Integer>();
    private SimpleBooleanProperty isOnline = new SimpleBooleanProperty();
    
    //ID
    
   
	public SimpleBooleanProperty getIsOnlineProperty() {
		return isOnline;
	}
	public Boolean getIsOnline() {
		return isOnline.get();
	}
	public void setIsOnline(Boolean isOnline) {
		this.isOnline.set(isOnline);
	}
	public User( String hintPassword, String password,
			String username,String email, String name, String phoneNum) {
		super(email,name,phoneNum);
		HintPassword.set(hintPassword);
		Password.set(password);
	    Username.set(username); 
	    isOnline.set(false);
	
	}
	public User() {
		super();
		isOnline.set(false);
		
	}
	
	

	
	public SimpleStringProperty getHintPasswordProperty() {
		return HintPassword;
	}
	public void setHintPassword(String hintPassword) {
		HintPassword.set(hintPassword) ;
	}
	public String getHintPassword() {
		return HintPassword.get();
	}
	
	
	public String getPassword() {
		return Password.get();
	}
	public void setPassword(String password) {
		Password.set(password); 
	}
	public SimpleStringProperty getPasswordProperty() {
		return Password;
	}
	public String getUsername() {
		return Username.get();
	}
	public void setUsername(String username) {
		Username.set(username);
	}
	public SimpleStringProperty getUsernameProperty() {
		return Username;
	}
    
}
