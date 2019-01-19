package org.hum.study.datastruct.linkedlist;

import org.hum.study.datastruct.linkedlist.LinkedListUtils.Node;

public class 链表反转 {

	public static void main(String[] args) {
		Node node = LinkedListUtils.init(10);
		LinkedListUtils.print(node);
		
		Node temp = node;
		Node prev = null;
		
		while (temp != null) {
			Node next = temp.next;
			temp.next = prev;
			prev = temp;
			temp = next;
		}
		
		LinkedListUtils.print(prev);
	}
}
