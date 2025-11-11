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

public class TOrDController {
    @FXML
    private Label displayText;
    public static HttpClient questionClient = HttpClient.newHttpClient();
    static Random random = new Random();
    ObjectMapper mapper = new ObjectMapper();
    public HttpRequest apiRequest; // using lowercase for API, because it is not a class.
    final String[] emergency_truth_list = {"Have you ever lied to your parents about what you were doing after school?", "Have you ever spread a false rumor on purpose?", "Would you rather be able to change the past or see into the future?", "If you had only 24 hours left to live, what would you do?", "What is the longest time that you think you could go without your cell phone?", "What flaw is enough to cause you to break off a relationship?", "How do you think you will die?", "Have you ever said anything bad about any person in this room?", "Have you ever thought about changing your name? If yes, to what?", "What was the nastiest joke you've ever made about someone?", "Have you ever been hypnotized?", "Have you ever seen a ghost?", "Would you still like your significant other/crush if he or she gained 100 pounds?", "Have you ever used money for lunch to buy other things (secretly)?", "Have you tried to forge your parent’s signature or impersonate them?", "What is your worst habit?", "What is one of your biggest fears?", "Have you ever been hpnotised?", "What do you dislike most in a friend?", "Is there anything that you would change about yourself?", "Do you keep a diary/ journal", "Have you ever been scolded by a friend", "What was the biggest lie you've told a teacher?", "What was the biggest lie you've told your parents?", "What’s the dumbest thing you ever said or did, around a boy/girl you liked or were dating?", "When was the last time you brushed your teeth?", "Have you ever eaten during the middle of the night and what?", "Have you peed in a pool?", "Would you rather have a sibling or a pet?", "what is the worst gift you have received?", "Have you ever given food to a homeless individual?", "Describe one of your most embarassin- I mean, humanising moments. (I'll only laugh a bit.)", "How often do you floss and brush your teeth?", "Who inside or outisde of the room do you have a crush on?", "How would you pull off the ultimate cheat in a game of monopoly?", "What would you do with £500?", "What was your last dream about?", "Do you sing in the shower?", "What would you do if you were a giant?", "What would you do if you could teleport?", "If you found £200 would you keep it or try to find the owner?", "What was your least believable reason to skive off school?", "If you had a time machine what time period would you visit?", "What do you like to do when you are alone?", "If you could be any celebrity, who would you be and why?", "What would you do with £2,000?", "What would you do with £1 million?", "What would be your new name, if you were allowed to choose one?", "Is there anything that you've regretted spending money on?", "What is your secret wish?", "What makes you bored out of your mind?", "It's a genie! Three wishes, what are they?", "What you want to be when you grow up?"};
    final String[] emergency_dare_list = {"Imitate an old man or lady.", "After everything you say add 'Whoa … I’m good!” for the next 10 minutes.", "Text someone using only your nose.", "Do jumping jacks until it is your turn again."};
    final String[] ratings = {"pg", "pg13"/*, "r"*/}; //r is commented out for the sake of decency due to referring to 18+ questions.
    final String[] themes = {"Original", "Pan", "Dark"};
    String currentRating = "x";
    String currentQType = "x";
    @FXML
    public ChoiceBox<String> ratingChoiceBox = new ChoiceBox<>();
    @FXML
    public void initialize(){
        ratingChoiceBox.getItems().addAll(ratings);
        ratingChoiceBox.setValue("pg");
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
            System.out.println(e.getCause());
            e.printStackTrace();
            question = emergency_question_list[random.nextInt(emergency_question_list.length)] + "\n[slow down for API]";
        }
        return question;
    }
}