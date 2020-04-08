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
        
         if(DatabaseManager.addNewGuest("T'est", "Guest", "4983247", "testguest@mail.com")){
            System.out.println("guest added");
        }
        
    }
      @FXML
    private void btnBackWasClicked() throws IOException, SQLException {
        App.setRoot("primary");

        
    }
}