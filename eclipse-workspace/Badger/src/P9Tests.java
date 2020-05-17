//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P9Tests
// Files: P9Tests.java
// Course: CS 300
//
// Author: Susan Jiao
// Email: jjiao25@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:Lynette Gao
// Partner Email: qgao38@wisc.edu
// Partner Lecturer's Name: Gary Dahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __x_ Write-up states that pair programming is allowed for this assignment.
// __x_ We have both read and understand the course Pair Programming Policy.
// _x__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class tests the methods within the Sett class and Badger class
 * 
 * @author JIAO & GAO
 *
 */
public class P9Tests {

	/**
	 * The main method calls the runAllSettTests and the runAllBadgerTests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (!runAllSettTests()) {// check all sett tests
			System.out.println("Not all sett class tests passed");
		}
		if (!runAllBadgerTests()) {// check all badger tests
			System.out.println("Not all badger class tests passed");
		}
		if (runAllSettTests() && runAllBadgerTests()) {
			// print out message if all passed
			System.out.println("All tests passed, congrats");
		}

	}

	/**
	 * This method returns true when all Badger tests pass
	 * 
	 * @return whether all badger tests passed
	 */
	public static boolean runAllBadgerTests() {
		// call the three badger class methods tests
		if (setLeftLNeighbTest() && setRightLNeighbTest() && badgerConstructor()) {
			return true; // returns true when all Badger tests pass
		} else {
			return false;
		}
	}

	/**
	 * this method returns true when all Sett tests pass
	 * 
	 * @return whether all sett test passed
	 */
	public static boolean runAllSettTests() {
		// calls all four sett class methods tests
		if (getLargestBadgerTest() && countBadgerTest() && getHeightTest()
				&& settConstructor()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks the badger class constructor
	 * 
	 * @return whether the constructor is implemented correctly
	 */
	public static boolean badgerConstructor() {
		Badger newBadger = new Badger(2); // want to have a badger of size 2
		if (newBadger.getSize() == 2) { // check whether the size is 2
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks the sett class constructor
	 * 
	 * @return whether the constructor is implemented correctly
	 */
	public static boolean settConstructor() {
		Sett settTest = new Sett(); // initialize a sett
		if (settTest.isEmpty()) { // check if the sett is empty
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks the setLeftLowerNeighbor method
	 * 
	 * @return whether the setLeftLNeightbor method is implemented correctly
	 */
	public static boolean setLeftLNeighbTest() {
		// Initialize three badges
		Badger b1 = new Badger(5);
		Badger b2 = new Badger(3);
		Badger b3 = new Badger(2);
		// set the left neighbor of b1 to b2
		b1.setLeftLowerNeighbor(b2);
		// set the left neighbor of b2 to b3
		b2.setLeftLowerNeighbor(b3);

		if (b2.getLeftLowerNeighbor() == b3) {// check if b2's lower left is b3
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks the setRightLowerNeighbor method
	 * 
	 * @return whether the setRigthtLowerNeighbor method is correctly implemented
	 */
	public static boolean setRightLNeighbTest() {
		// initialize three badgers
		Badger b1 = new Badger(3);
		Badger b2 = new Badger(5);
		Badger b3 = new Badger(7);
		// set the right neighbor of b1 to b2
		b1.setRightLowerNeighbor(b2);
		// set the right neighbor of b2 to b3
		b2.setRightLowerNeighbor(b3);

		if (b2.getRightLowerNeighbor() == b3) {// checks if b2's lower right is b3
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks the countBadger method
	 * 
	 * @return whether the countBadger method is implemented correctly
	 */
	public static boolean countBadgerTest() {
		// create a new sett
		Sett settTest = new Sett();
		// add 4 badgers of different size into the sett
		settTest.settleBadger(4);
		settTest.settleBadger(2);
		settTest.settleBadger(6);
		settTest.settleBadger(7);

		if (settTest.countBadger() == 4) { // check if the size is 4
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks the getHeight method
	 * 
	 * @return whether the getHeight method is implemented correctly
	 */
	public static boolean getHeightTest() {
		// create a new sett
		Sett settTest = new Sett();
		// add 4 Badgers of different size to the sett
		settTest.settleBadger(4);
		settTest.settleBadger(2);
		settTest.settleBadger(6);
		settTest.settleBadger(7);

		if (settTest.getHeight() == 3) {// check if the sett height is 3
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks the getLargestBadger method
	 * 
	 * @return whether the getLargestBadger method is implemented correctly
	 */
	public static boolean getLargestBadgerTest() {
		// create a new sett
		Sett settTest = new Sett();
		// add 4 Badgers of different size to the sett
		settTest.settleBadger(4);
		settTest.settleBadger(2);
		settTest.settleBadger(6);
		settTest.settleBadger(7);

		Badger largestB = settTest.getTopBadger().getRightLowerNeighbor()
				.getRightLowerNeighbor();
		// checks if the method returns the largest one
		if (settTest.getLargestBadger() == largestB) {
			return true;
		} else {
			return false;
		}
	}

}
