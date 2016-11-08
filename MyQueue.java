package com.nekruss.collections;

/*Наша очередь - циклическая, основана на массиве. И если массив заполняется, то маркер front 
перекидывается на начало очереди - на место освободившихся ячеек. */

public class MyQueue <T> {

	private int maxSize;
	private T [] queueArray;
	private int front;
	private int rear;
	private int nItems;
	
	public MyQueue (int sizeOfQueue){
		
		maxSize = sizeOfQueue;
		queueArray = (T[])(new Object[maxSize]);	// массив - размер очереди. 
		front = 0; 									//передний элемент. Он первым заходит и выходит
		rear = -1; 									// вставляем сюда
													// последним заходит и последним выходит
		nItems = 0;
		
	}
	
	public void insert (T insertionElem){
		
		if (rear == maxSize - 1) 			// циклический перенос если достигнут край массива
			rear = -1;
		queueArray[++rear] = insertionElem; // увеличение rear и вставка
		nItems++;							// увеличение количества элементов
	}
	
	public T remove (){ 					// извлечение элемента вначале очереди
		
		T temp = queueArray[front++];       // выборка удаляемого элемента и увеличение front
		if (front == maxSize)				// циклический перенос
			front = 0;
		nItems--; 							// уменьшаем количество элементов, ибо один нас покинул
		return temp;
	}
	
	
	public T peekFront () {					// чтение элемента вначале очереди
		return queueArray[front];
	}
	
	public boolean isEmpty (){
		return (nItems == 0);
	}
	
	public boolean isFull(){
		return (nItems == maxSize);
	}
	
	public int size(){
		return nItems;
	}
	
	
	
	
	
	
	
}
