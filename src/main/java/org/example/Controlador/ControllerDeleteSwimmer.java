package org.example.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.Domain.Swimmer;
import org.example.dao.SwimmerDAO;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerDeleteSwimmer {

    @FXML
    private Button ButtonDeleteSwimmer;

    @FXML
    private TextField IntCod_Swimmer;

    /**
     * Esta función se ejecuta al presionar el botón "DeleteSwimmer" y realiza la eliminación del nadador.
     *
     * @throws IOException
     */
    @FXML
    private void ButtonDeleteSwimmer() throws IOException {
        deleteSwimmer();
    }

    /**
     * Esta función se ejecuta al presionar el botón "Exit" y regresa al menú de opciones de nadador.
     *
     * @throws IOException
     */
    @FXML
    private void ButtonExit() throws IOException {
        App.setRoot("AdSw");
    }

    /**
     * Verifica si el campo IntCod_Swimmer está lleno y si el nadador con ese código existe en la base de datos.
     *
     * @return true si el campo está lleno y el nadador existe, false de lo contrario.
     */
    private boolean areFieldsFilledAndValid() {
        String codSwimmer = IntCod_Swimmer.getText();
        if (codSwimmer.isEmpty()) {
            return false;
        }

        int cod = Integer.parseInt(codSwimmer);
        SwimmerDAO swimmerDAO = new SwimmerDAO();
        try {
            Swimmer swimmer = swimmerDAO.findById(cod);
            return swimmer != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Esta función se encarga de eliminar el nadador de la base de datos.
     */
    private void deleteSwimmer() {
        String codSwimmer = IntCod_Swimmer.getText();
        if (areFieldsFilledAndValid()) {
            int cod = Integer.parseInt(codSwimmer);
            SwimmerDAO swimmerDAO = new SwimmerDAO();
            try {
                Swimmer swimmerToDelete = swimmerDAO.findById(cod);
                swimmerDAO.delete(swimmerToDelete);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Eliminar Nadador");
                alert.setHeaderText(null);
                alert.setContentText("Nadador eliminado con éxito");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al eliminar el nadador");
                alert.setHeaderText(null);
                alert.setContentText("Ocurrió un error al eliminar el nadador");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al eliminar el nadador");
            alert.setHeaderText(null);
            alert.setContentText("El campo de código del nadador está vacío o el nadador no existe en la base de datos");
            alert.showAndWait();
        }
    }
}
