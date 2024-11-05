package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.security.PublicKey;
import java.util.ResourceBundle;


public class PerfilControlador implements Initializable {
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    @FXML
    private MenuItem btnEditarDatos;

    @FXML
    private MenuItem btnCerrarsesion;

    @FXML
    private Label lblName;

    @FXML
    private TableView<?> tablaReservas;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> horaColumn;

    @FXML
    private TableColumn<?, ?> fechaColumn;

    @FXML
    private TableColumn<?, ?> instalacionColumn;

    @FXML
    private Label lblName1;
        @FXML
        void onCancelarReserva(ActionEvent event) {

        }

        @FXML
        void onCerrarSesion(ActionEvent event) {
        }

        @FXML
        void onCrearReserva(ActionEvent event) {

        }

        @FXML
        void onEditarDatos(ActionEvent event) {

        }
        @Override
        public void initialize(URL location, ResourceBundle resources){

        }
    }



