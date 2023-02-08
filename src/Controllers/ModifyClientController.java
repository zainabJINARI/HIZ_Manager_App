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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

	public class ModifyClientController {
		 private  Client client;

	    @FXML
	    private Button idCancel;

	    @FXML
	    private Button idModify;

	    @FXML
	    private TextField textFieldCIN;

	    @FXML
	    private TextField textFieldMail;

	    @FXML
	    private TextField textFieldName;

	    @FXML
	    private TextField textFieldPhoneNum;
	    private int counter=0;

	    @FXML
	    void ActionModifyClient(ActionEvent event) throws IOException, SQLException {
	    	 String url="/Interfaces/";
	    	    if(event.getSource()==idModify) {
	    	    	client.setCIN(textFieldCIN.getText());
	    	    	client.setEmail(textFieldMail.getText());
	    	    	client.setName(textFieldName.getText());
	    	    	client.setPhoneNum(textFieldPhoneNum.getText());
	    	    	Node node = (Node) event.getSource();
	   			 Stage thisStage = (Stage) node.getScene().getWindow();
	   			((ClientDaoImplementation)thisStage.getUserData()).updateClient(client);
	   			System.out.println(client.getCIN());
	    	    	
	      		     
	    	    	url+="Manage Client.fxml";
	      	    ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
	      	    
	      	    	 
	    	    }else if(event.getSource()==idCancel) {
	    	    	
	    	    	url+="Manage Client.fxml";
	    	    	 ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
	    	    }	

	    }
	    @FXML
	    void ShowData(MouseEvent event) {
	    	if(counter==0) {
	    	 Node node = (Node) event.getSource();
			 Stage thisStage = (Stage) node.getScene().getWindow();
 		     client =((ClientDaoImplementation)thisStage.getUserData()).getClt();
		     textFieldMail.setText(client.getEmail());
		   textFieldCIN.setText(client.getCIN());
		 textFieldName.setText(client.getName());
		textFieldPhoneNum.setText(client.getPhoneNum());
		System.out.println(client.getID());
	    	}
	    	counter++;
	    }

		

	}