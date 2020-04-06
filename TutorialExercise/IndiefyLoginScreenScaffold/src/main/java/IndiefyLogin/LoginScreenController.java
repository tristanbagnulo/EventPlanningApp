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
    Button loginButton;
    
    @FXML
    Label incorrectCred;
    
    @FXML
    TextField userField;
    
    @FXML
    PasswordField passwordField;
    
    @FXML
    Button nextButton;
    
    
    // Initiate the database class
    
    Database d;
    

    /* What should happen when you click the login button?
       How do we connect this function to the FXML view? */
    @FXML
    public void handleLoginButtonAction(ActionEvent event) throws IOException{
        
        // Get the user's input from the GUI
            System.out.println("Login Clicked");
            String user = userField.getText();
            String password = passwordField.getText();
            System.out.println(user + password);
            
            Database.openConnection();
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
    public void switchToMusic(ActionEvent event) throws IOException {
       
        System.out.println("Switching pages");
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/MusicList.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }		

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        incorrectCred.setVisible(false);
    }
}  
    


