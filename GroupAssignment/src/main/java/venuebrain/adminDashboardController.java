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
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class adminDashboardController{
    @FXML
    MenuBar adminMenu;
    
    @FXML
    Menu newMenu;
    
    @FXML
    MenuItem newEvent;
    
    @FXML
    MenuItem newGuest;
    
    @FXML
    Menu editMenu;
    
    @FXML
    MenuItem editEvent;
    
    @FXML
    MenuItem deleteGuest;
    
    @FXML
    Menu helpMenu;
    
    @FXML
    MenuItem about;
    
    @FXML
    Button backButton;
    
    @FXML
    Button createEvent;
    
    @FXML
    TextField newEventName;
    
    @FXML
    TextField newEventLocation;
    
    @FXML
    Label invalidEvent;
     
    @FXML
//    Initialise the TableView as FXML variables
    TableView eventTable = new TableView();
   
//    Initialise the TableColumns as FXML variables
    @FXML
   TableColumn<Event, String> eventNameCol = new TableColumn<>("Name");
   
    @FXML
   TableColumn<Event, String> eventLocationCol = new TableColumn<>("Location");
    
     @FXML
    public void initialize() {
        invalidEvent.setVisible(false);
        createEvent.setVisible(false);
        newEventName.setVisible(false);
        newEventLocation.setVisible(false);
     
        eventNameCol.setCellValueFactory(cellData -> cellData.getValue().getViewableEventName());
        eventLocationCol.setCellValueFactory(cellData -> cellData.getValue().getViewableLocation());
     
        eventTable.setItems(getEventListData());
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
                  // create a new music object
                    new Event(rs.getString("event_name"), rs.getString("location"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
       return FXCollections.observableArrayList(eventListToReturn);
    }
 
    @FXML
    private void showAbout() throws IOException, SQLException {
       App.showAbout();
    }
    
    @FXML
    private void createEventMenu() throws IOException, SQLException {
       createEvent.setVisible(true);
       newEventName.setVisible(true);
       newEventLocation.setVisible(true);
    }
    
    @FXML
    private void createNewEventClicked() throws IOException, SQLException {
       String enteredEventName = newEventName.getText();
       String enteredLocation = newEventLocation.getText();
       
       if(enteredEventName.isEmpty() || enteredLocation.isEmpty()){
           invalidEvent.setVisible(true);
       }else{
            DatabaseManager.addNewEvent(enteredEventName, enteredLocation);
            eventTable.setItems(getEventListData());
            newEventName.setText("");
            newEventLocation.setText("");
            createEvent.setVisible(false);
            newEventName.setVisible(false);
            newEventLocation.setVisible(false);
       }
    }
    @FXML
    private void editEventClicked() throws IOException, SQLException {
       Event selectedEvent = (Event) eventTable.getSelectionModel().getSelectedItem();
       
       System.out.println(selectedEvent.getEventName());
       System.out.println(selectedEvent.getLocation());
        
    }

     @FXML
    private void btnBackWasClicked() throws IOException, SQLException {
        
        App.setLoginRoot("adminLogin");  
    }
    

// @FXML
//    private void existingEventsColumn() throws IOException, SQLException {
//         
//    }
}