package org.example.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.App;
import org.example.Domain.MarkRecord;
import org.example.Domain.Swimmer;
import org.example.Domain.TrialSwimmer;
import org.example.dao.MarkrecordDAO;
import org.example.dao.SwimmerDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ControllerShowMark {
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonShow;
    @FXML
    private TextField IntCod;
    @FXML
    private TextField IntId;
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private TableView<MarkRecord> MarkSwimmerTableview;
    @FXML
    private TableColumn<MarkRecord, Integer> IdColumn;
    @FXML
    private TableColumn<MarkRecord, Integer> CodColumn;
    @FXML
    private TableColumn<MarkRecord, Integer> NameColumn;
    @FXML
    private TableColumn<MarkRecord, String> TimeColumn;
    @FXML
    private TableColumn<MarkRecord, LocalDate> DateColumn;
    private ObservableList<MarkRecord> MarkList = FXCollections.observableArrayList();

    /**
     * Esta función es para iniciar el mostrar marcas
     *
     * @throws IOException
     */
    @FXML
    private void ButonShow() throws IOException {
        showMarkSwimmer();
    }

    /**
     * funcion para mostrar los datos en la tabla
     */
    @FXML
    private void initialize() {
        try{
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("Cod_Swimmer"));
            CodColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
            TimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
            DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            MarkrecordDAO MDAO=new MarkrecordDAO();
            List<MarkRecord> aux = MDAO.findAll();
            MarkList.setAll(aux);



            MarkSwimmerTableview.setItems(MarkList);
        } catch (SQLException e) {
            System.out.println("no puedes mostrar");
        }
    }

    /**
     * Función para mostrar las marcas en la tabla y en la gráfica
     */
    private void showMarkSwimmer() {
        MarkrecordDAO markrecordDAO = new MarkrecordDAO();
        int codSwimmer = Integer.parseInt(IntCod.getText());
        int trialId = Integer.parseInt(IntId.getText());
        try {
            List<MarkRecord> result = markrecordDAO.findBySwimmerAndTrialSwimmer(codSwimmer, trialId);

            // Ordenar los registros por fecha
            Collections.sort(result, Comparator.comparing(MarkRecord::getDate));

            // Limpiar la lista antes de agregar los nuevos registros
            MarkList.clear();
            MarkList.addAll(result);
            MarkSwimmerTableview.setItems(MarkList);

            // Limpiar la gráfica antes de agregar los nuevos datos
            lineChart.getData().clear();

            // Crear una serie de datos para la gráfica
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Marcas");

            // Agregar los puntos a la serie de datos
            for (MarkRecord markRecord : result) {
                // Convertir el tiempo a un valor numérico (segundos)
                double timeInSeconds = convertTimeToSeconds(markRecord.getTime());
                series.getData().add(new XYChart.Data<>(markRecord.getDate().toString(), timeInSeconds));
            }

            // Configurar la gráfica
            lineChart.setTitle("Marcas de Nadador");
            lineChart.setLegendVisible(false);
            lineChart.setCreateSymbols(true);
            lineChart.setAnimated(false);
            lineChart.getData().add(series);

            // Obtener el contenedor desde el archivo FXML
            VBox chartContainer = (VBox) lineChart.getParent();

            // Actualizar el contenedor con la gráfica
            chartContainer.getChildren().clear();
            chartContainer.getChildren().add(lineChart);
        } catch (SQLException e) {
            // Error al obtener los datos de la base de datos, mostrar alerta de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de base de datos");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo obtener los registros de marca de la base de datos.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }


    /**
     *
     * @param time
     * @return funcion que combierte un string a double
     */
    private double convertTimeToSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        int decimas = Integer.parseInt(parts[2]);

        return minutes * 60 + seconds + decimas / 10.0;
    }




    /**
         * funcion para salir
         * @throws IOException
         */
    @FXML
    private void ButtonExit() throws IOException {
        App.setRoot("Option_Mark");
    }

}
