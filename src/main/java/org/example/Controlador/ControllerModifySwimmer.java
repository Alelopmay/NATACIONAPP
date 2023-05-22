package org.example.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.Domain.SEX;
import org.example.Domain.Swimmer;
import org.example.dao.SwimmerDAO;

import java.io.IOException;
import java.sql.SQLException;

import static org.example.Domain.SEX.*;
import static org.example.Util.Utils.*;

public class ControllerModifySwimmer {

    @FXML
    private Button buttonModify;
    @FXML
    private Button buttonExit;
    @FXML
    private TextField TextName;
    @FXML
    private TextField TextLast_Name;
    @FXML
    private ChoiceBox<SEX> choiceBoxSexo;
    @FXML
    private SEX[] sex={MASCULINO,FEMENINO,NOBINARIA};
    @FXML
    private TextField intAge;
    @FXML
    private TextField CodInt;

    private SwimmerDAO swimmerDAO; // Agrega la referencia al DAO

    /**
     * Inicializa el controlador.
     */
    public void initialize() {
        swimmerDAO = new SwimmerDAO(); // Inicializa el DAO

        // Agrega opciones de la enumeración SEX al ChoiceBox
        choiceBoxSexo.getItems().addAll(SEX.values());
    }

    /**
     * Acción del botón Modificar Nadador.
     */
    @FXML
    public void buttonModifySwimmer() throws IOException {
        modifySwimmer();
    }

    /**
     * Modifica el nadador.
     */
    private void modifySwimmer() throws IOException {
        int codSwimmer = Integer.parseInt(CodInt.getText());
        try {
            Swimmer existingSwimmer = swimmerDAO.findById(codSwimmer); // Busca el nadador existente en la base de datos
            if (existingSwimmer != null) {
                // Rellena los campos con la información existente del nadador
                TextName.setText(existingSwimmer.getName());
                TextLast_Name.setText(existingSwimmer.getLast_Name());
                intAge.setText(String.valueOf(existingSwimmer.getAge()));
                choiceBoxSexo.setValue(existingSwimmer.getSex());

                // Actualiza el nadador en la base de datos al pulsar el botón Modificar
                buttonModify.setOnAction(event -> {
                    try {
                        updateSwimmer(existingSwimmer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nadador encontrado");
                alert.setHeaderText(null);
                alert.setContentText("Se encontró el nadador y se rellenaron los campos.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se encontró un nadador con el código proporcionado.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el nadador en la base de datos.
     */
    private void updateSwimmer(Swimmer swimmer) throws IOException {
        // Obtén los valores de los campos
        String name = TextName.getText();
        String lastName = TextLast_Name.getText();
        int age = Integer.parseInt(intAge.getText());
        SEX sexo = choiceBoxSexo.getValue();

        // Actualiza los datos del nadador
        swimmer.setName(name);
        swimmer.setLast_Name(lastName);
        swimmer.setAge(age);
        swimmer.setSex(sexo);

        try {
            swimmerDAO.update(swimmer); // Actualiza el nadador en la base de datos
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nadador modificado");
            alert.setHeaderText(null);
            alert.setContentText("El nadador se ha modificado correctamente.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Acción del botón Salir.
     */
    @FXML
    public void exit() throws IOException {
        App.setRoot("adSw");
    }

    /**
     * Verifica si todos los campos están llenos.
     *
     * @return true si todos los campos están llenos, false de lo contrario.
     */
    private boolean areAllFieldsFilled() {
        return !TextName.getText().isEmpty()
                && !TextLast_Name.getText().isEmpty()
                && choiceBoxSexo.getValue() != null
                && !intAge.getText().isEmpty()
                && !CodInt.getText().isEmpty();
    }
}

