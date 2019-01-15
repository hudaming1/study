package org.hum.study.datastruct.linkedlist;

import org.hum.study.datastruct.linkedlist.LinkedListUtils.Node;

public class 求链表中间节点 {

	private static Node findMiddleNode(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("node is null");
		}
		
		Node fast = node;
		Node slow = node;
		while (true) {
			if (fast == null || slow == null || fast.next == null || slow.next == null || fast.next.next == null) {
				return slow;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(findMiddleNode(LinkedListUtils.init(1)));
		System.out.println(findMiddleNode(LinkedListUtils.init(2)));
		System.out.println(findMiddleNode(LinkedListUtils.init(5)));
		System.out.println(findMiddleNode(LinkedListUtils.init(10)));
		System.out.println(findMiddleNode(LinkedListUtils.init(100)));
	}
}
