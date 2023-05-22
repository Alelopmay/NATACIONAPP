package org.example.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.App;

import java.io.IOException;

public class ControllerOptionT {

    @FXML
    private Button ButtonAdd;
    @FXML
    private Button ButtonDelete;
    @FXML
    private Button ButtonShow;
    @FXML
    private Button ButtonExit;

    @FXML
    private Button Buttonmodify;

    /**
     * boton para salir
     * @throws IOException
     */
    @FXML
    private void ButtonExit() throws IOException {
        App.setRoot("HOME1");
    }

    /**
     * Funcion para ir aal fxml añadir prueba
     * @throws IOException
     */
    @FXML
    private void ButtonAdd() throws IOException {
        App.setRoot("add_trial");
    }

    /**
     * button para ir al fxml eliminarprueba
     * @throws IOException
     */
    @FXML
    private void ButtonDelete() throws IOException {
        App.setRoot("Delete_trial");
    }

    /**
     * funcion para ir a ver Prueba
     * @throws IOException
     */
    @FXML
    private void ButtonShow() throws IOException {
        App.setRoot("ShowTrialSwimmer");
    }

    /**
     * funcion para ir al fxml modificar nadador
     * @throws IOException
     */
    @FXML
    private void Buttonmodify() throws IOException {
        App.setRoot("modify_trial");
    }
}
