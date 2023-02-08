package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Models.Provider;
import dao.ProviderDaoImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProviderController implements Initializable {

    @FXML
    private TextField idName;

    @FXML
    private TextField idEmail;

    @FXML
    private TextField idPhoneNum;

    @FXML
    private Button idAdd;

    @FXML
    private Button idCancel;
    

    @FXML
    private ComboBox<String> idLevel = new  ComboBox<String>() ;
    private Provider provider;

    @FXML
    void AddProviderAction(ActionEvent event) throws SQLException, IOException {
    	String url="/Interfaces/";
		if(event.getSource()==idAdd) {
   	    	System.out.println("Provider added");
   	    	provider = new Provider(idName.getText(),idEmail.getText(),idPhoneNum.getText(),idLevel.getSelectionModel().getSelectedItem());
	    	 Node node = (Node) event.getSource();
 			 Stage thisStage = (Stage) node.getScene().getWindow();
 			((ProviderDaoImplementation)thisStage.getUserData()).addProvider(provider);	    	
 			url+="ManageProviders.fxml";
 	    ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
   	    	
   	    }else if(event.getSource()==idCancel) {
   	        System.out.println("Cancel Addition");
   	    	url+="ManageProviders.fxml";
   	       ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
   	    }

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		idLevel.getItems().addAll("High", "Medium", "Low");
		
	}

}