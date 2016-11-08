package com.nekruss.priorityQueue;

public interface MyPriorityQueue <T> {

	void insert (T e); 
	
	T delete ();
	
	T peek();
	
	int size();
	
	boolean isEmpty();
	
	boolean isFull();
	
	void printQueue();
	
	
}
