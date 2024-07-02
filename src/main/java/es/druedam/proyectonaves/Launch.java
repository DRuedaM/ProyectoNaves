package es.druedam.proyectonaves;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launch extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CRUD.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Proyecto Naves");
        stage.setScene(scene);
        stage.show();
    }
}
