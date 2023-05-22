package org.example.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.Domain.TrialSwimmer;
import org.example.dao.TrialSwimmerDAO;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerDeleteTrial {
    TrialSwimmerDAO daot = new TrialSwimmerDAO();

    @FXML
    private Button ButtonExit;
    @FXML
    private Button ButtonDelete;
    @FXML
    private TextField intId;

    /**
     * Esta función se ejecuta al presionar el botón "Exit" y regresa al menú de opciones de manipulación de pruebas.
     *
     * @throws IOException
     */
    @FXML
    private void ButtonExit() throws IOException {
        App.setRoot("option_manipulate_trial");
    }

    /**
     * Esta función se ejecuta al presionar el botón "Delete" y elimina una prueba por su ID.
     *
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void ButtonDeleteTrial() throws IOException, SQLException {
        deleteTrialById();
    }

    /**
     * Esta función se encarga de eliminar una prueba por su ID en la base de datos.
     *
     * @throws SQLException
     */
    public void deleteTrialById() throws SQLException {
        int Id = Integer.parseInt(intId.getText());

        try {
            TrialSwimmer trialToDelete = daot.findById(Id);
            if (trialToDelete != null) {
                daot.delete(trialToDelete);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Eliminar Prueba");
                alert.setHeaderText(null);
                alert.setContentText("Prueba eliminada con éxito");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al eliminar la prueba");
                alert.setHeaderText(null);
                alert.setContentText("No existe una prueba con ese ID");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting Trial.");
        }
    }
}
