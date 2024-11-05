package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
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
import java.util.ResourceBundle;

public class RegistrarReservaControlador implements Initializable {
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    @FXML
    public TextField txtidInstalacion;
    @FXML
    public TextField txtCedula;
    @FXML
    public DatePicker dpDiaReserva;
    @FXML
    public ComboBox<Horario> cbhoraReserva;


    private ObservableList<Horario> observableList;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Horario horario = new Horario();
//        cbTipoPersona.getItems().addAll(TipoPersona.values());
        observableList = FXCollections.observableArrayList();
//        cbhoraReserva.setItems(FXCollections.observableArrayList(horario.getHorarios()));
    }

}
