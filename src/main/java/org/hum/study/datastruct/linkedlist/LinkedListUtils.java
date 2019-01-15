package org.hum.study.datastruct.linkedlist;

public class LinkedListUtils {

	public static class Node {
		public Node next;
		public Object data;
		
		public Node() { } 
		public Node(Object data) { this(data, null); }
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			if (data == null) {
				return null;
			}
			return data.toString();
		}
	}
	
	public static Node init(int num) {
		if (num <= 0) {
			throw new IllegalArgumentException("param \"num\" must greatthan 0");
		}
		Node node = new Node(num);
		for (int i = num - 1; i > 0; i--) {
			Node n = new Node(i, node);
			node = n;
		}
		return node;
	}
	
	public static Node initCircleLinkedList() {
		Node node = new Node(1);
		
		node.next = new Node(2, new Node(3, new Node(4, new Node(5, node))));
		
		return node;
	}
	
	public static void print(Node node) {
		if (node == null) {
			System.out.println("linkedlist is null");
			return ;
		}
		Node printNode = node;
		while (printNode != null) {
			System.out.print(printNode.data + " -> ");
			printNode = printNode.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		print(initCircleLinkedList());
	}
}
