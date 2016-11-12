package com.nekruss.binaryTree;

public class MyBinaryTree {

	private Node root;

	private int size;
	
	
	public Node find(int key) {

		Node current = root;

		while (current.key != key) {

			if (current.key < key)
				current = current.leftChild;

			else
				current = current.rightChild;

			if (current == null)
				return null;
		}
		return current;
	}

	public void insert(int key, double data) {

		Node newNode = new Node();
		newNode.key = key;
		newNode.data = data;

		size++;
		
		if (root == null) {
			root = newNode;
		} else {

			Node current = root;
			Node parent = current;

			while (true) {

				if (key < current.key) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	private void traverse (Node localRoot){
		
		if(localRoot != null)
		{
			traverse(localRoot.leftChild);
			System.out.print(localRoot.data+"");
			traverse(localRoot.rightChild);
		}
	}
	
	public Node minimum (){
		Node current;
		Node last = root;
		current = root;
		while (current != null){
			last = current;
			current = current.leftChild;
		}
		return last;
	}
	
	public Node maximum(){
		Node current;
		Node max = root;
		current = root;
		while (current != null){
			max = current;
			current = current.rightChild;
		}
		return max;
	}
	
	public boolean delete(int key) {
		if (root == null) {
			return false;
		}
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		// поиск узла
		while (current.key != key) {
			parent = current;

			if (key < current.key) {
				// двигаться налево
				isLeftChild = true;
				current = current.leftChild;
			} else {
				// двигаться направо
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) {
				// узел не найден
				return false;
			}
		}
		// если узел не имеет потомков, то он просто удаляется
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root) {
				root = null;
			} else if (isLeftChild) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		}
		// если нет правого потомка, узел заменяется левым поддеревом
		else if (current.rightChild == null) {
			if (current == root) {
				root = current.leftChild;
			} else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			} else {
				parent.rightChild = current.leftChild;
			}
		}
		// если нет левого потомка, узел заменяется правым поддеревом
		else if (current.leftChild == null) {
			if (current == root) {
				root = current.rightChild;
			} else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			} else {
				parent.rightChild = current.rightChild;
			}
		}
		// два потомка, узел заменяется переемником
		else {
			// поиск переемника для удаляемого узла(current)
			Node successor = getSuccessor(current);
			// родитель current связывается с переемником
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.leftChild = successor;
			} else {
				parent.rightChild = successor;
			}			 
			//переемник связывается с левым потомком current
	         successor.leftChild = current.leftChild;
		}
		size--;
		return true;
	}

	/**
	 * Метод возвращает узел со следующим значенем после delNode. Для этого он
	 * сначала переходит к правому потомку, а затем отслеживает цепочку левых
	 * потомков этого узла.
	 * 
	 * @param delNode
	 *            узел, который требуется удалить
	 * @return возвращает переемника удаляемого узла
	 */
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		// переход к правому потомку
		Node current = delNode.rightChild;
		// пока есть левые потомки
		while (current != null) {
			successorParent = successor;
			successor = current;
			// переход к левому потомку
			current = current.leftChild;
		}
		// если переемник не является правым потомком, создать связи между
		// узлами
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	
}

class Node {

	public int key;
	public double data;

	public Node leftChild;
	public Node rightChild;

}