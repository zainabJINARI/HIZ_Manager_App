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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyProviderController implements Initializable{

    @FXML
    private TextField idName;

    @FXML
    private TextField idEmail;

    @FXML
    private TextField idPhoneNum;

    @FXML
    private Button idModify;

    @FXML
    private Button idCancel;

    @FXML
    private ComboBox<String> idLevel;
    private int counter=0;
    private Provider provider;

    @FXML
    void ModifyProviderAction(ActionEvent event) throws IOException, SQLException {
    	 String url="/Interfaces/";
    	    if(event.getSource()==idModify) {
    	    	provider.setEmail(idEmail.getText());
    	    	provider.setName(idName.getText());
    	    	provider.setPhoneNum(idPhoneNum.getText());
    	    	if(idLevel.getSelectionModel().getSelectedItem()!=null) {
    	    		provider.setLevelOfProvider(idLevel.getSelectionModel().getSelectedItem());
    	    	}else {
    	    		System.out.println("no modification in combo box");
    	    	}
    	    	Node node = (Node) event.getSource();
   			 Stage thisStage = (Stage) node.getScene().getWindow();
   			((ProviderDaoImplementation)thisStage.getUserData()).updateProvider(provider);
   			System.out.println("provider modified ");
    	    	
      		     
    	    	url+="ManageProviders.fxml";
      	    ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
      	    
      	    	 
    	    }else if(event.getSource()==idCancel) {
    	    	
    	    	url+="ManageProviders.fxml";
    	    	 ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
    	    }	

    }
    @FXML
    void ShowData(MouseEvent event) {
    	if(counter==0) {
    	 Node node = (Node) event.getSource();
		 Stage thisStage = (Stage) node.getScene().getWindow();
		 provider =((ProviderDaoImplementation)thisStage.getUserData()).getPrv();
		 idEmail.setText(provider.getEmail());
	     idName.setText(provider.getName());
	     idPhoneNum.setText(provider.getPhoneNum());
	     System.out.println(provider.getID());
    	}
    	counter++;
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		idLevel.getItems().addAll("High", "Medium", "Low");
		
	}

}