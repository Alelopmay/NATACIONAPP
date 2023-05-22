package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private  static  Stage stage;

    /**
     * esta es la funcion que inicia la interfaz grafica
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws IOException
     */
    @Override

    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 936, 693);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("fotos/icono.png"))));




    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/org/example/Controlador/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }




}