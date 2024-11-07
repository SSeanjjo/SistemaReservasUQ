package co.edu.uniquindio.reservasuq;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.enums.CostoInstalaciones;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
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

            Reserva reserva1 = new Reserva("01", "123", LocalDate.now(), "6;00", "123", CostoInstalaciones.PISTA_ATLETISMO.getCosto());
            Reserva reserva5 = new Reserva("01", "123", LocalDate.now(), "6:00", "123", CostoInstalaciones.PISTA_ATLETISMO.getCosto());
            Reserva reserva2 = new Reserva("02", "123", LocalDate.now(), "7:00", "123", CostoInstalaciones.PISCINA.getCosto());
            Reserva reserva3 = new Reserva("03", "123", LocalDate.now(), "8:00", "123", CostoInstalaciones.GIMNASIO.getCosto());
            Reserva reserva4 = new Reserva("05", "123", LocalDate.now(), "8:00", "123", CostoInstalaciones.CANCHA_DE_FUTBOL.getCosto());
            AppReserva.getListaReservas().add(reserva1);
            AppReserva.getListaReservas().add(reserva5);
            AppReserva.getListaReservas().add(reserva2);
            AppReserva.getListaReservas().add(reserva3);
            AppReserva.getListaReservas().add(reserva4);



            System.out.println(AppReserva.getListaInstalaciones().size());
            }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}