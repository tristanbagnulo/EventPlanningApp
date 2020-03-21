
package IndiefyLogin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Music {
    
    private StringProperty album;
    private StringProperty artist;
    private StringProperty genre;
    private StringProperty year;
   // Add the other attributes for the Music List
    
    public Music() {
       this("","","","");
    }

    public Music(String album, String artist, String genre, String year) {
        this.album = new SimpleStringProperty(album);
        this.artist = new SimpleStringProperty(artist);
        this.genre = new SimpleStringProperty(genre);
        this.year = new SimpleStringProperty(year);
        // Complete the constructor
    }
    
   // Add getters for String Properties
    public StringProperty getAlbum(){
        return album;
    }
    
    public void setAlbum (StringProperty album){
        this.album = album;
    }
    
    public StringProperty getArtist(){
        return artist;
    }
    
    public void setArtist(StringProperty artist){
        this.artist = artist;
    }
    
    public StringProperty getGenre(){
        return genre;
    }
    
    public void setGenre(StringProperty genre){
        this.genre = genre;
    }
    
    public StringProperty getYear(){
        return year;
    }
    
    public void setYear(StringProperty year){
        this.year = year;
    }
}
