module com.example.truthordare {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.truthordare to javafx.fxml;
    exports com.example.truthordare;
}