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
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String correoInstitucional, String password) throws Exception {
        reservasUQ.registrarPersona(cedula, nombre, tipoPersona, correoInstitucional, password);
    }


    @Override
    public void crearInstalacion(String nombre,int aforo, float costo, List<Horario> horarios) {
        reservasUQ.crearInstalacion(nombre, aforo, costo, horarios);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return reservasUQ.crearReserva(idInstalacion, cedulaPersona, diaReserva, horaReserva);
    }

    @Override
    public Persona obtenerPersona(String cedula) {
        return reservasUQ.obtenerPersona(cedula);
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

    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        }
    }




