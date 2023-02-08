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
import dao.ProviderDaoImplementation;
import dao.RoomsDaoImplementation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyEvent implements Initializable{

    @FXML
    private ComboBox<String> idPartyRoom;

    @FXML
    private ComboBox<String> idProviders;

    @FXML
    private TextField idNumberOfGuests;

    @FXML
    private ComboBox<String> idTypeOfEvent;

    @FXML
    private Button idSave;


    @FXML
    private TextField idMore;

    @FXML
    private Button idBack;

    @FXML
    private DatePicker end;

    @FXML
    private DatePicker start;
    ObservableList<Provider> Providers;
    ObservableList<PartyRoom> Rooms;
    private Event event =ClientEventAreaController.getEventDao().getEvt();
    RoomsDaoImplementation room = new RoomsDaoImplementation();
    ProviderDaoImplementation provider = new ProviderDaoImplementation();

    @FXML
    void ActionModifyEventController(ActionEvent ev) throws IOException, SQLException {
    	 String url="/Interfaces/";
    	    if(ev.getSource()==idSave) {
    	    	
    	    	/**
    	    	 * Modify The budget object
    	    	 */
    	    	ModifyBudgetEvent.getBudgetDao().setId(ModifyBudgetEvent.getBudg().getID());
    	    	ModifyBudgetEvent.getBudgetDao().updateBudget(ModifyBudgetEvent.getBudg());
    	    	
    	    	/**
    	    	 * Get user input if there is some modifications
    	    	 */
    	    	
    	    	if(idNumberOfGuests.getText().isBlank()==true || idMore.getText().isBlank()==true) {
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
    	   	    	if(idPartyRoom.getSelectionModel().getSelectedItem()!=null) {
    	   	    	for(PartyRoom item: Rooms) {
    	   	    		if(item.getAddress().compareTo(idPartyRoom.getSelectionModel().getSelectedItem())==0) {
    	   	    			idRoom=item.getID();
    	   	    		}
    	   	    	}
    	   	    	}else {
    	   	    		idRoom=event.getIdPartyR();
    	   	    	}
    	   	    	int idProvider=0;
    	   	    	if(idProviders.getSelectionModel().getSelectedItem()!=null) {
    	   	    	for(Provider item: Providers) {
    	   	    		if(item.getName().compareTo(idProviders.getSelectionModel().getSelectedItem())==0) {
    	   	    			idProvider=item.getID();
    	   	    		}
    	   	    	}
    	   	    	}else {
    	   	    		idProvider=event.getIdProvider();
    	   	    	}
    	   	    	LocalDate startD = start.getValue();
    	   	    	Instant instant = Instant.from(startD.atStartOfDay(ZoneId.systemDefault()));
    	   	    	java.sql.Date sqlDateS = new java.sql.Date(Date.from(instant).getTime());
    	   	    	
    	   	    	LocalDate endD = end.getValue();
    	   	    	Instant instant2 = Instant.from(endD.atStartOfDay(ZoneId.systemDefault()));
    	   	    	java.sql.Date sqlDateE = new java.sql.Date(Date.from(instant2).getTime());
    	   	    	event.setEndDate(sqlDateE);
    	   	    	event.setStartDate(sqlDateS);
    	   	    	event.setNbrGuets(numGuest);
    	   	    	event.setIdPartyR(idRoom);
    	   	    	event.setIdProvider(idProvider);
    	   	    	event.setMoreDetails(idMore.getText());
    	   	    	ClientEventAreaController.getEventDao().UpdateEvent(event);
    	   	    	url+="CreateEventArea.fxml";
    	   	    	
    	    	
    	    }else if(ev.getSource()==idBack) {
       	    	System.out.println("Cancel");
       	    	url+="BudgetEventModification.fxml";
       	    }
    		((Stage)( (Node) ev.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));

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
		List<String> roomsnames =GetRooms(ModifyBudgetEvent.getBudg().getBudgetRoom());
		for (String item : roomsnames) {
			idPartyRoom.getItems().add(item);
		}
		List<String> provnames = GetProviders(ModifyBudgetEvent.getBudg().getBudgetProvider());
		for (String item : provnames) {
			idProviders.getItems().add(item);
		}
		Instant instant = Instant.ofEpochMilli( event.getStartDate().getTime());
		start.setValue(LocalDate.ofInstant(instant, ZoneId.systemDefault())); 
		Instant instant2 = Instant.ofEpochMilli(event.getEndDate().getTime());
		end.setValue(LocalDate.ofInstant(instant2, ZoneId.systemDefault())); 
		
		idNumberOfGuests.setText( Integer.toString(event.getNbrGuets()));
		idMore.setText(event.getMoreDetails());
		
		
	}
	private List<String> GetRooms(Double budget){
		Rooms=RoomsDaoImplementation.getRooms();
		 List<String> roomsNames = new ArrayList<>();
		 for (PartyRoom item : Rooms) {
				if(item.getBudgetRoom()<=budget) {
					roomsNames.add(item.getAddress());
				}
			}
		 return roomsNames; 
		
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
		 return providerNames;
		
	}
    
   



}

