package application;

import javafx.scene.image.Image;

/**
 * This class represents each valid question by collecting topic, description, choices, and image
 * file in one node. This will be collected in QuestionBank.java
 *
 * @author Our final project team
 *
 */
public class Question {
  private String topic; // topic of the question
  private String description; // question statement
  private Answer[] choices; // choices and whether they are true or not
  private Image image; // image file
  private String corAn; // correct answer
  ////////////////////////////////
  private String imageFile; // name of image file. This will be used in OutputJSON.java
  ////////////////////////////////

  /**
   * This is a class for each choice which provides a description of that choice and whether such
   * choice is true or not
   *
   * @author Our final project team
   *
   */
  static class Answer {
	String option; // description of choice
	boolean isCorrect; // true or false

	/**
 	*
 	* @return description of choice
 	*/
	public String getOption() {
  	return option;
	}

	/**
 	* constructor with a given option and a given boolean
 	*
 	* @param option is a description of choice
 	* @param    	isCorrect: true or false
 	*/
	public Answer(String option, boolean isCorrect) {
  	this.option = option;
  	this.isCorrect = isCorrect;
	}
  }

  /**
   * This constructor will set all fields in this class
   *
   * @param topic     	is a given topic
   * @param description   is a given statement of the question
   * @param options   	is an array of all options
   * @param correctAnswer is a correct option
   * @param image     	is Image object that we want to display
   * @param imageFile 	is the name of image file
   */
  public Question(String topic, String description, String[] options, String correctAnswer,
  	Image image, String imageFile) {
	// set all fields
	this.topic = topic;
	this.description = description;
	this.image = image;
	this.corAn = correctAnswer;
	this.imageFile = imageFile;
	choices = new Answer[options.length];
	// set choices
	for (int i = 0; i < options.length; ++i) {
  	if (options[i].equals(correctAnswer)) {
    	choices[i] = new Answer(options[i], true); // if such choice is the same with correctAnswer,
                                               	// set as true
  	} else {
    	choices[i] = new Answer(options[i], false); //otherwise
  	}
	}

  }
  /**
   * accessor of topic
   * @return topic
   */
  public String getTopic() {
	return topic;
  }
  /**
   * accessor of question statement
   * @return question statement
   */
  public String getDescription() {
	return description;
  }
  /**
   * accessor of Image object
   * @return image
   */
  public Image getImage() {
	return image;
  }
  /**
   * accessor of all Answer objects
   * @return an array of Answer objects
   */
  public Answer[] choices() {
	return choices;
  }
  /**
   * accessor of a choice with a given index
   * @param numChoice is a given index
   * @return an answer corresponding to such index
   */
  public Answer getChoice(int numChoice) {
	return choices[numChoice];
  }
  /**
   * to check answer whether it is correct or not
   * @param userOption is an answer that the user enters
   * @return correct or incorrect
   */
  public boolean checkAnswer(int userOption) {
	return choices[userOption].isCorrect;
  }
  /**
   * accessor of a file name of image
   * @return a file name of image
   */
  public String getImageFile() {
	return imageFile;
  }
  /**
   * accessor of correct answer
   * @return correct answer
   */
  public String corAn() {
	return corAn;
  }
}





