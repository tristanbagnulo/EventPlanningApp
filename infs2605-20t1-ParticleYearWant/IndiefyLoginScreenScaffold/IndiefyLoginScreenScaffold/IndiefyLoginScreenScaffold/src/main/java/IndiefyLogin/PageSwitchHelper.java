/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Benny
 */
/* DO NOT ALTER THIS CLASS */
public class PageSwitchHelper {
    
    public void switcher(ActionEvent event, String page) throws IOException {
        System.out.println("Switching pages");
        Parent parent = FXMLLoader.load(getClass().getResource(page));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void switcher(MouseEvent event, String page) throws IOException {
        System.out.println("Switching pages");
        Parent parent = FXMLLoader.load(getClass().getResource(page));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

