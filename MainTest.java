package com.nekruss.collectionsTest;

import com.nekruss.collections.MyStack;

public class MainTest {

	public static void main(String[] args) {

		MyStack stack = new MyStack<String>(15);
		
		stack.push("qwe");
		stack.push("qwer");
		stack.push("qwerty");
		stack.push("qwerty");
		stack.push("qwerty");
		
		while (!stack.isEmpty()){
			
			System.out.println("our string: "+stack.peek());
			
			stack.pop();
			
			
		}
	
		

	}

}
