package com.nekruss.priorityQueue;

/*
 * Мы используем интерфейс очереди как шаблон.
 * пирамида у нас разрастается, от минимальных элементов в корне, и увеличиваеться 
 * т.е. ключ каждого узла меньше либо равен ключу потомков.
 */




public class QueueHeap <T> implements MyPriorityQueue<T>{

	private Object [] heap = new Object [4];
	
	private int size;
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void insert(T e) {

		if (e == null){
			throw new NullPointerException();
		}
		
		doubleArray(size+1);
		
		if (size == 0){
			heap[0] = e;
		}
		else{
			moveUp(size, e);
		}
		size++;
		}

	
	private void doubleArray(int size){
		if (size <= heap.length){
			return;
		}
		/*
		 * Создаем новый массив в 2 раза больше, чем size;
		 */
		Object[] newElements = new Object[2*size];
		
		// копируем элементы из старого:
		
		for (int i = 0; i < heap.length; i++){
			newElements[i] = heap[i];
		}
		
		// и делаем новый массив кучей
		heap = newElements;
	}
	
	private void moveUp(int k, T element){
		
		Comparable<? super T> key = (Comparable<? super T>) element;
		while ( k>0 ){
			int parent = (k-1)>>>1;
			Object e = heap[parent];
			if(key.compareTo((T) e ) >= 0){
				break;
			}
			
			heap[k] = e;
			k = parent;
		}
		
		heap[k] = key;
		
	}
	
	/**
	 * Минимальный узел всегда является корневым и хранится в нулевой ячейке массива.
	 * Проблема в том, что после удаления корень необходимо заполнить.
	 * Вместо сдвига всех элементов на одну позицию вперед используется более быстрое решение:
	 * 1) Удалить корневой узел
	 * 2) Переместить последний узел на место корневого
	 * 3) Смещать его вниз до тех пор, пока он не окажется ниже меньшего и выше большего узла.
	 */
	
	
	
	@Override
	public T delete () {
		if (size == 0){
		return null;
	}
		-- size;
		
		// запоминаем значение корневого узла:
		
		T result = (T) heap[0];
		
		// перемещаем последний на место корневого:
		
		heap[0] = heap[size];
		
		//удаляем лишнюю ссылку последнего элемента
		
		heap[size] = null;
		
		if (size !=0 ){
			// cмещаем новую вершину вниз, пока она не займет подобающее место:
			moveDown(0);
		}
		return result;
		
	}
	
	
	/*
	 * Смещаем узел с индексом index вниз, пока он не станет ниже меньшего, и выше большего узла
	 */
	
	@SuppressWarnings("unchecked")
	
	private void moveDown (int index){
		
		Comparable <? super T> key = (Comparable <? super T>) heap [index];
		
		int half = size / 2;
		// пока у узла имеется хотя бы один потомок:
		
		while (index < half){
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			
			// определяем индекс большего потомка 
			
			int smallestChild = leftChild;
			
			// если правый потомок есть, и он больше, чем левый:
			
			if(rightChild < size && 
		((Comparable<? super T>)heap [leftChild]).compareTo((T) heap[rightChild]) > 0)
			{
				smallestChild = rightChild;
			}
			// если текущий узел меньше обоих потомков, то смещение к вниз закончено
			
			if (key.compareTo((T) heap[smallestChild]) <= 0){
				break;
			}
			// или меньший потомок перемещается на место текущего узла.
			heap[index] = heap [smallestChild];
			// и перемещаем индекс на место меньшего потомка
			index = smallestChild;
		}
		// когда условие соблюдено - вставляем узел 
		heap[index] = key;
	}
	
	
	

	@Override
	public T peek() {
		return (T) heap[0];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printQueue() {
		for (int i = 0; i < size; i++){
			System.out.print(heap[i]+"");
		}
		System.out.println();
		
	}

}	
