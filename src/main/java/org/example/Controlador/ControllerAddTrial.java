package org.example.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.example.App;
import org.example.Domain.Meters;
import org.example.Domain.category;
import org.example.Domain.pool_type;
import org.example.Domain.style;
import org.example.Domain.SEX;
import org.example.Domain.TrialSwimmer;
import org.example.dao.TrialSwimmerDAO;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerAddTrial {

    @FXML
    private Button ButtonADD;
    @FXML
    private Button ButtonAddTrial;
    @FXML
    private Button ButtonExit;
    @FXML
    private ChoiceBox<SEX> choiceBoxSexo;
    @FXML
    private ChoiceBox<category> choiceBoxCategory;
    @FXML
    private ChoiceBox<pool_type> choiceBoxpolltype;
    @FXML
    private ChoiceBox<Meters> choiceBoxtestDistance;
    @FXML
    private ChoiceBox<style> choiceBoxStyle;

    /**
     * Esta función inicia el añadir prueba
     *
     * @throws IOException
     */
    @FXML
    private void ButtonAddTrial() throws IOException {
        addTrial();
    }

    /**
     * Verifica si todos los campos están llenos.
     *
     * @return true si todos los campos están llenos, false de lo contrario.
     */
    private boolean areFieldsFilled() {
        return choiceBoxSexo.getValue() != null &&
                choiceBoxCategory.getValue() != null &&
                choiceBoxpolltype.getValue() != null &&
                choiceBoxtestDistance.getValue() != null &&
                choiceBoxStyle.getValue() != null;
    }

    /**
     * Esta función añade la prueba en la base de datos
     *
     * @throws IOException
     */
    private void addTrial() throws IOException {
        if (areFieldsFilled()) {
            TrialSwimmerDAO trialSwimmerDAO = new TrialSwimmerDAO();

            SEX sex = choiceBoxSexo.getValue();
            category category = choiceBoxCategory.getValue();
            pool_type poolType = choiceBoxpolltype.getValue();
            Meters meters = choiceBoxtestDistance.getValue();
            style style = choiceBoxStyle.getValue();

            try {
                TrialSwimmer trialSwimmer = new TrialSwimmer(0, sex, category, poolType, meters, style);
                trialSwimmerDAO.save(trialSwimmer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Prueba Introducida");
                alert.setHeaderText(null);
                alert.setContentText("Prueba Introducida con éxito");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de base de datos");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo insertar la prueba en la base de datos.");
                alert.showAndWait();
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validación");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, completa todos los campos.");
            alert.showAndWait();
        }
    }

    @FXML
    public void initialize() {
        // Agregar opciones de la enumeración SEX al ChoiceBox
        choiceBoxSexo.getItems().addAll(SEX.values());
        choiceBoxCategory.getItems().addAll(category.values());
        choiceBoxCategory.getItems().addAll(category.values());
        choiceBoxpolltype.getItems().addAll(pool_type.values());
        choiceBoxtestDistance.getItems().addAll(org.example.Domain.Meters.values());
        choiceBoxStyle.getItems().addAll(style.values());
    }
    @FXML
    private void Buttonexit() throws IOException {
       App.setRoot("Option_manipulate_trial");
    }
}


