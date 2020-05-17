//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: WaitingQueueADT
// Files: WaitingQueueADT.java
// Course: CS 300
//
// Author: Lynette Gao
// Email: qgao38@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:Susan Jiao
// Partner Email: jjiao25@wisc.edu
// Partner Lecturer's Name: Gary Dahl
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
 * The CustomProcess class represents the type for the processes in our application.
 * @author JIAO&GAO
 *
 */
public class CustomProcess implements Comparable <CustomProcess> {

    private static int nextProcessId = 1; // stores the id to be assigned to the next process 
    // to be created
    private final int PROCESS_ID; // unique identifier for this process
    private int burstTime; // time required by this process for CPU execution
    
    /**
     * This constructor creates a new instance of CustomProcess with the given burstTime.
     * @param burstTime is the time required by a process 
     * to be executed in the Central Processing Unit
     */
    public CustomProcess(int burstTime) { 
        this.burstTime = burstTime;
        PROCESS_ID= nextProcessId; 
        nextProcessId++;//increments the next id
    }
    
    
    /**
     * This method compares the priority of two processor
     * @return a number that indicates the run-priority of the two processor
     */
    @Override
    public int compareTo(CustomProcess other) {
        if(this.burstTime < other.burstTime ) { //the process having the smaller burst time should be run first.
            return -1;//the current processor should run first
        }else if(this.burstTime > other.burstTime) {
            return 1; // the other processor should be run first
        }else { // if the two burst times are equal
            if(this.PROCESS_ID < other.PROCESS_ID) { //if the current processor has a smaller processID
                return -1; // the current processor should run first
            }else { // if the other processor has a smaller processID
                return 1; // the other processor should be run first
            }
        }
    }
    
    /**
     * This is the accessor method for processId
     * @return processId of the current processor
     */
    public int getProcessId() {
        return this.PROCESS_ID;
    }
    
    /**
     * This is the accessor method for BurstTime
     * @return burstTime of the current processor
     */
    public int getBurstTime() {
        return this.burstTime;
    }

}
