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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class is responsible for converting all the questions in the question
 * bank to a JSON file
 *
 * @author Ateam 166
 *
 */
public class OutputJSON {
  // The question bank
  private ArrayList<Question> allQuestions;

  /**
   * Constructor for the class
   *
   * @param allQuestions
   *      	the question bank
   */
  public OutputJSON(ArrayList<Question> allQuestions) {
	this.allQuestions = allQuestions;
  }

  /**
   * The key method of the class. Saves the questions into a JSON file
   *
   * @param jsonFilePath
   *      	The file name to be saved
   * @throws IOException
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void SaveJson(String jsonFilePath) throws IOException {
	JSONObject jO = new JSONObject();
	JSONArray jA = new JSONArray();
	JSONObject jo = new JSONObject();
	// Iterate over all the questions
	for (Question question : allQuestions) {
  	// Record basic information
  	jo.put("questionText", question.getDescription()); // question.getDescription
  	jo.put("topic", question.getTopic()); // question.getTopic
  	jo.put("image", question.getImageFile()); // NEED FIXATION
  	// Record all the choices of the question
  	JSONArray ja = new JSONArray();
  	for (int j = 0; j < question.choices().length; ++j) {
    	Map m = new LinkedHashMap(2);
    	if (question.choices()[j].isCorrect) {
      	m.put("isCorrect", "T");
    	} else {
      	m.put("isCorrect", "F");
    	}
    	m.put("choice", question.choices()[j].getOption());
    	ja.add(m);
  	}
  	jo.put("choiceArray", ja);
  	jA.add(jo);
  	jo = new JSONObject();
	}
	// Put all stuff under "questionArray"
	jO.put("questionArray", jA);
	// Use print writer to save the information into a JSON file named jsonFilePath
	PrintWriter pw = new PrintWriter(jsonFilePath);
	pw.write(jO.toJSONString());
	pw.flush();
	pw.close();
  }
}





