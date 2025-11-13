package com.example.truthordare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.truthordare.ThemeHandler.setTheme;
import static com.example.truthordare.ThemeHandler.themes;

public class TOrDController {
    @FXML
    private Label displayText;
    public static HttpClient questionClient = HttpClient.newHttpClient();
    static Random random = new Random();
    ObjectMapper mapper = new ObjectMapper();
    public HttpRequest apiRequest; // using lowercase for API, because it is not a class.
    final String[] emergency_truth_list = {"Have you ever lied to your parents about what you were doing after school?", "Have you ever spread a false rumor on purpose?", "Would you rather be able to change the past or see into the future?", "What would you do if you could teleport?", "If you found £200 would you keep it or try to find the owner?", "What was your least believable reason to skive off school?", "If you had a time machine what time period would you visit?"};
    final String[] emergency_dare_list = {"Imitate an old man or lady.", "After everything you say add 'Whoa … I’m good!” for the next 10 minutes.", "Text someone using only your nose.", "Do jumping jacks until it is your turn again."};
    String currentQType = "x";
    String currentRating = "x";
    final String[] ratings = {"pg", "pg13"/*, "r"*/}; //r is commented out for the sake of decency due to referring to 18+ questions.

    @FXML
    public ChoiceBox<String> ratingChoiceBox = new ChoiceBox<>();
    @FXML
    public ChoiceBox<Object> themeChoiceBox = new ChoiceBox<>();

    @FXML
    public void initialize(){
        //initialise and set up ratings choice box
        ratingChoiceBox.getItems().addAll(ratings);
        ratingChoiceBox.setValue("pg");
        //initialise and set up listener for theme choice box
        themeChoiceBox.getItems().addAll(themes);
        themeChoiceBox.setValue("Original"); // Set a default theme
        themeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldTheme, newTheme) -> {
                    if (newTheme != null) {
                        setTheme(newTheme.toString());
                    }
                }
        );
    }
    public void onDareButtonClick(ActionEvent actionEvent) {
        prepareRequest("dare");
        String dare = getQuestion(emergency_dare_list);
        displayText.setText(dare);
    }

    public void onTruthButtonClick(ActionEvent actionEvent) {
        prepareRequest("truth");
        String truth = getQuestion(emergency_truth_list);
        displayText.setText(truth);
    }

    private void prepareRequest(String qType) {
        if (!currentRating.equals(ratingChoiceBox.getValue()) || currentQType.equals(qType)) {
            currentRating = ratingChoiceBox.getValue();
            currentQType = qType;
            apiRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.truthordarebot.xyz/api/" + qType + "?rating="+ currentRating))
                    .build();
        }
    }

    private String getQuestion(String[] emergency_question_list) {
        String question;
        try {
            HttpResponse<String> questionResponse = questionClient.send(apiRequest, HttpResponse.BodyHandlers.ofString());
            APIResponse parsedResponse= mapper.readValue(questionResponse.body(), APIResponse.class);
            question = parsedResponse.question;
        } catch(Exception e) {
            question = emergency_question_list[random.nextInt(emergency_question_list.length)] + "\n[API ERROR]";
        }
        return question;
    }
}