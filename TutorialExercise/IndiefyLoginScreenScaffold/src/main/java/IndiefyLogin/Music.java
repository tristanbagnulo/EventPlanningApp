public class Music {
    
    private String album;
    private String artist;
    private String genre;
    private String year;
   // Add the other attributes for the Music List
    
    public Music(String album, String artist, String genre, String year) {
        this.album = album;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
    }

    public Music(String album) {
        this.album = new SimpleStringProperty(album);
        // Complete the constructor
    }
    
   // Add getters for String Properties
    
}
