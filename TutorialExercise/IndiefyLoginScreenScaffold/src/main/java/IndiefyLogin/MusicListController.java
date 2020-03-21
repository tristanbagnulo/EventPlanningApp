package IndiefyLogin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class MusicListController{
    
    @FXML
    // Initialise the TableView as FXML variables
    TableView musicListTable = new TableView();
   
    // Initialise the TableColumns as FXML variables
   TableColumn<Music, String> albumCol = new TableColumn<>("Album");
   TableColumn<Music, String> artistCol = new TableColumn<>("Artist");
   TableColumn<Music, String> genreCol = new TableColumn<>("Genre");
   TableColumn<Music, String> yearCol = new TableColumn<>("Year");
   
    // Initialise the database class
   Database d;
    
    // What annotation do you need here? 
   @FXML
    public void initialize() {
       /* 
        Initialise the TableView by setting the cell value factory
        Read this page for help: https://code.makery.ch/library/javafx-tutorial/part2/
        */
       albumCol.setCellValueFactory(cellData -> cellData.getValue().getAlbum());
       artistCol.setCellValueFactory(cellData -> cellData.getValue().getArtist());
       genreCol.setCellValueFactory(cellData -> cellData.getValue().getGenre());
       yearCol.setCellValueFactory(cellData -> cellData.getValue().getYear());
      
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
