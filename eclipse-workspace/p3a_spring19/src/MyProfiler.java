/**
 * Filename:   MyProfiler.java
 * Project:    p3b-201901     
 * Authors:    Lynette Gao (LEC 004)
 *
 * Semester:   Spring 2019
 * Course:     CS400
 * 
 * Due Date:   March 28
 * Version:    1.0
 * 
 * Credits:    NA
 * 
 * Bugs:       NA
 */


import java.util.TreeMap;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: MyProfiler
//Files: MyProfiler.java
//
//Author: Lynette Gao
//Email: qgao38@wisc.edu
//Lecturer's Name: Andrew Kuemmel
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:NA
//Partner Email: NA
//Partner Lecturer's Name: NA
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//__x_ Write-up states that pair programming is allowed for this assignment.
//__x_ We have both read and understand the course Pair Programming Policy.
//_x__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully
//acknowledge and credit those sources of help here. Instructors and TAs do
//not need to be credited here, but tutors, friends, relatives, room mates,
//strangers, and others do. If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons: NONE
//Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class is used as the data structure to test our hash table against
 * @author lynette
 *
 */
public class MyProfiler<K extends Comparable<K>, V> {

    HashTableADT<K, V> hashtable;//instance of a hash table
    TreeMap<K, V> treemap;//instance of a tree map
    
    /**
     * This method is the non-argument constructor for the profile
     */
    public MyProfiler() {
      hashtable = new HashTable<K,V>();
      treemap = new TreeMap<K,V>();
  
    }
    
    /**
     * This method insert the key value pair into the maps
     */
    public void insert(K key, V value) {
      treemap.put(key, value);
      try {
      hashtable.insert(key,value);
      }
      // catch possible exceptions
      catch(IllegalNullKeyException e){
        e.printStackTrace();
      } catch (DuplicateKeyException e) {
        e.printStackTrace();
      }
    }
    
    /**
     * This method is the getter method for the maps.
     */
    public void retrieve(K key) {
      treemap.get(key);
      try {
        hashtable.get(key);
      }
      //catch possible exceptions
      catch(IllegalNullKeyException e){
        e.printStackTrace();
      } catch (KeyNotFoundException e) {
        e.printStackTrace();
      }
      
    
    }
    
    /**
     * This method is the remove method for the maps.
     * @param key
     */
    public void remove(K key) {
      treemap.remove(key);
      try {
        hashtable.remove(key);
      }
      catch(IllegalNullKeyException e){
        e.printStackTrace();
      } 
    }
    
    public static void main(String[] args) {
        try {
            long numElements = Integer.parseInt(args[0]);

            
            // Create a profile object. 
            MyProfiler<Integer,Integer> mp = new MyProfiler<Integer, Integer>();
            
            //lots of inserts, retreives, removes
            for(int i = 0;i<=numElements;i++) {
              mp.insert(i, 1);
            }
            
            String msg = String.format("Inserted %d (key,value) pairs", numElements);
            System.out.println(msg);
            
            for(int i = 0;i<=numElements;i++) {
              mp.retrieve(i);
            }
            String msg2 = String.format("retreived %d (key) pairs", numElements);
            System.out.println(msg2);
            
            for(int i = 0;i<=numElements;i++) {
              mp.remove(i);
            }
            String msg3 = String.format("removed %d (key) pairs", numElements);
            System.out.println(msg3);
            
        }
        catch (Exception e) {
            System.out.println("Usage: java MyProfiler <number_of_elements>");
            System.exit(1);
        }
    }
}
