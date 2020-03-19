public class Music {
    
    private String album;
    private String artist;
    private String genre;
    private String year;
   // Add the other attributes for the Music List
    
    public Music(String album, String artist, String genre, String year) {
<<<<<<< HEAD
        this.album = album; 
        this.artist = artist; 
=======
        this.album = album;
        this.artist = artist;
>>>>>>> b10f87031e8c55f1edcd82f52147d85a4b7e4e9d
        this.genre = genre;
        this.year = year;
    }

    public Music(String album) {
        this.album = new SimpleStringProperty(album);
        // Complete the constructor
    }
    //What's good.
   // Add getters for String Properties
    public StringPropery getAlbum(){
        return album;
    }
    
    public void setAlbum (StringProperty album){
        this.album = album;
    }
    
    public String getArtist(){
        return artist;
    }
    
    public void setArtist(String artist){
        this.artist = artist;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public void setGenre(String genre){
        this.genre = genre;
    }
    
    public String getYear(){
        return year;
    }
    
    public void setYear(String year){
        this.year = year;
    }
}
