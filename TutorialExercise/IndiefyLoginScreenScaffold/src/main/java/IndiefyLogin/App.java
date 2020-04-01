/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.sql.SQLException;


//written by Neil Matani (z5162753) for Lab4 INFS2605 20t1

public class App extends Application {
    
    /* What should happen when you first start the program? */
   private static Scene scene;

  //@Override
   /* public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException{
        //Initiate the database
        
        loadDatabase();
        
        /* Add the four lines of code to load an FXML into a scene, attach it to a stage, 
        and show the stage 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScreen.fxml"));
        
        Scene scene = new Scene(root, 600,400);
        stage.setTitle("Indiefy");
        stage.setScene(scene);
        stage.show();
      
    }*/
  
    
      public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        loadDatabase();
          
        scene = new Scene(loadFXML("/fxml/LoginScreen"), 640, 480);
        stage.setScene(scene);
        stage.show();
        
     
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
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
