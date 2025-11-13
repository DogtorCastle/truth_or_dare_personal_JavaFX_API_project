package com.example.truthordare;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;


public class TOrDApplication extends Application {
    Screen mainScreen = Screen.getPrimary();
    Rectangle2D screenDimensions = mainScreen.getVisualBounds();


    //Initialises most the necessary stuff
    public void start(Stage stage) throws IOException {
        double windowWidth = screenDimensions.getWidth()*0.618;
        double windowHeight = screenDimensions.getHeight()*0.97;
        FXMLLoader fxmlLoader = new FXMLLoader(TOrDApplication.class.getResource("TOrD-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), windowWidth, windowHeight);
        scene.getStylesheets().add(TOrDApplication.class.getResource("DefaultTheme.css").toExternalForm());
        ThemeHandler.init(scene);
        stage.setTitle("TRUTH OR DARE!");
        stage.setScene(scene);
        stage.setX(screenDimensions.getMinX());
        stage.setY(screenDimensions.getMinY());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}