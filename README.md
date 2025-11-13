This is a simple project I made to develop my skills with front-end GUI and APIs. \
It is a simple truth-or-dare game where you can get truth-or-dare questions of various age ratings: PG, PG-13. \
Note: There is an 18+ "R" rating of questions that the API can provide, but I personally didn't want to have those accessible, so I removed the option.

The questions come from HTTP Requests to the Truth or Dare API (see documentation: https://docs.truthordarebot.xyz/api-docs). \
There are also questions from 2 "emergency_question_list" arrays stored in a short local string; if there is an issue with the HTTP request, then the program will use a question from one of them and warn the user with an API ISSUE message.
I used Jackson library to handle the REST/ HTTP API requests.

The GUI was made with JavaFX and FXML (JavaFX's version of XML). I tried to keep it simple since it really didn't need to be complex, but also give enough optionality 

There are 4 unique colour schemes, each with their own CSS files with style classes that specify colours and can be easily switched between from inside the game: 
- "Original" - the one I originally used when coding.
- "Dark" - a darker version of the default.
- "Hotline Miami" - a colour scheme based on the colours of the video game, Hotline Miami
- "Pride" - a combo of colours from bi, pan and trans flags because they're cool and the colours go well with each other.

Also used: Maven, IntelliJ, OOP, etc.

