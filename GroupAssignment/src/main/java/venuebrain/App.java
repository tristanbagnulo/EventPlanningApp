package venuebrain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    static Scene loginScene;
    static Scene dashboard;
    
    static Stage window;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        window = stage;
        
        setLoginRoot("primary");
        
        DatabaseManager.createSchema();
    }

    public static void setLoginRoot(String fxml) throws IOException {
        
        loginScene = new Scene(loadFXML(fxml), 640, 480);
        window.setScene(loginScene);
        window.show();
        loginScene.setRoot(loadFXML(fxml));   
    }
    
     public static void setDashboardRoot(String fxml, double x, double y) throws IOException {
        
        dashboard = new Scene(loadFXML(fxml), x, y);
        window.setScene(dashboard);
        window.show();
        dashboard.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}