//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (Quiz Generator)
// Course: (CS400, Lec 004, Spring 2019)
// Author: (Ateam 166)
// Email: (xli685@wisc.edu, qgao38@wisc.edu, rliu227@wisc.edu,
// wang929@wisc.edu, sanguanmoo@wisc.edu)
// Lecturer's Name: (Andrew L Kuemmel)
// Due date: (5/2/2019)
// Known Bugs:
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: ()
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
/**
 * This is the main class of this application, which contains all necessary GUIs.
 * @author Ateam 166
 *
 */
public class Main extends Application {
  private Stage stage; // The stage for the program
  private int numChoices;  // Number of choices of a question
  private User user;  // The user class
  private String fileName;  // The name of a file
  private Label fileNameLabel; // The name of a file in Label format
  private File file;  // A file
  private Image imageChosen;  // An image object

  /**
   * This method designs the startQuiz scene, displayed when buttonStart button is pressed
   *
   * @return Home scene
   * @throws FileNotFoundException
   */
  private Scene HomeScene() throws FileNotFoundException {
    // initiate user.qBank
    // Create label of the title:
    Label CS400Label = new Label("\nQuiz Generator\n ");
    CS400Label.setFont(new Font("Arial", 60));// change font
    CS400Label.setTextFill(Color.DARKGREY);// change color
    CS400Label.setMaxWidth(Double.MAX_VALUE);
    AnchorPane.setLeftAnchor(CS400Label, 0.0);
    AnchorPane.setRightAnchor(CS400Label, 0.0);
    CS400Label.setAlignment(Pos.CENTER); // center-aligned

    // Create label of the intro:
    Label intro = new Label("Welcome to Quiz Generator! "
        + "\nA brighter academic future starts here" + "\n(Yea who am I kidding)\n\n\n");
    intro.setFont(new Font("Arial", 15));// change font
    intro.setTextFill(Color.DARKGREY);// change color
    intro.setMaxWidth(Double.MAX_VALUE);
    
    Label warning = new Label("Don't forget to add questions before starting the quiz!");
    warning.setFont(new Font("Arial", 12));// change font
    warning.setTextFill(Color.RED);// change color
    warning.setMaxWidth(Double.MAX_VALUE);
    AnchorPane.setLeftAnchor(warning, 0.0);
    AnchorPane.setRightAnchor(warning, 0.0);
    warning.setAlignment(Pos.CENTER);
    
    AnchorPane.setLeftAnchor(intro, 0.0);
    AnchorPane.setRightAnchor(intro, 0.0);
    intro.setAlignment(Pos.CENTER);

    // Create BorderPane:
    BorderPane root = new BorderPane();
    // Create the 2 buttons on the first scene:
    Button buttonAdd = new Button();
    Button buttonStart = new Button();

    // buttonAdd directs to the scene of adding questions
    buttonAdd = bstyle(buttonAdd, 300, 60); // setting button style
    buttonAdd.setText("Add quiz");
    buttonAdd.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent t) {
        stage.setScene(AddQuestion());
    }
    });

    // buttonStart directs to the scene of doing quiz
    buttonStart = bstyle(buttonStart, 300, 60); // setting button style
    buttonStart.setText("Start quiz!");
    buttonStart.setOnAction(e->{
        if (User.qBank.getAllQuestions().size() == 0) {
        } else {
        stage.setScene(Startquiz());
        }
    }
    );

    // Put buttons into vBox:
    VBox vBox = new VBox(10);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(CS400Label, intro, warning, buttonAdd, buttonStart);
    root.setCenter(vBox);


    // Setting logo image:
    Image logoPic = new Image(new FileInputStream("application/logo.PNG"));
    ImageView logo = new ImageView(logoPic);
    logo.setFitWidth(600);
    logo.setFitHeight(800);

    root.setLeft(logo);
    return new Scene(root, 1200, 800, Color.GREY);
  }

  /**
   * This method designs the scene where the user can add questions to the question bank. The user
   * can choose whether to add a question manually or to add questions by using JSON File
   *
   * @return addQuestion scene
   */
  private Scene AddQuestion() {
    BorderPane root = new BorderPane();
    VBox vbox = new VBox(); // This will be a box for two buttons: JSON
                            // Quiz and Manual Quiz
    vbox.setAlignment(Pos.CENTER);
    Button jsonQuiz = new Button("Add quizzes from a JSON file"); // JSON
                                                                // Quiz
    Button manualQuiz = new Button("Create your own quiz"); // Manual Quiz
    jsonQuiz = bstyle(jsonQuiz, 250, 50);
    manualQuiz = bstyle(manualQuiz, 250, 50);

    vbox.getChildren().addAll(jsonQuiz, manualQuiz);
    root.setCenter(vbox);
    Button homeButton = new Button("Home"); // Home Button
    homeButton = bstyle(homeButton, 100, 50);
    homeButton.setMinWidth(100);

    root.setBottom(homeButton);

    jsonQuiz.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent t) {
        try {
        stage.setScene(JSONQuiz()); // After click a button go to
                                    // JSONQuiz
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    });
    manualQuiz.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent t) {
        try {
        stage.setScene(ManualQuiz()); // After click a button go to
                                        // ManualQuiz
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    });
    homeButton.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent t) {
        try {
        stage.setScene(HomeScene()); // After click a button go to
                                    // home scene
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }
    }
    });
    return new Scene(root);

  }

  /**
   * This scene will ask the user first that how many choices that the user want to have in a
   * question. After entering a valid number, we will use this number in next scene
   *
   * @return
   */
  private Scene ManualQuiz() {
    BorderPane root = new BorderPane();
    // Header
    Label header = new Label("\nCreate your own quiz!\n\n");
    header.setFont(new Font("Arial", 50));// change font
    header.setTextFill(Color.SKYBLUE);// change color
    header.setMaxWidth(Double.MAX_VALUE);
    AnchorPane.setLeftAnchor(header, 0.0);
    AnchorPane.setRightAnchor(header, 0.0);
    header.setAlignment(Pos.CENTER);

    // Instruction to add the number of options
    Label label = new Label(
        "   Before creating a question, please enter the valid number of choices (This should be an integer and more than 0, if not please retype):  ");
    label.setFont(new Font("Arial", 17));
    // TextField that obtain the number of questions
    TextField textField = new TextField();
    textField.setMaxWidth(50);
    HBox allButtons = new HBox(20);


    Button submit = new Button("Go!"); // submit button
    Button home = new Button("Home"); // go to home button
    submit = bstyle(submit, 100, 50);
    home = bstyle(home, 100, 50);

    allButtons.getChildren().addAll(submit, home);
    // allButtons.setAlignment(Pos.CENTER);
    root.setBottom(allButtons); // add all buttons

    HBox hbox = new HBox(20);
    hbox.getChildren().addAll(label, textField); // description and
                                                // TextField
    //hbox.setTranslateY(50);
    root.setTop(header);
    root.setCenter(hbox);

    
    
    submit.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent t) {
        try {
        numChoices = Integer.parseInt(textField.getText());
        // read the number. If not valid, throw
        if (numChoices <= 0) {
            throw new Exception(""); // the input is invalid
        }
        stage.setScene(ValidManualQuiz()); // if it's valid, go to
                                            // next page
        } catch (Exception e) {
        // e.printStackTrace();
        }
    }
    });
    home.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent t) {
        try {
        stage.setScene(HomeScene()); // just go back to home
        } catch (Exception e) {
        }
    }
    });

    return new Scene(root, 1200, 600, Color.GREY);
  }

  /**
   * This scene will ask the user to add questions by asking for JSON File. Then, this method will
   * read such file and all questions in QuestionBank
   *
   * @return displayed scene
   */
  @SuppressWarnings("static-access")
  private Scene JSONQuiz() {
    file = null; // reset file first
    BorderPane root = new BorderPane();
    // header
    Label header = new Label("\nCreate your own quiz!");
    header.setFont(new Font("Arial", 30));// change font
    header.setTextFill(Color.SKYBLUE);// change color
    header.setMaxWidth(Double.MAX_VALUE);
    AnchorPane.setLeftAnchor(header, 0.0);
    AnchorPane.setRightAnchor(header, 0.0);
    header.setAlignment(Pos.CENTER);

    // all content
    VBox fileChoosing = new VBox(10);
    Label description = new Label( // instructions
        "Please attach your valid JSON file. Otherwise, You cannot press the buttons 'Done!' and 'Add More Questions'");
    description.setFont(new Font("Arial", 15));
    Label space = new Label(""); // just create a space
    Button chooseJSON = new Button("Click here to choose JSON file");
    // chooseJSON = bstyle(chooseJSON);
    FileChooser fileChooser = new FileChooser();
    Label warningLabel = new Label(""); // will be reset if a chosen file
                                        // doesn't exist
    warningLabel.setFont(new Font("Arial", 15));
    warningLabel.setTextFill(Color.RED);
    warningLabel.setMinWidth(100);
    chooseJSON.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(final ActionEvent e) {
        fileChooser.setTitle("Choose JSON file"); // allow the user to
                                                // choose a file
        file = fileChooser.showOpenDialog(stage);
        if (file != null) { // this will show a user a file name that
                            // the user just added
        fileName = file.getName();
        fileNameLabel = new Label("File: " + fileName);
        fileNameLabel.setFont(new Font("Arial", 15));
        VBox newFileChoosing = new VBox();
        newFileChoosing.getChildren().addAll(space, description, chooseJSON, fileNameLabel,
            warningLabel); // update
                            // fileNameLabel
        root.setCenter(newFileChoosing);
        }
    }
    });
    fileNameLabel = new Label("File: (no attached file yet)"); // initial
                                                            // condition
    fileNameLabel.setFont(new Font("Arial", 15));
    fileNameLabel.setWrapText(true);
    fileChoosing.getChildren().addAll(space, description, chooseJSON, fileNameLabel, warningLabel);
    Label numQuestions = new Label("\n" + "The number of questions right now: "
        + user.qBank.getAllQuestions().size() + "       ");
    // Show number of questions the user has right now
    numQuestions.setFont(new Font("Arial", 15));
    Button done = new Button("Done!"); // This button allows the user to add
                                    // a question, if valid,
                                    // and go back to home screen.
    done = bstyle(done, 100, 50);
    done.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(final ActionEvent e) {
        if (file == null) {
        // if there is no file yet, set the Warning message
        warningLabel.setText("Warning: please make sure that your file exists!");
        } else {
        try {

            Json json = new Json(user);

            json.addQuestionJson(file.getName()); // add questions
                                                // in the
                                                // question bank
            stage.setScene(HomeScene());
            json = new Json(user); // reset json for the next time

        } catch (IOException e1) {
            if (e1.getMessage().equals("The picture is not valid")) {
            // even though the picture is not valid, we will
            // skip that question
            try {
                stage.setScene(HomeScene());
            } catch (FileNotFoundException e2) {
            }
            }
            // other than that. Tell the use the file does not exist
            warningLabel.setText("Warning: please make sure that your file is exists!");
        } catch (org.json.simple.parser.ParseException e1) {
            try {
            // just set a home scene
            stage.setScene(HomeScene());
            } catch (FileNotFoundException e2) {

            }

        }

        }
    }
    });
    Button addNewQuestion = new Button("Add More Questions");
    // add a valid question and go back to add question screen
    addNewQuestion = bstyle(addNewQuestion, 100, 50);
    addNewQuestion.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(final ActionEvent e) {
        if (file == null) {
        // if there is no file yet, set the Warning message
        warningLabel.setText("Warning: please make sure that your file exists!");
        } else {
        try {

            Json json = new Json(user);

            json.addQuestionJson(file.getName()); // add questions
                                                // in the
                                                // question bank
            stage.setScene(AddQuestion());
            json = new Json(user); // reset json for the next time

        } catch (IOException e1) {
            if (e1.getMessage().equals("The picture is not valid")) {
            // even though the picture is not valid, we will
            // skip that question
            try {
                stage.setScene(HomeScene());
            } catch (FileNotFoundException e2) {
            }
            }
            // other than that. Tell the use the file does not exist
            warningLabel.setText("Warning: please make sure that your file is exists!");
        } catch (org.json.simple.parser.ParseException e1) {
            // just set a home scene
            stage.setScene(AddQuestion());
        }
        }
    }
    });
    Button home = new Button("Home"); // just a home button
    home = bstyle(home, 100, 50);
    home.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(final ActionEvent e) {
        try {
        stage.setScene(HomeScene());
        } catch (Exception exp) {

        }
    }

    });
    HBox allButtons = new HBox(20); // this will collect all buttons
    allButtons.setAlignment(Pos.CENTER);
    allButtons.getChildren().addAll(addNewQuestion, done, home);
    root.setRight(numQuestions); // gather all things together
    root.setCenter(fileChoosing);
    root.setTop(header);
    root.setBottom(allButtons);
    return new Scene(root, 1000, 300);

  }

  /**
   * After the user set a valid number of choices, this method will give a user to enter valid
   * information for a question that the user desires
   *
   * @return a scene that the user can type all information
   */
  @SuppressWarnings("static-access")
  private Scene ValidManualQuiz() {
    ScrollPane validRoot = new ScrollPane(); // this will be the main root
                                            // of this method
    BorderPane root = new BorderPane(); // this will collect most of all
                                        // things in the class
    // header
    Label header = new Label("\nCreate your own quiz!\n\n");
    header.setFont(new Font("Arial", 30));// change font
    header.setTextFill(Color.SKYBLUE);// change color
    header.setMaxWidth(Double.MAX_VALUE);
    AnchorPane.setLeftAnchor(header, 0.0);
    AnchorPane.setRightAnchor(header, 0.0);
    header.setAlignment(Pos.CENTER);
    root.setTop(header);
    // This is just a space we want to add to make the program nicer
    Label space = new Label("       ");
    Label space1 = new Label("      ");
    Label space2 = new Label("");
    root.setLeft(space1); // left indent

    // instruction to add information
    VBox instruction = new VBox(5);
    Label instruction1 = new Label( // the first line of instruction
        "Please make sure that you fill all information (except picture, which is optional); ");
    Label instruction2 = // the second line of instruction
        new Label("otherwise, you cannot press 'Done!' and 'Add More Question' buttons.");
    instruction1.setFont(new Font("Arial", 13));
    instruction1.setTextFill(Color.RED);
    instruction2.setFont(new Font("Arial", 13));
    instruction2.setTextFill(Color.RED);
    instruction.getChildren().addAll(instruction1, instruction2);

    // display the number of questions at the right of the scene
    Label numQuestionsLabel = new Label(
        "the number of questions right now: " + user.qBank.getAllQuestions().size() + "     ");
    numQuestionsLabel.setFont(new Font("Arial", 15));
    root.setRight(numQuestionsLabel);

    // This will alphabetically display all topics to the user, and the user
    // can choose
    ArrayList<String> allTopics = new ArrayList<>(); // a copy of all topics
                                                    // in questionBank
    for (int i = 0; i < user.qBank.getTopicList().size(); ++i) {
    allTopics.add(user.qBank.getTopicList().get(i));
    }
    Collections.sort(allTopics); // sort them
    allTopics.add("other"); // add other option for the user
    ChoiceBox<String> topicChosen =
        new ChoiceBox<String>(FXCollections.observableArrayList(allTopics));

    // This will collect all details and information of a question
    VBox allDetails = new VBox(10);

    // Set a topic of a question
    HBox topic = new HBox(10);
    Label quizTopic = new Label("Quiz Topic:  ");
    quizTopic.setFont(new Font("Arial", 15));
    Label addNewTopic = new Label(" or add new topic ");
    addNewTopic.setFont(new Font("Arial", 15));
    TextField newTopic = new TextField(); // in this one, the user can add
                                        // his/her own topic
    topic.getChildren().addAll(quizTopic, topicChosen, addNewTopic, newTopic);

    // Set a question statement
    HBox allDescription = new HBox(10);
    Label description = new Label("Description:  "); // description
    description.setFont(new Font("Arial", 15));
    TextField getDescription = new TextField(); // the box that the user can
                                                // fill out
    getDescription.setPrefWidth(600);
    getDescription.setPrefHeight(50);
    allDescription.getChildren().addAll(description, getDescription);

    // Set all options with a given number of options from ManualQuiz()
    // scene
    VBox allChoices = new VBox(20);
    Label choice = new Label("Choices: ");
    choice.setFont(new Font("Arial", 15));
    allChoices.getChildren().add(choice);
    String[] choices = new String[numChoices]; // collect all choices
    TextField[] options = new TextField[numChoices]; // allow the user to
                                                    // add options
    for (int i = 0; i < numChoices; ++i) { // declare each option
    options[i] = new TextField();
    }
    for (int i = 0; i < numChoices; ++i) { // iteratively create label +
                                        // options
    Label choiceLabel = new Label(i + 1 + ": ");
    choiceLabel.setFont(new Font("Arial", 15));

    allChoices.getChildren().addAll(choiceLabel, options[i]); // iteratively
                                                                // add
                                                                // in
                                                                // allChoices
    }

    // Set the correct answer by letting the user to add a corresponding
    // index of an answer
    HBox correctAnswer = new HBox(10);
    Label correctLabel =
        new Label("Correct Answer (Please choose a number corresponding to a correct choice): ");
    correctLabel.setFont(new Font("Arial", 15));
    Integer[] oneToNum = new Integer[numChoices]; // [1,2,...,numChoices]
                                                // will be displayed in
                                                // ChoiceBox
    for (int i = 0; i < numChoices; ++i) {
    oneToNum[i] = new Integer(i) + 1;
    }
    ChoiceBox<Integer> correctNum = // allow user to choose an answer
        new ChoiceBox<Integer>(FXCollections.observableArrayList(oneToNum));
    correctAnswer.getChildren().addAll(correctLabel, correctNum);

    // This will ask the user to enter image file, if the user wants to
    HBox image = new HBox(10);
    Label imageLabel = new Label("image file: ");
    imageLabel.setFont(new Font("Arial", 15));
    Button choosingImage = new Button("Browse your image (.png, .jpg, or, .jpeg)");
    choosingImage = bstyle(choosingImage, 100, 50);
    FileChooser fileChooser = new FileChooser();
    fileNameLabel = new Label("File: (no attached file yet)"); // initial
                                                            // message
                                                            // of
                                                            // fileNameLabel
    fileNameLabel.setFont(new Font("Arial", 15));
    imageChosen = null; // reset image
    choosingImage.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(final ActionEvent e) {
        try {
        fileChooser.setTitle("Choose Picture file"); // this will
                                                    // allow the
                                                    // user to
                                                    // pick a
                                                    // picture
                                                    // from
                                                    // his/her
                                                    // computer
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {

            fileName = file.getName();
            if (!fileName.toLowerCase().endsWith("png") && !fileName.toLowerCase().endsWith("jpeg")
                && !fileName.toLowerCase().endsWith("jpg")) { // allow
                                                            // only
                                                            // specific
                                                            // types
            throw new Exception();
            }
            fileNameLabel.setText("File: " + fileName); // display
                                                        // the name
                                                        // of the
                                                        // file if
                                                        // nothing
                                                        // is wrong
        }

        } catch (Exception exp) {
        fileName = null; // if there is a problem just reset
                        // fileName
        }
    }
    });

    image.getChildren().addAll(imageLabel, choosingImage, fileNameLabel);

    allDetails.getChildren().addAll(instruction, topic, allDescription, allChoices, correctAnswer,
        image, space); // gather
                    // all
                    // things
                    // together
    root.setCenter(allDetails);
    VBox allButtonsWithSpace = new VBox(20); // collect all Buttons with
                                            // indent space
    HBox allButtons = new HBox(20); // all Buttons
    Label space3 = new Label("      "); // indent
    Button home = new Button("Home"); // go back to home
    Button addMoreQuestions = new Button("Add More Questions"); // add a
                                                                // question
                                                                // and go
                                                                // back to
                                                                // AddQuestion()
    Button done = new Button("Done!"); // add a question and go back to home
    home = bstyle(home, 100, 50);
    addMoreQuestions = bstyle(addMoreQuestions, 100, 50);
    done = bstyle(done, 100, 50);
    allButtons.getChildren().addAll(space3, addMoreQuestions, done, home);
    allButtonsWithSpace.getChildren().addAll(allButtons, space2);
    root.setBottom(allButtonsWithSpace);

    home.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent t) {
        try {
        stage.setScene(HomeScene()); // This one simply turns back
                                    // to home screen
        } catch (Exception e) {
        }
    }
    });
    addMoreQuestions.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent t) { // We need to check that all
                                        // information is valid
        try {
        String validTopic = null; // get a topic
        if (topicChosen.getValue() != null && !topicChosen.getValue().equals("other")) {
            validTopic = topicChosen.getValue(); // when the user
                                                // choose box of
                                                // topic but not
                                                // other
        } else if (newTopic.getText() != null && !newTopic.getText().equals("")) {
            validTopic = newTopic.getText(); // otherwise, get the
                                            // text at newTopic
        } else {
            throw new Exception(""); // if it's not valid, we don't
                                    // allow the user to add a
                                    // question
        }
        String description; // get a description
        if (getDescription.getText() != null && !getDescription.getText().equals("")) {
            description = getDescription.getText();
        } else {
            throw new Exception(""); // if it's not valid, we don't
                                    // allow the user to add a
                                    // question
        }
        for (int i = 0; i < numChoices; ++i) {
            if (options[i].getText() != null && !options[i].getText().equals("")) {
            choices[i] = options[i].getText(); // get all
                                                // choices
            } else {
            throw new Exception(""); // if the option is blank,
                                    // we don't allow the
                                    // user to add a
                                    // question
            }
        }
        String correctChoice; // get correct choice
        if (correctNum.getValue() != null) {
            correctChoice = choices[correctNum.getValue() - 1];
        } else {
            throw new Exception(); // need to choose correct choice
        }
        if (file != null) { // if file is not null, transform that
                            // file to Image object
            imageChosen = new Image(file.toURI().toString());
            user.qBank.addQuestion(new Question(validTopic, description, choices, correctChoice,
                imageChosen, fileName)); // add
                                        // a
                                        // question
        } else { // if file is null, just add none picture
            user.qBank.addQuestion(
                new Question(validTopic, description, choices, correctChoice, null, "None"));
        }
        stage.setScene(AddQuestion()); // change scene
        } catch (Exception e) {
        }

    }
    });
    done.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent t) { // We need to check that all
                                        // information is valid
        try {
        String validTopic = null; // get a topic
        if (topicChosen.getValue() != null && !topicChosen.getValue().equals("other")) {
            validTopic = topicChosen.getValue(); // when the user
                                                // choose box of
                                                // topic but not
                                                // other
        } else if (newTopic.getText() != null && !newTopic.getText().equals("")) {
            validTopic = newTopic.getText(); // otherwise, get the
                                            // text at newTopic
        } else {
            throw new Exception(""); // if it's not valid, we don't
                                    // allow the user to add a
                                    // question
        }
        String description; // get a description
        if (getDescription.getText() != null && !getDescription.getText().equals("")) {
            description = getDescription.getText();
        } else {
            throw new Exception(""); // if it's not valid, we don't
                                    // allow the user to add a
                                    // question
        }
        for (int i = 0; i < numChoices; ++i) {
            if (options[i].getText() != null && !options[i].getText().equals("")) {
            choices[i] = options[i].getText(); // get all
                                                // choices
            } else {
            throw new Exception(""); // if the option is blank,
                                    // we don't allow the
                                    // user to add a
                                    // question
            }
        }
        String correctChoice; // get correct choice
        if (correctNum.getValue() != null) {
            correctChoice = choices[correctNum.getValue() - 1];
        } else {
            throw new Exception(); // need to choose correct choice
        }
        if (file != null) { // if file is not null, transform that
                            // file to Image object
            imageChosen = new Image(file.toURI().toString());
            user.qBank.addQuestion(new Question(validTopic, description, choices, correctChoice,
                imageChosen, fileName)); // add
                                        // a
                                        // question
        } else { // if file is null, just add none picture
            user.qBank.addQuestion(
                new Question(validTopic, description, choices, correctChoice, null, "None"));
        }
        stage.setScene(HomeScene()); // change scene
        } catch (Exception e) {
        }

    }
    });
    validRoot.setContent(root); // set them to be scrollable
    return new Scene(validRoot);

  }

  /**
    * This method design the scene where user can select the number of
    * questions he or she would like to do for each topic. The number will be
    * used for quiz generation. Displayed when startQuiz button is pressed.
    *
    * @return startQuiz scene
    * @throws FileNotFoundException
    */
    @SuppressWarnings("static-access")
    private Scene Startquiz() {
        user.preference = new ArrayList<Integer>();
        user.quiz = new ArrayList<Question>();
        user.userChoices = new ArrayList<Integer>();
        user.score = 0;
        // Creating guidance info label:
        Label label = new Label("Please enter the number of question you want for each topic:");
        label.setFont(new Font("Arial", 15));
        ScrollPane root = new ScrollPane();
        BorderPane spaceWithTopic = new BorderPane();
        root.setContent(spaceWithTopic);
        VBox topic = new VBox(20);
        spaceWithTopic.setCenter(topic);
        topic.getChildren().addAll(label);
        topic.setAlignment(Pos.CENTER);

        // List every topic with a text box asking
        // the number of questions the user would like to have:
        for (int i = 0; i < user.qBank.getTopicList().size(); i++) {
            Label nameLabel = new Label("Topic : " + user.qBank.getTopicList().get(i)
                    + "     Number of all questions for this topic: " + user.qBank.allQuestions.get(user.qBank.getCorrespondingIndex(i)).size());
            TextField text = new TextField();
            // topic.getChildren().add(i, text);
            topic.getChildren().addAll(nameLabel, text);
        }

        // Pressing startButton:
        // Generating quiz based on user's input,
        // And directing the user to the scene of displaying the first question
        Button startButton = new Button("Start!");
        startButton = bstyle(startButton, 100, 50);
        topic.getChildren().addAll(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ObservableList<Node> List = topic.getChildren();
                Integer[] inputTopic = new Integer[user.qBank.allTopics.size()];
                int sortedTopicNumber = 0;
                for (int a = 0; a < List.size(); a++) {
                    if (List.get(a).getClass().getName().equals("javafx.scene.control.TextField")) {
                        TextField tf = (TextField) List.get(a);
                        try {
                            int num1 = Integer.parseInt(tf.getText());
                            inputTopic[user.qBank.getCorrespondingIndex(sortedTopicNumber)] = num1;
                            sortedTopicNumber++;
                        } catch (NumberFormatException ex) {
                            inputTopic[user.qBank.getCorrespondingIndex(sortedTopicNumber)] = 0;
                            sortedTopicNumber++;
                            continue;
                        } catch (NullPointerException ex) {

                            inputTopic[user.qBank.getCorrespondingIndex(sortedTopicNumber)] = 0;
                            sortedTopicNumber++;
                            continue;
                        }

                    }
                }
                for (int a = 0; a < inputTopic.length; a++) {
                    user.preference.add(inputTopic[a]);
                }
                try {
                    user.generateQuiz();
                    stage.setScene(question(0));
                } catch (IndexOutOfBoundsException | NullPointerException e1) {
                 
                }
                user.preference = new ArrayList<Integer>();
            }
        }

        );
        root.setContent(topic);
        root.setMinSize(1500, 1000);

        return new Scene(root, 1200, 800, Color.GREY);
    }
  /**
   * This scene shows up when the user is taking the quiz. The user is doing the ith question,
   * where i is indexOfQuesion
   * @param indexOfQuestion: the index of question the user is working on
   * @return answer scene
   */
  public Scene question(int indexOfQuestion) {
    Integer questionNumber = indexOfQuestion + 1; 
        // The actual index displayed on screen starts with 1
    Integer allQuestionsNumber = user.quiz.size(); // the total number of questions of the quiz
    // The label appears on top of GUI
    Label questionNumberLabel =
        new Label("Question " + questionNumber.toString() + "/" + allQuestionsNumber.toString());
    questionNumberLabel.setFont(new Font("Arial", 20));// change font
    questionNumberLabel.setTextFill(Color.GREY);// change color
    questionNumberLabel.setMaxWidth(Double.MAX_VALUE);

    AnchorPane.setLeftAnchor(questionNumberLabel, 0.0);
    AnchorPane.setRightAnchor(questionNumberLabel, 0.0);
    questionNumberLabel.setAlignment(Pos.CENTER); // center-aligned
    Question question = user.quiz.get(indexOfQuestion);

    ArrayList<CheckBox> boxes = new ArrayList<CheckBox>();
    // Generate all check boxes of the question
    for (int i = 0; i < question.choices().length; i++) {
    CheckBox box = new CheckBox(question.choices()[i].option);
    box.setFont(new Font("Arial", 15));
    boxes.add(box);
    }
    // Check answer button leads to new scene
    Button check = new Button("Check Answer");
    check = bstyle(check, 100, 50);
    check.setOnAction(e -> questionEvent(indexOfQuestion, boxes, false));

    BorderPane root = new BorderPane();
    VBox vbox = new VBox(10);
    vbox.setAlignment(Pos.CENTER_LEFT);
    // Get the description of the question and set it into the GUI
    Label description = new Label(question.getDescription());
    description.setFont(new Font("Arial", 15));
    description.setPrefWidth(1000);
    description.setWrapText(true);
    Label space = new Label("                   ");
    vbox.getChildren().addAll(description);
    // Add image to the question if there exists an image
    if (question.getImage() != null) {
    ImageView imageV = new ImageView(question.getImage());
    imageV.setFitWidth(200);
    imageV.setFitHeight(200);
    vbox.getChildren().addAll(imageV);
    }

    vbox.getChildren().addAll(boxes);
    vbox.getChildren().addAll(check);

    root.setCenter(vbox);
    root.setTop(questionNumberLabel);
    root.setLeft(space);
    return new Scene(root, 1200, 800, Color.GREY);
  }

  /**
     * This is the event after clicking button check in question scene.
     * Check and record user answer from check boxes, and direct to the answer scene.
     * @param indexOfQuestion
     * @param boxes
     * @param isEnd
     */
  private void questionEvent(int indexOfQuestion, ArrayList<CheckBox> boxes, boolean isEnd) {

    int selected = 0; // number of check boxes selected
    int userAnswer = -1; // default user's answer
    
    for (CheckBox box : boxes) {
    if (box.isSelected()) {
        userAnswer = boxes.indexOf(box);
        selected++;
    }
    // more than one check box selected
    if (selected > 1) {
        userAnswer = -1;
        stage.setScene(question(indexOfQuestion));
        return;
    }
    }
    if (selected == 0) { // no answer selected
    stage.setScene(question(indexOfQuestion));
    return;
    }
    if (userAnswer < user.quiz.get(indexOfQuestion).choices().length) {
// Record answer without checking correctness yet:
    user.recordAnswer(indexOfQuestion, userAnswer);
    }

    stage.setScene(answer(indexOfQuestion));
  }
  /**
   * This scene shows up when the user finishes each question.
   * @param indexOfQuestion: the index of question
   * @return the next question scene or end quiz scene
   */
  public Scene answer(int indexOfQuestion) {

    BorderPane root = new BorderPane();
    VBox vbox = new VBox(20);
    // Get the question
    Question question = user.quiz.get(indexOfQuestion);
    Label space = new Label("                   ");
    Label description = new Label(question.getDescription());
    description.setFont(new Font("Arial", 15));
    description.setPrefWidth(1000);
    description.setWrapText(true);
    vbox.getChildren().addAll(description);

    int answer = user.userChoices.get(indexOfQuestion);
    // Shows necessary information, such as correct answer, your answer, etc.
    if (answer != -1) {
    String userAnswer = question.getChoice(answer).option;
    Label corAns = new Label("Correct answer: " + question.corAn());
    Label usAns = new Label("Your answer: " + userAnswer);
    corAns.setFont(new Font("Arial", 15));
    usAns.setFont(new Font("Arial", 15));
    vbox.getChildren().addAll(corAns, usAns);
    }
    // Next question button
    Button next = new Button("Next Question");
    next = bstyle(next, 100, 50);
    next.setOnAction(e -> stage.setScene(question(indexOfQuestion + 1)));
    // Submit quiz button
    Button submit = new Button("Submit Quiz");
    submit = bstyle(submit, 100, 50);
    submit.setOnAction(e -> stage.setScene(endQuiz()));

    if (indexOfQuestion + 1 < user.quiz.size()) {
    vbox.getChildren().add(next);
    } else {
    vbox.getChildren().add(submit);
    }

    vbox.setAlignment(Pos.CENTER_LEFT);
    root.setCenter(vbox);
    root.setLeft(space);
    return new Scene(root, 1200, 800, Color.GREY);
  }
  /**
   * The end quiz scene
   * @return Determined on what the user wants. For example, if he wants save the questions,
   * then lead to a new scene saving the questions
   */
  public Scene endQuiz() {
    BorderPane root = new BorderPane();
    // Show the score
    Label conclusion = new Label("Congratulations! \nHere is your Score:");
    Label score = new Label(Double.toString(user.getScore() * 100) + "% \n\n");
    Label space = new Label("   ");
    Label space2 = new Label("  ");
    conclusion.setFont(new Font("Arial", 30));
    score.setFont(new Font("Arial", 30));
    // Three options for the user, as described in the buttons
    Button save = new Button("Save the quiz and exit!");
    Button retakeQuiz = new Button("Reset and take a quiz again");
    Button exit = new Button("Exit without save");
    save = bstyle(save, 100, 50);
    retakeQuiz = bstyle(retakeQuiz, 100, 50);
    exit = bstyle(exit, 100, 50);
    // Set to corresponding scenes with different buttons clicked
    exit.setOnAction(e -> {
    stage.setScene(ExitConfirmation());
    });

    retakeQuiz.setOnAction(e -> stage.setScene(Startquiz()));
    save.setOnAction(e -> stage.setScene(saveFile()));
    // Set all buttons
    HBox allButtons = new HBox(20);
    allButtons.getChildren().addAll(save, space, retakeQuiz, space2, exit);
    allButtons.setAlignment(Pos.CENTER);
    VBox vbox = new VBox(20);
    vbox.getChildren().addAll(conclusion, score, allButtons);
    vbox.setAlignment(Pos.CENTER);
    root.setCenter(vbox);
    return new Scene(root, 1200, 800, Color.GREY);
  }
  /**
   * The save file scene
   * @return Go to goodbye scene
   */
  @SuppressWarnings("static-access")
  public Scene saveFile() {
    VBox root = new VBox(20);
    HBox nameYourFile = new HBox(20);
    // The label is used for prompting user entering the file name
    Label nameLabel = new Label("       Name your file (don't need to fill .json)");
    TextField fileName = new TextField();
    nameYourFile.getChildren().addAll(nameLabel, fileName);
    HBox allButtons = new HBox(20);
    // Two user selections
    Button back = new Button("Back");
    Button save = new Button("Save and exit");
    back = bstyle(back, 100, 50);
    save = bstyle(save, 100, 50);
    // Add all buttons
    allButtons.getChildren().addAll(back, save);
    allButtons.setAlignment(Pos.CENTER);
    root.getChildren().addAll(nameYourFile, allButtons);
    // Set scenes
    back.setOnAction(e -> {
    stage.setScene(endQuiz());
    });
    save.setOnAction(e -> {
    if (fileName.getText() != null) {
        String jsonFilePath = fileName.getText() + ".json";
        OutputJSON output = new OutputJSON(user.qBank.getAllQuestions());
        try {
        output.SaveJson(jsonFilePath);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        // Go to goodbye scene
        stage.setScene(goodByeScene());
    }
    });
    return new Scene(root);
  }
  /**
   * Confirms that the user wants to exit without save
   * @return Terminates if selected yes, otherwise go back one step
   */
  public Scene ExitConfirmation() {
    VBox root = new VBox(20);
    // Prompting user for exit without save
    Label exit = new Label("\n  Are you sure to exit without saving questions?  ");
    exit.setFont(new Font("Arial",20));
    HBox yesNo = new HBox(20);
    // Two buttons, yes and no
    Button yes = new Button("Yes");
    Button no = new Button("No");
    yes = bstyle(yes, 100, 50);
    no = bstyle(no, 100, 50);
    // Set scenes
    yesNo.getChildren().addAll(yes, no);
yesNo.setAlignment(Pos.CENTER);
    yes.setOnAction(e -> {
    stage.setScene(goodByeScene());
    });
    no.setOnAction(e -> {
    stage.setScene(endQuiz());
    });
    root.getChildren().addAll(exit, yesNo);
    return new Scene(root);
  }
  /**
   * The goodbye scene of the application
   * @return Program terminates
   */
  public Scene goodByeScene() {
    PauseTransition delay = new PauseTransition(Duration.seconds(5));
    // Close the program
    delay.setOnFinished(event -> stage.close());
    delay.play();
    BorderPane root = new BorderPane();
    VBox allMessage = new VBox(20);
    // The goodbye label
    Label CS400Label = new Label("\nGood bye! Good luck with your exam next week <3 \n ");
    CS400Label.setFont(new Font("Arial", 20));// change font
    CS400Label.setTextFill(Color.BLUE);// change color
    CS400Label.setMaxWidth(Double.MAX_VALUE);
    AnchorPane.setLeftAnchor(CS400Label, 0.0);
    AnchorPane.setRightAnchor(CS400Label, 0.0);
    CS400Label.setAlignment(Pos.CENTER); // center-aligned
    Label time = new Label("The program will close in 5 seconds");
    time.setFont(new Font("Arial", 10));// change font
    time.setTextFill(Color.DARKGREY);// change color
    time.setMaxWidth(Double.MAX_VALUE);
    AnchorPane.setLeftAnchor(time, 0.0);
    AnchorPane.setRightAnchor(time, 0.0);
    time.setAlignment(Pos.CENTER);
    allMessage.getChildren().addAll(CS400Label, time);
    root.setCenter(allMessage);
    return new Scene(root);
  }
  /**
   * This method takes in a button and style it.
   * @param b the button
   * @param minWidth
   * @param prefHeight
   * @return a button
   */
  public Button bstyle(Button b, int minWidth, int prefHeight) {
    b.setMinWidth(minWidth);
    b.setPrefHeight(prefHeight);
    b.setStyle(
        "-fx-background-color: linear-gradient(to bottom, derive(-fx-text-box-border, -10%), "
            + "-fx-text-box-border),linear-gradient(from 0px 0px to 0px 5px, "
            + "derive(-fx-control-inner-background, -9%), -fx-control-inner-background);");

    DropShadow shadow = new DropShadow();
    // Adding the shadow when the mouse cursor is on
    b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent e) {
        b.setEffect(shadow);
    }
    });
    // Removing the shadow when the mouse cursor is off
    b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent e) {
        b.setEffect(null);
    }
    });
    return b;
  }

  /**
   * This method is the driver of displaying all Scenes:
   */
  @Override
  public void start(Stage primaryStage) {
    user = new User();

    try {
    stage = primaryStage;
    // stage.setMaximized(true);
    Scene scene = HomeScene();
    primaryStage.setScene(scene);
    primaryStage.setTitle("Quiz Generator");
    primaryStage.show();

    } catch (Exception e) {
    e.printStackTrace();
    }
  }
  /**
   * The main method
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}







