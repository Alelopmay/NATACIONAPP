package org.example.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.Domain.MarkRecord;
import org.example.Domain.Swimmer;
import org.example.Domain.TrialSwimmer;
import org.example.dao.MarkrecordDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.example.Util.Utils.validateTimeFormat;

public class ControllerModifyMark {

    @FXML
    private Button buttonModify;
    @FXML
    private Button buttonExit;
    @FXML
    private TextField IntcodIdMark;
    @FXML
    private TextField IntId;
    @FXML
    private TextField intCod;
    @FXML
    private TextField MarkTime;
    @FXML
    private TableColumn<MarkRecord, Integer> CodColumn;
    @FXML
    private TableColumn<MarkRecord, Integer> IDColumn;
    @FXML
    private TableColumn<MarkRecord, Integer> IdMarkColumn;
    @FXML
    private TableColumn<MarkRecord, Integer> TimeColumn;
    private ObservableList<MarkRecord> MarkList = FXCollections.observableArrayList();
    @FXML
    private TableView<MarkRecord> MarkSwimmerTableview;
    @FXML
    private void initialize() {

        try{

            CodColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            TimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));


            MarkrecordDAO MDAO=new MarkrecordDAO();
            List<MarkRecord> aux = MDAO.findAll();
            MarkList.setAll(aux);



            MarkSwimmerTableview.setItems(MarkList);
        } catch (SQLException e) {
            System.out.println("no puedes mostrar");
        }
    }



    /**
     * Esta función se ejecuta al presionar el botón "Modify" y modifica la marca.
     *
     * @throws IOException
     */
    @FXML
    public void buttonModifyMark() throws IOException {
        if (checkFieldsNotEmpty() && checkIfSwimmerAndIdExist()) {
            modifyMark();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incompletos o inválidos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, complete todos los campos, verifique que el nadador y el ID existan y que el formato de tiempo sea válido (HH:MM:SS).");
            alert.showAndWait();
        }
    }

    /**
     * Esta función modifica la marca en la base de datos.
     *
     * @throws IOException
     */
    public void modifyMark() throws IOException {
        MarkrecordDAO MDAO = new MarkrecordDAO();
        int codSwimmer = Integer.parseInt(intCod.getText());
        int id = Integer.parseInt(IntId.getText());
        int idMarkRecord = Integer.parseInt(IntcodIdMark.getText());
        String time = MarkTime.getText();

        try {
            if(validateTimeFormat(time)) {
                Swimmer swimmer = new Swimmer();
                swimmer.setCod_Swimmer(codSwimmer);

                TrialSwimmer trialSwimmer = new TrialSwimmer();
                trialSwimmer.setId(id);

                MarkRecord markRecord = new MarkRecord(swimmer, trialSwimmer, idMarkRecord, time, null);
                MDAO.update(markRecord);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Marca Modificada");
                alert.setHeaderText(null);
                alert.setContentText("Marca modificada con éxito");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de base de datos");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo modificar la marca en la base de datos. comprueba que esta bien el formato de los campos");
                alert.showAndWait();

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * Esta función se ejecuta al presionar el botón "Exit" y regresa al menú de opciones de marca.
     *
     * @throws IOException
     */
    @FXML
    public void exit() throws IOException {
        App.setRoot("ShowTable");
    }

    /**
     * Esta función verifica si todos los campos están rellenados.
     *
     * @return true si los campos están rellenados, false si hay algún campo vacío.
     */
    private boolean checkFieldsNotEmpty() {
        return !IntcodIdMark.getText().isEmpty() && !IntId.getText().isEmpty()
                && !intCod.getText().isEmpty() && !MarkTime.getText().isEmpty();
    }

    /**
     *
     * @return la funcion comprueba que el nadador exista
     */
    private boolean checkIfSwimmerAndIdExist() {
        int codSwimmer = Integer.parseInt(intCod.getText());
        int id = Integer.parseInt(IntId.getText());

        // Realizar la verificación en la base de datos
        MarkrecordDAO markrecordDAO = new MarkrecordDAO();

        try {
            // Verificar si el nadador existe
            Swimmer swimmer = markrecordDAO.getSwimmerByCodSwimmer(codSwimmer);
            if (swimmer == null) {
                return false; // El nadador no existe en la base de datos
            }

            // Verificar si el ID existe en los registros del nadador
            TrialSwimmer trialSwimmer = new TrialSwimmer();
            trialSwimmer.setId(id);
            boolean exists = markrecordDAO.checkMarkRecordExists(swimmer, trialSwimmer);
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error al acceder a la base de datos
        }
    }

}
