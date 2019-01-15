package org.hum.study.datastruct.linkedlist;

import org.hum.study.datastruct.linkedlist.LinkedListUtils.Node;

public class 删除链表第N个节点 {
	
	private static Node deleteAt(Node node, int index) {
		if (node == null || index < 0) {
			throw new IllegalArgumentException("param invaild");
		}
		Node cursor = node;
		Node prev = null;
		while (cursor != null && index >= 0) {
			if (index == 0) {
				if (prev == null) {
					node = node.next;
					return node;
				} else {
					Node next = cursor.next;
					prev.next = next;
					return node;
				}
			}
			index --;
			prev = cursor;
			cursor = cursor.next;
		}
		throw new IndexOutOfBoundsException();
	}

	public static void main(String[] args) {
		Node node = LinkedListUtils.init(10);
		LinkedListUtils.print(node);
//		node = deleteAt(node, 0);
//		node = deleteAt(node, 1);
		deleteAt(node, 5);
//		deleteAt(node, 9);
//		deleteAt(node, 9);
//		deleteAt(node, 15);
		LinkedListUtils.print(node);
	}
}
