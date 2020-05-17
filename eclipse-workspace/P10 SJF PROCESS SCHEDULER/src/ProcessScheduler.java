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

import java.util.Scanner;

/**
 * The ProcessScheduler class represents the data type for the main scheduler
 * for our processes.
 * 
 * @author JIAO&GAO
 *
 */
public class ProcessScheduler {
    private int currentTime; // stores the current time after the last run
    private int numProcessesRun; // stores the number of processes run so far
    private CustomProcessQueue queue; // this processing unit's custom process queue

    /**
     * This is the constructor of the class that sets up the CustomProcessQueue queue
     */
    public ProcessScheduler() {
        this.queue = new CustomProcessQueue(); //set up the queue
        this.currentTime = 0;
        this.numProcessesRun = 0;
    }

    /**
     * This method enqueues the given process in the CustomProcessQueue queue
     * 
     * @param process
     *            is the current process
     */
    public void scheduleProcess(CustomProcess process) {
        queue.enqueue(process);
    }

    /**
     * This method implements the run action and messages of the process scheduler
     * 
     * @return the log containing all the actions and updates
     */
    public String run() {
        String log = "";
        int queueSize = queue.size();
        //starting message containing the queue size
        if (queueSize == 0 || queueSize == 1) { //case when size is 0 or 1
            log += "Starting " + queueSize + " process\n\n";
        } else if (queueSize > 1) {//case when size is greater than 1
            log += "Starting " + queueSize + " processes\n\n";
        }

        while (!queue.isEmpty()) { //as long as queue is not empty
            //remove data from the queue
            CustomProcess remove = queue.dequeue();
            //message containing details about the removed data
            log += "Time " + this.currentTime + " : Process ID " + remove.getProcessId() + " Starting.\n";
            this.currentTime += remove.getBurstTime(); //update current time
            log += "Time " + this.currentTime + " : Process ID " + remove.getProcessId() + " Completed.\n";
            this.numProcessesRun++; // update the number of processes run
        }
         //when all data are removed
        log += "\nTime " + this.currentTime + " : All scheduled processes completed.\n";
        return log; 
    }
    
    
    /**
     * This is the main method of the class the drives this whole project
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProcessScheduler scheduler = new ProcessScheduler();
        String input1 = ""; // user's command
        int input2 =0; // user inputed burst time 
        System.out.println("==========   Welcome to the SJF Process Scheduler App   ========\n");

        do{//menu
            System.out.println("Enter command:");
            System.out.println("[schedule <burstTime>] or [s <burstTime>]");
            System.out.println("[run] or [r]");
            System.out.println("[quit] or [q]\n");
            if (sc.hasNextLine()) {
                String input = sc.nextLine().trim().toLowerCase();
                String[] parts = input.split(" ");
                input1 = parts[0]; //obtains user's command
                if (parts.length == 2) {
                    try {
                        //potential exception if user didn't input a integer
                   input2 = Integer.parseInt(parts[1]); 
                   if(! (input2> 0)) { //user must input a positive burst time 
                       System.out.println("WARNING: burst time MUST be greater than 0!\n");
                       continue;
                   }
                    }catch(NumberFormatException e) {//when the user didn't input an integer
                        System.out.println("WARNING: burst time MUST be an integer!\n");
                        continue;
                    }
                }
            }
            
            //when the user wants the schedule a new process
            if(input1.equals("s") || input1.equals("schedule")) {
                CustomProcess newP = new CustomProcess(input2);
                scheduler.scheduleProcess(newP);
                System.out.println("Process ID " + newP.getProcessId() + " scheduled. Burst Time = " 
                + newP.getBurstTime() +"\n");
            } else if(input1.equals("run") || input1.equals("r")) {//user wants to run the scheduler
                System.out.println(scheduler.run());
                //if user enters anything else other than (s/schedule/r/run/q/quit)
            }else if(!(input1.equals("quit") || input1.equals("q"))){
                System.out.println("WARNING: Please enter a valid command!\n");
                continue;
            }

        }while(!(input1.equals("quit") || input1.equals("q")));//continue as long as user didn't quit
        //print out ending message
        System.out.println( scheduler.numProcessesRun +" processes run in "+ scheduler.currentTime +" units of time!");
        System.out.println("Thank you for using our scheduler!");
        System.out.println("Goodbye!");
    }

}
