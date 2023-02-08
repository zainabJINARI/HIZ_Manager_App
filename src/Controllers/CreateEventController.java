package Controllers;




import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Models.Event;
import Models.PartyRoom;
import Models.Provider;
import dao.BudgetDaoImplementation;
import dao.ProviderDaoImplementation;
import dao.RoomsDaoImplementation;
import javafx.scene.control.DatePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEventController implements Initializable{

    @FXML
    private ComboBox<String> idPartyRoom = new  ComboBox<String>() ;

    @FXML
    private ComboBox<String> idProviders= new  ComboBox<String>() ;;

    @FXML
    private TextField idNumberOfGuests;

    @FXML
    private ComboBox<String> idTypeOfEvent= new  ComboBox<String>() ;


    @FXML
    private Button idAdd;

    @FXML
    private TextField idMore;

    @FXML
    private Button idBack;

    @FXML
    private DatePicker start;

    @FXML
    private DatePicker end;
    RoomsDaoImplementation room = new RoomsDaoImplementation();
    ProviderDaoImplementation provider = new ProviderDaoImplementation();
    BudgetDaoImplementation budgetDao = new BudgetDaoImplementation();
    ObservableList<Provider> Providers;
    ObservableList<PartyRoom> Rooms;
    @FXML
    void ActionCreateEventController(ActionEvent event) throws IOException, SQLException { 
    	String url="/Interfaces/";
    	if(event.getSource()==idBack) {
    		Node node = (Node) event.getSource();
			 Stage thisStage = (Stage) node.getScene().getWindow();
			 thisStage.setUserData(CreateEventsController.getBudget());
   	    	url+="BudgetEventCreation.fxml";
   	    }else if(event.getSource()==idAdd) {
   	    	System.out.println("Provider");
   	    	budgetDao.addBudget(CreateEventsController.getBudget());
   	    	
   	    	if(idPartyRoom.getSelectionModel().getSelectedItem()==null || idProviders.getSelectionModel().getSelectedItem()==null || idTypeOfEvent.getSelectionModel().getSelectedItem()==null || idNumberOfGuests.getText().isBlank()==true || idMore.getText().isBlank()==true) {
   	    	 Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    alert.setTitle("Some informations Missing");
	    	    alert.setHeaderText("Please Fill The whole form");
	    	    alert.setContentText("Make sure all values required are filled");
	    	    alert.getDialogPane().getStylesheets().add("/application/Alert.css");
	    	    alert.showAndWait();
   	    	}
   	    	int numGuest=0;
   	    	try {
   	         numGuest = Integer.parseInt(idNumberOfGuests.getText());
   	    	}catch(NumberFormatException e) {
   	    	 Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    alert.setTitle("Invalid Input");
	    	    alert.setHeaderText("Please enter a valid number.");
	    	    alert.setContentText("Make sure you enter a number in N° Guests Field.");
	    	    alert.getDialogPane().getStylesheets().add("/application/Alert.css");
	    	    alert.showAndWait();
   	    	}	
   	    	
   	    	int idRoom=0;
   	    	for(PartyRoom item: Rooms) {
   	    		if(item.getAddress().compareTo(idPartyRoom.getSelectionModel().getSelectedItem())==0) {
   	    			idRoom=item.getID();
   	    		}
   	    	}
   	    	int idProvider=0;
   	    	for(Provider item: Providers) {
   	    		if(item.getName().compareTo(idProviders.getSelectionModel().getSelectedItem())==0) {
   	    			idProvider=item.getID();
   	    		}
   	    	}
   	    	LocalDate startD = start.getValue();
   	    	
   	    	Instant instant = Instant.from(startD.atStartOfDay(ZoneId.systemDefault()));
   	    	java.sql.Date sqlDateS = new java.sql.Date(Date.from(instant).getTime());
   	    	
   	    	LocalDate endD = end.getValue();
   	    	Instant instant2 = Instant.from(endD.atStartOfDay(ZoneId.systemDefault()));
   	    	java.sql.Date sqlDateE = new java.sql.Date(Date.from(instant2).getTime());
   	    	Event event1 = new Event(idProvider,sqlDateS,sqlDateE,idMore.getText(),BudgetDaoImplementation.getId(),idRoom,numGuest,idTypeOfEvent.getSelectionModel().getSelectedItem());
   	    	ClientEventAreaController.getEventDao().AddEvent(event1);
   	    	
   	    	url+="CreateEventArea.fxml";
   	       
   	       
   	    }
    	((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
    	

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	   try {
		room.getAllRooms();
		provider.getAllProviders();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		idTypeOfEvent.getItems().addAll("Funeral","Wedding","Other");
		List<String> roomsnames =GetRooms(CreateEventsController.getBudget().getBudgetRoom());
		for (String item : roomsnames) {
			idPartyRoom.getItems().add(item);
		}
		List<String> provnames = GetProviders(CreateEventsController.getBudget().getBudgetProvider());
		for (String item : provnames) {
			idProviders.getItems().add(item);
		}
		
	}
	private List<String> GetRooms(Double budget){
		Rooms=RoomsDaoImplementation.getRooms();
		 List<String> roomsNames = new ArrayList<>();
		 for (PartyRoom item : Rooms) {
				if(item.getBudgetRoom()<=budget) {
					roomsNames.add(item.getAddress());
				}
			}
		 
		 if (roomsNames!=null) { return roomsNames;}
		 else {
			 roomsNames.add("No Party Room matched") ;
		      return roomsNames;
		      }
	}
	private List<String> GetProviders(Double budget){
		 Providers=ProviderDaoImplementation.getProviders();
		 List<String> providerNames = new ArrayList<>();
		 String level ="";
		 if(budget<=5000) {
			 level="Low";
		 }else if((budget>5000) && (budget<=10000)) {
			 level="Medium";
		 }else {
			 level="High";
		 }
		 for (Provider item : Providers) {
				if(item.getLevelOfProvider().compareTo(level)==0) {
					providerNames.add(item.getName());
				}
		 }
		 if (providerNames!=null) { return providerNames;}
		 else {
			 providerNames.add("No provider matched") ;
		      return providerNames;
		      }
	}
    
   



}
