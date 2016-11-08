package com.nekruss.priorityQueue;



public class TestQueue {

	public static void main(String[] args) {
		
		MyPriorityQueue<Integer> intee = new QueueHeap<>();
		
		intee.insert(1);
		intee.insert(2);
		intee.insert(3);
		intee.insert(4);
		intee.insert(5);
		intee.insert(6);

		
		intee.printQueue();
			
			for (int i=0; i < 4; i++){
				intee.delete();
			}
			intee.printQueue();
	}

}
