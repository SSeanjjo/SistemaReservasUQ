package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

public class LoginControlador {

    @FXML
    public TextField txtCorreo;
    @FXML
    public TextField txtPassword;

    private final ControladorPrincipal controladorPrincipal;
    Sesion sesion = Sesion.getInstancia();
    Persona persona = sesion.getPersona();

    ReservasUQ reservasUQ = ReservasUQ.getInstance();
//    Persona sesionActual =  sesion.setPersona(persona);


    public LoginControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public boolean validarPassword(String contrasena, String hashed) {
        return BCrypt.checkpw(contrasena, hashed);
    }

    public void login(ActionEvent actionEvent) {
        for (Persona persona : reservasUQ.getListaPersonas()) {
            if (txtCorreo.getText().equals(persona.getCorreoInstitucional()) && validarPassword(txtPassword.getText(), persona.getPassword())) {
                //Se agrega el panel segun el tipo de user
            }

        }


    }
}
