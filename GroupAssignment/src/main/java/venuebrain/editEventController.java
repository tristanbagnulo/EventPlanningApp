package venuebrain;

import java.io.IOException;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static venuebrain.DatabaseManager.sharedConnection;

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
    Label changesSavedLbl;
    
    @FXML
    TableView guestTable = new TableView();
    
//    Initialise the TableColumns as FXML variables
   @FXML
   TableColumn<Guest, String> guestfNameCol = new TableColumn<>("First Name");
   
    @FXML
   TableColumn<Guest, String> guestlNameCol = new TableColumn<>("Last Name");
    
    @FXML
    TableColumn<Guest, String> guestACodeCol = new TableColumn<>("Access Code");
    
    Event selectedEvent;
    
    @FXML
    public void initialize() throws IOException, SQLException {
      
       guestfNameCol.setCellValueFactory(cellData -> cellData.getValue().getViewableFName());
        guestlNameCol.setCellValueFactory(cellData -> cellData.getValue().getViewableLName());
        guestACodeCol.setCellValueFactory(cellData -> cellData.getValue().getViewableAccessCode());
        
        guestTable.setItems(getGuestListData());
        
        changesSavedLbl.setVisible(false);
  
        
    }
    
   public void initData(Event event) throws SQLException{
       
       selectedEvent = event;
       eventIDLbl.setText(Integer.toString(DatabaseManager.getEventID(event)));
       
       eventNameTxt.setText(event.getEventName());
       eventLocationTxt.setText(event.getLocation());
        
   }
   
             //Get the guest list from the database
//            String guestListQuery = "﻿SELECT g.guest_id, g.access_code, g.first_name, g.last_name, "
//                    + "g.email_address, g.phone_number, i.event_id, i.guest_id "
//                    + "﻿FROM guest g "
//                    + "﻿INNER JOIN invitation i "
//                    + "﻿ON g.guest_id = i.guest_id "
//                    + "﻿WHERE i.event_id = ?";
   
  private ObservableList<Guest> getGuestListData() throws SQLException {
        List<Guest> guestListToReturn = new ArrayList<>();
        int selectedEventID = DatabaseManager.getEventID(selectedEvent);
        DatabaseManager.openConnection();
        try {
            String guestListQuery = "﻿SELECT first_name, last_name, access_code, email_address, phone_number FROM guest " 
                                  + "JOIN invitation USING(guest_id) WHERE event_id = " + selectedEventID;
            ResultSet rs;
            Statement st = DatabaseManager.sharedConnection.createStatement();
            //ps.setInt(1, selectedEventID);
            rs = st.executeQuery(guestListQuery);
            while(rs.next()) {
                Guest listGuest = new Guest(rs.getString("first_name"), rs.getString("last_name"), 
                        rs.getString("email_address"), rs.getString("phone_number"));
                listGuest.setAccessCode(rs.getString("access_code"));
                guestListToReturn.add(listGuest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DatabaseManager.closeConnection();
        }   
       return FXCollections.observableArrayList(guestListToReturn);
    }
    
  @FXML
  private void btnSaveChanges() throws IOException, SQLException {

        
        String newEventName = eventNameTxt.getText();
        String newEventLocation = eventLocationTxt.getText();
        
        int selectedEventID = DatabaseManager.getEventID(selectedEvent);
        DatabaseManager.openConnection();
        try{
            DatabaseManager.openConnection();
            String updateString = "UPDATE event SET event_name = ?, location = ? WHERE event_id = ?";
            selectedEvent.setEventName(newEventName);
            selectedEvent.setLocation(newEventLocation);
            PreparedStatement ps = sharedConnection.prepareStatement(updateString);
            ps.setString(1, selectedEvent.getEventName());
            ps.setString(2, selectedEvent.getLocation());
            ps.setInt(3, selectedEventID);
            ps.executeUpdate();
            System.out.println("Event ID = " + selectedEventID + " name set to " + selectedEvent.getEventName()
                    + " location set to " + selectedEvent.getLocation());
            changesSavedLbl.setVisible(true);
        }catch (SQLException e){
            System.out.println("Event could not be found!");
            System.out.println(e.getMessage());
        }finally{
            DatabaseManager.closeConnection();
        } 
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
