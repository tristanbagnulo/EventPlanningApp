/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author jacob
 */
public class LoginScreenController {

    //Initiate JavaFX nodes (visual elements), how do we connect these variables to the FXML view?
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button loginButton;
    @FXML
    Button nextButton;
    @FXML
    Label indiefy;
    @FXML
    Label message;

    // Initiate the database class
    Database d = new Database();
    PageSwitchHelper pSwitch = new PageSwitchHelper();

    /* What should happen when you click the login button?
       How do we connect this function to the FXML view? */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {

        String user = username.getText();
        String pass = password.getText();

        // Get the user's input from the GUI
        if (d.tryLogin(user, pass)) {
            // What should the user see when the login is successful?
            message.setText("Successfully Logged in!");
            nextButton.setVisible(true);

        } else {
            // What should the user see when the login is unsuccessful?
            message.setText("Incorrect username or password");
            nextButton.setVisible(false);

        }

    }

    @FXML
    private void handleNextButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event, "MusicList.fxml");
    }

    @FXML
    public void initialize() {
        // What should the user see when the screen loads?
        System.out.println("Loading...");
        nextButton.setVisible(false);
    }

}
