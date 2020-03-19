public class Music {
    
    private StringProperty album;
    private String artist;
    private String genre;
    private String year;
   // Add the other attributes for the Music List
    
    public Music() {
        this("","","","");
    }

    public Music(String album) {
        this.album = new SimpleStringProperty(album);
        // Complete the constructor
    }
    
   // Add getters for String Properties
    
}
