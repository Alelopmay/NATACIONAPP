module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    opens org.example.Domain to javafx.base;



    opens org.example to javafx.fxml;
    opens org.example.Conections to java.xml.bind;
    exports org.example;
    exports org.example.Controlador;
    opens org.example.Controlador to javafx.fxml;
}
