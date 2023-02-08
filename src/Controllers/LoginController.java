package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import Models.User;
import dao.UserDaoImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class LoginController {
      @FXML private TextField textFieldUsername;
      @FXML private PasswordField textFieldPassword;
      @FXML private Label labelHint;     
      private static UserDaoImplementation user ;
      private  static User user1;
     
      public static UserDaoImplementation getUser() {
		return user;
	}
      public static void setUser(UserDaoImplementation use) {
  		user=use;
  	}
	
	public static User getUser1() {
		return user1;
	}

	public static void setUser1(User user1) {
		LoginController.user1 = user1;
	}

	public void ActionLogin(ActionEvent event) throws SQLException, IOException {
    	  System.out.println("btn clicked");
    	  String username = textFieldUsername.getText();
    	  String password = textFieldPassword.getText();
    	  System.out.println(username);
    	  user = new UserDaoImplementation();
       	  user1 = user.getUser(username);
    	  if(user1 != null){
    		  if(user1.getPassword().compareTo(password)==0) {
	    		System.out.println("connexion avec succée");
	    		System.out.println(user1.getIsOnline());
	    		user1.setIsOnline(true);
	    		user.isOnline(user1);
	    		 Node node = (Node) event.getSource();
				 Stage thisStage = (Stage) node.getScene().getWindow();
	 			 thisStage.close();
	  			 thisStage.setUserData(user1);
	  			 Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/Home.fxml"));
  			     Scene scene = new Scene(root);
  		      	 thisStage.setScene(scene);
  		     	 thisStage.show();	
    		  }else {
    			  System.out.println("incorrect psw");
    			  labelHint.setText("Incorrect Password\nHint password is : "+user1.getHintPassword());
    			 
    		  }
	    		
	    	}else {
	    		System.out.println("identifiant incorrect");
	    		labelHint.setText("Username doesn't exist");
	    	}
    	  
      }
      public void ActionCreateAccount(ActionEvent event) throws IOException {
    	  System.out.println("Create ACcount");
    	  Node node = (Node) event.getSource();
 		  Stage thisStage = (Stage) node.getScene().getWindow();
 		  Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/Registration.fxml"));
 		  Scene scene = new Scene(root);
// 			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 		  thisStage.setScene(scene);
      }
      
}
