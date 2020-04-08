package venuebrain;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class guestLoginController {
  
    @FXML
    Button loginButton;
    
    @FXML
    Button backButton;
    
    @FXML
    TextField guestAccess;
    
    @FXML
    Label invalidCode;
       
    @FXML
    protected void initialize() {
        invalidCode.setVisible(false);
    }
    
    @FXML
    private void btnLoginClicked() throws IOException {
        System.out.println("Login Clicked");
        String enteredAccessCode = guestAccess.getText();
        if (DatabaseManager.fetchAccessCode(enteredAccessCode)){
            System.out.println("Correct Access Code");
            invalidCode.setVisible(false);
        }else {
            invalidCode.setVisible(true);
        }
    }
    
    @FXML
    private void btnBackWasClicked() throws IOException, SQLException {
        
        App.setRoot("primary");
    }
    @FXML
    private void btnBackToGuestLogIn() throws IOException, SQLException {
        
        App.setRoot("guestLogin");
    }
}