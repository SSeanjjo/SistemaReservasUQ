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
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegistrarReservaControlador implements Initializable {
    @FXML
    private TextField txtidInstalacion;
    @FXML
    private TextField txtCedula;
    @FXML
    private ComboBox<String> cbTipoInstalaciones;
    @FXML
    private DatePicker dpDiaReserva;
    @FXML
    private ComboBox<String> cbhoraReserva;
    @FXML
    private TextField txtCosto;
    @FXML
    private TableView<Instalaciones> tablaInstalaciones;
    @FXML
    private TableColumn<Reserva, String> colNombreInstalacion;
    @FXML
    private TableColumn<Reserva, String> colcodigoIdInstalacion;
    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> colidInstalacion;
    @FXML
    private TableColumn<Reserva, String> colNombreReservaInstalacion;
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
/*
    public void crearReserva(ActionEvent actionEvent) {
        try {
            String tipoInstalacion = cbTipoInstalaciones.getValue();
            String idInstalacion = txtidInstalacion.getText();
            String cedula = txtCedula.getText();
            LocalDate diaReserva = dpDiaReserva.getValue();
            String horaReserva = cbhoraReserva.getValue();

            if (tipoInstalacion == null || idInstalacion.isEmpty() || cedula.isEmpty() || diaReserva == null || horaReserva == null) {
                mostrarAlerta("Todos los campos deben estar completos para crear la reserva.", Alert.AlertType.WARNING);
                return;
            }


            double costo = reservasUQ.calcularCosto(tipoInstalacion, persona.getTipoPersona().name(), cedula);

            Reserva reserva = reservasUQ.crearReserva(tipoInstalacion, String.valueOf(idInstalacion), cedula, diaReserva, horaReserva, costo);
            observableListReservas.add(reserva);
            limpiarFormularioReserva();
            actualizarTabla();
            mostrarAlerta("Reserva creada exitosamente.", Alert.AlertType.INFORMATION);
            controladorPrincipal.navegarVentana("/reservacion.fxml", "Reservacion");
            controladorPrincipal.cerrarVentana(txtCedula);

        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }*/

    public void crearReserva(ActionEvent actionEvent) {
        try {
            String tipoInstalacion = cbTipoInstalaciones.getValue();
            String idInstalacion = txtidInstalacion.getText();
            String cedula = txtCedula.getText();
            LocalDate diaReserva = dpDiaReserva.getValue();
            String horaReserva = cbhoraReserva.getValue();

            if (tipoInstalacion == null || idInstalacion.isEmpty() || cedula.isEmpty() || diaReserva == null || horaReserva == null) {
                mostrarAlerta("Todos los campos deben estar completos para crear la reserva.", Alert.AlertType.WARNING);
                return;
            }

            double costo = reservasUQ.calcularCosto(tipoInstalacion, cedula);
            txtCosto.setText(String.format("%.2f", costo)); // Automatically set cost in UI

            Reserva reserva = reservasUQ.crearReserva(tipoInstalacion, idInstalacion, cedula, diaReserva, horaReserva);
            observableListReservas.add(reserva);
            limpiarFormularioReserva();
            actualizarTabla();
            mostrarAlerta("Reserva creada exitosamente.", Alert.AlertType.INFORMATION);
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

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
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

    public void cargarHorasDisponibles() {
        String selectedInstalacion = cbTipoInstalaciones.getValue();

        if (selectedInstalacion != null) {
            Instalaciones instalacion = reservasUQ.getListaInstalaciones().stream()
                    .filter(inst -> inst.getNombre().equals(selectedInstalacion))
                    .findFirst()
                    .orElse(null);

            if (instalacion != null) {
                LocalDateTime horaInicio = instalacion.getHoraInicio();
                LocalDateTime horaFin = instalacion.getHoraFin();

                ObservableList<String> horasDisponibles = FXCollections.observableArrayList();
                LocalTime hora = horaInicio.toLocalTime();

                while (!hora.isAfter(horaFin.toLocalTime())) {
                    horasDisponibles.add(hora.toString());
                    hora = hora.plusHours(1); // Adjust as needed for different intervals
                }

                cbhoraReserva.setItems(horasDisponibles);
            }
        }
    }

   /* public void actualizarCosto() {
        try {
            String tipoInstalacion = cbTipoInstalaciones.getValue();
            String cedula = txtCedula.getText();

            if (tipoInstalacion != null && cedula != null && !cedula.isEmpty()) {
                Persona persona = sesion.getPersona();
                if (persona == null) {
                    mostrarAlerta("Usuario no autenticado. Inicie sesión nuevamente.", Alert.AlertType.ERROR);
                    return;
                }
                double costo = reservasUQ.calcularCosto(tipoInstalacion, persona.getTipoPersona().name(), cedula);
                txtCosto.setText(String.valueOf(costo));
            }
        } catch (Exception e) {
            mostrarAlerta("Error al calcular el costo: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }*/

    public void actualizarCosto() {
        try {
            String tipoInstalacion = cbTipoInstalaciones.getValue();
            String cedula = txtCedula.getText();

            if (tipoInstalacion != null && cedula != null && !cedula.isEmpty()) {
                double costo = reservasUQ.calcularCosto(tipoInstalacion, cedula);
                txtCosto.setText(String.format("%.2f", costo));
            }
        } catch (Exception e) {
            mostrarAlerta("Error al calcular el costo: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void cargarCostoReserva() {
        String tipoInstalacion = cbTipoInstalaciones.getValue();
        String cedula = txtCedula.getText();

        if (tipoInstalacion != null && !cedula.isEmpty()) {
            try {
                Persona persona = reservasUQ.obtenerPersona(cedula).orElseThrow(() -> new Exception("Persona no encontrada"));
                double costo = reservasUQ.calcularCosto(tipoInstalacion, cedula);
                txtCosto.setText(String.valueOf(costo));
            } catch (Exception e) {
                mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    private void calcularYMostrarCosto() {
        try {
            String tipoInstalacion = cbTipoInstalaciones.getValue();
            String cedula = txtCedula.getText();

            // Ensure both fields are not empty before calculating the cost
            if (tipoInstalacion != null && !tipoInstalacion.isEmpty() && cedula != null && !cedula.isEmpty()) {
                double costo = reservasUQ.calcularCosto(tipoInstalacion, cedula);
                txtCosto.setText(String.format("%.2f", costo)); // Format to two decimal places
            } else {
                txtCosto.setText(""); // Clear cost if required fields are missing
            }
        } catch (Exception e) {
            mostrarAlerta("Error al calcular el costo: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        colNombreInstalacion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colcodigoIdInstalacion.setCellValueFactory(new PropertyValueFactory<>("codigoId"));
        colidInstalacion.setCellValueFactory(new PropertyValueFactory<>("idInstalacion"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        diaReserva.setCellValueFactory(new PropertyValueFactory<>("diaReserva"));
        horaReserva.setCellValueFactory(new PropertyValueFactory<>("horaReserva"));*/

        colidInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdInstalacion().toString()));
        colNombreReservaInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreInstalacion(observableListInstalaciones)));
      //  colNombreReservaInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreInstalacion()));
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        diaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaReserva().toString()));
        horaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraReserva()));

        cbhoraReserva.setItems(FXCollections.observableArrayList(new Horario().generarHorarios()));

        cbTipoInstalaciones.setItems(FXCollections.observableArrayList(
                reservasUQ.getListaInstalaciones().stream()
                        .map(Instalaciones::getNombre)
                        .collect(Collectors.toList())
        ));
        /*
        observableListInstalaciones = FXCollections.observableArrayList();
        observableListInstalaciones.setAll(new ArrayList<>());
        tablaInstalaciones.setItems(observableListInstalaciones);

        // Initialize the observable lists with data
        observableListReservas = FXCollections.observableArrayList(reservasUQ.listarTodasReservas());
//        observableListInstalaciones = FXCollections.observableArrayList(reservasUQ.getListaInstalaciones());

        // Set each TableView to the correct list
        tablaReservas.setItems(observableListReservas);
//        tablaInstalaciones.setItems(observableListInstalaciones);
*/

        // Initialize reservations and installations
        observableListReservas = FXCollections.observableArrayList(reservasUQ.listarTodasReservas());
        observableListInstalaciones = FXCollections.observableArrayList(reservasUQ.getListaInstalaciones());

        // Populate TableViews
        tablaReservas.setItems(observableListReservas);
        tablaInstalaciones.setItems(observableListInstalaciones);


        // Event handler for selecting an instalacion
        cbTipoInstalaciones.setOnAction(event -> {
            cargarHorasDisponibles();
            String selectedInstalacion = cbTipoInstalaciones.getValue();
            Instalaciones instalacion = reservasUQ.getListaInstalaciones().stream()
                    .filter(inst -> inst.getNombre().equals(selectedInstalacion))
                    .findFirst()
                    .orElse(null);
            if (instalacion != null) {
                txtidInstalacion.setText(instalacion.getId());
            }
        });

        // Listener for automatic cost calculation when cedula changes
        txtCedula.textProperty().addListener((observable, oldValue, newValue) -> {
            calcularYMostrarCosto();
        });

        cbTipoInstalaciones.setItems(FXCollections.observableArrayList(
                reservasUQ.getListaInstalaciones().stream()
                        .map(Instalaciones::getNombre)
                        .collect(Collectors.toList())
        ));

        cbTipoInstalaciones.valueProperty().addListener((obs, oldVal, newVal) -> actualizarCosto());
        txtCedula.textProperty().addListener((obs, oldVal, newVal) -> actualizarCosto());

        actualizarTabla();


        /*
        // Populate ComboBox for instalaciones
        cbTipoInstalaciones.setItems(FXCollections.observableArrayList(
                reservasUQ.getListaInstalaciones().stream()
                        .map(Instalaciones::getNombre)
                        .collect(Collectors.toList())
        ));
        cargarHorasDisponibles();

        // Listener for automatic cost calculation when installation type changes
        cbTipoInstalaciones.valueProperty().addListener((observable, oldValue, newValue) -> {
            calcularYMostrarCosto();
        });

        actualizarTabla();
        */
    }

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

