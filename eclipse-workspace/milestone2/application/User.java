package application;
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (User)
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

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * This class represents a user when he or she is taking the quiz. It keeps track of the all the
 * information that is needed for them to take a quiz
 * 
 * @author Member TEAM 166
 *
 */
public class User {
  public ArrayList<Question> quiz;// collection of questions for the user
  public ArrayList<Integer> userChoices;// collection of choices for each question
  // the user chooses
  public ArrayList<Integer> preference;// number of question for each topic,
  // the order is the same as the
  // topicList
  public static QuestionBank qBank;// the question bank for the user
  public double score;// final score of the quiz for the user

  /**
   * Constructor for a user
   */
  public User() {
    // initialize all fields:
    quiz = new ArrayList<Question>();
    qBank = new QuestionBank();
    userChoices = new ArrayList<Integer>();
    preference = new ArrayList<Integer>();
    score = 0;
  }

  /**
   * This method add a question to the use's question bank
   * 
   * @param topic         of the question
   * @param description   of the question
   * @param options       of the question
   * @param correctAnswer of the question
   * @param image         of the question(if there is any)
   * @param imageFile     of the question
   */
  public void addQuestion(String topic, String description, String[] options, String correctAnswer,
      Image image, String imageFile) {
    Question a = new Question(topic, description, options, correctAnswer, image, imageFile);// create
                                                                                            // a new
                                                                                            // question
    qBank.addQuestion(a);// adding the question to the bank
  }


  /**
   * This methods keep records of the topics and number of questions for each topic
   * 
   * @param topic
   * @param number of questions for the specific topic
   */
  public void getPreferenceNumberOneTopic(String topic, int number) {
    int indexOfTopic = qBank.TopIndex(topic);// get the location for the topic
    preference.add(indexOfTopic, number);// update the number of questions needed by the user
  }

  /**
   * This method generate the collection of questions for the quiz at a time
   */
  public void generateQuiz() {
    // loop through each topic to get a number of questions for each topic
    for (int i = 0; i < preference.size(); i++) {
      int num = preference.get(i);
      String topic = qBank.allTopics.get(i);
      // add the question to the quiz collector
      if (num >= qBank.allQuestions.get(i).size())
        quiz.addAll(qBank.allQuestions.get(i));
      else {
        for (int j = 0; j < num; j++) {
          quiz.add(qBank.getQuestion(topic));
        }
      }
    }

  }

  /**
   * This method records the user's answer for each question
   * 
   * @param indexOfQuestion
   * @param option          of the user
   */
  public void recordAnswer(int indexOfQuestion, int option) {
    userChoices.add(indexOfQuestion, option);
  }

  /**
   * This method calculate the score of the quiz in percentile
   * 
   * @return the score of the quiz
   */
  public double getScore() {
    int correctNum = 0;
    // loop through each question to check whether the user is correct
    for (int i = 0; i < quiz.size(); i++) {
      if (quiz.get(i).checkAnswer(userChoices.get(i))) {
        correctNum++;
      }
    }
    score = ((double) correctNum) / ((double) quiz.size());// calculate the final score
    return score;
  }

}


