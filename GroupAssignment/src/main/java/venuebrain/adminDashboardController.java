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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
//    Initialise the TableView as FXML variables
    TableView dashboard = new TableView();
   
//    Initialise the TableColumns as FXML variables
    @FXML
   TableColumn<Event, String> eventNameCol = new TableColumn<>("Name");
   
    @FXML
   TableColumn<Event, String> eventLocationCol = new TableColumn<>("Location");
    
     @FXML
    public void initialize() {
//     eventNameCol.setCellValueFactory(cellData -> cellData.getValue().getEventName());
//     eventLocationCol.setCellValueFactory(cellData -> cellData.getValue().getLocation());
     
      dashboard.setItems(getEventListData());
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
                    new Event(rs.getString("Name"), rs.getString("Location"))
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
    private void btnBackWasClicked() throws IOException, SQLException {
        
        App.setLoginRoot("adminLogin");  
    }
    

// @FXML
//    private void existingEventsColumn() throws IOException, SQLException {
//         
//    }
}