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
import javafx.stage.Stage;

public class App extends Application {
    
    /* What should happen when you first start the program? */
    @Override
    public void start(Stage stage) throws Exception {
        //Initiate the database
       Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
       Scene scene = new Scene(root);
       stage.setTitle("Screen with FXML");
       stage.setScene(scene);
       stage.show();
       
        /* Add the four lines of code to load an FXML into a scene, attach it to a stage, 
        and show the stage */
    }

    public static void main(String[] args) {
      launch(args);
      
    //What should go in here?
    }
	//nothing

    private void loadDatabase() {
       //What method should we be calling from the Database class?
       Database.createLoginTable();
    }
    
}
