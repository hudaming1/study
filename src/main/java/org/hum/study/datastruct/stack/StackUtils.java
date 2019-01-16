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
			if (cursor >= arr.length) {
				Object[] newArr = new Object[arr.length * 2];
				System.arraycopy(arr, 0, newArr, 0, arr.length);
				arr = newArr;
			}
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

		@Override
		public void push(T t) {
			if (t == null) {
				throw new IllegalArgumentException("data mustn't be null");
			} else if (header == null) {
				header = new Node<>(t, header, null);
				return ;
			}
			
			Node<T> node = new Node<>(t, null, header);
			header.prev = node;
			header = node;
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
			
			return node.data;
		}

		@Override
		public boolean isEmpty() {
			return header == null;
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
