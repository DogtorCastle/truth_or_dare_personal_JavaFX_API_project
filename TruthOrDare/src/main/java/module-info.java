module com.example.truthordare {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.example.truthordare to javafx.fxml;
    exports com.example.truthordare;
}