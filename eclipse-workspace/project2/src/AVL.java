//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AVL
// Files: AVL.java
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
 * This class represents the data structure of a AVL tree, which extends the BST tree. It overrides
 * insert and remove method etc.
 * 
 * @author Lynette
 *
 */

public class AVL<K extends Comparable<K>, V> extends BST<K, V> {

  /**
   * Getter for the height of the node
   * 
   * @param N node
   * @return the height of the specified node
   */
  private int height(BSTNode<K, V> N) {
    if (N == null)
      return 0;

    return N.height;
  }

  /**
   * This setter updates the height of a specific node
   * 
   * @param node
   */
  private void updateHeight(BSTNode<K, V> node) {

    // if node is a leaf
    if (node.left == null && node.right == null) {
      node.height = 0;
    }

    // no left child
    else if (node.left == null) {

      // set height
      node.height = node.right.height + 1;
    }

    else if (node.right == null) {

      // set height
      node.height = node.left.height + 1;
    }

    else {

      // find maximum between height of subtrees
      int maximum = max(node.right.height, node.left.height);
      node.height = maximum + 1;
    }
  }

  /**
   * Getter for the balance factor of the node
   * 
   * @param N node
   * @return
   */
  private int getBalance(BSTNode<K, V> N) {
    if (N == null)
      return 0;

    return getHeightHelper(N.left) - getHeightHelper(N.right);// BF = Height(left)-Height(right)
  }


  /**
   * This method represents the remove method of the AVL tree
   */
  @Override
  public boolean remove(K key) {
    try {
      if (super.remove(key) == true) {

        // after remove the node, update the height and balance of the root
        updateHeight(root);
        int balance = getBalance(root);

        // fix the unbalanced node from the root
        // If this node becomes unbalanced, then there are 4 cases
        // Left rotate
        if (balance > 1 && getBalance(root.left) > 0)
          root = rightRotate(root);

        // Left Right rotate
        if (balance > 1 && getBalance(root.left) < 0) {
          root.left = leftRotate(root.left);
          root = rightRotate(root);
        }

        // Right Right rotate
        if (balance < -1 && getBalance(root.right) < 0)
          root = leftRotate(root);

        // Right Left rotate
        if (balance < -1 && getBalance(root.right) > 0) {
          root.right = rightRotate(root.right);
          root = leftRotate(root);
        }
        return true;
      } else {
        return false;
      }
    } catch (IllegalNullKeyException e) {
      return false;
    } catch (KeyNotFoundException e) {
      return false;
    }
  }


  /**
   * This method represents the insert method of a AVL tree
   */
  @Override
  public void insert(K key, V value) throws DuplicateKeyException, IllegalArgumentException {

    if (key == null) {
      throw new IllegalArgumentException();
    }
    root = insertHelp(root, key, value);// call the helper of the AVL insertion
    this.numKeys++;// update the number of keys in the tree after insertion
  }

  /**
   * This recursive method helps insert the correct node and fix the unbalanced tree
   * 
   * @param n   root of the tree
   * @param key of the new node
   * @param v   value of the new node
   * @return
   * @throws DuplicateKeyException
   */
  private BSTNode<K, V> insertHelp(BSTNode<K, V> n, K key, V v) throws DuplicateKeyException {
    if (n == null) { // if empty, new node will return back to method.
      return new BSTNode<K, V>(key, v);
    }
    if (key.compareTo(n.key) < 0) { // determine the location of insert, if key is larger
                                    // than the node, go left, else go right.
      // add key to the left subtree
      n.left = insertHelp(n.left, key, v);
      // add method to restructure BST
    } else if (key.compareTo(n.key) > 0) {
      n.right = insertHelp(n.right, key, v);
    } else {
      // if none of the above.
      throw new DuplicateKeyException();
    }
    updateHeight(n); // Determine the height of node.
    int balanceFactor = getBalance(n); // establish balance factor of the node.

    // fix the tree depending based on different cases
    if (balanceFactor > 1 && key.compareTo(n.left.key) < 0) { // right rotation
      return rightRotate(n);
    }
    if (balanceFactor < -1 && key.compareTo(n.right.key) > 0) {// left rotation
      return leftRotate(n);
    }
    if (balanceFactor > 1 && key.compareTo(n.left.key) > 0) {// left-right rotation
      n.left = leftRotate(n.left);
      return rightRotate(n);
    }
    if (balanceFactor < -1 && key.compareTo(n.right.key) < 0) {// right-left rotation
      n.right = rightRotate(n.right);
      return leftRotate(n);
    }
    return n; // unchanged
  }


  /**
   * This method performs the left rotate
   * @param g grandparent node
   * @return the new root
   */
  private BSTNode<K, V> leftRotate(BSTNode<K, V> g) {
    BSTNode<K, V> p = g.right;
    BSTNode<K, V> h = p.left;

    // Perform rotation
    g.right = h;
    p.left = g;


    // Update heights
    g.height = max(getHeightHelper(g.left), getHeightHelper(g.right)) + 1;
    p.height = max(getHeightHelper(p.left), getHeightHelper(p.right)) + 1;

    // Return new root
    return p;
  }

  /**
   * This method performs the right rotate
   * @param g grandparent node
   * @return the new root
   */
  private BSTNode<K, V> rightRotate(BSTNode<K, V> g) {
    BSTNode<K, V> p = g.left;
    BSTNode<K, V> h = p.right;

    // Perform rotation
    g.left = h;
    p.right = g;


    // Update heights
    g.height = max(height(g.left), height(g.right)) + 1;
    p.height = max(height(p.left), height(p.right)) + 1;

    // Return new root
    return p;
  }

  /**
   * This method get the maximum value of two integers
   * @param a
   * @param b
   * @return larger value
   */
  private int max(int a, int b) {
    return (a > b) ? a : b;
  }



}
