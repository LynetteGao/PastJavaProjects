import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;


//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AVLTest
// Files: AVLTest.java
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
/**
 * This class include the tests to test the rebalancing of the AVL tree operations
 * 
 * Notice: testBST_008 and testBST_010 and testBST_011 and testBST_009 will fail because it only
 * aims to check the BST tree
 * 
 * @author GAO
 *
 */


// @SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

  AVL<String, String> avl;
  AVL<Integer, String> avl2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = bst = avl = createInstance();
    dataStructureInstance2 = bst2 = avl2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    avl = null;
    avl2 = null;
  }


  /**
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected AVL<String, String> createInstance() {
    return new AVL<String, String>();
  }


  /**
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected AVL<Integer, String> createInstance2() {
    return new AVL<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testAVL_001_insert_sorted_order_simple() {
    try {
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(30, "30");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testAVL_002_insert_reversed_sorted_order_simple() {
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfLeftChildOf(30).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(10, "10");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testAVL_003_insert_smallest_largest_middle_order_simple() {
    try {
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      avl2.insert(30, "30");
      if (!avl2.getKeyOfRightChildOf(10).equals(30))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testAVL_004_insert_largest_smallest_middle_order_simple() {
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfLeftChildOf(30).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }

  /**
   * This test whether the balance can maintain when inserting more numbers
   */
  @Test
  void testAVL_005_insert_large_order() {
    try {
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");
      avl2.insert(20, "20");
      avl2.insert(30, "30");
      avl2.insert(40, "40");
      avl2.insert(50, "50");
      avl2.insert(25, "25");


      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(30));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(25));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(30), new Integer(40));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(40), new Integer(50));


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }


  /**
   * This test whether the balance when removing from a larger tree
   */
  @Test
  void testAVL_006_remove_large_order() {
    try {
      avl2.insert(10, "10");
      avl2.insert(20, "20");
      avl2.insert(30, "30");
      avl2.insert(40, "40");
      avl2.insert(50, "50");
      avl2.insert(25, "25");

      avl2.remove(30);
      // IF rebalancing is working,
      // the tree should have 40 at the root
      // and 20 as its left child and 50 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(40));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(40), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(25));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(40), new Integer(50));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * This test tests whether tree remains balance when it remove and then insert
   */
  @Test
  void testAVL_007_remove_insert() {
    try {
      avl2.insert(10, "10");
      avl2.insert(20, "20");
      avl2.insert(30, "30");
      avl2.insert(40, "40");

      avl2.remove(30);

      avl2.insert(50, "50");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child
      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(40));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * This test tests whether tree remains balance when it insert, remove, and then insert
   */
  @Test
  void testAVL_08_insert_remove_insert() {
    try {
      avl2.insert(10, "10");
      avl2.insert(20, "20");
      avl2.insert(30, "30");
      avl2.insert(40, "40");

      avl2.remove(30);

      avl2.insert(50, "50");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 40 as its right child
      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(40));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(40), new Integer(50));

    } catch (Exception e) {
      e.printStackTrace();
    }



  }
}
