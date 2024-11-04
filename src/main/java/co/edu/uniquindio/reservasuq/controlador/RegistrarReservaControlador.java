package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarReservaControlador implements Initializable {
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    @FXML
    public TextField txtCedula;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtCorreoInstitucional;
    @FXML
    public TextField txtContrasena;
    @FXML
    private ComboBox<TipoPersona> cbTipoPersona;

    public void registrarPersona(ActionEvent actionEvent){
        try {
            String cedula = txtCedula.getText();
            String nombre = txtNombre.getText();
            String correoInstitucional = txtCorreoInstitucional.getText();
            String contrasena = txtContrasena.getText();
            TipoPersona  tipoPersona = cbTipoPersona.getValue();

            reservasUQ.registrarPersona(cedula, nombre, tipoPersona, correoInstitucional, contrasena);
            limpiarFormularioRegistro();
            mostrarAlerta("Persona registrada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    private void limpiarFormularioRegistro() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtCorreoInstitucional.setText("");
        txtContrasena.setText("");
        cbTipoPersona.setValue(null);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTipoPersona.getItems().addAll(TipoPersona.values());
    }

}
