import java.util.ArrayList;
import java.util.NoSuchElementException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Sett
// Files: Sett.java
// Course: CS 300
//
// Author: Lynette GAO
// Email: qgao38@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:SUSAN JIAO
// Partner Email: Jjiao25@wisc.edu
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
 * This class represents a Sett, where a group of Badgers live together. Each Sett is organized as a
 * BST of Badger nodes.
 * 
 * @author JIAO & GAO
 *
 */
public class Sett {
  private Badger topBadger;// the badger that is on the root of the sett

  /**
   * Constructs an empty Sett.
   */
  public Sett() {
    this.topBadger = null;
  }

  /**
   * Retrieve the top Badger within this Sett (the one that was settled first).
   * 
   * @return The Badger living on the top of the current Sett.
   */
  public Badger getTopBadger() {
    return this.topBadger;
  }

  /**
   * Checks whether this Sett is empty.
   * 
   * @return true if this Sett is empty, false otherwise.
   */
  public boolean isEmpty() {
    // check the whether there exists a top badger
    if (getTopBadger() == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Creates a new Badger object with the specified size, and inserts them into this Sett
   * 
   * @param size The size of the new Badger that will be settled.
   * @throws java.lang.IllegalArgumentException When a Badger with the specified size already exists
   *         within this Sett.
   */
  public void settleBadger(int size) throws java.lang.IllegalArgumentException {
    Badger newBadger = new Badger(size);
    try {
      if (newBadger != null) {
        if (topBadger == null) {// if there is no badger is the BST
          topBadger = newBadger; // the new badger becomes the topbadger
        } else {
          settleHelper(topBadger, newBadger);
        }
      }
    } catch (IllegalArgumentException e1) {
      System.out.println(e1.getMessage()); // print error message
                                           // if badger of the same size already exists
      throw e1;
    }

  }



  /**
   * This recursive helper method is used to help settle a new Badger within this Sett.
   * 
   * @param current   is the top badger we start from
   * @param newBadger is the new badger to be added
   * @throws IllegalArgumentException if the a badger with the same size already exists
   */
  private void settleHelper(Badger current, Badger newBadger)
      throws java.lang.IllegalArgumentException {

    if (current.getSize() == newBadger.getSize()) { // if a badger of the same size already exists
                                                    // throw exception and warning
      throw new IllegalArgumentException(
          "WARNING: failed to settle the badger with size {" + newBadger.getSize()
              + "}, as there is already a badger with the same size in this sett");
    } else if (newBadger.getSize() < current.getSize()) { // if the new badger is smaller and if the
                                                          // lower left is empty
      if (current.getLeftLowerNeighbor() == null) {
        current.setLeftLowerNeighbor(newBadger); // set the new Badger in place
      } else { // if not empty recurse sethelper with the lower left one
        settleHelper(current.getLeftLowerNeighbor(), newBadger);
      }
    } else if (newBadger.getSize() > current.getSize()) { // if the new badger is larger and if the
                                                          // lower right is empty
      if (current.getRightLowerNeighbor() == null) {
        current.setRightLowerNeighbor(newBadger); // set the new badger in place
      } else {// if not empty
        settleHelper(current.getRightLowerNeighbor(), newBadger);// recurse the setHelper with the
                                                                 // lower right one

      }

    }

  }

  /**
   * Finds a Badger of a specified size in this Sett.
   * 
   * @param size is the ideal size to search for
   * @return the badger with the intended size
   * @throws NoSuchElementException when no badger of such size exists
   */
  public Badger findBadger(int size) throws java.util.NoSuchElementException {
    try {
      return findHelper(topBadger, size);
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      throw new NoSuchElementException();
    }
  }


  /**
   * This recursive helper method is used to help find a Badger within this Sett.
   * 
   * @param current is Badger to start searching with
   * @param size    is ideal size to search for
   * @return the Badger with the specified size
   * @throws NoSuchElementException when no badger of such size exists
   */
  private Badger findHelper(Badger current, int size) throws java.util.NoSuchElementException {

    if (current == null) {
      throw new NoSuchElementException(
          "WARNING: failed to find a" + " badger with size {size} in the sett");

    }
    if (current.getSize() == size) {
      return current; // if found return that badger
    } else if (size < current.getSize()) { // if size is smaller than the current badger
      // recurse left
      return findHelper(current.getLeftLowerNeighbor(), size);
    } else {
      // if size is larger than the current badger
      // recurse right
      return findHelper(current.getRightLowerNeighbor(), size);
    }


  }

  /**
   * Counts how many Badgers live in this Sett.
   * 
   * @return the number of badgers living in this sett
   */
  public int countBadger() {
    return countHelper(this.topBadger);
  }

  /**
   * This recursive helper method is used to help count the number of Badgers in this Sett.
   * 
   * @param current is the Badger that we start counting with
   * @return the number of badger starting from the current badger down in the BST
   * 
   */
  private int countHelper(Badger current) {

    if (current == null) { // base case
      return 0;
    } else {
      Badger rBadger = current.getRightLowerNeighbor();
      Badger lBadger = current.getLeftLowerNeighbor();
      int count = 1;
      if (rBadger != null) { // keep recurse left until null is reached
        count += countHelper(rBadger);
      }
      if (lBadger != null) {// keep recurse right until null is reached
        count += countHelper(lBadger);
      }
      return count;
    }

  }

  /**
   * Gets all Badgers living in the Sett as a list in ascending order of their size: smallest one in
   * the front (at index zero), through the largest one at the end (at index size-1).
   * 
   * @return the list containing the set of all badgers in order
   */
  public java.util.List<Badger> getAllBadgers() {
    // create an arraylist to store the badgers
    java.util.List<Badger> badgerList = new ArrayList<Badger>();
    getAllHelper(topBadger, badgerList);
    return badgerList;
  }

  /**
   * This recursive helper method is used to help collect the Badgers within this Sett into a List.
   * 
   * @param current    is the top badger that we start from
   * @param allBadgers is the list that collects all badgers in order
   */
  private void getAllHelper(Badger current, java.util.List<Badger> allBadgers) {

    if (current == null) {// base case
      return;
    }
    getAllHelper(current.getLeftLowerNeighbor(), allBadgers); // recurse left
    allBadgers.add(current); // add
    getAllHelper(current.getRightLowerNeighbor(), allBadgers); // recurse right

  }

  /**
   * Computes the height of the Sett, as the number of nodes from root to the deepest leaf Badger
   * node.
   * 
   * @return the height of the sett
   */
  public int getHeight() {
    return getHeightHelper(this.topBadger);
  }

  /**
   * This recursive helper method is used to help compute the height of this Sett.
   * 
   * @param current is the top badger of the current tree/subtree
   * @return the height of this BST
   */
  private int getHeightHelper(Badger current) {

    if (current == null) {
      return 0;
    } else {
      int lHeight = getHeightHelper(current.getLeftLowerNeighbor()); // recurse right
      int rHeight = getHeightHelper(current.getRightLowerNeighbor());// recurse left
      // compare which way down the BST has the largest height
      if (lHeight > rHeight) {
        return (lHeight + 1); // add 1 to LHeight if left branch is longer
      } else {
        return (rHeight + 1); // add 1 to RHeight if right branch is longer
      }
    }

  }

  /**
   * Retrieves the largest Badger living in this Sett.
   * 
   * @return largest Badger living in this Sett
   */
  public Badger getLargestBadger() {
    if (this.topBadger == null) {
      return null;// return null if the sett is empty
    }
    Badger temp = this.topBadger;
    // iterate right until null is reached
    while (temp.getRightLowerNeighbor() != null)
      temp = temp.getRightLowerNeighbor();
    return temp;
  }

  /**
   * This method empties this Sett to no longer contain any Badgers.
   */
  public void clear() {
    this.topBadger = null;
  }
}
