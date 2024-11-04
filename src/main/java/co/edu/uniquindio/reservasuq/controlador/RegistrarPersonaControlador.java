package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.mindrot.jbcrypt.BCrypt;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarPersonaControlador implements Initializable {
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
    private void bindingPasswords(){
        txtPassword.textProperty().addListener((observable,oldValue, newValue) ->
                txtPasswordShow.setText(newValue));
        txtConfirmPass.textProperty().addListener((observable, oldValue, newValue) ->
                txtConfirmPassShow.setText(newValue));
        txtPasswordShow.textProperty().addListener((observable, oldValue, newValue)->
                txtPassword.setText(newValue));
        txtConfirmPassShow.textProperty().addListener((observabe, oldValue, newValue)->
                txtConfirmPass.setText(newValue));
    }
    private boolean validarPassword() throws Exception{
        if (!txtPassword.getText().equals(txtConfirmPass.getText())){
            throw new Exception("Las contraseñas con coinciden");
        }
        return txtPassword.getText().equals(txtConfirmPass.getText());

    }

    public void registrarPersona(ActionEvent actionEvent){
        try { if(validarPassword()){
            String cedula = txtCedula.getText();
            String nombre = txtNombre.getText();
            String correoInstitucional = txtCorreoInstitucional.getText();
            String contrasena = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
            TipoPersona  tipoPersona = cbTipoPersona.getValue();
            reservasUQ.registrarPersona(cedula, nombre, tipoPersona, correoInstitucional, contrasena);
            limpiarFormularioRegistro();
            mostrarAlerta("Persona registrada correctamente", Alert.AlertType.INFORMATION);}

        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    private void limpiarFormularioRegistro() {
        txtConfirmPass.clear();
        txtCedula.clear();
        txtNombre.clear();
        txtCorreoInstitucional.clear();
        txtPassword.clear();
        cbTipoPersona.setValue(null);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTipoPersona.getItems().addAll(TipoPersona.values());
        bindingPasswords();
    }

}
