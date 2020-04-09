package venuebrain;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;


public class guestDashboardController {
  
    
    
    @FXML
    Button logoutButton;

    
    
    @FXML
    protected void initialize() {
        //invalidCode.setVisible(false);
    }
    
     @FXML
    private void btnLogoutWasClicked() throws IOException {
        
        App.setLoginRoot("primary");
        
    }

    
}
