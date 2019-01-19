package org.hum.study.datastruct.linkedlist;

import org.hum.study.datastruct.linkedlist.LinkedListUtils.Node;

public class 链表闭环检测 {

	/**
	 * 采用追指针方式判断，时间复杂度O(n/2)
	 */
	static boolean checkIsCircle(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("node is null");
		}
		Node fast = node;
		Node slow = node;

		while (true) {
			if (fast == null || fast.next == null || fast.next.next == null || slow == null || slow.next == null) {
				// 如果一旦有null则说明到末尾了，就不是闭环
				return false;
			}
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
	}
	
	/**
	 * 时间复杂度O(n)
	 */
	static boolean checkIsCircle2(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("node is null");
		}
		
		Node header = node;
		Node cursor = node;
		while (cursor != null) {
			if (cursor.next == null) {
				return false;
			}
			cursor = cursor.next;
			if (header == cursor) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Node circleLinkedList = LinkedListUtils.initCircleLinkedList();
		System.out.println(checkIsCircle2(LinkedListUtils.init(1)));
		System.out.println(checkIsCircle2(LinkedListUtils.init(9)));
		System.out.println(checkIsCircle2(LinkedListUtils.init(100)));
		System.out.println(checkIsCircle2(circleLinkedList));
	}
}
