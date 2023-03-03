module com.example.demobinomotron {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens Application to javafx.fxml;
    exports Application;
    exports DataBase;
    opens DataBase to javafx.fxml;
    exports Modele;
    opens Modele to javafx.fxml;
}