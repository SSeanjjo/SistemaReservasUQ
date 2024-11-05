package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PerfilControlador implements Initializable {
    private void loadUserData() { /* Load user data into fields */ }
    private void initializeTableColumns() { /* Set up TableView columns */ }
    Sesion sesion = Sesion.getInstancia();
    Persona persona = sesion.getPersona();
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    @FXML
    public TextField txtCedula;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtCorreoInstitucional;
    @FXML
    private ComboBox<TipoPersona> cbTipoPersona;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmPass;
    @FXML
    private TextField txtPasswordShow;
    @FXML
    private TextField txtConfirmPassShow;
    @FXML
    private CheckBox checkShowPass;

    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> colidInstalacion;
    @FXML
    private TableColumn<Reserva, String> colCedula;
    @FXML
    private TableColumn<Reserva, String> diaReserva;
    @FXML
    private TableColumn<Reserva, String> horaReserva;


    private ObservableList<Reserva> observableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (persona != null) {
            txtCedula.setText(persona.getCedula());
            txtNombre.setText(persona.getNombre());
            txtCorreoInstitucional.setText(persona.getCorreoInstitucional());
            cbTipoPersona.setValue(persona.getTipoPersona());
        }

        colidInstalacion.setCellValueFactory(new PropertyValueFactory<>("idInstalacion"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        diaReserva.setCellValueFactory(new PropertyValueFactory<>("diaReserva"));
        horaReserva.setCellValueFactory(new PropertyValueFactory<>("horaReserva"));

//        // Set up Table Columns with Reserva fields
//        colidInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdInstalacion()));
//        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
//        diaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaReserva().toString()));
//        horaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraReserva()));

        // Initialize observable list
        observableList = FXCollections.observableArrayList();
        tablaReservas.setItems(observableList);

        actualizarTabla();
    }

    public void actualizarTabla() {
        if (persona != null) {
            List<Reserva> reservasFiltradas = reservasUQ.getListaReservas().stream()
                    .filter(reserva -> reserva.getCedula().equals(persona.getCedula()))
                    .collect(Collectors.toList());
            observableList.setAll(reservasFiltradas);
        }
    }

    public void OnMostrarPassword(){
        boolean mostrar = checkShowPass.isSelected();
        txtPassword.setVisible(!mostrar);
        txtConfirmPass.setVisible(!mostrar);
        txtPasswordShow.setVisible(mostrar);
        txtConfirmPassShow.setVisible(mostrar);
    }
    public void cargarReservas(ActionEvent actionEvent) {
        reservasUQ.getListaReservas().stream()
                .filter(reserva -> reserva.getCedula().equals(txtCedula.getText()))
                .forEach(System.out::println);
    }


}
