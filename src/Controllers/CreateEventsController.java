package Controllers;

import java.io.IOException;

import Models.Budget;
import dao.BudgetDaoImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateEventsController {
	/**
	 * this class is the controller of the BudgetEventCreation fxml file
	 */
	@FXML private TextField idRoomsBudget;
	@FXML private TextField idTotalBudget;
	@FXML private TextField idProvidersBudget;
	@FXML private TextField idAvance;
	@FXML private Button idNext;
	@FXML private Button idCancel;
	private Double BudgetR;
	private Double ProviderB ;
	private Double  TotalB;
	private Double Avance ;
	private int counter=0;
	public static Budget getBudget() {
		return budget;
	}

	public static void setBudget(Budget budget) {
		CreateEventsController.budget = budget;
	}

	private static Budget budget;
//	BudgetDaoImplementation budgetDao = new BudgetDaoImplementation();
	 @FXML
	    void ShowData(MouseEvent event) {
	    	Node node = (Node) event.getSource();
			 Stage thisStage = (Stage) node.getScene().getWindow();
			 if(thisStage.getUserData()!=null) {
				 if(counter==0) {
					 idProvidersBudget.setText(((Budget)thisStage.getUserData()).getBudgetProvider().toString());
					 idTotalBudget.setText(((Budget)thisStage.getUserData()).getTotalBudget().toString());
					 idRoomsBudget.setText(((Budget)thisStage.getUserData()).getBudgetRoom().toString());
					 idAvance.setText(((Budget)thisStage.getUserData()).getAvance().toString());
					 
				 }
			 }
			 counter++;
	    }
	
	 public void ActionCreateEventsController(ActionEvent event) throws IOException {
		 String url="/Interfaces/";
   	    if(event.getSource()==idNext) {
   	    	try {
   	    	 BudgetR = Double.parseDouble(idRoomsBudget.getText());
   	    	 ProviderB = Double.parseDouble(idProvidersBudget.getText());
   	    	  TotalB = Double.parseDouble(idTotalBudget.getText());
   	    	 Avance = Double.parseDouble(idAvance.getText());
   	    	    
   	    	} catch (NumberFormatException e) {
   	    	    Alert alert = new Alert(Alert.AlertType.ERROR);
   	    	    alert.setTitle("Invalid Input");
   	    	    alert.setHeaderText("Invalid Number On one of the fields of the form ");
   	    	    alert.setContentText("Please enter a valid number.");
   	    	    alert.getDialogPane().getStylesheets().add("/application/Alert.css");
   	    	    alert.showAndWait();
   	    	  
   	    	}
   	    	budget = new Budget(Avance,TotalB,BudgetR,ProviderB);
   	    	
   	    	System.out.println("Budget Adding");
   	    	url+="CreatEvent.fxml";
   	    }else if(event.getSource()==idCancel) {
   	    	System.out.println("Cancel");
   	    	url+="CreateEventArea.fxml";
   	    }


		((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
   	}

	
	
}