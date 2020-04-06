package venuebrain;

import java.io.IOException;
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
        DatabaseManager.openConnection();
        if (DatabaseManager.fetchAccessCode(enteredAccessCode)){
            System.out.println("Correct Access Code");
            invalidCode.setVisible(false);
        }else {
            invalidCode.setVisible(true);
        }
        DatabaseManager.closeConnection();
    }
    
    @FXML
    private void btnBackWasClicked() throws IOException {
        
        App.setRoot("primary");
        
    }
    
}
