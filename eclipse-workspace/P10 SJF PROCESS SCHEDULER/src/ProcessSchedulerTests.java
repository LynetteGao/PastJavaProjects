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
 * This class tests whether methods in the other classes are implemeted correctly
 * 
 * @author JIAO&GAO
 *
 */
public class ProcessSchedulerTests {
  /**
   * This is the main method of the class that calls the other tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    // call all the tests in the class
    if (testEnqueueCustomProcessQueue() && testDequeueCustomProcessQueue()
        && testPeekCustomProcessQueue() && testSizeCustomProcessQueue()) {
      System.out.println("All tests passed, congrats!");
    } else {
      System.out.println("At least one test failed");
    }
  }

  /**
   * This test checks the correctness of the enqueue operation implemented in the CustomProcessQueue
   * class
   * 
   * @return whether the enqueue method is implemented correctly
   */
  public static boolean testEnqueueCustomProcessQueue() {
    // creates a new custom process queue to store data
    CustomProcessQueue newQ = new CustomProcessQueue();
    // Initialize four custom process with different burst time
    CustomProcess p1 = new CustomProcess(5);
    CustomProcess p2 = new CustomProcess(3);
    CustomProcess p3 = new CustomProcess(6);
    CustomProcess p4 = new CustomProcess(4);
    // enqueue all four onto the queue
    newQ.enqueue(p1);
    newQ.enqueue(p2);
    newQ.enqueue(p3);
    newQ.enqueue(p4);

    CustomProcess[] heapT = newQ.getHeap();
    // checks if each enqueue returns the correct data
    if (heapT[1] == p2) {
      if (heapT[2] == p4) {
        if (heapT[3] == p3) {
          if (heapT[4] == p1) {
            return true;
          }
        }
      }
    }

    return false;

  }

  /**
   * This test checks the correctness of the dequeue operation implemented in the CustomProcessQueue
   * class
   * 
   * @return whether the dequeue method is implemented correctly
   */
  public static boolean testDequeueCustomProcessQueue() {
    // creates a new custom process queue to store data
    CustomProcessQueue newQ = new CustomProcessQueue();
    // Initialize four custom process with different burst time
    CustomProcess p1 = new CustomProcess(5);
    CustomProcess p2 = new CustomProcess(3);
    CustomProcess p3 = new CustomProcess(6);
    CustomProcess p4 = new CustomProcess(4);
    // enqueue all four onto the queue
    newQ.enqueue(p1);
    newQ.enqueue(p2);
    newQ.enqueue(p3);
    newQ.enqueue(p4);

    // checks if each dequeue returns the correct data
    if (newQ.dequeue() == p2) {
      if (newQ.dequeue() == p4) {
        if (newQ.dequeue() == p1) {
          if (newQ.dequeue() == p3) {
            return true;
          }
        }
      }
    }
    return false;

  }

  /**
   * This test checks the correctness of the peek operation
   * 
   * @return whether the peek method is implemented correctly
   */
  public static boolean testPeekCustomProcessQueue() {
    // creates a new custom process queue to store data
    CustomProcessQueue newQ = new CustomProcessQueue();
    // Initialize four custom process with different burst time
    CustomProcess p1 = new CustomProcess(5);
    CustomProcess p2 = new CustomProcess(3);
    CustomProcess p3 = new CustomProcess(6);
    CustomProcess p4 = new CustomProcess(4);
    // enqueue all four onto the queue
    newQ.enqueue(p1);
    newQ.enqueue(p2);
    newQ.enqueue(p3);
    newQ.enqueue(p4);

    if (newQ.peek() == p2) {// checks if peek returns the top priority one
      return true;
    } else {
      return false;
    }
  }

  /**
   * This test checks the correctness of the size operation implemented in the CustomProcessQueue
   * class
   * 
   * @return whether the size method is implemented correctly
   */
  public static boolean testSizeCustomProcessQueue() {
    // creates a new custom process queue to store data
    CustomProcessQueue newQ = new CustomProcessQueue();
    // Initialize four custom process with different burst time
    CustomProcess p1 = new CustomProcess(5);
    CustomProcess p2 = new CustomProcess(3);
    CustomProcess p3 = new CustomProcess(6);
    CustomProcess p4 = new CustomProcess(4);
    // enqueue all four onto the queue
    newQ.enqueue(p1);
    newQ.enqueue(p2);
    newQ.enqueue(p3);
    newQ.enqueue(p4);
    // checks if size is actually 4
    if (newQ.size() == 4) {
      return true;
    } else {
      return false;
    }
  }


}
