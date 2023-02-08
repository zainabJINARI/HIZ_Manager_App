package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TaskController {
	@FXML private Button   idBack;
	@FXML private Button idSave;
	@FXML private TextArea idTextArea;
	@FXML private DatePicker idDate;
	public void ActionTask (ActionEvent event) throws IOException {
		 String url="/Interfaces/";
	   	    if(event.getSource()==idBack) {
	   	    	System.out.println("Manage profile");
	   	    	url+="ManageProfil.fxml";
	   	    	((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
	   	    }else if(event.getSource()==idSave) {
	   	    	
	   	    }
	}
}
