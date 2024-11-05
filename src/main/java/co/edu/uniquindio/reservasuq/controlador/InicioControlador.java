package co.edu.uniquindio.reservasuq.controlador;
import javafx.event.ActionEvent;

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
    public void irReservacion(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/reservacion.fxml", "Reservación");
    }


}
