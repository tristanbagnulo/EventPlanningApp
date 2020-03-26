package venuebrain;

import java.io.IOException;
import javafx.fxml.FXML;

public class adminLoginController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}