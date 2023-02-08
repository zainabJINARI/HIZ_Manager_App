package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import Models.Provider;
import dao.ProviderDaoImplementation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProviderController implements Initializable{
	@FXML private Button idAdd;
	@FXML private Button idModify;
	@FXML private Button idSupprim;
	@FXML private Button idHome;
	@FXML private Button idClients;
	@FXML private Button idProfil;
	@FXML private Button idPartyR;
	@FXML
    private TableView<Provider> tableProvider;

    @FXML
    private TableColumn<Provider, Integer> idProv;

    @FXML
    private TableColumn<Provider, String> nameProv;

    @FXML
    private TableColumn<Provider, String> emailProv;

    @FXML
    private TableColumn<Provider, String> PhoneNumProv;

	@FXML
    private TableColumn<Provider, String> levelProv;
	private ProviderDaoImplementation ProviderDao = new ProviderDaoImplementation();
	
	 public void ActionProviderController(ActionEvent event) throws IOException, SQLException {
		 String url="/Interfaces/";
   	    if(event.getSource()==idHome) {
   	    	System.out.println("Home");
   	    	url+="Home.fxml";
   	    }else if(event.getSource()==idClients) {
   	    	System.out.println("Client");
   	    	url+="Manage Client.fxml";
   	    }else if(event.getSource()==idAdd) {
   	    	System.out.println("Adding providdr");
   	    	url+="AddProviders.fxml";
   	    	Node node = (Node) event.getSource();
			 Stage thisStage = (Stage) node.getScene().getWindow();
			 thisStage.close();
 			 Parent root = FXMLLoader.load(getClass().getResource(url));
 			 thisStage.setUserData(ProviderDao);
 			 
 				Scene scene = new Scene(root);
 				thisStage.setScene(scene);
 				thisStage.show();
   	    }
    	else if(event.getSource()==idModify) {
    		System.out.println("Modify smth");
    		if(tableProvider.getSelectionModel().getSelectedItem()==null) {
   	   	    	Alert alert = new Alert(Alert.AlertType.WARNING);
   	   	    	alert.setTitle("Selet Item");
   	   	    	alert.setHeaderText("Please select the item you want to modify ");
   	   	    	alert.setContentText("You can not proceed without selecting an item");
   	   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
   	   	    	alert.showAndWait();
   	   	      }
   	   	      else {
    		
    		
	    	System.out.println("Modify");
   	    	url+="UpdateProviders.fxml";
   	    	ProviderDao.setPrv(tableProvider.getSelectionModel().getSelectedItem());
   	    	System.out.println(ProviderDao.getPrv());
   	    	Node node = (Node) event.getSource();
			 Stage thisStage = (Stage) node.getScene().getWindow();
			 thisStage.close();
 			 Parent root = FXMLLoader.load(getClass().getResource(url));
 			 thisStage.setUserData(ProviderDao);
 				Scene scene = new Scene(root);
 				thisStage.setScene(scene);
 				thisStage.show();
   	   	      }
	    }
    	else if(event.getSource()==idSupprim) {
    
    		if(tableProvider.getSelectionModel().getSelectedItem()==null) {
   	   	    	Alert alert = new Alert(Alert.AlertType.WARNING);
   	   	    	alert.setTitle("Selet Item");
   	   	    	alert.setHeaderText("Please select the item you want to delete");
   	   	    	alert.setContentText("You can not proceed without selecting an item");
   	   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
   	   	    	alert.showAndWait();
   	   	      }
   	   	      else {
   	   	    	  		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			   	    	alert.setTitle("Deleltion Confirmation");
			   	    	alert.setHeaderText("You are trying to delete this item ");
			   	    	alert.setContentText("Are you sure you want to procced");
			   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
			   	    	ButtonType buttonTypeProceed = new ButtonType("Proceed");
			   	    	ButtonType buttonTypeCancel = new ButtonType("Cancel");
			   	    	alert.getButtonTypes().setAll(buttonTypeProceed, buttonTypeCancel);
			
			   	    	Optional<ButtonType> result = alert.showAndWait();
			
			   	    	if (result.get() == buttonTypeProceed){
			   	    		Boolean check = true; 
			   	   	    	 System.out.println(tableProvider.getSelectionModel().getSelectedItem().getID());
			   	   	    	 try {
			   	   	         	ProviderDao.deleteProvider(tableProvider.getSelectionModel().getSelectedItem());
			   	   	    		
			   	   	    	 }catch(java.sql.SQLIntegrityConstraintViolationException e) {
			   	   	    		check=false; 
			   	   	    	 Alert ERRORDeletion = new Alert(AlertType.ERROR);
							 ERRORDeletion.setTitle("Item deletion");
							 ERRORDeletion.setHeaderText("You cannot delete this Item");
							 ERRORDeletion.setContentText("This Item is Already Assigned to an event  \n created  to procced delelte the event first");
							 ERRORDeletion.getDialogPane().getStylesheets().add("/application/Alert.css");
							 ERRORDeletion.show();
			   	   	    		 
							 
							 
			   	   	    	}
			   	   	    	 
			   	   	   if(check) {
			   	   		tableProvider.getItems().setAll(ProviderDao.getAllProviders());
							 System.out.println("Item deleted seccesfully ");
							 Alert seccesfulDeletion = new Alert(AlertType.INFORMATION);
							 seccesfulDeletion.setTitle("Item deletion");
							 seccesfulDeletion.setHeaderText("Item Deleltion");
							 seccesfulDeletion.setContentText("Provider is Delelted seccesfully");
							 seccesfulDeletion.getDialogPane().getStylesheets().add("/application/Alert.css");
							 seccesfulDeletion.show();

			             	   Timeline timeline = new Timeline(new KeyFrame(
			   	               Duration.seconds(1.5),
			   	               ae -> seccesfulDeletion.close()));
			   	               timeline.play();
			   	    			
			   	    		}
			   	   	   
			   	    	} else {
 		
			   	    	}
	           }
    	}
      	else if(event.getSource()==idProfil) {
	    	System.out.println("Profil");
	    	url+="ManageProfil.fxml";
	    }else if(event.getSource()==idPartyR) {
	    	System.out.println("Party room");
	    	url+="ManagePartyR.fxml";
	    }
   	    if(event.getSource()!=idModify && event.getSource()!=idSupprim && event.getSource()!=idAdd ) {
		((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
   	    }
   	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idProv.setCellValueFactory(new PropertyValueFactory<Provider,Integer>("ID"));
		nameProv.setCellValueFactory(new PropertyValueFactory<Provider,String>("Name"));
		emailProv.setCellValueFactory(new PropertyValueFactory<Provider,String>("Email"));
		PhoneNumProv.setCellValueFactory(new PropertyValueFactory<Provider,String>("PhoneNum"));
		levelProv.setCellValueFactory(new PropertyValueFactory<Provider,String>("levelOfProvider"));
        try {
			tableProvider.getItems().setAll(ProviderDao.getAllProviders());
		} catch (SQLException e) {
			e.getCause();
			e.printStackTrace();
		}
		
	}

    
}
