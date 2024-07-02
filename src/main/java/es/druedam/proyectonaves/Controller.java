package es.druedam.proyectonaves;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller extends Window implements Initializable
{
    @FXML
    private TableView<Alumno> crudTable;

    @FXML
    private BorderPane panelCrud;

    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textCurso;
    @FXML
    private Spinner<Integer> numInvitaciones;

    @FXML
    public TextField textCorreoGmail;
    @FXML
    public TextField textClave;
    @FXML
    public TextField textAsunto;
    @FXML
    public TextArea textContent;

    @FXML
    public void deleteData()
    {
        Dialog newDialog = new Dialog();
        newDialog.setTitle("Eliminar datos");
        newDialog.setContentText("Desea eliminar todos los datos de la base de datos?");
        newDialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
        newDialog.getDialogPane().getButtonTypes().add(ButtonType.NO);

        Optional<ButtonType> result = newDialog.showAndWait();
        if(result.get() == ButtonType.YES)
        {
            System.out.println("Base de datos borrada");
            Conexion.borrarBaseDeDatos();
        }
    }

    @FXML
    public void openPanelCrud()
    {
        panelCrud.setVisible(true);
        rellenarTabla();
    }

    @FXML
    public void crearAlumno()
    {
        Conexion.createAlumno(new Alumno(textCorreo.getText(), textNombre.getText() , textCurso.getText()), numInvitaciones.getValue());
        rellenarTabla();
    }

    @FXML
    public void eliminarAlumno()
    {
        Conexion.deleteAlumno(textCorreo.getText());
        rellenarTabla();
    }

    @FXML
    public void modificarAlumno()
    {
        rellenarTabla();
    }

    @FXML
    public void leerAlumno()
    {
        rellenarTabla();
    }

    @FXML
    public void enviarMail()
    {
        EnviarMail.recogerDatosYEnviar(textCorreoGmail.getText(), textClave.getText(), textAsunto.getText(), textContent.getText());
    }


    public void rellenarTabla()
    {
        crudTable.getColumns().clear();
        crudTable.getItems().clear();

        TableColumn correo = new TableColumn("correo");
        TableColumn nombre_alumno = new TableColumn("nombre");
        TableColumn curso = new TableColumn("curso");
        TableColumn codigo = new TableColumn("codigo");
        TableColumn validado = new TableColumn("validado");
        TableColumn enviado = new TableColumn("enviado");
        TableColumn fecha_validacion = new TableColumn("fecha_validacion");
        crudTable.getColumns().addAll(fecha_validacion,enviado,validado,codigo,curso,nombre_alumno,correo);

        ArrayList<Alumno> listaInvitacion= Conexion.leerAlumnos();

        ObservableList<Alumno> data = FXCollections.observableArrayList(listaInvitacion);

        correo.setCellValueFactory(new PropertyValueFactory<Alumno,String>("correo"));
        nombre_alumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre_alumno"));
        curso.setCellValueFactory(new PropertyValueFactory<Alumno,String>("curso"));
        codigo.setCellValueFactory(new PropertyValueFactory<Alumno,String>("codigo"));
        validado.setCellValueFactory(new PropertyValueFactory<Alumno,Boolean>("validado"));
        enviado.setCellValueFactory(new PropertyValueFactory<Alumno,Boolean>("enviado"));
        fecha_validacion.setCellValueFactory(new PropertyValueFactory<Alumno,String>("fecha_validacion"));

        crudTable.setItems(data);
    }

    @FXML
    public void seleccionarFichero()
    {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(this);
        CSVParser.ParseFile(selectedFile);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        valueFactory.setValue(0);
        numInvitaciones.setValueFactory(valueFactory);
    }


    public TextField getTextCorreoGmail() {
        return textCorreoGmail;
    }

    public TextField getTextClave() {
        return textClave;
    }
}
