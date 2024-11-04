package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.collections.FXCollections;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        cbTipoPersona.getItems().addAll(TipoPersona.values());
        cbTipoPersona.setItems(FXCollections.observableArrayList(TipoPersona.values()));
    }

}
