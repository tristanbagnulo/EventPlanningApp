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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    MenuItem createInvitations;
    
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
    Label welcomeUserlbl;
    
    @FXML
    Button guestManager;
     
    @FXML
//    Initialise the TableView as FXML variables
    public TableView eventTable = new TableView();
   
//    Initialise the TableColumns as FXML variables
    @FXML
   TableColumn<Event, String> eventNameCol = new TableColumn<>("Name");
   
    @FXML
   TableColumn<Event, String> eventLocationCol = new TableColumn<>("Location");
    
     @FXML
    public void initialize() {
        
        welcomeUserlbl.setText("Welcome " + App.getCurrentUser().getFName() + " !");
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
    public void editEventClicked() throws IOException, SQLException {
      
       
     // int selectedID = DatabaseManager.getEventID((Event) eventTable.getSelectionModel().getSelectedItem());
     
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("editEvent.fxml"));
       Parent tableViewParent = loader.load();
       
       Stage eventStage = new Stage();
       Scene eventScene = new Scene(tableViewParent, 373, 580);
       
      // App.setNewWindow(eventStage, eventScene, "editEvent", 373, 580);
       editEventController controller = loader.getController();
       controller.initData((Event) eventTable.getSelectionModel().getSelectedItem());
       
        eventStage.setScene(eventScene);
        eventStage.show();

    }
    
    @FXML
    public void createInvitationsClicked() throws IOException, SQLException {
      
       
     // int selectedID = DatabaseManager.getEventID((Event) eventTable.getSelectionModel().getSelectedItem());
     
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("createInvitations.fxml"));
       Parent tableViewParent = loader.load();
       
       Stage inviteStage = new Stage();
       Scene inviteScene = new Scene(tableViewParent, 547, 580);
       
      // App.setNewWindow(eventStage, eventScene, "editEvent", 373, 580);
       createInvitationsController controller = loader.getController();
       controller.initData((Event) eventTable.getSelectionModel().getSelectedItem());
       
        inviteStage.setScene(inviteScene);
        inviteStage.show();

    }
    
    

     @FXML
    private void btnBackWasClicked() throws IOException, SQLException {
        
        App.setLoginRoot("adminLogin");  
    }
    
     @FXML
    private void btnGuestManager() throws IOException, SQLException {
        
        Stage guestManagerStage = new Stage();
        Scene guestManagerScene = null;
        
        App.setNewWindow(guestManagerStage, guestManagerScene, "guestManager", 678, 645);
    }
    

// @FXML
//    private void existingEventsColumn() throws IOException, SQLException {
//         
//    }
}