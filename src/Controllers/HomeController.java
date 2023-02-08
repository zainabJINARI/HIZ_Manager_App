package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class HomeController {
	@FXML private Button idProvider;
	@FXML private Button idParty;
	@FXML private Button idClients;
	@FXML private Button idProfil;
	@FXML private Button idLogOut;
    @FXML private Button IdHelp;
	public void ActionHomeController(ActionEvent event) throws IOException, SQLException {
//		event.getSource()
		String url="/Interfaces/";
		if(event.getSource()==idProfil) {
   	    	System.out.println("Profil");
   	    	Node node = (Node) event.getSource();
			 Stage thisStage = (Stage) node.getScene().getWindow();
			 thisStage.setUserData(thisStage.getUserData());
   	    	url+="ManageProfil.fxml";
   	    }else if(event.getSource()==idProvider) {
   	    	System.out.println("Provider");
   	    	url+="ManageProviders.fxml";
   	    }else if(event.getSource()==idParty) {
   	    	System.out.println("Party");
   	    	url+="ManagePartyR.fxml";
   	    }else if(event.getSource()==idClients) {
   	    	System.out.println("Client");
   	    	url+="Manage Client.fxml";
   	    }else if(event.getSource()==idLogOut) {
   	    	
   	    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
   	    	alert.setTitle("Login out Dialog");
   	    	alert.setHeaderText("You are trying to log out from application ");
   	    	alert.setContentText("Are you sure you want to procced");
   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
   	    	ButtonType buttonTypeProceed = new ButtonType("Proceed");
   	    	ButtonType buttonTypeCancel = new ButtonType("Cancel");
   	    	alert.getButtonTypes().setAll(buttonTypeProceed, buttonTypeCancel);

   	    	Optional<ButtonType> result = alert.showAndWait();

   	    	if (result.get() == buttonTypeProceed){
   	    		url+="Login.fxml";
   	    		System.out.println("yes");
    			 (LoginController.getUser1()).setIsOnline(false);
   	    		(LoginController.getUser()).isOnline(LoginController.getUser1());
   	    		((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
   	    		
   	    	} else {
   	    		
   	    	}
   	    }else if(event.getSource()==IdHelp) {
   	    	url+="Help.fxml";
   	    }
		if(event.getSource()!=idLogOut) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
		}
	}
		
		


}