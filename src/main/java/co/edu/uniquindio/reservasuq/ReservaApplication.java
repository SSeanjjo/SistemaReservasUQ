package co.edu.uniquindio.reservasuq;

import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
            AppReserva.registrarPersona("321", "Juan Pérez", TipoPersona.ESTUDIANTE, "juan@example.com", "4321");
            AppReserva.registrarPersona("123", "Ana Pérez", TipoPersona.ESTUDIANTE, "ana@example.com", "1234");
            }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}