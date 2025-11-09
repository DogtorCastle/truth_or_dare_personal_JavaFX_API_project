package com.example.truthordare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;


public class TOrDApplication extends Application {
    public static HttpRequest truthRequest;
    public static HttpRequest dareRequest;
    public static String[] ratings = {"pg", "pg13", "r"};
    public static String currentRating = "";
    public static ChoiceBox<String> ratingChoiceBox = new ChoiceBox<>();

    //Initialises most the necessary stuff
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TOrDApplication.class.getResource("TOrD-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 1000);
        stage.setTitle("TRUTH OR DARE!");
        stage.setScene(scene);
        stage.show();
        ratingChoiceBox.getItems().addAll(ratings);
        ratingChoiceBox.setValue("pg");
    }

    public static void main(String[] args) {
        launch();
    }
}