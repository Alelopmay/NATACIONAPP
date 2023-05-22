package org.example.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.Domain.Meters;
import org.example.Domain.Swimmer;
import org.example.Domain.TrialSwimmer;
import org.example.dao.TrialSwimmerDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ControllerShowTrial {

    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonModify;
    @FXML
    private TableView<TrialSwimmer> TrialTableView; // Crear una variable TableView para los nadadores
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<Swimmer, Integer> IdColumn;
    @FXML
    private TableColumn<Swimmer, String> CategoryColumn;
    @FXML
    private TableColumn<Swimmer, String> StyleColumn;
    @FXML
    private TableColumn<Swimmer, String> poolTypeColumn;
    @FXML
    private TableColumn<Swimmer, String> MetersColumn;
    @FXML
    private TableColumn<Swimmer, String> SexColumn;

    @FXML
    private TextField styleField;
    @FXML
    private TextField metersField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField poolTypeField;
    @FXML
    private TextField sexField;

    /**
     * ObservableList que crea un FXcollection para mostrar un arraylist en la tabla del FXML
     */
    private ObservableList<TrialSwimmer> trialList = FXCollections.observableArrayList();



    /**
     * funcion para mostrar las pruebas en la tabla
     */
    public void initialize() {
        try {
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
            CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
            StyleColumn.setCellValueFactory(new PropertyValueFactory<>("style"));
            poolTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Pool_Type"));
            MetersColumn.setCellValueFactory(new PropertyValueFactory<>("Meters"));
            SexColumn.setCellValueFactory(new PropertyValueFactory<>("Sex"));

            TrialSwimmerDAO TDAO = new TrialSwimmerDAO();
            List<TrialSwimmer> trialSwimmers = TDAO.findAll();

            // Convertir la lista de TrialSwimmer a ObservableList<TrialSwimmer>
            trialList = FXCollections.observableArrayList(trialSwimmers);
            TrialTableView.setItems(trialList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * funcion para hacer un buscador por el id la prueba
     */
    @FXML
    private void searchTrialSwimmers() {
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            try {
                int trialId = Integer.parseInt(searchText);
                TrialSwimmerDAO TDAO = new TrialSwimmerDAO();
                TrialSwimmer trial = TDAO.findById(trialId);
                if (trial != null) {
                    trialList.setAll(trial);
                } else {
                    trialList.clear();
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                TrialSwimmerDAO TDAO = new TrialSwimmerDAO();
                List<TrialSwimmer> allTrialSwimmers = TDAO.findAll();
                trialList.setAll(allTrialSwimmers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * funcion para salir
     * @throws IOException
     */
    @FXML
    private void ButtonExit()throws IOException {
        App.setRoot("Option_manipulate_trial");
    }
    @FXML
    private void ButtonModify()throws IOException {
        App.setRoot("modify_trial");
    }
    @FXML
    private void addTrialSwimmer() {
        String styleStr = styleField.getText().trim();
        String metersStr = metersField.getText().trim();
        String categoryStr = categoryField.getText().trim();
        String poolTypeStr = poolTypeField.getText().trim();
        String sexStr = sexField.getText().trim();

        if (styleStr.isEmpty() || metersStr.isEmpty() || categoryStr.isEmpty() || poolTypeStr.isEmpty() || sexStr.isEmpty()) {
            // Manejar el caso cuando los campos están vacíos
            // Puedes mostrar un mensaje de error o realizar alguna acción adicional
            return;
        }

        Meters meters;
        try {
            meters = Meters.valueOf(metersStr.toUpperCase()); // Convertir a mayúsculas antes de comparar
        } catch (IllegalArgumentException e) {
            // Manejar el caso cuando la cadena no coincide con ningún valor del enumerado
            // Puedes mostrar un mensaje de error o realizar alguna acción adicional
            return;
        }

        // Continuar con el procesamiento normal si todos los campos son válidos
        // ...
    }


    }

