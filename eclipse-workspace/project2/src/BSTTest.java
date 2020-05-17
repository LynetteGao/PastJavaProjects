import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: BSTTest
// Files: BSTTest.java
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
 * This class include the tests for methods of data structure BST, which extends DataStructureADT
 * 
 * @author GAO
 *
 */
public class BSTTest extends DataStructureADTTest {

  BST<String, String> bst;
  BST<Integer, String> bst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    dataStructureInstance = bst = createInstance();
    dataStructureInstance2 = bst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = bst = null;
    dataStructureInstance2 = bst2 = null;
  }

  /**
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected BST<String, String> createInstance() {
    return new BST<String, String>();
  }

  /**
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected BST<Integer, String> createInstance2() {
    return new BST<Integer, String>();
  }

  /**
   * Test that empty trees still produce a valid but empty traversal list for each of the four
   * traversal orders.
   */
  @Test
  void testBST_001_empty_traversal_orders() {
    try {

      List<String> expectedOrder = new ArrayList<String>();

      // Get the actual traversal order lists for each type
      List<String> inOrder = bst.getInOrderTraversal();
      List<String> preOrder = bst.getPreOrderTraversal();
      List<String> postOrder = bst.getPostOrderTraversal();
      List<String> levelOrder = bst.getLevelOrderTraversal();

      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      // check whether the list are empty
      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }

  }

  /**
   * Test that trees with one key,value pair produce a valid traversal lists for each of the four
   * traversal orders.
   */
  @Test
  void testBST_002_check_traversals_after_insert_one() {

    try {

      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      bst2.insert(10, "ten");
      if (bst2.numKeys() != 1)
        fail("added 10, size should be 1, but was " + bst2.numKeys());

      List<Integer> inOrder = bst2.getInOrderTraversal();
      List<Integer> preOrder = bst2.getPreOrderTraversal();
      List<Integer> postOrder = bst2.getPostOrderTraversal();
      List<Integer> levelOrder = bst2.getLevelOrderTraversal();

      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      // check whether the list contains one element
      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }

  }


  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_003_check_inOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Pre-Order traversal order: 20-10-30
   */
  @Test
  void testBST_004_check_preOrder_for_balanced_insert_order() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected PREOrder 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20); // V
      expectedOrder.add(10); // L
      expectedOrder.add(30); // R

      // GET PRE-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }



  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Post-Order traversal order: 10-30-20
   */
  @Test
  void testBST_005_check_postOrder_for_balanced_insert_order() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected POSTOrder 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(30); // R
      expectedOrder.add(20); // V

      // GET POST-ORDER and check
      List<Integer> actualOrder = bst2.getPostOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Level-Order traversal order: 20-10-30
   */
  @Test
  void testBST_006_check_levelOrder_for_balanced_insert_order() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected LEVELOrder 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20); // lEVEL 1
      expectedOrder.add(10); // LEVEL 2
      expectedOrder.add(30); // LEVEL 3

      // GET LEVEL-ORDER and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }

  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_007_check_inOrder_for_not_balanced_insert_order() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10-20-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // 1ST
      expectedOrder.add(20); // 2ND
      expectedOrder.add(30); // 3RD

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }


  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Pre-Order traversal order: 10-20-30
   */
  @Test
  void testBST_008_check_preOrder_for_not_balanced_insert_order() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected PREOrder 10-20-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // 1ST
      expectedOrder.add(20); // 2ND
      expectedOrder.add(30); // 3RD

      // GET PRE-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }

  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Post-Order traversal order: 30-20-10
   */
  @Test
  void testBST_009_check_postOrder_for_not_balanced_insert_order() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected POSTOrder 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(30); // 1ST
      expectedOrder.add(20); // 2ND
      expectedOrder.add(10); // 3RD

      // GET POST-ORDER and check
      List<Integer> actualOrder = bst2.getPostOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Level-Order traversal order: 10-20-30 (FIXED ON 2/14/18)
   */
  @Test
  void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected LEVEL Order 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // 1ST
      expectedOrder.add(20); // 2ND
      expectedOrder.add(30); // 3RD

      // GET LEVEL-ORDER and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }


  }



  /**
   * This test check the tree is correctly implemented when one child is deleted for unbalanced
   * tree(one child)
   */
  @Test
  void testBST_011_check_delete_for_one_child_for_unbalanced() {
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");


      // expected inOrder 10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // R
      expectedOrder.add(30); // R


      // GET LEVEL-ORDER and check
      bst2.remove(20);
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * This test check the tree is correctly implemented when one child is deleted for unbalanced tree
   * (two children)
   */
  @Test
  void testBST_012_check_delete_for_two_child() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(15, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(25, "3rd key inserted");
      bst2.insert(40, "3rd key inserted");


      // expected inOrder 25-15-30-40
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(25);
      expectedOrder.add(15);
      expectedOrder.add(30);
      expectedOrder.add(40);


      // GET LEVEL-ORDER and check
      bst2.remove(20);
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * This test check the tree is correctly implemented when calling the delete method twice
   */
  @Test
  void testBST_013_check_delete_and_delete() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(15, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(25, "3rd key inserted");
      bst2.insert(40, "3rd key inserted");

      bst2.remove(20);
      bst2.remove(30);
      // expected inOrder 25-15-40
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(25);
      expectedOrder.add(15);
      expectedOrder.add(40);


      // GET LEVEL-ORDER and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  @Test
  /**
   * This test check the tree is correctly implemented when calling the delete and then insert
   * method
   */
  void testBST_014_check_delete_and_insert() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(15, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(25, "3rd key inserted");
      bst2.insert(40, "3rd key inserted");
      bst2.remove(20);
      bst2.insert(26, "26");

      // expected inOrder 25-15-30-26-40
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(25);
      expectedOrder.add(15);
      expectedOrder.add(30);
      expectedOrder.add(26);
      expectedOrder.add(40);


      // GET LEVEL-ORDER and check

      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  @Test
  /**
   * This test check whether the height is calculated correctly
   */
  void testBST_015_check_height() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(15, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(25, "3rd key inserted");
      bst2.insert(40, "3rd key inserted");


      // expected height should be 3
      int height = 3;

      if (height != bst2.getHeight()) {
        fail("height is wrong");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  @Test
  /**
   * This test check whether the get method returns the correct node's value
   */
  void testBST_016_check_get() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(15, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(25, "3rd key inserted");
      bst2.insert(40, "3rd key inserted");


      if (bst2.get(20).equals("bst2.get(20)")) {
        fail("get method is wrong");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }



}
