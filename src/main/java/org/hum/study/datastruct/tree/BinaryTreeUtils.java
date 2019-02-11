package org.hum.study.datastruct.tree;

import java.util.LinkedList;
import java.util.Queue;

import org.hum.study.datastruct.stack.StackUtils;
import org.hum.study.datastruct.stack.StackUtils.Stack;

public class BinaryTreeUtils {

	static class BinaryTree<T> {
		public TreeNode<T> root;
		
		public void printPreOrder() {
			printPreOrder(root);
		}
		
		// 递归先序遍历
		private void printPreOrder(TreeNode<T> node) {
			if (node == null || node.data == null) {
				return ;
			}
			System.out.println(node.data);
			printPreOrder(node.left);
			printPreOrder(node.right);
		}
		
		// 栈先序遍历
		public void printPreOrderByStack() {
			if (root == null) {
				System.out.println("tree is null");
			}
			
			Stack<TreeNode<T>> stack = new StackUtils.LinkedStack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				TreeNode<T> node = stack.pop();
				System.out.println(node.data);
				if (node.right != null) {
					stack.push(node.right);
				}
				if (node.left != null) {
					stack.push(node.left);
				}
			}
		}
		
		// 层级遍历
		public void levelOrder() {
			Queue<TreeNode<T>> list = new LinkedList<>();
			list.add(root);
			while (!list.isEmpty()) {
				TreeNode<T> node = list.poll();
				System.out.println(node.data);
				if (node.left != null) {
					list.add(node.left);
				}
				if (node.right != null) {
					list.add(node.right);
				}
			}
		}
		
		// 递归方式查找最大值
		@SuppressWarnings("unchecked")
		public T findMaxValue(TreeNode<T> node) {
			if (node == null || node.data == null) {
				return null;
			} else if (node.data != null && node.left == null && node.right == null) {
				return node.data;
			}
			Comparable<T> max = (Comparable<T>) node.data;
			Comparable<T> leftData = (Comparable<T>) findMaxValue(node.left);
			Comparable<T> rightData = (Comparable<T>) findMaxValue(node.right);
			
			if (leftData == null && rightData != null) {
				return max.compareTo((T) rightData) < 0 ? (T) rightData : (T) max;
			} else if (leftData != null && rightData == null) {
				return max.compareTo((T) leftData) < 0 ? (T) leftData : (T) max;
			} else if (leftData == null || rightData == null) {
				return (T) max;
			} 
			
			if (max.compareTo((T) leftData) < 0) {
				return leftData.compareTo((T) rightData) < 0 ? (T) rightData : (T) leftData;
			} else {
				return max.compareTo((T) rightData) < 0 ? (T) rightData : (T) max;
			}
		}
		
		public T findMaxValue() {
			return findMaxValue(root);
		}
		
		// 找到目标值(栈寻找)
		public TreeNode<T> findValue(T t) {
			if (t == null) {
				throw new IllegalArgumentException("param mustn't be null");
			}
			
			Stack<TreeNode<T>> stack = new StackUtils.ArrStack<>();
			stack.push(root);
			
			while (!stack.isEmpty()) {
				TreeNode<T> node = stack.pop();
				if (node.data.equals(t)) {
					return node;
				}
				if (node.left != null) {
					stack.push(node.left);
				}
				if (node.right != null) {
					stack.push(node.right);
				}
			}
			return null;
		}
		
		// 获得data到Root的节点路径
		public Stack<TreeNode<T>> findRootPath(T data) {
			Stack<TreeNode<T>> stack = new StackUtils.ArrStack<>();
			if (root == null || root.data == null) {
				return stack;
			} else if (root.data.equals(data)) {
				stack.push(root);
				return stack;
			}
			TreeNode<T> node = findRootPath(stack, root, data);
			if (node != null) {
				stack.push(node);
			}
			return stack;
		}
		
		private TreeNode<T> findRootPath(Stack<TreeNode<T>> stack, TreeNode<T> node, T data) {
			if (node == null || node.data == null) {
				return null;
			}
			if (node.data.equals(data)) {
				return node;
			}
			
			if (node.left != null) {
				TreeNode<T> n = findRootPath(stack, node.left, data);
				if (n != null) {
					stack.push(n);
					return node;
				}
			}

			if (node.right != null) {
				TreeNode<T> n = findRootPath(stack, node.right, data);
				if (n != null) {
					stack.push(n);
					return node;
				}
			}
			
			return null;
		}

		// 找到公共祖先
		public TreeNode<T> findLCA(T data1, T data2) {
			Stack<TreeNode<T>> stack1 = findRootPath(data1);
			if (stack1.isEmpty()) {
				throw new RuntimeException("data[" + data1 + "] not exists in tree.");
			}
			Stack<TreeNode<T>> stack2 = findRootPath(data2);
			if (stack2.isEmpty()) {
				throw new RuntimeException("data[" + data2 + "] not exists in tree.");
			}
			TreeNode<T> node = root;
			while (!stack1.isEmpty() && !stack2.isEmpty()) {
				TreeNode<T> node1 = stack1.pop();
				TreeNode<T> node2 = stack2.pop();
				if (node1 != node2) {
					return node;
				}
				node = node1;
			}
			return node;
		}
		
		// 统计数的高度
		public int getTreeHeiht() {
			if (root == null) {
				return 0;
			}
			return getTreeHeight(root, 1);
		}
		
