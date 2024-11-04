package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PerfilControlador {
    ReservasUQ reservasUQ = ReservasUQ.getInstance();

    @FXML
    public TextField txtCedula;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtCorreoInstitucional;
    @FXML
    private ComboBox<TipoPersona> cbTipoPersona;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmPass;
    @FXML
    private TextField txtPasswordShow;
    @FXML
    private TextField txtConfirmPassShow;
    @FXML
    private CheckBox checkShowPass;

    public void OnMostrarPassword(){
        boolean mostrar = checkShowPass.isSelected();
        txtPassword.setVisible(!mostrar);
        txtConfirmPass.setVisible(!mostrar);
        txtPasswordShow.setVisible(mostrar);
        txtConfirmPassShow.setVisible(mostrar);
    }

    public void cargarReservas(ActionEvent actionEvent){
        for(Reserva reserva : reservasUQ.getListaReservas()){
            if(reserva.getCedula().equals(txtCedula.getText())){
                System.out.println(reserva);
            }
        }
    }

}
