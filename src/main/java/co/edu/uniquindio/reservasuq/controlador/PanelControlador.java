package co.edu.uniquindio.reservasuq.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class PanelControlador {

    @FXML
    private StackPane panelPrincipal;

    public void mostrarRegistroUsuario(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registroPaciente.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    public void mostrarListaUsuario(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listaPacientes.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    public void mostrarRegistroReservas(ActionEvent actionEvent) {
        Parent node = cargarPanel("/agendarCita.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    public void mostrarListaReservas(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listaCitas.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    private Parent cargarPanel(String fxmlFile){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            return loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
