package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import Models.Client;
import dao.ClientDaoImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddClientController  {
	
	@FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldMail;

    @FXML
    private TextField textFieldCIN;

    @FXML
    private TextField textFieldPhoneNum;

    @FXML
    private Button idAdd;

    @FXML
    private Button idCancel;
    
	private Client client;
    @FXML
    void ActionAddClient(ActionEvent event) throws IOException, SQLException {
    	 String url="/Interfaces/";
    	    if(event.getSource()==idAdd) {
     	    	client = new Client(textFieldCIN.getText(),textFieldMail.getText(),textFieldName.getText(),textFieldPhoneNum.getText());
     	    	 Node node = (Node) event.getSource();
      			 Stage thisStage = (Stage) node.getScene().getWindow();
      		     ((ClientDaoImplementation)thisStage.getUserData()).addClient(client);
    	    	url+="Manage Client.fxml";
      	    ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
      	    
      	    	 
    	    }else if(event.getSource()==idCancel) {
    	    	
    	    	url+="Manage Client.fxml";
    	    	 ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
    	    }	
    	   
    }	
    
}
