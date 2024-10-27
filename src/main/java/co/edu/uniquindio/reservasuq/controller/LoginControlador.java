package co.edu.uniquindio.reservasuq.controller;


import co.edu.uniquindio.reservasuq.model.Persona;
import co.edu.uniquindio.reservasuq.model.Sesion;
import co.edu.uniquindio.reservasuq.model.TipoPersona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginControlador {


    @FXML
    public TextField txtCorreo;


    @FXML
    public TextField txtPassword;


    private final co.edu.uniquindio.reservasuq.controlador.ControladorPrincipal controladorPrincipal;


    public LoginControlador() {
        this.controladorPrincipal = co.edu.uniquindio.reservasuq.controlador.ControladorPrincipal.getInstancia();
    }


    public void login(ActionEvent actionEvent) {


        try {


            String email = txtCorreo.getText();
            String password = txtPassword.getText();


            Persona persona = controladorPrincipal.login(email, password);


            if(persona.getTipoPersona() != TipoPersona.ADMINISTRATIVO) {
                controladorPrincipal.navegarVentana("/panelCliente.fxml", "Panel Usuario");
            }else{
                controladorPrincipal.navegarVentana("/panelAdmin.fxml", "Panel Administrador");
            }


            controladorPrincipal.cerrarVentana(txtCorreo);


        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    Sesion sesion = Sesion.getInstancia();
//    sesion.setPersona(persona);
}
