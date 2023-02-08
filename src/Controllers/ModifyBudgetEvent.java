package Controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Models.Budget;
import dao.BudgetDaoImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyBudgetEvent implements Initializable{

    @FXML
    private TextField idRoomsBudget;

    @FXML
    private TextField idTotalBudget;

    @FXML
    private TextField idAvance;

    @FXML
    private TextField idProvidersBudget;

    @FXML
    private Button idNext;
    private Double BudgetR;
    private Double ProviderB;
    private Double TotalB;
    private Double Avance;
    

    @FXML
    private Button idCancel;
    private static BudgetDaoImplementation budgetDao = new BudgetDaoImplementation();
    private static  Budget budg;

    public static Budget getBudg() {
		return budg;
	}


	public static BudgetDaoImplementation getBudgetDao() {
		return budgetDao;
	}


	public static void setBudgetDao(BudgetDaoImplementation budgetDao) {
		ModifyBudgetEvent.budgetDao = budgetDao;
	}


	public static void setBudg(Budget budg) {
		ModifyBudgetEvent.budg = budg;
	}


	@FXML
    void ActionCreateEventsController(ActionEvent event) throws IOException {
    	String url="/Interfaces/";
		if(event.getSource()==idCancel) {
   	    	System.out.println("Client Area");
   	    	url+="CreateEventArea.fxml";
   	    }else if(event.getSource()==idNext) {
   	    
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
    	    	budg.setAvance(Avance);
    	    	budg.setBudgetProvider(ProviderB);
    	    	budg.setBudgetRoom(BudgetR);
    	    	budg.setTotalBudget(TotalB);
    	    	
    	    	
   	    	System.out.println("Modification event step 2");
   	    	
   	    	url+="ModifyEvent.fxml";
   	    }
		((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));


    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			budg= budgetDao.getBudgetById(ClientEventAreaController.getEventDao().getEvt().getIdBudget()) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		idRoomsBudget.setText(budg.getBudgetRoom().toString());
		idTotalBudget.setText(budg.getTotalBudget().toString());
		idAvance.setText(budg.getAvance().toString());
		idProvidersBudget.setText(budg.getBudgetProvider().toString());
	}

}