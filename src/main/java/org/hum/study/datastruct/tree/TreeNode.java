package org.hum.study.datastruct.tree;

public class TreeNode<T> {
	public T data;
	public TreeNode<T> left;
	public TreeNode<T> right;

	public TreeNode() {
	}

	public TreeNode(T data) {
		this.data = data;
	}

	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "[TreeNode data=" + data + "]";
	}
}