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

import static org.example.Util.Utils.*;

public class ControllerAddSwimmer {

    @FXML
    private Button ButtonAddSwimmer;

    @FXML
    private TextField TextName;
    @FXML
    private TextField TextLast_Name;
    @FXML
    private ChoiceBox<SEX> choiceBoxSexo;
    @FXML
    private SEX[] sex = {SEX.MASCULINO, SEX.FEMENINO, SEX.NOBINARIA};
    @FXML
    private TextField intAge;

    /**
     * Botón para volver a las opciones de nadador
     *
     * @throws IOException
     */
    @FXML
    private void ButtonExit() throws IOException {
        App.setRoot("adSw");
    }

    /**
     * Botón para iniciar el añadir nadador
     *
     * @throws IOException
     */
    @FXML
    private void ButtonAddSwimmer() throws IOException {
        addSwimmer();
    }

    /**
     * Verifica si todos los campos están llenos.
     * @return true si todos los campos están llenos, false de lo contrario.
     */
    private boolean areFieldsFilled() {
        String name = TextName.getText();
        String last_name = TextLast_Name.getText();
        String age = intAge.getText();

        return !name.isEmpty() && !last_name.isEmpty() && !age.isEmpty();
    }

    /**
     * Esta función se encarga de añadir los nadadores a la base de datos
     *
     * @throws IOException
     */
    private void addSwimmer() throws IOException {
        SwimmerDAO swimmerDAO = new SwimmerDAO();

        String name = TextName.getText();
        String last_name = TextLast_Name.getText();
        int age = Integer.parseInt(intAge.getText());
        SEX sex = choiceBoxSexo.getValue();

        try {
            if (areFieldsFilled() && ValidName(name) && validLast_name(last_name) && validAge(age)) {
                Swimmer swimmer = new Swimmer(0, name, last_name, age, sex, "");
                swimmerDAO.save(swimmer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nadador introducido");
                alert.setHeaderText(null);
                alert.setContentText("Nadador introducido con éxito");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de validación");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, completa todos los campos correctamente.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de base de datos");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo insertar el Nadador en la base de datos.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Esta función es para inicializar el enum del género
     */
    @FXML
    public void initialize() {
        // Agregar opciones de la enumeración SEX al ChoiceBox
        choiceBoxSexo.getItems().addAll(SEX.values());
    }
}

