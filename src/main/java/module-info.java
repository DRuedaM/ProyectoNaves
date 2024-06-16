module es.druedam.proyectonaves {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires com.google.zxing;
    requires jakarta.mail;
    requires org.eclipse.angus.mail;
    requires mysql.connector.j;
    requires com.google.gson;
    requires java.net.http;
    requires jakarta.activation;


    opens es.druedam.proyectonaves to javafx.fxml, com.google.gson;
    exports es.druedam.proyectonaves;
}