
import java.util.ArrayList; // allowed for creating traversal lists
import java.util.List; // required for returning List<K>

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: BST
// Files: BST.java
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
 * This class represents the data structure of a BST tree, which implements the BSTAFT. It includes
 * the traversal, insert and remove method etc.
 * 
 * @author Lynette
 *
 */


public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  protected BSTNode<K, V> root;
  protected int numKeys; // number of keys in BST

  /**
   * This method represents a public, default no-arg constructor
   */
  public BST() {


  }

  /**
   * This recursive helper method is used to get the pre-order traversal done
   * 
   * @param root is the root node of the BST tree
   * @param arr  is the list that stores the nodes in traversal order
   * @return the array with nodes in pre-order
   */
  protected List<K> preOrderHelper(BSTNode<K, V> root, List<K> arr) {
    // CASE 1: the tree is null
    if (root == null) {
      return arr;// return empty list
    }
    // CASE 2: the tree is null
    // traverse the node in pre-order
    arr.add(root.key);
    preOrderHelper(root.left, arr);
    preOrderHelper(root.right, arr);

    return arr;

  }

  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getPreOrderTraversal() {
    List<K> list = new ArrayList<K>();
    preOrderHelper(this.root, list);// call the helper
    return list;

  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    List<K> list = new ArrayList<K>();
    postOrderHelper(this.root, list);
    return list;
  }

  /**
   * This recursive helper method is used to get the post-order traversal done
   * 
   * @param root is the root node of the BST tree
   * @param arr  is the list that stores the nodes in traversal order
   * @return the array with nodes in post-order
   */
  protected List<K> postOrderHelper(BSTNode<K, V> root, List<K> arr) {
    // when the tree is empty
    if (root == null) {
      return arr;
    }
    // traverses in post order
    postOrderHelper(root.left, arr);
    postOrderHelper(root.right, arr);
    arr.add(root.key);

    return arr;

  }

  /**
   * Returns the keys of the data structure in level-order traversal order. If the SearchTree is
   * empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    List<K> list = new ArrayList<K>();
    int h = this.getHeight();// get the levels of the tree
    int i;
    // traverse each level
    for (i = 1; i <= h; i++)
      levelOrderHelper(root, list, i);
    return list;
  }


  /**
   * This recursive helper method is used to get the level-order traversal done
   * 
   * @param root is the root node of the BST tree
   * @param arr  is the list that stores the nodes in traversal order
   * @return the array with nodes in level-order
   */
  protected List<K> levelOrderHelper(BSTNode<K, V> root, List<K> arr, int level) {
    // return null if the tree is empty
    if (root == null)
      return arr;
    if (level == 1)
      arr.add(root.key);
    else if (level > 1) {
      // traverse each node in that level
      levelOrderHelper(root.left, arr, level - 1);
      levelOrderHelper(root.right, arr, level - 1);
    }
    return arr;

  }


  /**
   * Returns the keys of the data structure in in-order traversal order. If the SearchTree is empty,
   * an empty list is returned.
   * 
   * @return List of Keys in in-order
   */
  @Override
  public List<K> getInOrderTraversal() {
    List<K> list = new ArrayList<K>();
    inOrderHelper(this.root, list);// call helper method
    return list;
  }

  /**
   * This recursive helper method is used to get the in-order traversal done Expected order: LRV
   * 
   * @param root is the root node of the BST tree
   * @param arr  is the list that stores the nodes in traversal order
   * @return the array with nodes in level-order
   */
  protected List<K> inOrderHelper(BSTNode<K, V> root, List<K> arr) {
    if (root == null) {
      return arr;
    }

    // traverse in-order
    inOrderHelper(root.left, arr);
    arr.add(root.key);
    inOrderHelper(root.right, arr);


    return arr;

  }

  /**
   * This method adds the key,value pair to the data structure and increase the number of keys. If
   * key is null, throw IllegalNullKeyException; If key is already in data structure, throw
   * DuplicateKeyException();
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // If key is null, throw IllegalNullKeyException
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // check whether the key is in the data structure
    if (contains(key) == true) {
      throw new DuplicateKeyException();
    }

    // call insert helper method
    this.root = insertHelper(root, key, value);
    this.numKeys++;// update the number of keys in the data structure
  }

  /**
   * This recursive helper method is used to insert the BST Node in right place in the data
   * structure
   * 
   * @param root  the root of the BST tree
   * @param key   of the node be inserted
   * @param value of the node be inserted
   * @return a new BST tree
   */
  protected BSTNode<K, V> insertHelper(BSTNode<K, V> root, K key, V value) {
    /* If the tree is empty, return a new node */
    if (root == null) {
      root = new BSTNode<K, V>(key, value);
      return root;
    }

    /* Otherwise, recur down the tree */
    if (key.compareTo(root.key) < 0)
      root.left = insertHelper(root.left, key, value);
    else if (key.compareTo(root.key) > 0)
      root.right = insertHelper(root.right, key, value);

    /* return the new node */
    return root;
  }

  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys. If
   * key is null, throw IllegalNullKeyException.If key is not found, throw KeyNotFoundException().
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // If key is null, throw IllegalNullKeyException
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // If key is not found, throw KeyNotFoundException().
    if (this.contains(key) == false) {
      throw new KeyNotFoundException();
    } else {
      this.root = removeHelper(this.root, key);// call removeHelper method
    }
    this.numKeys--;// update the number of keys in the data structure
    return true;
  }

  /**
   * This recursive helper method is used to remove the node given the key
   * 
   * @param root the root of the BST tree
   * @param key  of the node be removed
   * @return a new BST tree
   */
  protected BSTNode<K, V> removeHelper(BSTNode<K, V> root, K key) {

    // find the key beginning from the node
    if (key.compareTo(root.key) < 0)
      root.left = removeHelper(root.left, key);// recursively go to the left
    else if (key.compareTo(root.key) > 0)
      root.right = removeHelper(root.right, key);// recursively go to the right

    // if key is same as root's key, then This is the node to be deleted
    else {
      // node with only one child or no child
      if (root.left == null)
        return root.right;
      else if (root.right == null)
        return root.left;

      // node with two children: Get the inorder successor (smallest
      // in the right subtree)
      root.key = findSuccessor(root.right);// call the method to find inorder successor

      // Delete the inorder successor using the helper method
      root.right = removeHelper(root.right, root.key);
    }

    return root;
  }

  /**
   * This method is used to find the in-order successor fo a given node
   * 
   * @param root is the node which the successor will be found for
   * @return the value of the successor
   */
  protected K findSuccessor(BSTNode<K, V> root) {
    K minv = root.key;
    // using while loop to go ti the left-most node of the subtree
    while (root.left != null) {
      minv = root.left.key;
      root = root.left;
    }
    return minv;
  }


  /**
   * This method returns the value associated with the specified key. If key is null, throw
   * IllegalNullKeyException. If key is not found, throw KeyNotFoundException().
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // If key is null, throw IllegalNullKeyException
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (contains(key) == false) {
      throw new KeyNotFoundException();
    } else {
      return getHelper(this.root, key).value;// use helper method to get the node
    }
  }

  /**
   * This recursive helper method is used to get the node given the key
   * 
   * @param root the root of the BST tree
   * @param key  to be found for the node
   * @return the node with the given key
   */
  public BSTNode<K, V> getHelper(BSTNode<K, V> root, K key) {
    // Base Cases: root is null or key is present at root
    if (root == null || root.key.compareTo(key) == 0)
      return root;

    // root key is greater than value
    if (root.key.compareTo(key) > 0)
      return getHelper(root.left, key);// go to the left

    // key is greater than root's key
    return getHelper(root.right, key);// go to the right
  }

  /**
   * Returns true if the key is in the data structure. If key is null, throw
   * IllegalNullKeyException. Returns false if key is not null and is not present
   * 
   * @see DataStructureADT#contains(java.lang.Comparable)
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (this.root == null) {
      return false;
    } else {
      return search(this.root, key);// call the helper method to search the key
    }
  }

  /**
   * This recursive helper method is find the node with given value
   * 
   * @param root  of the tree
   * @param value to be found
   * @return whether the key is found
   */
  protected boolean search(BSTNode<K, V> root, K value) {
    // find the key in the root
    if (root.key.compareTo(value) == 0)
      return true;
    // search the left subtree if the key is less than root's key
    else if (root.key.compareTo(value) > 0) {
      if (root.left == null)
        return false;
      else
        return search(root.left, value);
    }
    // otherwise, search right subtree
    else if (root.key.compareTo(value) < 0) {
      if (root.right == null)
        return false;
      else
        return search(root.right, value);
    }
    return false;
  }

  /**
   * This method returns the number of keys in the tree
   * 
   * @see DataStructureADT#numKeys()
   */
  @Override
  public int numKeys() {

    return this.numKeys;
  }

  /**
   * This tree return the key of the root of the tree
   * 
   * @see BSTADT#getKeyAtRoot()
   */

  @Override
  public K getKeyAtRoot() {
    return this.root.key;
  }

  /**
   * Tries to find a node with a key that matches the specified key. *
   * 
   * @throws IllegalNullKeyException if key argument is null
   * 
   * @throws KeyNotFoundException    if key is not found in this BST
   * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (contains(key) == false) {
      throw new KeyNotFoundException();
    } else {
      return getHelper(this.root, key).left.key;// call the get helper method
    }
  }

  /**
   * Tries to find a node with a key that matches the specified key. *
   * 
   * @throws IllegalNullKeyException if key argument is null
   * 
   * @throws KeyNotFoundException    if key is not found in this BST
   * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (contains(key) == false) {
      throw new KeyNotFoundException();
    } else {
      return getHelper(this.root, key).right.key;// call the helper method to locate the node
    }
  }

  /**
   * This method returns the height of this BST. If root is null, return 0 If root is a leaf, return
   * 1 Else return 1 + max( height(root.left), height(root.right) )
   * 
   * @see BSTADT#getHeight()
   */
  @Override
  public int getHeight() {
    return getHeightHelper(this.root);
  }

  /**
   * This recursive method find the height of the tree
   * 
   * @param current
   * @return
   */
  protected int getHeightHelper(BSTNode<K, V> current) {

    if (current == null) {
      return 0;
    } else {
      int lHeight = getHeightHelper(current.left); // recurse right
      int rHeight = getHeightHelper(current.right);// recurse left
      // find BST which has the largest height
      if (lHeight > rHeight) {
        return (lHeight + 1); // update the height of left subtree
      } else {
        return (rHeight + 1); // update the height of right subtree
      }
    }



  }
}
