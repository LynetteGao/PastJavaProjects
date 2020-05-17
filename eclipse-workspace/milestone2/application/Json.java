//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Quiz Generator)
// Course:          (CS400, Lec 004, Spring 2019)
// Author:          (Ateam 166)
// Email:           (xli685@wisc.edu, qgao38@wisc.edu, rliu227@wisc.edu,
//                  wang929@wisc.edu, sanguanmoo@wisc.edu)
// Lecturer's Name: (Andrew L Kuemmel)
// Due date:        (5/2/2019)
// Known Bugs:
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
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
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  ()
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.image.Image;

/**
 * This class is responsible for parsing the input JSON file which contains
 * questions
 *
 * @author Ateam 166
 *
 */
public class Json {
  User user;

  // A user
  /**
   * Constructor of Json class
   *
   * @param user
   */
  public Json(User user) {
    this.user = user;
  }

  /**
   * The key method of this class. Parses the given JSON file and save the
   * questions to question bank.
   *
   * @param jsonFilepath
   *        The file to be parsed.
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ParseException
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void addQuestionJson(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
    // The description of the question
    String description = "";
    // The topic of the question
    String topic = "";
    // The image of the question, in string form
    String image = "";
    // T if the choice is correct. F if the choice is incorrect
    String yn = "";
    // One choice of the question
    String choice = "";
    // Stores the correct answer
    String correctAnswer = "";
    // All choices of the question
    String[] selections;
    // The array list storing all choices of the question
    ArrayList<String> choices = new ArrayList<String>();
    // Used later to transform arraylist of string into string[]
    int index = 0;
    // number of correct selections, should be one, if not then the question is
    // discarded
    int numCorrect = 0;
    // Parsing the file
    Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
    JSONObject jo = (JSONObject) obj;
    JSONArray ja = (JSONArray) jo.get("questionArray");
    // Parse the array of questions
    Iterator itr1 = ja.iterator();
    Iterator<Map.Entry> itr2;
    Iterator<Map.Entry> itr4;
    // Iterates over all questions
    while (itr1.hasNext()) {
    itr2 = ((Map) itr1.next()).entrySet().iterator();
    // Iterates over fields of questions, such as question description, question
    // topic, etc.
    while (itr2.hasNext()) {
        Map.Entry pair = itr2.next();
        // Deal with parsing exceptions
        if (pair == null) {
        throw new ParseException(0, "there is a problem with parsing");
        }
        if (pair.getKey() == null) {
        throw new ParseException(0, "there is a problem with parsing");
        }
        // Parse each of the corresponding fields
        if (pair.getKey().equals("questionText")) {
        description = (String) pair.getValue();
        } else if (pair.getKey().equals("topic")) {
        topic = (String) pair.getValue();
        } else if (pair.getKey().equals("image")) {
        image = (String) pair.getValue();
        } else if (pair.getKey().equals("choiceArray")) {
        try {
            Object obj2 = pair.getValue();
            JSONArray ja2 = (JSONArray) obj2;
            Iterator itr3 = ja2.iterator();
            // Parse through "isCorrect" field and "choice" field
            while (itr3.hasNext()) {
            itr4 = ((Map) itr3.next()).entrySet().iterator();
            // Parse through the contents of iscorrect and choice
            while (itr4.hasNext()) {
                Map.Entry pair2 = itr4.next();
                if (pair2.getKey().equals("isCorrect")) {
                yn = (String) pair2.getValue();
                if (yn.equals("T")) {
                    ++numCorrect;
                    correctAnswer = choice;
                }
                } else if (pair2.getKey().equals("choice")) {
                choice = (String) pair2.getValue();
                choices.add(choice);
                }
            }
            }
        } catch (Exception e) {
            continue;
        }

        } else {
        continue;
        }
    }
    Image theimage = null;
    // If number of correct answer is not one, discard this question
    if (numCorrect != 1) {
        numCorrect = 0;
        choices = new ArrayList<String>();
        continue;
    }
    numCorrect = 0;
    // Deal with the case when there is no image for this question
    if (!image.equals("none")) {
        File thefile = new File(image);
        if (!thefile.exists() || thefile.isDirectory()) {
        choices = new ArrayList<String>();
        continue;
        }
        theimage = new Image(thefile.toURI().toString());
    }
    // Transform the arraylist of strings into array of strings
    selections = new String[choices.size()];
    for (String s : choices) {
        selections[index] = s;
        index++;
    }
    index = 0;
    // Add the question into the user question bank
    user.addQuestion(topic, description, selections, correctAnswer, theimage, image);
    // Initialize the choice array for next parsing
    choices = new ArrayList<String>();
    }
  }
}







