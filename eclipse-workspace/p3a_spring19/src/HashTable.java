import java.util.ArrayList;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: HashTable
// Files: HashTable.java
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
 * This class represents the data structure of a HashTable, which implements the HashTableADT. It
 * includes the get, insert and remove method etc. The algorithm for hashing is using the positive
 * hashcode given by Java.
 * 
 * @author Lynette
 *
 */

public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

  private int tableSize;// the capacity of the table
  private final double loadFactorThreshold;// load factor that causes a resize and rehash
  private ArrayList<HashNode<K, V>> table;// the array represents the hash table
  private int itemNumber;// number of items in the table

  /**
   * This inner class represents the inner node to be inserted to the hash table. It includes the
   * key,value pair.
   * 
   * @author lynette
   *
   * @param <K> key of the node
   * @param <V> value of the node
   */
  private class HashNode<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private HashNode<K, V> next;// this is the next hashnode of this node

    /**
     * Constructor for the HashNode
     * 
     * @param key   of the node
     * @param value of the node
     */
    private HashNode(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }


  /**
   * This method represents a public, default no-arg constructor
   */
  public HashTable() {
    this.itemNumber = 0;// initialize an empty table
    this.tableSize = 1;// with size
    this.loadFactorThreshold = 0.75;// set the default threshold
    table = new ArrayList<>();// create the array holding the hash table
    for (int i = 0; i < tableSize; i++)
      table.add(null);
  }



  /**
   * This constructor creates a new hashTable with initial capacity and load factor
   * 
   * @param initialCapacity     is the initial table size
   * @param loadFactorThreshold the threshold when the table needs to resize
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
    this.tableSize = initialCapacity;// initialize the table size
    this.loadFactorThreshold = loadFactorThreshold;// initialize the thereshold
    this.table = new ArrayList<>(tableSize);// create a arraylist with the given size
    this.itemNumber = 0;// set the item number to 0
    for (int i = 0; i < tableSize; i++)
      table.add(null);
  }

  /**
   * This method returns the index of the given key in the array
   * 
   * @param key of the given node
   * @return index of the key
   */
  private int getIndex(K key) {
    int hashCode = key.hashCode();// call the hashCode() method to get the default hashCode
    // negate the hashCode if it is less than 0
    if (hashCode < 0) {
      hashCode = -hashCode;
    }
    // get the index of the position in the array given the key
    int index = hashCode % tableSize;
    return index;
  }

  /**
   * Add the key,value pair to the data structure and increase the number of keys. If key is null,
   * throw IllegalNullKeyException; If key is already in data structure, throw
   * DuplicateKeyException();
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // If key is null,throw IllegalNullKeyException;
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    int index = getIndex(key);// get the index of the given key
    HashNode<K, V> currNode = table.get(index);
    // Check if key is already present
    while (currNode != null) {
      // If key is already in data structure, throw DuplicateKeyException()
      if (currNode.key.compareTo(key) == 0) {
        throw new DuplicateKeyException();
      }
      currNode = currNode.next;
    }
    // insert the new node to the table
    currNode = table.get(index);// get the node in the given position
    HashNode<K, V> newNode = new HashNode<K, V>(key, value);// create a new hash node using
                                                            // key,value pair
    // add the new node to the chained bucket(maybe empty)
    newNode.next = currNode;
    table.set(index, newNode);
    this.itemNumber++;// update the item number in the table

    // rehash if current load factor is greater than threshold
    if (this.getLoadFactor() >= this.getLoadFactorThreshold()) {
      // create a new temporary table to store the nodes
      ArrayList<HashNode<K, V>> newTable = this.table;
      // set the table to a new size and empty
      this.table = new ArrayList<>();
      this.tableSize = tableSize * 2 + 1;
      this.itemNumber = 0;
      for (int i = 0; i < tableSize; i++)
        table.add(null);
      // add the node back to the new hash table
      for (HashNode<K, V> headNode : newTable) {
        while (headNode != null) {
          insert(headNode.key, headNode.value);
          headNode = headNode.next;
        }
      }

    }



  }

  /**
   * If key is found, remove the key,value pair from the data structure decrease number of keys.
   * return true
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    // If key is null, throw IllegalNullKeyException
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // If key is not found, return false
    try {
      get(key);
    } catch (KeyNotFoundException e) {
      return false;
    }

    int index = getIndex(key);// get the index of where the key suppose to be
    HashNode<K, V> currNode = table.get(index);// get the node at that index
    HashNode<K, V> prevNode = null;// trace the previous node in the bucket
    // loop through the bucket to find the node of the given key
    while (currNode != null) {
      if (currNode.key.compareTo(key) == 0) {
        if (prevNode == null) {
          table.set(index, currNode.next);
        } else {
          prevNode.next = currNode.next;// delete the node by setting the previous node pointing
          // to the next node
        }
        this.itemNumber--;// update the item number in the table
        return true;
      } else {
        prevNode = currNode;
        currNode = currNode.next;
      }
    }
    return false;
  }

  /**
   * Returns the value associated with the specified keyDoes not remove key or decrease number of
   * keys
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int index = getIndex(key);//get the index of the key
    //loop through the bucket to find the node with the given key
    HashNode<K, V> currNode = table.get(index);
    while (currNode != null) {
      if (currNode.key.equals(key)) {
        return currNode.value;// return the value of the node if it is found
      }
      currNode = currNode.next;
    }
    throw new KeyNotFoundException();

  }

  /**
   * This method returns the number of key,value pairs in the data structure
   */
  @Override
  public int numKeys() {
    return this.itemNumber;
  }

  /**
   * Returns the load factor threshold that was passed into the constructor when creating the
   * instance of the HashTable.
   */
  @Override
  public double getLoadFactorThreshold() {
    return this.loadFactorThreshold;
  }

  /**
   * Returns the current load factor for this hash table load factor = number of items / current
   * table size
   */
  @Override
  public double getLoadFactor() {
    return ((double) this.itemNumber / this.tableSize);
  }

  /**
   * Return the current Capacity (table size) of the hash table array.
   */
  @Override
  public int getCapacity() {
    return this.tableSize;
  }

  /**
   * Returns the collision resolution scheme used for this hash table.Implement with one of the
   * following collision resolution strategies. Define this method to return an integer to indicate
   * which strategy.
   * 
   * @return 8 CHAINED BUCKET: linked nodes of linked node
   **/
  @Override
  public int getCollisionResolution() {
    return 8;
  }



}
