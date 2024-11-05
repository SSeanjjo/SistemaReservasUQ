package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class RegistrarReservaControlador implements Initializable {

    @FXML
    public TextField txtidInstalacion;
    @FXML
    public TextField txtCedula;
    @FXML
    public ComboBox<String>cbTipoInstalaciones;
    @FXML
    public DatePicker dpDiaReserva;
    @FXML
    public ComboBox<String> cbhoraReserva;

    ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    Sesion sesion = Sesion.getInstancia();
    Persona persona = sesion.getPersona();
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    private ObservableList<Horario> observableList;


    public void registrarReserva(ActionEvent actionEvent) {
        try {
            String tipoInstalacion = cbTipoInstalaciones.getValue();
            String idInstalacion = txtidInstalacion.getText();
            String cedula = txtCedula.getText();
            LocalDate diaReserva = dpDiaReserva.getValue();
            String horaReserva = cbhoraReserva.getValue();

            Reserva reserva = reservasUQ.crearReserva(tipoInstalacion, idInstalacion, cedula, diaReserva, horaReserva);
            limpiarFormularioReserva();

            ControladorPrincipal.cerrarVentana(txtCedula);
            controladorPrincipal.navegarVentana("/reservacion.fxml", "Reservacion");

        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormularioReserva() {
        txtidInstalacion.setText("");
        txtCedula.setText("");
        dpDiaReserva.setValue(null);
        cbhoraReserva.setValue(null);
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    public void cargarIntalaciones(ActionEvent actionEvent) {
        reservasUQ.getListaInstalaciones().stream()
                .filter(instalacion -> instalacion.getNombre().equals(cbTipoInstalaciones.getValue()))
//                .filter(reserva -> reserva.getCedula().equals(txtCedula.getText()))
                .forEach(System.out::println);
    }

    public void cargarReservas(ActionEvent actionEvent) {
        reservasUQ.getListaReservas().stream()
                .filter(reserva -> reserva.getCedula().equals(txtCedula.getText()))
                .forEach(System.out::println);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Horario horario = new Horario();
        cbhoraReserva.setItems(FXCollections.observableArrayList(horario.generarHorarios()));
        cbTipoInstalaciones.setItems(FXCollections.observableArrayList(reservasUQ.getListaInstalaciones().toString()));
//        cbTipoInstalaciones.setItems(FXCollections.observableArrayList(reservasUQ.getListaInstalaciones().toString()));
//        observableList = FXCollections.observableArrayList();
    }

}
