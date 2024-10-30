package co.edu.uniquindio.reservasuq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class ReservasUqAplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReservasUqAplication.class.getResource("/inicio.fxml"));
        Scene scene= new Scene(fxmlLoader.load(), 900, 500);
        primaryStage.setTitle("Sistema reservas UQ");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main (String[] args) {launch();}
}
