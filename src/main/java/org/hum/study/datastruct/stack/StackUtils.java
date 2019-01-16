package org.hum.study.datastruct.stack;

public class StackUtils {
	
	static interface Stack<T> {
		public void push(T t);
		public T pop();
		public boolean isEmpty();
	}

	// 线性栈（基于数组实现）
	static class ArrStack<T> implements Stack<T> {
		private final int DEFAULT_SIZE = 16;
		private Object [] arr;
		private int cursor;
		public ArrStack() {
			arr = new Object[DEFAULT_SIZE];
			cursor = 0;
		}
		
		public void push(T val) {
			arr[cursor ++] = val;
		}
		
		@SuppressWarnings("unchecked")
		public T pop() {
			cursor --;
			T val = (T) arr[cursor];
			arr[cursor] = null;
			return val;
		}
		
		public boolean isEmpty() {
			return cursor == 0;
		}
	}
	
	// 链式栈（基于链表实现）
	static class LinkedStack<T> implements Stack<T> {

		@Override
		public void push(T t) {
			// TODO Auto-generated method stub
		}

		@Override
		public T pop() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	public static void main(String[] args) {
		ArrStack<Integer> stack = new ArrStack<>();
		for (int i = 1; i <= 10; i++) {
			stack.push(i);
		}
		
		while (!stack.isEmpty() ) {
			System.out.println(stack.pop());
		}
	}
}
