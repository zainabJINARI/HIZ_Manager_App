package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import Models.PartyRoom;
import dao.RoomsDaoImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPartyRController {
	  @FXML
	    private Button idAdd;

	    @FXML
	    private TextField idAddress;

	    @FXML
	    private TextField idBudget;

	    @FXML
	    private Button idCancel;

	    @FXML
	    private TextField idName;
	    
	    private PartyRoom room;
	    @FXML
	    void ActionAddRooms(ActionEvent event) throws IOException, SQLException {

	    	String url="/Interfaces/";
    	    if(event.getSource()==idAdd) {
    	    	Double bdgRoom=0.0; 
    	    	
    	    	try {
    	    		bdgRoom=Double.parseDouble(idBudget.getText());
//     	    	room = new PartyRoom(idName.getText(),idAddress.getText(),Double.parseDouble(idBudget.getText()));
    	    	}catch(java.lang.NumberFormatException e ) {
    	    		Alert alert = new Alert(Alert.AlertType.ERROR);
       	   	    	alert.setTitle("Invalid Input ");
       	   	    	alert.setHeaderText("Invalid input for the field Budget Room");
       	   	    	alert.setContentText(" Make sure you entered a valid number  ");
       	   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
       	   	    	alert.showAndWait();
    	    	}
    	    	if(bdgRoom<=0) {
    	    		Alert alert = new Alert(Alert.AlertType.ERROR);
       	   	    	alert.setTitle("Invalid Input ");
       	   	    	alert.setHeaderText("Invalid input for the field Budget Room");
       	   	    	alert.setContentText(" Make sure you entered a valid number  ");
       	   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
       	   	    	alert.showAndWait();
    	    	}else {
    	    	
    	    	room = new PartyRoom(idName.getText(),idAddress.getText(),bdgRoom);
     	    	 Node node = (Node) event.getSource();
      			 Stage thisStage = (Stage) node.getScene().getWindow();
      			 System.out.println((RoomsDaoImplementation)thisStage.getUserData());
      			 System.out.println(room);
      		     ((RoomsDaoImplementation)thisStage.getUserData()).addRooms(room);
    	    	url+="ManagePartyR.fxml";
    	    	
      	    ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
    	    	}
      	    	 
    	    }else if(event.getSource()==idCancel) {
    	    	
    	    	url+="ManagePartyR.fxml";
    	    	 ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
    	    }	
	    }

}