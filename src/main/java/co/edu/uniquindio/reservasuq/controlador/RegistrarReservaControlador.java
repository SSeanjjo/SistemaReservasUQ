package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.modelo.instalaciones.Instalaciones;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegistrarReservaControlador implements Initializable {
    @FXML
    private TextField txtidInstalacion;
    @FXML
    private TextField txtCedula;
    @FXML
    private ComboBox<String>cbTipoInstalaciones;
    @FXML
    private DatePicker dpDiaReserva;
    @FXML
    private ComboBox<String> cbhoraReserva;
    @FXML
    private TableView<Instalaciones> tablaInstalaciones;
    @FXML
    private TableColumn<Reserva, String> colNombreInstalacion;
    @FXML
    private TableColumn<Reserva, String> colcodigoIdInstalacion;
    @FXML
    private  TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> colidInstalacion;
    @FXML
    private TableColumn<Reserva, String> colCedula;
    @FXML
    private TableColumn<Reserva, String> diaReserva;
    @FXML
    private TableColumn<Reserva, String> horaReserva;

    ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    Sesion sesion = Sesion.getInstancia();
    Persona persona = sesion.getPersona();
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    private ObservableList<Horario> observableListHoras;
    private ObservableList<Instalaciones> observableListInstalaciones;
    private ObservableList<Reserva> observableListReservas;

    public void crearReserva(ActionEvent actionEvent) {
        try {
            String tipoInstalacion = cbTipoInstalaciones.getValue();
            String idInstalacion = txtidInstalacion.getText();
            String cedula = txtCedula.getText();
            LocalDate diaReserva = dpDiaReserva.getValue();
            String horaReserva = cbhoraReserva.getValue();

            Reserva reserva = reservasUQ.crearReserva(tipoInstalacion, String.valueOf(idInstalacion), cedula, diaReserva, horaReserva);
            observableListReservas.add(reserva);
            limpiarFormularioReserva();

            controladorPrincipal.navegarVentana("/reservacion.fxml", "Reservacion");
            controladorPrincipal.cerrarVentana(txtCedula);

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

    private void actualizarTabla() {
        observableListReservas.setAll(reservasUQ.listarTodasReservas());
        observableListInstalaciones.setAll(reservasUQ.getListaInstalaciones());
//        observableListInstalaciones.setAll(reservasUQ.getListaInstalaciones());
        tablaReservas.setItems(observableListReservas);
        tablaInstalaciones.setItems(observableListInstalaciones);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colidInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdInstalacion().toString()));
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        diaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaReserva().toString()));
        horaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraReserva()));



        cbhoraReserva.setItems(FXCollections.observableArrayList(new Horario().generarHorarios()));

        cbTipoInstalaciones.setItems(FXCollections.observableArrayList(
                reservasUQ.getListaInstalaciones().stream()
                        .map(Instalaciones::getNombre)
                        .collect(Collectors.toList())
        ));

        observableListInstalaciones = FXCollections.observableArrayList();
        observableListInstalaciones.setAll(new ArrayList<>());
        tablaInstalaciones.setItems(observableListInstalaciones);

        // Initialize the observable lists with data
        observableListReservas = FXCollections.observableArrayList(reservasUQ.listarTodasReservas());
//        observableListInstalaciones = FXCollections.observableArrayList(reservasUQ.getListaInstalaciones());

        // Set each TableView to the correct list
        tablaReservas.setItems(observableListReservas);
//        tablaInstalaciones.setItems(observableListInstalaciones);

        actualizarTabla();
    }

    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        cbhoraReserva.setItems(FXCollections.observableArrayList(new Horario().generarHorarios()));

        cbTipoInstalaciones.setItems(FXCollections.observableArrayList(
                reservasUQ.getListaInstalaciones().stream()
                        .map(Instalaciones::getNombre)
                        .collect(Collectors.toList())
        ));


        observableListReservas = FXCollections.observableArrayList(reservasUQ.listarTodasReservas());
        observableListInstalaciones = FXCollections.observableArrayList(reservasUQ.getListaInstalaciones());

        tablaReservas.setItems(observableListReservas);
        tablaInstalaciones.setItems(observableListInstalaciones);
        actualizarTabla();
    }*/

    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        Horario horario = new Horario();

        cbhoraReserva.setItems(FXCollections.observableArrayList(horario.generarHorarios()));
        cbTipoInstalaciones.setItems(FXCollections.observableArrayList(reservasUQ.getListaInstalaciones().toString()));

        observableListInstalaciones = FXCollections.observableArrayList();
        observableListInstalaciones.setAll(new ArrayList<>());
        tablaInstalaciones.setItems(observableListInstalaciones);

        observableListHoras = FXCollections.observableArrayList();
        observableListHoras.setAll(new ArrayList<>());
        tablaReservas.setItems(observableListReservas);

        actualizarTabla();
    }*/
}
