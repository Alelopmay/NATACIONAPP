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
import org.example.dao.SwimmerDAO;
import org.example.dao.TrialSwimmerDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ControllerTable {
    @FXML
    private Button ButtonAdd;
    @FXML
    private Button ButtonExit;
    @FXML
    private Button ButtonModify;
    @FXML
    private TableView<Swimmer> swimmerTableView;
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<Swimmer, Integer> CodColumn;
    @FXML
    private TableColumn<Swimmer, String> nameColumn;
    @FXML
    private TableView<TrialSwimmer> TrialTableViewt;
    @FXML
    private TextField searchFieldt;
    @FXML
    private TableColumn<TrialSwimmer, Integer> IdColumn;
    @FXML
    private TableColumn<TrialSwimmer, String> CategoryColumn;
    @FXML
    private TableColumn<TrialSwimmer, String> StyleColumn;
    @FXML
    private TableColumn<TrialSwimmer, String> poolTypeColumn;
    @FXML
    private TableColumn<TrialSwimmer, Meters> MetersColumn;
    @FXML
    private TableColumn<TrialSwimmer, String> SexColumn;
    @FXML
    private TableColumn<TrialSwimmer, String>  lastnameColumn;
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

    private ObservableList<Swimmer> swimmerList = FXCollections.observableArrayList();
    private ObservableList<TrialSwimmer> trialList = FXCollections.observableArrayList();



    /**
     * funcion para ir al modificar marca
     * @throws IOException
     */
    @FXML
    private void ButonModifyMark() throws IOException {
        App.setRoot("Modify_mark");
    }

    /**
     * funcion para añadir una marca
     * @throws IOException
     */
    @FXML
    private void ButonaddMark() throws IOException {
        App.setRoot("add_mark");
    }
    @FXML
    private void ButtonExit() throws IOException {
        App.setRoot("Option_Mark");
    }

    /**
     * esta funcion es para la tabla de nadadores
     */
    public void initialize() {

        try {

            CodColumn.setCellValueFactory(new PropertyValueFactory<>("Cod_Swimmer"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("Last_Name"));




            SwimmerDAO SDAO= new SwimmerDAO();
            List<Swimmer> aux = SDAO.findAll();
            swimmerList.setAll(aux);

            //swimmerList.addAll(SDAO.findAll());

            swimmerTableView.setItems(swimmerList);
        } catch (SQLException e) {
            System.out.println("no puedes mostrar");
        }
        try {

            IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
            CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
            StyleColumn.setCellValueFactory(new PropertyValueFactory<>("style"));
            poolTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Pool_Type"));
            MetersColumn.setCellValueFactory(new PropertyValueFactory<>("Meters"));
            SexColumn.setCellValueFactory(new PropertyValueFactory<>("Sex"));

            TrialSwimmerDAO TDAO=new TrialSwimmerDAO();
            trialList.addAll(TDAO.findAll());

            TrialTableViewt.setItems(trialList);
        } catch (SQLException e) {
            // handle SQL exception
        }
    }
    /**
     * esta funcion es para hacer el buscador en la tabla de el nadador
     */
    @FXML
    private void searchSwimmer() {
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            try {
                SwimmerDAO swimmerDAO = new SwimmerDAO();
                List<Swimmer> searchResults = swimmerDAO.search(searchText);
                swimmerList.setAll(searchResults);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                SwimmerDAO swimmerDAO = new SwimmerDAO();
                List<Swimmer> allSwimmers = swimmerDAO.findAll();
                swimmerList.setAll(allSwimmers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * funcion para mostrar las pruebas en la tabla
     */
    public void initialize2() {

        try {

            IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
            CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
            StyleColumn.setCellValueFactory(new PropertyValueFactory<>("style"));
            poolTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Pool_Type"));
            MetersColumn.setCellValueFactory(new PropertyValueFactory<>("Meters"));
            SexColumn.setCellValueFactory(new PropertyValueFactory<>("Sex"));

            TrialSwimmerDAO TDAO=new TrialSwimmerDAO();
            trialList.addAll(TDAO.findAll());

            TrialTableViewt.setItems(trialList);
        } catch (SQLException e) {
            // handle SQL exception
        }
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
            meters = Meters.valueOf(metersStr);
        } catch (IllegalArgumentException e) {
            // Manejar el caso cuando la cadena no coincide con ningún valor del enumerado
            // Puedes mostrar un mensaje de error o realizar alguna acción adicional
            return;
        }

        // Continuar con el procesamiento normal si todos los campos son válidos
        // ...
    }


}
