package es.druedam.proyectonaves;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private TableView<Invitacion> crudTable;

    @FXML
    private VBox panelCrud;

    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textCurso;
    @FXML
    private Spinner<Integer> numInvitaciones;


    @FXML
    public void deleteData()
    {
        Dialog newDialog = new Dialog();
        newDialog.setTitle("Eliminar datos");
        newDialog.setContentText("Desea eliminar todos los datos de la base de datos?");
        newDialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
        newDialog.getDialogPane().getButtonTypes().add(ButtonType.NO);
        newDialog.show();
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
        DatabaseManager.crearAlumno(new Invitacion(textCorreo.getText(), textNombre.getText(), textCurso.getText()), numInvitaciones.getValue());
    }

    @FXML
    public void eliminarAlumno()
    {
        DatabaseManager.eliminarAlumno(textCorreo.getText());
    }

    @FXML
    public void modificarAlumno()
    {
        //DatabaseManager.modificarAlumno();
    }

    @FXML
    public void leerAlumno()
    {
        // DatabaseManager.leerAlumnos();
    }


    public void rellenarTabla()
    {
        TableColumn correo = new TableColumn("correo");
        TableColumn nombre_alumno = new TableColumn("nombre");
        TableColumn curso = new TableColumn("curso");
        TableColumn codigo = new TableColumn("codigo");
        TableColumn validado = new TableColumn("validado");
        TableColumn enviado = new TableColumn("enviado");
        TableColumn fecha_validacion = new TableColumn("fecha_validacion");
        crudTable.getColumns().addAll(fecha_validacion,enviado,validado,codigo,curso,nombre_alumno,correo);

        ArrayList<Invitacion> listaInvitacion;
        listaInvitacion = DatabaseManager.leerAlumnos();

        ObservableList<Invitacion> data = FXCollections.observableArrayList(listaInvitacion);

        correo.setCellValueFactory(new PropertyValueFactory<Invitacion,String>("correo"));
        nombre_alumno.setCellValueFactory(new PropertyValueFactory<Invitacion,String>("nombre"));
        curso.setCellValueFactory(new PropertyValueFactory<Invitacion,String>("curso"));
        codigo.setCellValueFactory(new PropertyValueFactory<Invitacion,String>("codigo"));
        validado.setCellValueFactory(new PropertyValueFactory<Invitacion,Boolean>("validado"));
        enviado.setCellValueFactory(new PropertyValueFactory<Invitacion,Boolean>("enviado"));
        fecha_validacion.setCellValueFactory(new PropertyValueFactory<Invitacion,String>("fecha_validacion"));

        crudTable.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        valueFactory.setValue(0);
        numInvitaciones.setValueFactory(valueFactory);
    }
}
