//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: BSTNode
// Files: BSTNode.java
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
 * This class represents the BSTNode, which include the key and value. It also contains the left and
 * right BSTNode
 * 
 * @author Lynette
 *
 */
class BSTNode<K, V> {

  K key; //key of the node
  V value;//value of the node
  BSTNode<K, V> left;//the left child node of this current node
  BSTNode<K, V> right;//the right child node of this current node
  int balanceFactor;
  int height;


  /**
   * This method represents the constructor of the BSTNode
   * @param key of the node
   * @param value of the node
   * @param leftChild of the BST Node
   * @param rightChild of the BST Node
   */
  BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
    this.key = key;
    this.value = value;
    this.left = leftChild;
    this.right = rightChild;
    this.height = 0;//initiate to zero for AVL NODE
    this.balanceFactor = 0;//initiate to zero for AVL Node
  }

  /**
   * This constructor creates a BSTNode only with key and value
   * @param key of the node
   * @param value of the node
   */
  BSTNode(K key, V value) {
    this(key, value, null, null);
  }


}
