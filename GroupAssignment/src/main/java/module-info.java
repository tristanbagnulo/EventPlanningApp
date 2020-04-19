module venuebrain {
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
	requires itextpdf;
    
    requires java.sql;
    requires java.base;

    opens venuebrain to javafx.fxml;
    exports venuebrain;
}