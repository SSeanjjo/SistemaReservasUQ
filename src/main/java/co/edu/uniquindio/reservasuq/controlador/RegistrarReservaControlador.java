package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrarReservaControlador implements Initializable {
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    @FXML
    public TextField txtidInstalacion;
    @FXML
    public TextField txtCedula;
    @FXML
    private DatePicker dpDiaCita;
    @FXML
    private ComboBox<String> cbHoraCita;

    public void generarReserva(ActionEvent actionEvent) {
    try {
        String idInstalacion = txtidInstalacion.getText();
        String cedula = txtCedula.getText();
        LocalDate diaCita = dpDiaCita.getValue();
        String horaCita = cbHoraCita.getValue();

        reservasUQ.crearReserva(idInstalacion, cedula, diaCita, horaCita);
        limpiarFormulario();
        mostrarAlerta("Reserva generada correctamente", Alert.AlertType.INFORMATION);

    } catch (Exception e){
        mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);}
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    private void limpiarFormulario() {
        txtidInstalacion.setText("");
        txtCedula.setText("");
        dpDiaCita.setValue(null);
        cbHoraCita.setValue(null);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Horario horario = new Horario(); // Instancia de la clase Horario
        cbHoraCita.setItems(FXCollections.observableArrayList(horario.getHorarios())); // Obtiene los horarios y los asigna
    }



}
