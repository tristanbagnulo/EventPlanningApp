/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

//written by Neil Matani (z5162753) for Lab4 INFS2605 20t1

/**
 *
 * @author jacob
 */

public class LoginScreenController implements Initializable{

    //Initiate JavaFX nodes (visual elements), how do we connect these variables to the FXML view?
    @FXML
    Button loginButton = new Button("Login");
    Label incorrectCred = new Label("Incorrect password or username!");
    TextField userField = new TextField();
    PasswordField passwordField = new PasswordField();
    
    
    // Initiate the database class
    
    Database d;
    

    /* What should happen when you click the login button?
       How do we connect this function to the FXML view? */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        
        // Get the user's input from the GUI
            String user = userField.getText();
            String password = passwordField.getText();

            
            if (d.tryLogin(user, password)) {
              // What should the user see when the login is successful?

              System.out.println("Login Successful");
            } else {
              // What should the user see when the login is unsuccessful?
              incorrectCred.setVisible(true);
            } 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

