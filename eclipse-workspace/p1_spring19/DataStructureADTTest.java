//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DataStructureADTTest
// Files: DataStructureADTTest.java
// Course: CS 400 LEC004
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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is an ADT that tests the methods of data structure, which extends DataStructureADT
 * 
 * @author GAO
 *
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance; // instance of the DT

  protected abstract T createInstance();


  /**
   * create instance of DT before each test method
   * 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  /**
   * clear the instance after each test
   * 
   * @throws Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }

  /**
   * This method confirms the empty DT has size zero
   */
  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }


  /**
   * This method insert one key,value pair and remove it, then confirm size is 0.
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("1", "2");// insert one new node
    if (dataStructureInstance.size() != 1)
      fail("data structure should have size=1, but size=" + dataStructureInstance.size());
  }

  /**
   * This method insert one key,value pair and remove it, then confirm size is 0.
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_zero() {
    dataStructureInstance.insert("3", "4");// insert
    dataStructureInstance.remove("3");// remove
    if (dataStructureInstance.size() != 0)// size should remain zero
      fail("data structure should have size=0, but size=" + dataStructureInstance.size());
  }


  /**
   * This method insert a few key,value pairs such that one of them has the same key as an earlier
   * one. Confirm that a RuntimeException is thrown.
   */
  @Test
  void test03_duplicate_exception_is_throwninsert() {
    // CASE 1: insert duplicate key right after the other
    String one ="3";
    String two = "4";
    try {
      dataStructureInstance.insert(one, two);
      dataStructureInstance.insert(one, two);// insert duplicate nodes
      fail("You shoud have caught this");
    } catch (RuntimeException e) {
      // this is good
    } catch (Exception e) {
      fail("fail to catch exception");
    }
    // CASE 2: insert duplicate keys not next to each other
    try {
      dataStructureInstance.insert("key", "key");
      dataStructureInstance.insert("5", "6");
      dataStructureInstance.insert("7", "8");
      dataStructureInstance.insert("key", "key");
    } catch (RuntimeException e) {
      // this is good
    } catch (Exception e) {
      fail("fail to catch exception");
    }


  }

  /**
   * This method remove a key that does not exist in the DT. Confirm whether remove method return
   * false
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    // CASE 1: remove when the DT is empty
    if (dataStructureInstance.remove("C") != false) {
      fail("key is not found,should return false.");
    }
    // CASE 2: remove when the DT is not empty
    dataStructureInstance.insert("A", "B");
    // remove a key that is not in DT
    if (dataStructureInstance.remove("C") != false) {
      fail("key is not found,should return false.");
    }
  }

  /**
   * This methods check whether remove and whether will decrease the size
   */
  @Test
  void test5_remove_success_decrease_size() {
    dataStructureInstance.insert("C", "D");
    dataStructureInstance.remove("C");
    // check whether node still exist in the DT after removing
    if (dataStructureInstance.contains("C")) {
      fail("fail to remove the key");
    }
    // check whether it has updated the size after removing
    if (dataStructureInstance.size() != 0) {
      fail("fail to decrease the size after removing");
    }

  }

  /**
   * This methods confirm the size is update correctly after insertion
   */
  @Test
  void test6_size_after_insert() {
    // insert three nodes
    dataStructureInstance.insert("Q", "W");
    dataStructureInstance.insert("E", "R");
    dataStructureInstance.insert("T", "Y");
    // check whether the size is raised to three
    if (dataStructureInstance.size() != 3) {
      fail("wrong size");
    }
  }

  /**
   * This method check confirms that the duplicate can be inserted after the original one has been
   * removed
   */
  @Test
  void test7_insert_duplicate_after_removed() {
    try {
      dataStructureInstance.insert("key2", "key2");
      dataStructureInstance.insert("9", "10");
      dataStructureInstance.insert("11", "12");
      dataStructureInstance.remove("key2");// remove the original node(duplicate)
      dataStructureInstance.insert("key2", "key2");// add the duplicate again, which should work
    } catch (RuntimeException e) {
      fail("Duplicate has been removed");
    }
  }

  /**
   * This method confirms the insert method does not allow null key and throw the
   * illegalArgumentException
   */
  @Test
  void test8_insert_null() {
    try {
      dataStructureInstance.insert(null, null);// insert a node with null key
      fail("You should have caught this");
    } catch (IllegalArgumentException e) {
      // this is good
    }
  }

  /**
   * This method confirms that when removing null key from the DT will throw
   * IllegalArgumentException
   */
  @Test
  void test9_remove_null() {
    try {
      dataStructureInstance.remove(null);// remove the node with null key
      fail("You should have caught this");
    } catch (IllegalArgumentException e) {
      // this is good
    }
  }

  /**
   * This method confirms the get method return the right value associated with the key
   */
  @Test
  void test10_get_right_value() {
    dataStructureInstance.insert("U", "I");
    if (!dataStructureInstance.get("U").equals("I")) {
      fail("not getting the right value");
    }

  }

  /**
   * This method confirms the get method will throw IllegalArgumentException when trying to get the
   * value of a null key
   */
  @Test
  void test11_get_null() {
    try {
      dataStructureInstance.get(null);
      fail("You should have caught this");
    } catch (IllegalArgumentException e) {
      // this is good
    }
  }

  /**
   * This method confirms when the contain method tries to check null in the DT should return false
   * anyway
   */
  @Test
  void test12_contain_null() {
    if (dataStructureInstance.contains(null) != false) {
      fail("should renturn false when key is null");
    }
  }

  /**
   * This method check whether the DT can be inserted with more than 500 nodes and update the size
   * correctly
   */
  @Test
  void test13_store_500_items() {
    // insert 501 nodes without duplicates
    for (int i = 1; i <= 501; i++) {
      dataStructureInstance.insert(String.valueOf(i), String.valueOf(i));
      // check whether the size has been updated each time the node is added
      if (dataStructureInstance.contains(String.valueOf(i)) != true) {
        fail("not adding 501 items right");
      }
    }

  }

//

}
