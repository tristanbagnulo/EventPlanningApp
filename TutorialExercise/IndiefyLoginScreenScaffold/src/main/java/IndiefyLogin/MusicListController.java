package IndiefyLogin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class MusicListController {
    
    @FXML
    // Initialise the TableView as FXML variables
    TableView musicListTable = new TableView();
   
    // Initialise the TableColumns as FXML variables
   TableColumn<String, Music> albumCol = new TableColumn<>("Album");
   TableColumn<String, Music> artistCol = new TableColumn<>("Artist");
   TableColumn<String, Music> genreCol = new TableColumn<>("Genre");
   TableColumn<String, Music> yearCol = new TableColumn<>("Year");
   
    // Initialise the database class
    
    // What annotation do you need here?
    public void initialize() {
       /* 
        Initialise the TableView by setting the cell value factory
        Read this page for help: https://code.makery.ch/library/javafx-tutorial/part2/
        */

        // Set the items that should be contained in the TableView
    }

    private ObservableList<Music> getMusicListData() {
        List<Music> musicListToReturn = new ArrayList<>();
        try {
            
            // Get the music list from the database
            
            while(rs.next()) {
                musicListToReturn.add(
                  // create a new music object
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
       return FXCollections.observableArrayList(musicListToReturn);
    }
    
}
