/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class App extends Application {
    
    /* What should happen when you first start the program? */
  
     
    @Override
    public void start(Stage stage) throws Exception {
        //Initiate the database
        
        loadDatabase();
        
        /* Add the four lines of code to load an FXML into a scene, attach it to a stage, 
        and show the stage */
        Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
        
        Scene scene = new Scene(root, 200,150);
        stage.setTitle("Indiefy Login");
        stage.setScene(scene);
        stage.show();
      
    }

    public static void main(String[] args) {
        //What should go in here?
        launch(args);
    }

    private void loadDatabase() throws ClassNotFoundException, SQLException{
       //What method should we be calling from the Database class?
          Database.openConnection();
          Database.createLoginTable();
    }
    
  }
