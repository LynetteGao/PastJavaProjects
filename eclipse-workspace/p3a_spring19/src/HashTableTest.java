import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Random;

/**
 * This class include the tests for methods of data structure HashTabvle, which extends
 * DataStructureADT
 * 
 * @author Lynette
 *
 */
public class HashTableTest {

  HashTable<Integer, String> table;


  @Before
  public void setUp() throws Exception {

  }

  @AfterEach
  public void tearDown() throws Exception {

  }

  /**
   * Tests that a HashTable returns an integer code indicating which collision resolution strategy
   * is used. REFER TO HashTableADT for valid collision scheme codes.
   */
  @Test
  public void test000_collision_scheme() {
    HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer, String>();
    int scheme = htIntegerKey.getCollisionResolution();
    if (scheme < 1 || scheme > 9)
      fail("collision resolution must be indicated with 1-9");
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws IllegalNullKeyException
   */
  @Test
  public void test001_IllegalNullKey() {
    try {
      HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer, String>();
      htIntegerKey.insert(null, null);//insert a null node
      fail("should not be able to insert null key");
    } catch (IllegalNullKeyException e) {
      /* expected */ } catch (Exception e) {
      fail("insert null key should not throw exception " + e.getClass().getName());
    }
  }

  /**
   * This method tests whether get method returns the correct value of the given key
   */
  @Test
  public void test002_getKey_after_Insert() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.75);
      htIntegerKey.insert(1, "1");
      htIntegerKey.insert(2, "2");
      htIntegerKey.insert(3, "3");
      htIntegerKey.insert(4, "4");
      htIntegerKey.insert(5, "5");
      assertEquals(htIntegerKey.get(5), "5");//insert 5, then get 5
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }
  }

  /**
   * This method tests whether the
   */
  @Test
  public void test003_get_key_not_in_table() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.75);
      htIntegerKey.insert(1, "1");
      htIntegerKey.insert(2, "2");
      htIntegerKey.insert(3, "3");
      htIntegerKey.insert(4, "4");
      htIntegerKey.insert(5, "5");
      htIntegerKey.get(100);//get the key that is not in the table
      fail("should throw KeyNotFoundException()");
    } catch (KeyNotFoundException e) {
      // as expected
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }
  }

  /**
   * This method tests whether the rehash happens successfully
   */
  @Test
  public void test004_insert_check_capacity() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.5);
      htIntegerKey.insert(1, "1");
      htIntegerKey.insert(2, "2");//the table should be resized to 7
      assertEquals(htIntegerKey.getCapacity(), 7);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * This test checks whether two duplicates are allowed in the table
   */
  @Test
  public void test005_insert_check_duplicate_key() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.5);
      htIntegerKey.insert(1, "1");
      htIntegerKey.insert(1, "2");//insert another duplicate key
      fail("should throw DuplicateKeyException");
    } catch (DuplicateKeyException e) {
      // as expected
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 005: " + e.getMessage());
    }
  }


  /**
   * This test checks whether remove can successfully remove the node in the table
   */
  @Test
  public void test006_insert_remove() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.5);
      htIntegerKey.insert(1, "1");
      htIntegerKey.insert(2, "2");
      htIntegerKey.insert(3, "3");
      htIntegerKey.remove(1);
      htIntegerKey.get(1);
    } catch (KeyNotFoundException e) {
      // as expected
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 006: " + e.getMessage());
    }
  }

  /**
   * This test checks whether remove can successfully remove the node in the table
   */
  @Test
  public void test007_remove_non_existing() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.5);
      htIntegerKey.insert(1, "1");
      htIntegerKey.insert(2, "2");
      htIntegerKey.insert(3, "3");
      assertEquals(htIntegerKey.remove(4), false);//remove a node that does not exist
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 007: " + e.getMessage());
    }
  }

  /**
   * This test checks whether remove can successfully remove the node in the table
   */
  @Test
  public void test008_remove_null() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.5);
      htIntegerKey.insert(1, "1");
      htIntegerKey.insert(2, "2");
      htIntegerKey.remove(null);//remove null node
      fail("should throw IllegalNullKeyException()");
    } catch (IllegalNullKeyException e) {
      // as expected
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 008: " + e.getMessage());
    }
  }
  
  /**
   * This test checks whether the load factor is updated after rehashing
   */
  @Test
  public void test009_load_factor_change() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.5);//set the threshold low
      htIntegerKey.insert(1, "1");
      htIntegerKey.insert(2, "2");
      htIntegerKey.insert(3, "3");//the table should be resized
      assertEquals(htIntegerKey.getLoadFactor(),(double)3/7);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 009: " + e.getMessage());
    }
  }
  
  /**
   * This test checks whether remove can successfully remove the node in the table
   */
  @Test
  public void test010_add_long_table() {
    try {
      HashTable<Integer, String> htIntegerKey = new HashTable<Integer, String>(3, 0.75);
      //insert 100 nodes
      for(int i=0;i<100;i++) {
        htIntegerKey.insert(i, "1");
      }
      assertEquals(htIntegerKey.numKeys(),100);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 010: " + e.getMessage());
    }
  }
  


  // TODO add your own tests of your implementation

}
