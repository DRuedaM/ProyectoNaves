module es.druedam.proyectonaves {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.druedam.proyectonaves to javafx.fxml;
    exports es.druedam.proyectonaves;
}