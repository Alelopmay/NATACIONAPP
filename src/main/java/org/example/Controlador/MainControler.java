package org.example.Controlador;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.App;

import java.io.IOException;

public class MainControler {
    @FXML
    private Button btn_home;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField pasword;
    @FXML
    private TextField fallo;
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Buttonexit;


    @FXML
    private void ControladorP() throws IOException {

        if (usuario.getText().equals("alejandro") && pasword.getText().equals("natacion")) {
            App.setRoot("HOME1");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de inicio de sesión");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos.");
            alert.showAndWait();

        }
    }

    /**
     * funcion para ir a las opciones de natacion
     * @throws IOException
     */
    @FXML
    private void buttonOption1() throws IOException {
        App.setRoot("adSw");
    }

    /**
     * funcion para ir a las opciones de pruebas
     * @throws IOException
     */

    @FXML
    private void buttonOption2() throws IOException {
        App.setRoot("Option_manipulate_trial");
    }

    /**
     * funcion para ir a las opciones de las marcas
     * @throws IOException
     */

    @FXML
    private void buttonOption3 () throws IOException {
        App.setRoot("Option_Mark");
    }
    @FXML
    private void buttonOptionExit () throws IOException {
        App.setRoot("login");
    }

}