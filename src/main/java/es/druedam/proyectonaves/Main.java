package es.druedam.proyectonaves;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Proyecto Naves");
        stage.setScene(scene);
        stage.show();
        QRGenerator hola = new QRGenerator();
        hola.generarQR();
    }

    public static void main(String[] args) {
        launch();
    }
}