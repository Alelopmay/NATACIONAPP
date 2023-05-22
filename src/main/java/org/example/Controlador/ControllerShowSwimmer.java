package org.example.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.Domain.Swimmer;
import org.example.dao.SwimmerDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ControllerShowSwimmer {
    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonModifySwimmer;
    @FXML
    private TableView<Swimmer> swimmerTableView; // Crear una variable TableView para los nadadores
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<Swimmer, Integer> CodColumn;
    @FXML
    private TableColumn<Swimmer, String> nameColumn;
    @FXML
    private TableColumn<Swimmer, String> lastnameColumn;
    @FXML
    private TableColumn<Swimmer, Integer> ageColumn;
    @FXML
    private TableColumn<Swimmer, String> sexColumn;
    @FXML
    private TableColumn<Swimmer, String> categoryColumn;


    /**
     * ObservableList que crea un FXcollection para mostrar un arraylist en la tabla del FXML
     */
    private ObservableList<Swimmer> swimmerList = FXCollections.observableArrayList();

    /**
     * funcion para salir
     * @throws IOException
     */
    @FXML
    private void handleButtonExit() throws IOException {
        App.setRoot("adSw");
    }

    /**
     * esta funcion para poner los datos en la tabla de nadadores
     */
    public void initialize() {

        try {

            CodColumn.setCellValueFactory(new PropertyValueFactory<>("Cod_Swimmer"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("Last_Name"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
            sexColumn.setCellValueFactory(new PropertyValueFactory<>("Sex"));



            SwimmerDAO SDAO= new SwimmerDAO();
            List<Swimmer> aux = SDAO.findAll();
            swimmerList.setAll(aux);

            //swimmerList.addAll(SDAO.findAll());

            swimmerTableView.setItems(swimmerList);
        } catch (SQLException e) {
           System.out.println("no puedes mostrar");
        }
    }
    @FXML
    private void ButtonExit()throws IOException {
        App.setRoot("adSw");
    }
    @FXML
    private void ButtonModify()throws IOException {
        App.setRoot("modify_swimmer");
    }

    /**
     * esta funcion es para hacer el buscador en la tabla
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








}
