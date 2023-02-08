package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import dao.ClientDaoImplementation;
import dao.ProviderDaoImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

	public class DeleteClientController {

	    @FXML
	    private Button idCancel;

	    @FXML
	    private Button idDelete;

	    @FXML
	    void ActionDeleteController(ActionEvent event) throws SQLException, IOException {
	    	 String url="/Interfaces/";
	    	    if(event.getSource()==idDelete) {
	    	    	Node node = (Node) event.getSource();
		   			 Stage thisStage = (Stage) node.getScene().getWindow();
		   			if((thisStage.getUserData().getClass().toString()).equals("class dao.ClientDaoImplementation")) {
		   				((ClientDaoImplementation)thisStage.getUserData()).deleteClient((((ClientDaoImplementation)thisStage.getUserData()).getClt()));
		   				url+="Manage Client.fxml";
		   			}else if((thisStage.getUserData().getClass().toString()).equals("class dao.ProviderDaoImplementation")){
		   				((ProviderDaoImplementation)thisStage.getUserData()).deleteProvider((((ProviderDaoImplementation)thisStage.getUserData()).getPrv()));
		   				url+="ManageProviders.fxml";
		   			}
	    	    }
	    	    else if(event.getSource()==idCancel) {
	    	    	Node node = (Node) event.getSource();
		   			 Stage thisStage = (Stage) node.getScene().getWindow();
		   			if((thisStage.getUserData().getClass().toString()).equals("class dao.ClientDaoImplementation")) {
		   				
		   				url+="Manage Client.fxml";
		   			}else if((thisStage.getUserData().getClass().toString()).equals("class dao.ProviderDaoImplementation")){
		   			
		   				url+="ManageProviders.fxml";
		   			}
		   			 
	    	    }
	    	    ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));

	    }

	}

   

