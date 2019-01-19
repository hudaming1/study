package org.hum.study.datastruct.stack;

import java.util.Arrays;

public class StackUtils {
	
	public static interface Stack<T> {
		public void push(T t);
		public T pop();
		public boolean isEmpty();
		public T poll();
		public int size();
	}

	// 线型栈（基于数组实现）
	@SuppressWarnings("unchecked")
	public static class ArrStack<T> implements Stack<T> {
		private final int DEFAULT_SIZE = 16;
		private Object [] arr;
		private int cursor;
		public ArrStack() {
			arr = new Object[DEFAULT_SIZE];
			cursor = 0;
		}
		
		public void push(T val) {
			if (cursor >= arr.length) {
				Object[] newArr = new Object[arr.length * 2];
				System.arraycopy(arr, 0, newArr, 0, arr.length);
				arr = newArr;
			}
			arr[cursor ++] = val;
		}
		
		public T pop() {
			cursor --;
			T val = (T) arr[cursor];
			arr[cursor] = null;
			return val;
		}
		
		public boolean isEmpty() {
			return cursor == 0;
		}

		@Override
		public T poll() {
			return (T) arr[cursor - 1];
		}

		@Override
		public int size() {
			return cursor;
		}
		
		@Override
		public String toString() {
			if (isEmpty()) {
				return "[]";
			}
			return Arrays.toString(arr);
		}
	}
	
	// 链式栈（基于链表实现）
	public static class LinkedStack<T> implements Stack<T> {
		
		static class Node<T> {
			public T data;
			public Node<T> prev;
			public Node<T> next;
			public Node(T data, Node<T> prev, Node<T> next) {
				this.data = data;
				this.prev = prev;
				this.next = next;
			}
		}
		
		private Node<T> header;
		private int len;

		@Override
		public void push(T t) {
			if (t == null) {
				throw new IllegalArgumentException("data mustn't be null");
			} else if (header == null) {
				header = new Node<>(t, header, null);
				len ++;
				return ;
			}
			
			Node<T> node = new Node<>(t, null, header);
			header.prev = node;
			header = node;
			len ++;
		}

		@Override
		public T pop() {
			if (header == null) {
				throw new IndexOutOfBoundsException();
			}
			
			Node<T> node = header;
			if (node.next != null) {
				node.next.prev = null;
				header = node.next;
			} else {
				header = null;
			}
			
			len --;
			return node.data;
		}

		@Override
		public boolean isEmpty() {
			return len == 0;
		}

		@Override
		public T poll() {
			if (len == 0) {
				throw new NullPointerException("linkedlist is null");
			}
			return header.data;
		}

		@Override
		public int size() {
			return len;
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new ArrStack<>();
		for (int i = 1; i <= 100; i++) {
			stack.push(i);
		}
		
		while (!stack.isEmpty() ) {
			System.out.println(stack.pop());
		}
	}
}
