package co.edu.uniquindio.reservasuq;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.enums.CostoInstalaciones;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        incializarDatosAppReserva();
        FXMLLoader fxmlLoader = new FXMLLoader(ReservaApplication.class.getResource("/inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("Reserva App");
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.setMaximized(true);
        stage.show();
    }

    private void incializarDatosAppReserva() {
        try {
            ReservasUQ AppReserva = ReservasUQ.getInstance();
            // Agregar personas por defecto
            AppReserva.registrarPersona("123", "Juan Pérez", "juan@mail.com", "1234", TipoPersona.ESTUDIANTE);

            // Agregar instalaciones por defecto
            AppReserva.crearInstalacion("01","Pista de atletismo", 600, 10000,LocalDateTime.of(LocalDate.now(), LocalTime.of(6, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 0)));
            AppReserva.crearInstalacion("02", "Piscina", 100, 5000, LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 30)));
            AppReserva.crearInstalacion("03", "Gimnasio", 50, 20000, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)));
            AppReserva.crearInstalacion("05", "Cancha de fútbol", 200, 15000, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)));
            AppReserva.crearInstalacion("06", "Cancha de Balocesto", 50, 10000, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)));
            AppReserva.crearInstalacion("07", "Aulas de estudio grupal", 50, 0, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)));
            AppReserva.crearInstalacion("08", "Salas de estudio individual", 50, 0, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)));

            AppReserva.crearReserva("Pista de atletismo", "01", "123", LocalDate.now(), "6:00");
            AppReserva.crearReserva("Piscina", "02", "123", LocalDate.now(), "7:00");
            AppReserva.crearReserva("Gimnasio", "03", "123", LocalDate.now(), "8:00");
            AppReserva.crearReserva("Cancha de fútbol", "05", "123", LocalDate.now(), "8:00");
            }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}