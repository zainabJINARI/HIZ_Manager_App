package Controllers;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class MainController {
	@FXML private Button idProvider;
	@FXML private Button idParty;
	@FXML private Button idSkip;
	
	
	
	
	
	
	
	 public void ActionBuildWork(ActionEvent event) throws IOException {
		 String url="/Interfaces/";
	   	    if(event.getSource()==idSkip) {
	   	    	System.out.println("Home");
	   	    	url+="Home.fxml";
	   	    }else if(event.getSource()==idProvider) {
	   	    	System.out.println("Provider");
	   	    	url+="ManageProviders.fxml";
	   	    }else if(event.getSource()==idParty) {
	   	    	System.out.println("Party");
	   	    	url+="ManagePartyR.fxml";
	   	    }
	   	    
	   	 ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
     }

	 
}
