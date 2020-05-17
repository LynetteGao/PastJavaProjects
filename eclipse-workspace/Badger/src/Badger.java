//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Badger
// Files: Badger.java
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
 * This class represents a Badger which is designed to live in a Sett. Each Badger object represents
 * a single node within a BST (known as a Sett).
 * 
 * @author JIAO & GAO
 *
 */
public class Badger {
  private final int SIZE; // size of the badger
  private Badger leftLowerNeighbor;// badger's left lower neighbor
  private Badger rightLowerNeighbor;// badger's right lower neighbor

  /**
   * Creates a new Badger with specified size.
   * 
   * @param size the size of the newly constructed Badger object.
   */
  public Badger(int size) {
    this.SIZE = size;
  }

  /**
   * Retrieves neighboring badger that is smaller than this one.
   * 
   * @return The left lower neighbor of current badger.
   */
  public Badger getLeftLowerNeighbor() {
    return this.leftLowerNeighbor;
  }

  /**
   * Changes this badger's lower left neighbor.
   * 
   * @param badger The new left lower neighbor of current badger.
   */
  public void setLeftLowerNeighbor(Badger badger) {
    this.leftLowerNeighbor = badger;
  }

  /**
   * Retrieves neighboring badger that is bigger than this one.
   * 
   * @return The right lower neighbor of current badger.
   */
  public Badger getRightLowerNeighbor() {
    return this.rightLowerNeighbor;
  }

  /**
   * Changes this badger's lower right neighbor.
   * 
   * @param badger The new left right neighbor of current badger.
   */
  public void setRightLowerNeighbor(Badger badger) {
    this.rightLowerNeighbor = badger;
  }

  /**
   * Retrieves the size of this badger.
   * 
   * @return The size of current badger.
   */
  public int getSize() {
    return this.SIZE;
  }


}