		private int getTreeHeight(TreeNode<T> node, int height) {
			if (node == null || (node.left == null && node.right == null)) {
				return height;
			}
			int leftHeight = 0, rightHeight = 0;
			if (node.left != null) {
				leftHeight = getTreeHeight(node.left, height + 1);
			}
			if (node.right != null) {
				rightHeight = getTreeHeight(node.right, height + 1);
			}
			return leftHeight > rightHeight ? leftHeight : rightHeight;
		}
		
		// 生成镜像树
		public BinaryTree<T> createMirrorTree() {
			// TODO
			return null;
		}
		
		// 判断是否是严格二叉树(儿女节点双全)
		public boolean isStrictBinaryTree() {
			if (root == null) {
				return false;
			}
			Stack<TreeNode<T>> stack = new StackUtils.LinkedStack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				TreeNode<T> node = stack.pop();
				if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) {
					return false;
				}
				if (node.left != null) {
					stack.push(node.left);
				}
				if (node.right != null) {
					stack.push(node.right);
				}
			}
			return true;
		}
		
		public boolean isStrictBinaryTree(TreeNode<T> node) {
			if (node == null) {
				return false;
			}
			if (node.left != null && node.right != null) {
				if (!isStrictBinaryTree(node.left)) {
					return false;
				}
				return isStrictBinaryTree(node.right);
			} else if (node.left == null && node.right == null) {
				return true;
			} else if ((node.left == null && node.right != null) || (node.left != null && node.right == null)){
				return false;
			} else {
				throw new RuntimeException("unreachable code!");
			}
		}
		
		// 判断是否是完全二叉树(只允许最后一层有空缺结点且空缺在右边，即叶子结点只能在层次最大的两层上出现；对任一结点，如果其右子树的深度为j，则其左子树的深度必为j或j+1 即度为1的点只有1个或0个)
		public boolean isCompleteBinaryTree() {
			// TODO
			return false;
		}

		// 判断是否是满二叉树(正三角形树)
		public boolean isFullBinaryTree() {
			// TODO
			return false;
		}
		
		// 统计出叶子节点数量
		public int getLeafCount() {
			if (root == null) {
				return 0;
			}
			int leafCount = 0;
			Stack<TreeNode<T>> stack = new StackUtils.LinkedStack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				TreeNode<T> node = stack.pop();
				if (node != null && node.data != null) {
					leafCount ++;
				}
				if (node.left != null) {
					stack.push(node.left);
				}
				if (node.right != null) {
					stack.push(node.right);
				}
			}
			return leafCount;
		}
		
		// 统计出满叶子节点数量
		public int getFullLeafCount() {
			// TODO
			return 0;
		}
		
		// 判断两棵树是否相等
		@Override
		public boolean equals(Object obj) {
			// TODO
			return super.equals(obj);
		}
		
		// 计算树的宽度
		public int getWeight() {
			// TODO
			return 0;
		}
		
		// 统计出同一层节点数据之和最大的层
		public int getMaxLevelLayer() {
			// TODO
			return 0;
		}
		
		// Zigzag遍历
		public void printZigzag() {
			// TODO
		}
	
		// 构建简单二叉树
		public static BinaryTree<Integer> buildSimpleTree() {
			BinaryTree<Integer> tree = new BinaryTree<Integer>();
			TreeNode<Integer> node2 = new TreeNode<Integer>(2, new TreeNode<Integer>(4, null, null), new TreeNode<Integer>(5, null, new TreeNode<>(8)));
//			TreeNode<Integer> node2 = new TreeNode<Integer>(2, new TreeNode<Integer>(4, null, null), null);
			TreeNode<Integer> node3 = new TreeNode<Integer>(3, new TreeNode<Integer>(6, null, null), new TreeNode<Integer>(7, new TreeNode<>(92), new TreeNode<>(9)));
			tree.root = new TreeNode<Integer>(1, node2, node3);
			return tree;
		}
		
		// 根据中序遍历和先序遍历构建二叉树
		public static <T> BinaryTree<T> buildTree4MidAndPreOrder(T[] midArr, T[] preArr) {
			// TODO
			return null;
		}
		
		// 给定任意遍历序列，尝试构建二叉树，构建失败抛出异常
		public static <T> BinaryTree<T> buildTree4Common(T[] orderArr1, T[] ordeArr2) {
			// TODO
			// 注意：必须满足2个序列中有一个是中序遍历，因此尝试通过3种序列构建，如果都构建失败，则意味不满足构建Tree的序列
			// 1.中序 + 先序
			// 2.中序 + 后序
			// 3.中序 + 层序
			return null;
		}
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> simpleTree = BinaryTree.buildSimpleTree();
//		simpleTree.levelOrder();
//		System.out.println(BinaryTree.buildSimpleTree().findValue(31));
//		System.out.println(simpleTree.getTreeHeiht());
//		System.out.println(simpleTree.findRootPath(8));
//		System.out.println(simpleTree.findLCA(8, 6));
//		System.out.println(simpleTree.getLeafCount());
//		System.out.println(simpleTree.isStrictBinaryTree());
//		System.out.println(simpleTree.isStrictBinaryTree(simpleTree.root));
		System.out.println(simpleTree.isFullBinaryTree());
	}
}
