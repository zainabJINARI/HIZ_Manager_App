
package Controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {

    @FXML
    private Button idProvider;

    @FXML
    private Button idProvider1;

    @FXML
    private Button idProvider11;

    @FXML
    private Button idProvider111;

    @FXML
    void ActionHomeController(ActionEvent event) throws IOException {
    	String url="/Interfaces/";
    	
		     
   	     url+="Home.fxml";
	    ((Stage)( (Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
    	
    }

}