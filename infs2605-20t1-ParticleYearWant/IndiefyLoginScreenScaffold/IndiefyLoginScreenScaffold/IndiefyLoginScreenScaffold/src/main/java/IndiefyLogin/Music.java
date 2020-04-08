/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Benny
 */
public class Music {

    private StringProperty album;
    private StringProperty artist;
    private StringProperty genre;
    private StringProperty year;
    // Add the other attributes for the Music List

    public Music() {
        this(null,null,null,null);
    }

    public Music(String album, String artist, String genre, String year) {
        this.album = new SimpleStringProperty(album);
        this.artist = new SimpleStringProperty(artist);
        this.genre = new SimpleStringProperty(genre);
        this.year = new SimpleStringProperty(year);
    }

    // Add getters for String Properties
    public String getAlbum() {
        return album.get();
    }

    public String getArtist() {
        return artist.get();
    }

    public String getGenre() {
        return genre.get();
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty albumProperty() {
        return album;
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public StringProperty yearProperty() {
        return year;
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public void setYear(String year) {
        this.year.set(year);
    }

}
