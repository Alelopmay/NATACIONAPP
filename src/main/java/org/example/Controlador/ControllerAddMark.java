package org.example.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.Domain.MarkRecord;
import org.example.Domain.Swimmer;
import org.example.Domain.TrialSwimmer;
import org.example.dao.MarkrecordDAO;
import org.example.Util.Utils;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerAddMark {
    @FXML
    private Button exit;
    @FXML
    private Button ButtonAdd;
    @FXML
    private TextField IntCod;
    @FXML
    private TextField IntId;
    @FXML
    private TextField MarkTime;

    /**
     * Botón para volver atrás
     * @throws IOException
     */
    @FXML
    private void ButtonExit() throws IOException {
        App.setRoot("ShowTable");
    }

    /**
     * Botón que activa la función de añadir marca
     * @throws IOException
     */
    @FXML
    private void ButtonAdd() throws IOException {
        if (checkFieldsNotEmpty() && validateTimeFormat(MarkTime.getText())) {
            AddMark();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validación");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, completa todos los campos y asegúrate de que el formato de tiempo sea correcto (HH:MM:SS).");
            alert.showAndWait();
        }
    }

    /**
     * Verifica si todos los campos están llenos.
     * @return true si todos los campos están llenos, false de lo contrario.
     */
    private boolean checkFieldsNotEmpty() {
        String codSwimmer = IntCod.getText();
        String id = IntId.getText();
        String markTime = MarkTime.getText();

        return !codSwimmer.isEmpty() && !id.isEmpty() && !markTime.isEmpty();
    }

    /**
     * Verifica el formato de tiempo (HH:MM:SS).
     * @param time El tiempo a validar.
     * @return true si el formato es válido, false de lo contrario.
     */
    private boolean validateTimeFormat(String time) {
        return Utils.validateTimeFormat(time);
    }

    /**
     * Esta función introduce la marca en la base de datos.
     * @throws IOException
     */
    private void AddMark() throws IOException {
        MarkrecordDAO MDAO = new MarkrecordDAO();
        int codSwimmer = Integer.parseInt(IntCod.getText());
        int id = Integer.parseInt(IntId.getText());
        String time = MarkTime.getText();

        try {
            Swimmer swimmer = new Swimmer();
            swimmer.setCod_Swimmer(codSwimmer);

            TrialSwimmer trialSwimmer = new TrialSwimmer();
            trialSwimmer.setId(id);

            MarkRecord mark = new MarkRecord(swimmer, trialSwimmer, 0, time, null);
            MDAO.save(mark);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Marca Introducida");
            alert.setHeaderText(null);
            alert.setContentText("Marca Introducida con éxito");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
