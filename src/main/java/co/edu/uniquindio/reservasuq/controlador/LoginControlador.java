package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
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
        return BCrypt.checkpw(String.valueOf(contrasena), hashed);
    }


    public void login(ActionEvent actionEvent) {
        boolean loginSuccessful = false;
        String errorMsg = "Correo o contraseña incorrectos";

        for (Persona persona : reservasUQ.getListaPersonas()) {
            if (txtCorreo.getText().equals(persona.getCorreoInstitucional())) {
                if (validarPassword(txtPassword.getText(), persona.getContrasena())) {
                    sesion.setPersona(persona);
                    ControladorPrincipal.cerrarVentana(txtCorreo);
                    controladorPrincipal.navegarVentana("/profile.fxml", "Perfil");
                    loginSuccessful = true;
                    break;
                } else {
                    errorMsg = "Contraseña incorrecta";
                }
            }
        }

        if (!loginSuccessful) {
            mostrarAlerta(errorMsg, Alert.AlertType.ERROR);
            limpiarFormularioRegistro();
        }
    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    private void limpiarFormularioRegistro() {
        txtCorreo.clear();
        txtPassword.clear();
    }
     /*  public void login(ActionEvent actionEvent) {
        boolean loginSuccessful = false; // Track if a valid user is found
        for (Persona persona : reservasUQ.getListaPersonas()) {
            if (txtCorreo.getText().equals(persona.getCorreoInstitucional())
                    && validarPassword(txtPassword.getText(), persona.getPassword())) {
                sesion.setPersona(persona);
                controladorPrincipal.navegarVentana("/profile.fxml", "Perfil");
                loginSuccessful = true;
                break; // Exit the loop once a match is found
            }
        }

        if (!loginSuccessful) {
            mostrarAlerta("Correo o contraseña incorrectos", Alert.AlertType.ERROR);
            limpiarFormularioRegistro();
        }
    }*/

//    public void login(ActionEvent actionEvent) {
//        boolean loginSuccessful = false;
//        for (Persona persona : reservasUQ.getListaPersonas()) {
//            if (txtCorreo.getText().equals(persona.getCorreoInstitucional()) &&
//                    validarPassword(txtPassword.getText(), persona.getPassword())) {
//                sesion.setPersona(persona);
//                controladorPrincipal.navegarVentana("/profile.fxml", "Perfil");
//                loginSuccessful = true;
//                break;
//            }
//        }
//
//        if (!loginSuccessful) {
//            mostrarAlerta("Correo o contraseña incorrectos", Alert.AlertType.ERROR);
//            limpiarFormularioRegistro();
//        }
//    }
}
