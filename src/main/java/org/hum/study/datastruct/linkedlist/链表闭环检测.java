package org.hum.study.datastruct.linkedlist;

import org.hum.study.datastruct.linkedlist.LinkedListUtils.Node;

/**
 * 采用追指针方式判断
 * @author huming
 */
public class 链表闭环检测 {

	private static boolean checkIsCircle(Node node) {
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
	
	public static void main(String[] args) {
		Node circleLinkedList = LinkedListUtils.initCircleLinkedList();
		System.out.println(checkIsCircle(LinkedListUtils.init(1)));
		System.out.println(checkIsCircle(LinkedListUtils.init(9)));
		System.out.println(checkIsCircle(LinkedListUtils.init(100)));
		System.out.println(checkIsCircle(circleLinkedList));
	}
}
