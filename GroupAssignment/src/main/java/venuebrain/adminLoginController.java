package venuebrain;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;

public class adminLoginController {

    @FXML
    private void switchToPrimary() throws IOException, SQLException {
        //App.setRoot("primary");
        
         if(DatabaseManager.addNewGuest("T'est", "Guest", "4983247", "testguest@mail.com")){
            System.out.println("guest added");
        }
        
    }
}