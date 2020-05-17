import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: PackageManagerTest
// Files: PackageManagerTest.java
// Course: CS 400 LEC004
//
// Author: Lynette Gao
// Email: qgao38@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:Roxin Liu
// Partner Email: rliu227@wisc.edu
// Partner Lecturer's Name: Andrew
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
 * This class include the tests for methods of PackageManager
 * 
 * @author Lynette&Roxin
 * 
 */

public class PackageManagerTest {

  PackageManager a = new PackageManager();

  @Before
  public void setUp() throws Exception {

  }

  @AfterEach
  public void tearDown() throws Exception {

  }

  /**
   * Tests that whether the construct graph method can throw the IOException when the path is
   * illgeal
   */
  @Test
  public void test000_illgeal_json_path() {

    try {
      a.constructGraph("illgealfile");// try to access an illegal file
      fail("should have caught IOEXCEPTION");
    } catch (IOException | ParseException e) {
      // expected
    } catch (Exception e) {
      fail("should have caught IOEXCEPTION");
    }

  }

  /**
   * Tests that whether the construct graph method construct the graph appropriately
   */
  @Test
  public void test001_contruct_graph() {
    try {
      a.constructGraph("shared_dependencies.json");
      Set<String> h = new HashSet<String>();
      h.add("A");
      h.add("B");
      h.add("C");
      h.add("D");
      assertEquals(a.getAllPackages(), h);// compare the list of packages
    } catch (IOException | ParseException e) {
      fail("should not have caught IOEXCEPTION");
    }
  }

  /**
   * Test that whether the getInstallatioOrder can Given a package name, returns a list of packages
   * in a valid installation order.
   * 
   * @throws PackageNotFoundException
   * @throws CycleException
   */
  @Test
  public void test002_contruct_graph() throws CycleException, PackageNotFoundException {
    try {
      a.constructGraph("shared_dependencies.json");
      ArrayList<String> h = new ArrayList<String>();
      h.add("D");
      h.add("B");
      h.add("C");
      h.add("A");
      assertEquals(a.getInstallationOrder("A"), h);// get the installation order for one specific
                                                   // package
    } catch (IOException | ParseException e) {
      fail("should not have caught IOEXCEPTION");
    }
  }

  /**
   * Test that whether the getInstallatioOrder can catch a cycle exception
   * 
   * @throws PackageNotFoundException
   * @throws CycleException
   */
  @Test
  public void test003_contruct_graph_with_cycle() {
    try {
      a.constructGraph("cyclic.json");// show throw cycle exception when building a cycle graph
      a.getInstallationOrder("A");
      fail("should have caught cycle exception");
    } catch (CycleException e) {
      // expected
    } catch (Exception e) {
      fail("should have caught cycleException");
    }
  }

  /**
   * Test that whether the getInstallatioOrder can catch a package illegal exception
   */
  @Test
  public void test004_contruct_graph_with_illegal_package() {
    try {
      a.constructGraph("cyclic.json");
      a.getInstallationOrder("illegal");// get the order for an non-exixt package
      fail("should have caught cycle exception");
    } catch (PackageNotFoundException e) {
      // expected
    } catch (Exception e) {
      fail("should have caught cycleException");
    }
  }

  /**
   * Test toIntall implement correctly when with cycle
   */
  @Test
  public void test005_to_install_cycle() {
    try {
      a.constructGraph("cyclic.json");// show throw cyclic exception
      a.toInstall("A", "B");
      fail("should have caught cycle exception");
    } catch (CycleException e) {
      // expected
    } catch (Exception e) {
      fail("should have caught cycleException");
    }
  }

  /**
   * Test toIntall implement correctly without cycle
   */
  @Test
  public void test06_t0_install() {
    try {
      a.constructGraph("shared_dependencies.json");
      ArrayList<String> h = new ArrayList<String>();
      h.add("C");
      h.add("A");
      assertEquals(a.toInstall("A", "B"), h);// test the to install function
    } catch (Exception e) {
      fail("should have caught cycleException");
    }
  }

  /**
   * Test whether toInstall can find out detect the package not found exception
   */
  @Test
  public void test07_t0_install_package_not_exist() {
    try {
      a.constructGraph("shared_dependencies.json");
      a.toInstall("G", "F");
      fail("should have thrown package not found exception");
    } catch (PackageNotFoundException e) {
      // expected
    } catch (Exception e) {
      fail("should have caught other Exception");
    }

  }

  /**
   * Test whether the getInstallationOrderForAllPackages method can return correct topological order
   */
  @Test
  public void test08_t0_install_for_all() {
    try {
      ArrayList<String> h = new ArrayList<String>();
      h.add("D");
      h.add("B");
      h.add("C");
      h.add("A");
      a.constructGraph("shared_dependencies.json");
      assertEquals(h, a.getInstallationOrderForAllPackages());// test the to install all method
    } catch (Exception e) {
      fail("should not throw exception");
    }

  }

  /**
   * Test whether the getInstallationOrderForAllPackages method can throw cycle exception
   */
  @Test
  public void test09_t0_install_for_all_with_cycle() {
    try {

      a.constructGraph("cyclic.json");
      a.getInstallationOrderForAllPackages();
      fail("should throw exception with cycle");
    } catch (CycleException e) {
      // expected
    } catch (Exception e) {
      fail("should have caught exepction");
    }

  }

  /**
   * Test whether the method can return the correct package with max dependency
   */
  @Test
  public void test10_MaxDependencies() {
    try {
      a.constructGraph("shared_dependencies.json");
      String max = a.getPackageWithMaxDependencies();
      System.out.print(max);
      assertEquals(max, "A");// test the maxDependency method

    } catch (Exception e) {
      fail("Fail to find package with max dependencies");
    }
  }


}

