package Controllers;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerLoading {

    @FXML
    private Label myLabel;
    @FXML
    private ImageView myImage;


    private String message = "Welcome To HIZ Manager";
    private int charIndex = 0;
   
    public void initialize() {
    	 myLabel.setStyle("-fx-effect: dropshadow(gaussian, #f0c4f7, 5, 0.5, 0, 0);");
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.3),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                myLabel.setText(message.substring(0, charIndex++));
                                if (charIndex > message.length()) {
                                    charIndex = 0;
                                }
                            }
                        }));
        timeline.play();
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4), myImage);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.play();
        PauseTransition pause = new PauseTransition(Duration.seconds(7.5));
        pause.setOnFinished(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Interfaces/Login.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) myLabel.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}