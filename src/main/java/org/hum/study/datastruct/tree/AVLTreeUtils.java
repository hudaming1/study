package org.hum.study.datastruct.tree;

public class AVLTreeUtils {

    /**
     * <pre>
     * 	单侧旋转：
     * 	  LL（新插入节点val-12）		RR（新插入节点val-180）
     *      		100							100
     *	    	   /   \						   /   \
     *		  50   140					  50	   140
     *		 /  \						 	   /	  \
     *      30   70							 120	  160
     *     /											 \
     *    12 										 180
     *  双侧旋转：
     *  	  LR（新插入节点val-80）		RL（新插入节点val-110）
     *       	100							100
     *	    	   /   \						   /   \
     *		  50   140					  50	   140
     *		 /  \						 	   /	  \
     *      30   70							 120	  160
     *     		   \							 /		 
     *     			80					   110		 
     *   		
     * </pre>
     */
	static class AVLTree<T> {
		public TreeNode<T> root;
		
		public void add(T data) {
			
		}
		
		public void delete(T data) {
			
		}
		
		public void print() {
			
		}
	}
}
