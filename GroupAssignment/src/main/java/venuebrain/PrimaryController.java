package venuebrain;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
  
    @FXML
    Button adminButton;
    
    @FXML
    Button guestButton;
    
    @FXML
    protected void initialize() {
        
    }
    
    @FXML
    private void switchToAdminLogin() throws IOException {
        App.setRoot("adminLogin");
    }
    
    @FXML
    private void btnGuestWasClicked() {
        
        System.out.println("Guest login TBD");
        
    }
}
