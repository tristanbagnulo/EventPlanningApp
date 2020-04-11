package venuebrain;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class adminDashboardController {
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
    
    
//     @FXML
//    // Initialise the TableView as FXML variables
//    TableView dashboard = new TableView();
   
    // Initialise the TableColumns as FXML variables
//    @FXML
//   TableColumn<event, Integer> existingColumn = new TableColumn<>("Existing Events");
//    
//    @FXML
//   TableColumn<> createdByColumn = new TableColumn<>("Created by");
    
     @FXML
    protected void initialize() {
     
    }
    
     @FXML
    private void showAbout() throws IOException, SQLException {
       App.showAbout();
    }
    

// @FXML
//    private void existingEventsColumn() throws IOException, SQLException {
//         
//    }
}
