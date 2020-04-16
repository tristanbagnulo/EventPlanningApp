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
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;


public class guestDashboardController {
  
    @FXML
    Label welcomeUserlbl;
    
    @FXML
    Button logoutButton;
   

 @FXML
//    Initialise the TableView as FXML variables
    public TableView event = new TableView();
   
//    Initialise the TableColumns as FXML variables
    @FXML
   TableColumn<Event, String> nameCol = new TableColumn<>("Events Invited to");
   
    @FXML
   TableColumn<Event, String> locationCol = new TableColumn<>("Location");
    
    @FXML
   TableColumn<Event, String> date = new TableColumn<>("Date");
        
    @FXML
   TableColumn<Event, String> rsvpStatus = new TableColumn<>("RSVP Status");
    

    
     @FXML
    public void initialize() {
        
        welcomeUserlbl.setText("Welcome " + App.getCurrentUser().getFName() + " !");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().getViewableEventName());
        locationCol.setCellValueFactory(cellData -> cellData.getValue().getViewableLocation());
       
        
        //date.setCellValueFactory(cellData -> cellData.getValue().getViewableDate());
        

     
        event.setItems(getEventListData());
    }
    
    private ObservableList<Event> getEventListData() {
        List<Event> eventListToReturn = new ArrayList<>();
        try {
            
            // Get the event list from the database
            DatabaseManager.openConnection();
            String eventListQuery = "SELECT * FROM event;";
            Statement st = DatabaseManager.sharedConnection.createStatement();
            ResultSet rs = st.executeQuery(eventListQuery);
            
            while(rs.next()) {
                eventListToReturn.add(
                  // create a new event object
                    new Event(rs.getString("event_name"), rs.getString("location"))
                );
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
       return FXCollections.observableArrayList(eventListToReturn);
    }
    
     @FXML
    private void btnLogoutWasClicked() throws IOException {
        
        App.setLoginRoot("primary");
        
    }

    
}
