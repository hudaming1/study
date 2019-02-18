package org.hum.study.datastruct.tree;

public class RedBlackTreeUtils {

	/**
	 * 红黑树特性
	 * <pre>
	 *   1.节点都是红色或黑色
	 *   2.根节点是黑色
	 *   3.子节点下的nil节点都是黑色
	 *   4.红色节点的两个子节点都是黑色的
	 *   5.从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点
	 *   其他注意点：
	 *   	新增节点是红色
	 * </pre>
	 */
	public static class RedBlackTree<T extends Comparable<T>> {
		
		private RbTreeNode<T> root;
		
		public RbTreeNode<T> add(T data) {
			root = add(root, data);
			root.color = false; // root保持黑色
			return root;
		}
		
		public RbTreeNode<T> add(RbTreeNode<T> node, T data) {
			/** 1.插入节点到合适位置 **/
			if (data.compareTo(node.data) < 0) {
				node.left = add((RbTreeNode<T>) node.left, data);
			} else if (data.compareTo(node.data) > 0) {
				node.right = add((RbTreeNode<T>) node.right, data);
			} else {
				node.data = data;
			}
			/** 2.维护红黑树的平衡性 **/
			// 1.左黑右红，左旋并染红
			
			// 2.左红左左红，右旋并染红
			
			// 3.左右都红，染黑，自己红
			
			return node;
		}
		
		public void delete(T data) {
		}
		
		public RbTreeNode<T> find(T data) {
			// 还像二叉树查找即可
			return null;
		}
	}
	
	public static class RbTreeNode<T> extends TreeNode<T> {
		// true-红；false-黑
		public boolean color;
	}
}
