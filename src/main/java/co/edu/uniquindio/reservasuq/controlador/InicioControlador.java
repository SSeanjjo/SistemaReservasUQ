package co.edu.uniquindio.reservasuq.controlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Getter;


public class InicioControlador{

    private final co.edu.uniquindio.reservasuq.controlador.ControladorPrincipal controladorPrincipal;

    public InicioControlador() {
        this.controladorPrincipal = co.edu.uniquindio.reservasuq.controlador.ControladorPrincipal.getInstancia();
    }

    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/login.fxml","Iniciar Sesión");
    }

    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/registro.fxml", "Registro Persona");
    }

}



