//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: CustomProcessQueue
// Files: CustomProcessQueue.java
// Course: CS 300
//
// Author: Susan Jiao
// Email: jjiao25@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:Lynette Gao
// Partner Email: qgao38@wisc.edu
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
 * This classes constructs a queue that stores and removes customprocess data
 * 
 * @author JIAO
 *
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {

	private static final int INITIAL_CAPACITY = 20; // the initial capacity of the heap
	private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize array
	private int size; // number of CustomProcesses present in this CustomProcessQueue

	/**
	 * The constructor creates an empty CustomProcessQueue.
	 */
	public CustomProcessQueue() {
		this.heap = new CustomProcess[INITIAL_CAPACITY];//Initialize the heap
		size = 0; //initializes the size
		
	}

	/**
	 * This method inserts new object according to its priority 
	 * Highest priority is at the top
	 * 
	 * @param newObject
	 *            is the new custom process data to be added
	 */
	@Override
	public void enqueue(CustomProcess newObject) {
		if (this.heap == null) {// case when the heap is null
			heap[0] = null;
			heap[1] = newObject;
		} else {// case when there's already data in the heap
			if(size*2 >= heap.length) {//if the array is full
				//create a new array doubling the size of the original one
			  CustomProcess[] newHeap = new CustomProcess[this.heap.length *2];
			  for (int i = 0; i < this.heap.length; i++) {
				  newHeap[i] = this.heap[i];
			  }
			  this.heap = newHeap;
			}
			heap[size+1] = newObject;
			//call the helper method to maintain the heap order
			minHeapPercolateUp(size+1);
            this.size++;
		}

	}

     /**
      * This method helps the enqueue process by percolating up the heap
      * to maintain the heap order
      * @param index is the location of the current process
      */
	private void minHeapPercolateUp(int index) {
		
		CustomProcess newObject = heap[index];
		CustomProcess parent = heap[index/2];
		// if the newObject has higher priority than its parent
		if(parent != null) { 
		if (newObject.compareTo(parent) < 0) { 
				// swap the parent and the newObject
			heap[index/2] = newObject;
			heap[index] = parent;
			minHeapPercolateUp(index/2);// recurse through parent
		}
		}
	}

	/**
	 * This method removes the top priority process in the queue and returns it
	 * 
	 * @return the top priority process in the queue
	 */
	@Override
	public CustomProcess dequeue() {
		if (this.isEmpty() == true) { // case when the heap is empty
			return null;
		} else {// when there exits data in the heap
				// remove and return the higher priority one(the root)
            CustomProcess topPriority = heap[1];
			if(heap[2] == null) {//case when the heap contains only one process
			heap[1] = null; 
			size--;
			return topPriority;
			}
			
			//case when there is more than one process in the heap
			heap[1] = heap[size]; // replace topPriority(root) with last one in array
			heap[size] = null; // remove the topPriority
            size--;
			minHeapPercolateDown(1); // call the private helper to percolate down the tree from the root
			return topPriority;
		}
	}

	/**
	 * The method helps the dequeue method to percolate up the heap 
	 * to ensure the heap structure is maintained
	 * @param index is the position of the current process
	 */
	private void minHeapPercolateDown(int index) {
		CustomProcess leftChild = heap[index * 2];
		CustomProcess rightChild = heap[index * 2 + 1];
		CustomProcess current = heap[index];

		if (leftChild != null) {// if left child is not null
			if (rightChild != null) {// if right child is not null
				// if one of the children has higher priority than the current one
				if (leftChild.compareTo(current) < 0 || rightChild.compareTo(current) < 0) {
					if (leftChild.compareTo(rightChild) < 0) {// if left child has higher priority
						// swap with left child
						heap[index] = leftChild;
						heap[index * 2] = current;
						minHeapPercolateDown(index * 2); // recurse through left child
					} else {// if right child has higher priority
							// swap with right child
						heap[index] = rightChild;
						heap[index * 2 + 1] = current;
						minHeapPercolateDown(index * 2 + 1); // recurse through right child
					}
				}
			} else {// if leftChild exist but rightChild don't
				if (leftChild.compareTo(current) < 0) {// if the leftChild has higher priority
					// swap with left child
					heap[index] = leftChild;
					heap[index * 2] = current;
					minHeapPercolateDown(index * 2); // recurse through left child
				}
			}
		}

	}

	/**
	 * This method returns the top priority process in the heap
	 * @return the top priority custom process
	 */
	@Override
	public CustomProcess peek() {
		//case when the heap is empty
		if (this.isEmpty() == true) {
			return null;
		}else {//when it is not empty
			return heap[1]; //return the top priority one
		}
	}

	/**
	 * This method returns number of CustomProcesses present in this
	 * CustomProcessQueue
	 * 
	 * @return the number of CustomProcesses present in this CustomProcessQueue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * This method checks whether the heap is empty
	 * 
	 * @return whether the heap is empty
	 */
	@Override
	public boolean isEmpty() {//CHNAGED
       if(heap[1] == null || this.heap == null) {
    	   return true;
       }else {
    	   return false;
       }
		
//      if (heap[1] == null) { 
//			return true;
//		} else {
//			return false;
//		}
	}

	/**
	 * This is the getter method for heap
	 * @return heap that stores the data 
	 */
	public CustomProcess[] getHeap() {
		return heap;
	}
}
