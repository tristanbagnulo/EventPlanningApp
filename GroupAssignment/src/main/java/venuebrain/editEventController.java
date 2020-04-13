package venuebrain;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class editEventController {

  
    @FXML
    Button deleteGuests;
    
    @FXML
    Button saveChanges;
    
    @FXML
    Label eventIDLbl;
    
    @FXML
    TextField eventNameTxt;
    
    @FXML
    TextField eventLocationTxt;
    
    @FXML
    TableView guestTable = new TableView();
    
//    @FXML
//    TableColumn fNameCol;
//    
//    @FXML
//    TableColumn lNameCol;
//    
//    @FXML
//    TableColumn accessCodeCol;
    
//    Initialise the TableColumns as FXML variables
   @FXML
   TableColumn<Guest, String> guestfNameCol = new TableColumn<>("First Name");
   
    @FXML
   TableColumn<Guest, String> guestlNameCol = new TableColumn<>("Last Name");
    
    @FXML
    TableColumn<Guest, String> guestACodeCol = new TableColumn<>("Access Code");
    
 
    @FXML
    public void initialize() throws IOException, SQLException {
      
  
        
    }
    
   public void initData(Event event) throws SQLException{
       
       eventIDLbl.setText(Integer.toString(DatabaseManager.getEventID(event)));
       
       eventNameTxt.setText(event.getEventName());
       eventLocationTxt.setText(event.getLocation());
        
   }
    
    
    
    /*@FXML
    private void switchToAdminLogin() throws IOException {
        App.setLoginRoot("adminLogin");
    }
    
    @FXML
    private void btnGuestWasClicked() throws IOException {
        
        App.setLoginRoot("guestLogin");
        
    }*/
}
