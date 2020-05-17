//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DS_My
// Files: DS_My.java LEC004
//
// Author: Lynette Gao
// Email: qgao38@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:NA
// Partner Email: NA
// Partner Lecturer's Name: NA
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
 * This class represents a data structure which implements the DataStructureADT. It has a inner
 * class Node for storing key and value as a pair
 * 
 * @author Lynette
 *
 */
public class DS_My implements DataStructureADT {

  /**
   * This inner class represents a node within the data structure
   * 
   * @author Lynette
   */
  private class Node {
    private Comparable key;// the key of the node
    private Object value;// data value associated with a given key.
    Node next;// the next Node related to the current node in the data structure

    /**
     * The constructor creates a node with key and value.
     */
    private Node(Comparable k, Object v) {
      key = k;// initialize the key
      value = v;// innitialize the value
      next = null;// set the next node to null
    }

  }

  private Node head;// the head of the data structure
  private int size;// the size of the structure

  /**
   * The constructor creates a new DS.
   */
  public DS_My() {
    head = null;// initialize the head to null
    size = 0;// size should be zero when initializing

  }

  /**
   * This method inserts new node to the data structure
   * 
   * @param k is the key of the new node
   * @param v is the value of the new node
   */
  @Override
  public void insert(Comparable k, Object v) {
    // Create a new node with given data
    Node new_node = new Node(k, v);
    new_node.next = null;
    // throw the IllegalArgumentException when the key is null
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }
    // If key is already in data structure, throws RuntimeException("duplicate key")
    if (contains(k)) {
      throw new RuntimeException("duplicate key");
    }
    // If the data structure is empty, make the new node as head
    if (head == null) {
      head = new_node;
      size++;// update the size of the DT when a node is added
    } else {
      // Else traverse till the last node and insert the new_node there
      Node last = head;
      while (last.next != null) {
        last = last.next;
      }
      // Insert the new_node at last node
      last.next = new_node;

      size++;// update the size of the DT when a node is added
    }

  }

  /**
   * This method removes the key from the data structure and decreases size
   * 
   * @param k the key of the node that will be removed
   * @return true if the key is found and removed, false otherwise
   */
  @Override
  public boolean remove(Comparable k) {
    Node currNode = head;// the current node that is being traversed
    Node prev = null;// trace the previous node that is being traversed
    // If key is null, throws IllegalArgumentException("null key") without decreasing size
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }
    // If key is not found, returns false.
    else if (contains(k) == false) {
      return false;
    } else {
      // CASE 1:
      // If head node itself holds the key
      if (currNode != null && currNode.key.equals(k)) {
        head = currNode.next;// remove the node by setting it to the next node
        this.size--;// decrease the size of DT
        return true;
      }
      // CASE 2:
      // If the key is somewhere other than at head
      // Search for the key to be deleted
      else {
        while (currNode != null && !currNode.key.equals(k)) {
          // If currNode does not hold key
          // continue to next node
          prev = currNode;// keep track of the previous node
          currNode = currNode.next;
        }
        if (currNode != null) {
          prev.next = currNode.next;// remove the key from the DT by setting it to the next node
          this.size--;// decrease the size of DT when the node is removed
          return true;
        } else {
          return false;// could not find the key within the DT
        }

      }
    }
  }

  /**
   * This method returns whether the specified key is in the data structure
   * 
   * @param k the key that needs to be found in DT
   * @return the true if the DT contains the key, false otherwise
   */
  @Override
  public boolean contains(Comparable k) {
    // Returns false if key is null
    if (k == null) {
      return false;
    }
    // Store head node
    Node currNode = head;
    Node prev = null;
    // CASE 1:
    // If head node itself holds the key
    if (currNode != null && currNode.key.equals(k)) {
      return true;
    } else {
      while (currNode != null && !currNode.key.equals(k)) {
        // If currNode does not hold key
        // continue to next node
        prev = currNode;
        currNode = currNode.next;
      }
    }
    if (currNode != null) {
      return true;// return true when key in DT
    } else {
      return false;// key is not in DT
    }
  }

  /**
   * This method returns the value associated with the specified key
   * 
   * @return the value of the key
   * @param k they key of the node that needs to be found
   */
  @Override
  public Object get(Comparable k) {
    // Store head node
    Node currNode = head;
    Node prev = null;
    // If key is null, throws IllegalArgumentException("null key")
    if (k == null) {
      throw new IllegalArgumentException("null key");
    } else if (contains(k) == false) {
      return null;// return null if the key is not in the DT
    } else {
      if (currNode != null && currNode.key.equals(k)) {
        return currNode.value;
      } else {
        while (currNode != null && !currNode.key.equals(k)) {
          // If currNode does not hold key
          // continue to next node
          prev = currNode;
          currNode = currNode.next;
        }
      }
      // the key is found
      if (currNode != null) {
        return currNode.value;// return the value of the found key
      }
      // the key is not found
      else {
        return null;
      }

    }
  }

  /**
   * This method returns the number of elements in the data structure
   * 
   * @return the number of nodes
   */
  @Override
  public int size() {
    return size;
  }

}
