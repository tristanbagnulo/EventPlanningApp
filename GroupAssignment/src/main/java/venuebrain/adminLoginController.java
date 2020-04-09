package venuebrain;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class adminLoginController {
   
    @FXML
    Button backButton;

    @FXML
    private void switchToPrimary() throws IOException, SQLException {
        //App.setRoot("primary");
        
        //testing addNewGuest method
         if(DatabaseManager.addNewGuest("T''est", "Guest", "testguest@mail.com", "04983247")){
            System.out.println("guest added");
        }
        
    }
      @FXML
    private void btnBackWasClicked() throws IOException, SQLException {
        App.setLoginRoot("primary");

        
    }
}