/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

//written by Neil Matani (z5162753) for Lab4 INFS2605 20t1

/**
 *
 * @author jacob
 */

public class LoginScreenController implements Initializable{

    //Initiate JavaFX nodes (visual elements), how do we connect these variables to the FXML view?
    @FXML
    Button loginButton = new Button("Login");
    
    @FXML
    Label incorrectCred = new Label("Incorrect password or username");
    
    @FXML
    TextField userField = new TextField();
    
    @FXML
    PasswordField passwordField = new PasswordField();
    
    @FXML
    Button nextButton = new Button("Next>>>");
    
    
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
              nextButton.setVisible(true);
            } else {
              // What should the user see when the login is unsuccessful?
              incorrectCred.setVisible(true);
            } 
    }
    
    @FXML
    private void switchToMusic(ActionEvent event) throws IOException {
       
        System.out.println("Switching pages");
        Parent parent = FXMLLoader.load(getClass().getResource("MusicList.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }		

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        incorrectCred.setVisible(false);
    }
}  
    


