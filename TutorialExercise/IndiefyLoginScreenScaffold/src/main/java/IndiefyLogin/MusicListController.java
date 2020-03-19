public class MusicListController {
    
    // Initialise the TableView as FXML variables

    // Initialise the TableColumns as FXML variables
    
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
