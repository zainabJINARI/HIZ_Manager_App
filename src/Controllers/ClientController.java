package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import Models.Client;
import dao.ClientDaoImplementation;
import dao.EventDaoImplementation;
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


public class ClientController implements Initializable{
	@FXML private Button idHome;
	@FXML private Button idProvider;
	@FXML private Button idParty;
	@FXML private Button idProfil;
	@FXML private Button idAdd;
	@FXML private Button idModify;
	@FXML private Button idSupprim;
	@FXML private Button idEvent;
    @FXML
    private TableView<Client> tableClient;

    @FXML
    private TableColumn<Client, Integer> idClient;

    public ClientDaoImplementation getCilentDao() {
		return ClientDao;
	}
	

	@FXML
    private TableColumn<Client, String> idName;

    @FXML
    private TableColumn<Client, String> idMail;

    @FXML
    private TableColumn<Client, String> idPhoneNum;

    @FXML
    private TableColumn<Client, String> idCin;
  
    private  ClientDaoImplementation ClientDao =new ClientDaoImplementation();
    public TableView<Client> getTableClient() {
		return tableClient;
	}
	public void setTableClient(TableView<Client> tableClient) {
		this.tableClient = tableClient;
	}

	public void ActionClientController(ActionEvent event) throws IOException, SQLException {
//		event.getSource()
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
   	    }else if(event.getSource()==idProfil) {
   	    	System.out.println("Client");
   	    	url+="ManageProfil.fxml";
   	    	
 
	    }else if(event.getSource()==idAdd) {
   	    	 System.out.println("Adding clients");
   	    	 url+="AddClient.fxml";
	 			 Node node = (Node) event.getSource();
				 Stage thisStage = (Stage) node.getScene().getWindow();
	 			 thisStage.close();
	  			 Parent root = FXMLLoader.load(getClass().getResource(url));
	  			 thisStage.setUserData(ClientDao);
  			 Scene scene = new Scene(root);
  			 thisStage.setScene(scene);
  			 thisStage.show();
 			
	    }else if(event.getSource()==idEvent) {
	    	if(tableClient.getSelectionModel().getSelectedItem()==null) {
   	   	    	Alert alert = new Alert(Alert.AlertType.WARNING);
   	   	    	alert.setTitle("Selet Item");
   	   	    	alert.setHeaderText("Please select the item  ");
   	   	    	alert.setContentText(" You cannot access Event area of undefined \n client ");
   	   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
   	   	    	alert.showAndWait();
   	   	      }
   	   	      else {
   	    	System.out.println("EVENT AREA");
   	    	url+="CreateEventArea.fxml";
   	    	EventDaoImplementation.setCtl(tableClient.getSelectionModel().getSelectedItem());
   	   	      }
 
	    }else if(event.getSource()==idSupprim) {
	    	
	    	if(tableClient.getSelectionModel().getSelectedItem()==null) {
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
			   	    	alert.setContentText("If you procced All informations related to the client will be deleted including its events");
			   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
			   	    	ButtonType buttonTypeProceed = new ButtonType("Proceed");
			   	    	ButtonType buttonTypeCancel = new ButtonType("Cancel");
			   	    	alert.getButtonTypes().setAll(buttonTypeProceed, buttonTypeCancel);
			
			   	    	Optional<ButtonType> result = alert.showAndWait();
			
			   	    	if (result.get() == buttonTypeProceed){
			   	    		EventDaoImplementation.setCtl(tableClient.getSelectionModel().getSelectedItem());
			   	    		ClientDao.deleteClient(tableClient.getSelectionModel().getSelectedItem());
			   	   	        tableClient.getItems().setAll(ClientDao.getAllClients());
							 System.out.println("Item deleted seccesfully ");
							 Alert seccesfulDeletion = new Alert(AlertType.INFORMATION);
							 seccesfulDeletion.setTitle("Item deletion");
							 seccesfulDeletion.setHeaderText("Item Deleltion");
							 seccesfulDeletion.setContentText("Client Delelted seccesfully");
							 seccesfulDeletion.getDialogPane().getStylesheets().add("/application/Alert.css");
							 seccesfulDeletion.show();

			             	   Timeline timeline = new Timeline(new KeyFrame(
			   	               Duration.seconds(1.5),
			   	               ae -> seccesfulDeletion.close()));
			   	               timeline.play();
			   	    		
			   	    	} else {
			   	    		
			   	    		
			   	    	}
   	   	  
   	   	      }
   	    	
   	    	
	    }else if(event.getSource()==idModify) {
	    	if(tableClient.getSelectionModel().getSelectedItem()==null) {
   	   	    	Alert alert = new Alert(Alert.AlertType.WARNING);
   	   	    	alert.setTitle("Selet Item");
   	   	    	alert.setHeaderText("Please select the item  ");
   	   	    	alert.setContentText(" You cannot modify undefined client ");
   	   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
   	   	    	alert.showAndWait();
   	   	      }
   	   	      else {
   	    	System.out.println("Client modified");
   	    	url+="UpdateClient.fxml";
   	    	ClientDao.setClt(tableClient.getSelectionModel().getSelectedItem());
   	    	System.out.println(ClientDao.getClt());
   	    	Node node = (Node) event.getSource();
			 Stage thisStage = (Stage) node.getScene().getWindow();
			 thisStage.close();
 			 Parent root = FXMLLoader.load(getClass().getResource(url));
 			 thisStage.setUserData(ClientDao);
 				Scene scene = new Scene(root);
 				thisStage.setScene(scene);
 				thisStage.show();
   	    	
   	   	      }
 
	    }
		if(event.getSource()!=idModify &&event.getSource()!=idSupprim && event.getSource()!=idAdd) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
		}

}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 idClient.setCellValueFactory(new PropertyValueFactory<Client, Integer>("ID"));
		 idName.setCellValueFactory(new PropertyValueFactory<Client, String>("Name"));
		 idMail.setCellValueFactory(new PropertyValueFactory<Client, String>("Email"));
		 idPhoneNum.setCellValueFactory(new PropertyValueFactory<Client, String>("PhoneNum"));
		 idCin.setCellValueFactory(new PropertyValueFactory<Client, String>("CIN"));
		 
		 try {
			tableClient.getItems().setAll(ClientDao.getAllClients());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}