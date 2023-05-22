package org.example.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.App;

import java.io.IOException;

public class ControllerOptionS {

    @FXML
    private Button ButtonAdd;
    @FXML
    private Button ButtonDelete;
    @FXML
    private Button ButtonShow;
    @FXML
    private Button ButtonExit;

    @FXML
    private Button Buttonmodify_Swimmer;

    /**
     * funcion para moverme al HOME1
     * @throws IOException
     */
    @FXML
    private void ButtonExit() throws IOException {
        App.setRoot("HOME1");
    }
    @FXML
    /**
     * funcion para moverme al add_swimmer
     */
    private void ButtonAdd() throws IOException {
        App.setRoot("add_Swimmer");
    }

    /**
     * funcion para moverme al DeleteSwimmer
     * @throws IOException
     */
    @FXML
    private void ButtonDelete() throws IOException {
        App.setRoot("Delete_Swimmer");
    }

    /**
     * funcion para moverme al ShowSwimmer
     * @throws IOException
     */
    @FXML
    private void ButtonShow() throws IOException {
        App.setRoot("ShowSwimmer");
    }

    /**
     * funcion para moverme al modify_Swimmer
     * @throws IOException
     */
    @FXML
    private void Buttonmodify() throws IOException {
        App.setRoot("modify_Swimmer");
    }
}
