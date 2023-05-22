package org.example.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.App;

import java.io.IOException;

public class ControllerOptionM {
    @FXML
    private Button ButtonShowTable;
    @FXML
    private Button ButtonShow;
    @FXML
    private Button ButtonExit;
    @FXML
    private Button ButtonGraf;


    @FXML
    private Button Buttonmodify_Mark;

    /**
     * Funcion para ir al añadir marca
     * @throws IOException
     */

    @FXML
    private void ButonShowTable() throws IOException {
        App.setRoot("ShowTable");
    }


    /**
     * funcion  para ir al ver marcas
     * @throws IOException
     */
    @FXML
    private void ButonShowMark() throws IOException {
        App.setRoot("ShowMarkForSwimmer");
    }

    /**
     * funcion para salir
     * @throws IOException
     */
    @FXML
    private void ButonExit() throws IOException {
        App.setRoot("HOME1");
    }

}
