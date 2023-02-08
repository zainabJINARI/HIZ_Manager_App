package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ProfilController {
	@FXML private Button idHome;
	@FXML private Button idProvider;
	@FXML private Button idParty;
	@FXML private Button idClients;
	@FXML private Button idPersoInfo;
	@FXML private Button idTasks;
	
	 public void ActionProfileController(ActionEvent event) throws IOException {
		 String url="/Interfaces/";
   	    if(event.getSource()==idHome) {
   	    	System.out.println("Home");
   	    	url+="Home.fxml";
   	    }else if(event.getSource()==idProvider) {
   	    	System.out.println("Provider");
   	    	url+="ManageProviders.fxml";
   	    }else if(event.getSource()==idParty) {
   	    	System.out.println("Party");
   	    	url+="ManagePartyR.fxml";
   	    }else if(event.getSource()==idClients) {
   	    	System.out.println("Client");
   	    	url+="Manage Client.fxml";
   	    }else if(event.getSource()==idPersoInfo) {
   	    	System.out.println("Personal info");
     	    Stage thisStage = (Stage)( (Node) event.getSource()).getScene().getWindow();
     	    thisStage.setUserData(thisStage.getUserData());
   	    	url+="PersonnelInformation.fxml";
   	    }else if(event.getSource()==idTasks) {
   	    	System.out.println("Tasks");
   	    	url+="Tasks.fxml";
   	    } 
//    Node node = (Node) event.getSource();
//	Stage thisStage = (Stage)( (Node) event.getSource()).getScene().getWindow();
//	Parent root = FXMLLoader.load(getClass().getResource(url));
//	Scene scene = new Scene(FXMLLoader.load(getClass().getResource(url)));
		((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
   	}

    
	
	
	
}


