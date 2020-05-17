//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: WaitingQueueADT
// Files: WaitingQueueADT.java
// Course: CS 300
//
// Author: Lynette Gao
// Email: qgao38@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:Susan Jiao
// Partner Email: jjiao25@wisc.edu
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
 * This class provides an interface that represents the pattern for our ready processes waiting list.
 * @author JIAO&GAO
 *
 * @param <T>
 */
public interface WaitingQueueADT<T extends Comparable<T>> {
/**
 * This method inserts a newObject in the priority queue.
 * @param newObject the new object to be inserted 
 */
  public void enqueue(T newObject); 

  /**
   * This method removes and returns the item with the highest priority
   * @return the highest priority object in the list 
   */
  public T dequeue(); 

  /**
   * This method returns without removing the item with the highest priority
   * @return the highest priority object in the list
   */
  public T peek(); 

  /**
   * This method returns the size of the waiting queue
   * @return the size of the waiting queue
   */
  public int size(); 

  /**
   * This method checks if the waiting queue is empty
   * @return whether the waiting queue is empty
   */
  public boolean isEmpty(); 
}