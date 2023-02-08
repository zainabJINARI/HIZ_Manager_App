package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

import Models.Client;
import Models.Event;
import Models.User;
import dao.EventDaoImplementation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClientEventAreaController implements Initializable {

    @FXML
    private Button GoBack;

    @FXML
    private TableColumn<Event, Integer> NumGuests;

    @FXML
    private TableColumn<Event, Date> endDate;

    @FXML
    private TableColumn<Event, String> eventType;

    @FXML
    private Button idAdd;

    @FXML
    private TableColumn<Event, Integer> idBudget;

    @FXML
    private TableColumn<Event, Integer> idEvent;

    @FXML
    private Button idModify;

    @FXML
    private TableColumn<Event, Integer> idParty;

    @FXML
    private TableColumn<Event, Integer> idProvider;

    @FXML
    private Button idSupprim;

    @FXML
    private TableColumn<Event, String> moreDetails;

    @FXML
    private TableColumn<Event, Date> startDate;

    @FXML
    private TableView<Event> tableEvent;
    private static EventDaoImplementation eventDao = new EventDaoImplementation();
    /**
     * Event Dao setters and getter 
     * @return
     */

    public static EventDaoImplementation getEventDao() {
		return eventDao;
	}
	public static void setEventDao(EventDaoImplementation eventDao) {
		ClientEventAreaController.eventDao = eventDao;
	}

/**
 * The method takes control of all the evens triggered by btns in the interface 
 * @param event
 * @throws IOException
 * @throws SQLException 
 */

	@FXML
    void ActionClientEventAreaController(ActionEvent event) throws IOException, SQLException {
    	String url="/Interfaces/";
		if(event.getSource()==GoBack) {
   	    	System.out.println("Client Area");
   	    	url+="Manage Client.fxml";
   	    }else if(event.getSource()==idAdd) {
   	    	System.out.println("Provider");
   	    	Node node = (Node) event.getSource();
			 Stage thisStage = (Stage) node.getScene().getWindow();
			 thisStage.setUserData(null);
   	    	url+="BudgetEventCreation.fxml";
   	    }else if(event.getSource()==idModify) {
   	    	/**
   	    	 * In this line we are trying to set the event to be used on the next interfaces where it should be modified
   	    	 */
   	      if(tableEvent.getSelectionModel().getSelectedItem()==null) {
   	    	Alert alert = new Alert(Alert.AlertType.WARNING);
   	    	alert.setTitle("Selet Item");
   	    	alert.setHeaderText("Please select an item from  the table");
   	    	alert.setContentText("You can not proceed without selecting an item");
   	    	alert.getDialogPane().getStylesheets().add("/application/Alert.css");
   	    	alert.showAndWait();
   	      }
   	      else {
   	    	EventDaoImplementation.setEvt(tableEvent.getSelectionModel().getSelectedItem());
   	    	System.out.println("Provider");
   	    	url+="BudgetEventModification.fxml";
   	      }
   	    }else if(event.getSource()==idSupprim) {
   	    	if(tableEvent.getSelectionModel().getSelectedItem()==null) {
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

			   	   	    	 System.out.println(tableEvent.getSelectionModel().getSelectedItem().getID());
			   	   	         eventDao.DeleteEvent(tableEvent.getSelectionModel().getSelectedItem().getID(),tableEvent.getSelectionModel().getSelectedItem().getIdBudget());
			   	   	         tableEvent.getItems().setAll(eventDao.getAllEvents());
							 System.out.println("Item deleted seccesfully ");
							 Alert seccesfulDeletion = new Alert(AlertType.INFORMATION);
							 seccesfulDeletion.setTitle("Item deletion");
							 seccesfulDeletion.setHeaderText("Item Deleltion");
							 seccesfulDeletion.setContentText("Event is Delelted seccesfully");
							 seccesfulDeletion.getDialogPane().getStylesheets().add("/application/Alert.css");
							 seccesfulDeletion.show();

			             	   Timeline timeline = new Timeline(new KeyFrame(
			   	               Duration.seconds(1.5),
			   	               ae -> seccesfulDeletion.close()));
			   	               timeline.play();
			   	    		
			   	    	} else {
			   	    		
			   	    		
			   	    	}
   	   	  
   	   	      }
   	    }
		if(event.getSource()!=idSupprim) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
		}
    }

   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("here");
		endDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("endDate"));
		eventType.setCellValueFactory(new PropertyValueFactory<Event, String>("typeOfEvent"));
		idBudget.setCellValueFactory(new PropertyValueFactory<Event, Integer>("idBudget"));
		idEvent.setCellValueFactory(new PropertyValueFactory<Event, Integer>("ID"));
		 NumGuests.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbrGuets"));
		 idParty.setCellValueFactory(new PropertyValueFactory<Event, Integer>("idPartyR"));
		 idProvider.setCellValueFactory(new PropertyValueFactory<Event, Integer>("idProvider"));
		 moreDetails.setCellValueFactory(new PropertyValueFactory<Event, String>("MoreDetails"));
		 startDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("startDate"));
		 
		 try {
			 tableEvent.getItems().setAll(eventDao.getAllEvents());
			 System.out.println(eventDao.getAllEvents());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}