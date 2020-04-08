/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import static IndiefyLogin.Database.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Benny
 */
public class MusicListController {

    @FXML
    Button back;
    // Initialise the TableView as FXML variables
    @FXML
    TableView<Music> musicTableView;
    // Initialise the TableColumns as FXML variables
    @FXML
    TableColumn<Music, String> albumColumn;
    @FXML
    TableColumn<Music, String> artistColumn;
    @FXML
    TableColumn<Music, String> genreColumn;
    @FXML
    TableColumn<Music, String> yearColumn;

    // Initialise the database class
    Database d = new Database();
    PageSwitchHelper pSwitch = new PageSwitchHelper();

    //What annotation do you need here?
    @FXML
    public void initialize() {
        /* 
        Initialise the TableView by setting the cell value factory
        Read this page for help: https://code.makery.ch/library/javafx-tutorial/part2/
         */
        albumColumn.setCellValueFactory(
                cellData -> cellData.getValue().albumProperty());
        artistColumn.setCellValueFactory(
                cellData -> cellData.getValue().artistProperty());
        genreColumn.setCellValueFactory(
                cellData -> cellData.getValue().genreProperty());
        yearColumn.setCellValueFactory(
                cellData -> cellData.getValue().yearProperty());

        // Set the items that should be contained in the TableView
        musicTableView.setItems(this.getMusicListData());
    }

    private ObservableList<Music> getMusicListData() {
        List<Music> musicListToReturn = new ArrayList<>();
        try {
            // Get the music list from the database
            Database.openConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM MUSIC;");
            
            while (rs.next()) {
                musicListToReturn.add(
                        new Music(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4))
                // create a new music object
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(musicListToReturn);
    }


}