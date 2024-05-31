module es.druedam.proyectonaves {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires core;
    requires java.sql;


    opens es.druedam.proyectonaves to javafx.fxml;
    exports es.druedam.proyectonaves;
}