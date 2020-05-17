//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:       	(Quiz Generator)
// Course:      	(CS400, Lec 004, Spring 2019)
// Author:      	(Ateam 166)
// Email:       	(xli685@wisc.edu, qgao38@wisc.edu, rliu227@wisc.edu,
//               	wang929@wisc.edu, sanguanmoo@wisc.edu)
// Lecturer's Name: (Andrew L Kuemmel)
// Due date:    	(5/2/2019)
// Known Bugs:
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:	(name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:     	(identify each person and describe their help in detail)
// Online Sources:  ()
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class collects all questions that the user add manually or add by using JSON file. The
 * database will categorize questions based on topics
 *
 * @author Our final project group
 *
 */
public class QuestionBank {
  static ArrayList<String> allTopics; // List of all topics
  static ArrayList<ArrayList<Question>> allQuestions; // List of List of Questions. This will be
                                                  	// categorized based on topic in allTopics.
                                                  	// For example, if we want to find a question
                                                  	// with a topic "a", we can find what is an
                                                  	// index of "a" in allTopics first and use
                                                  	// that index to find all questions topic "a"
                                                  	// in allQuestions

  /**
   * This is just a constructor that initiates all fields.
   */
  public QuestionBank() {
	allTopics = new ArrayList<String>();
	allQuestions = new ArrayList<ArrayList<Question>>();
  }

  /**
   * This will return a sorted list of all topics in our question bank
   *
   * @return a list of sorted topics
   */
  @SuppressWarnings("static-access")
  public ArrayList<String> getTopicList() {
	ArrayList<String> sortedTopics =
    	(ArrayList<String>) this.allTopics.stream().sorted().collect(Collectors.toList()); // usual
                                                                                       	// sort
	return sortedTopics;
  }

  /**
   * The problem after using getTopicList() is that we don't have any reference anymore, so this
   * class we will return reference for a given index in the sorted list
   *
   * @param sortedIndex is an index in the sorted list
   * @return corresponding index in the actual list
   */
  public int getCorrespondingIndex(int sortedIndex) {
	ArrayList<String> sortedTopics = getTopicList();
	String topic = sortedTopics.get(sortedIndex); // get a topic first
	return allTopics.indexOf(topic); // find such topic in actual topic list
  }

  /**
   * This method will add a valid question to a static collection of questions in the class. If
   * there is no such topic, create a new topic in allTopics and allQuestions and add
   *
   * @param question is a question we want to add
   */
  public void addQuestion(Question question) {
	if (!allTopics.contains(question.getTopic())) { // if this is a new topic
  	allTopics.add(question.getTopic()); // add a topic to allTopics
  	ArrayList<Question> newTopic = new ArrayList<>();
  	newTopic.add(question);
  	allQuestions.add(newTopic); // add a new one in allQuestions
	} else { // if such topic already exists
  	int topicNum = 0;
  	for (int i = 0; i < allTopics.size(); ++i) {
    	if (question.getTopic().equals(allTopics.get(i))) { // find such topic
      	topicNum = i; // find index
      	break;
    	}
  	}
  	allQuestions.get(topicNum).add(question); // add such question
	}
  }

  /**
   * This method will return a random question in a given topic
   *
   * @param topic is a topic for the question we want
   * @return a random question with a given topix
   */
  public Question getQuestion(String topic) {
	if (!allTopics.contains(topic)) { // if such topic is in the list, just return null
  	return null;
	} else {
  	int topicIndex = TopIndex(topic); // get such index
  	Random rand = new Random();
  	int randomIndex = rand.nextInt(allQuestions.get(topicIndex).size()); // random a question in
                                                                       	// that topic
  	return allQuestions.get(topicIndex).get(randomIndex); //return that question
	}

  }
  /**
   * This method will return all questions in the question bank
   * @return a list of all questions
   */
  public ArrayList<Question> getAllQuestions() {
	ArrayList<Question> allArrayQuestions = new ArrayList<>(); // all
                                                           	// questions
	for (int i = 0; i < allTopics.size(); ++i) { //iteratively add questions in each topic
  	for (int j = 0; j < allQuestions.get(i).size(); ++j) {
    	allArrayQuestions.add(allQuestions.get(i).get(j));
  	}
	}
	return allArrayQuestions;
  }
  /**
   * This will give a corresponding index of such topic in allTopics
   * @param topic is a topic we want to find
   * @return a corresponding index of a given topic
   */
  public int TopIndex(String topic) {
	int index = 0;
	if (!allTopics.contains(topic)) { //if there is no such topic
  	return -1;
	} else {
  	for (int i = 0; i < allQuestions.size(); i++) {
    	if (allTopics.get(i).equals(topic)) { //search through allTopics
      	index = i;
    	}
  	}
	}
	return index;

  }

}










