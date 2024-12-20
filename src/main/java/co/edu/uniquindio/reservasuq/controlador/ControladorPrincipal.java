package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ControladorPrincipal implements ServiciosReservasUQ {

    private static ControladorPrincipal INSTANCIA;
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    private ControladorPrincipal() {
    }

    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        return reservasUQ.login(correo, contrasena);
    }


    @Override
    public void registrarPersona(String cedula, String nombre, String correoInstitucional, String contrasena, TipoPersona tipoPersona) throws Exception {
        reservasUQ.registrarPersona(cedula, nombre, correoInstitucional, contrasena, tipoPersona);
    }


    @Override
    public void crearInstalacion(String id, String nombre,int aforo, float costo, java.time.LocalDateTime horaInicio, java.time.LocalDateTime horaFin) {
        reservasUQ.crearInstalacion(id, nombre, aforo, costo, horaInicio, horaFin);
    }

    @Override
    public Reserva crearReserva(String tipoInstalacion, String idInstalacion, String cedula, LocalDate diaReserva, String horaReserva) throws Exception {
        return reservasUQ.crearReserva(tipoInstalacion, idInstalacion, cedula, diaReserva, horaReserva);
    }


    @Override
    public Optional<Persona> obtenerPersona(String cedula) {
        return reservasUQ.getListaPersonas().stream()
                .filter(persona -> persona.getCedula().equals(cedula))
                .findFirst();
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return List.of();
    }

    //TODO Completar con el resto de métodos necesarios para la aplicación

    public void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public  void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));

            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

}
