package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.TipoPersona;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class RegistrarReservaControlador {
    @FXML
    public TextField txtCedula;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtCorreoInstitucional;
    @FXML
    public TextField txtContrasena;
    @FXML
    public ComboBox cbTipoPersona;

    public void registrarPersona(ActionEvent actionEvent){
        try {
            String cedula = txtCedula.getText();
            String nombre = txtNombre.getText();
            String correoInstitucional = txtCorreoInstitucional.getText();
            String contrasena = txtContrasena.getText();
            TipoPersona  tipoPersona = cbTipoPersona.getValue();



        }

    }






}
