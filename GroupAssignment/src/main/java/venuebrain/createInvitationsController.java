package venuebrain;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class createInvitationsController {
  
    @FXML
    Button saveChanges;
    
    @FXML
    Label eventIDLbl;
    
    @FXML
    Label eventNameLbl;
    
    @FXML
    Label eventLocationLbl;
    
    
    @FXML
    TableView guestTable = new TableView();
    
    
//    Initialise the TableColumns as FXML variables
   @FXML
   TableColumn<Guest, String> guestfNameCol = new TableColumn<>("First Name");
   
    @FXML
   TableColumn<Guest, String> guestlNameCol = new TableColumn<>("Last Name");
    
    @FXML
    TableColumn<Guest, String> guestACodeCol = new TableColumn<>("Access Code");
    
    @FXML
    TableColumn<Guest, String> guestEmailCol = new TableColumn<>("Email");
    
    @FXML
    TableColumn<Guest, String> guestPhoneCol = new TableColumn<>("Phone Number");
    
 
    @FXML
    public void initialize() throws IOException, SQLException {
      
       guestfNameCol.setCellValueFactory(cellData -> cellData.getValue().getViewableFName());
        guestlNameCol.setCellValueFactory(cellData -> cellData.getValue().getViewableLName());
        guestACodeCol.setCellValueFactory(cellData -> cellData.getValue().getViewableAccessCode());
        guestEmailCol.setCellValueFactory(cellData -> cellData.getValue().getViewableEmail());
        guestPhoneCol.setCellValueFactory(cellData -> cellData.getValue().getViewablePhone());
        
        guestTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        guestTable.setItems(getGuestListData());
    }
    
   public void initData(Event event) throws SQLException{
       
       eventIDLbl.setText(Integer.toString(DatabaseManager.getEventID(event)));
       eventNameLbl.setText(event.getEventName());
       eventLocationLbl.setText(event.getLocation());
   }
   
  private ObservableList<Guest> getGuestListData() {
        List<Guest> guestListToReturn = new ArrayList<>();
        try {
            
            // Get the guest list from the database
            DatabaseManager.openConnection();
            String guestListQuery = "SELECT * FROM guest;";
            Statement st = DatabaseManager.sharedConnection.createStatement();
            ResultSet rs = st.executeQuery(guestListQuery);
            
            while(rs.next()) {
                Guest listGuest = new Guest(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email_address"), rs.getString("phone_number"));
                listGuest.setAccessCode(rs.getString("access_code"));
                
                guestListToReturn.add(
                    listGuest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
       return FXCollections.observableArrayList(guestListToReturn);
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
