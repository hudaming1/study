package org.hum.study.datastruct.tree;

public class AVLTreeUtils {

    /**
     * AVL树：
     * 	高度平衡树，每次增减节点后，根据高度来判断是否平衡，在失衡时通过自转（LL、RR、LR和RL）来重新达到平衡。
     * <pre>
     *	插入流程：
     *	 1.判断新节点插入位置：如果new_value小于current_node，则插入左子节点；反之插入到右子节点
     *	 2.递归查找到叶子节点，并插入新的节点
     *	 3.判断整棵Tree是否平衡，如不平衡则开始递归进行旋转
     *	 4.递归计算节点的高度
     * </pre>
     * <pre>
     * 	单侧旋转：
     * 	  RR（新插入节点val-12）		
     *      		100						 50
     *	    	   /   \						/  \	
     *		  50   140				   30  100
     *		 /  \		 ----RR--->	  /    /  \
     *      30   70					 12   70  140
     *     /									
     *    12									
     *   LL（新插入节点val-180）
     *   		100						 140					
     *   	   /   \						/   \
     *   	  50	   140				   100  160
     *  	     	   /  \	  ----LL--->	  /   \     \
     *  	   		 120  160			 50   120   180
     *  					 \
     *  		 			 180
     *  双侧旋转：
     *  	  LR（新插入节点val-80）		
     *       	100						 100						 70
     *	    	   /   \						/   \					/  \
     *		  50   140				   70   140				   50  100			
     *		 /  \		 ----LL--->	  /  \ 		 ----RR--->	  /    /  \ 
     *      30   70					 50   80					 30   80  140
     *     		   \					/						
     *     			80			   30					   
     *    RL（新插入节点val-110）
     *    		100						 100						 120 
     *    	   /   \						/   \					/   \
     *    	  50	   140				   50   120				   100   140
     *    		   /  \	  ----RR--->	  		/  \  ----LL--->	  /   \    \
     *    		 120	  160			 	  110  140			 50   110  160
     *    		 /						  		  \	
     *    	   110			   	   				  160
     * </pre>
     * <pre>
     * 	关于旋转：
     * 	  LL：右升根，根降左(var newRoot = node.right; node.right = newRoot.left; newRoot.left = node;)
     * 	  RR：左升根，根降右(var newRoot = node.left; node.left = newRoot.right; newRoot.right = node;)
     * 	  LR：右旋左子，左选自己(node.left = RR(node.left); node = LL(node);)
     * 	  RL：左选右子，右旋自己(node.right = LL(node.right); node = RR(node);)
     * </pre>
     */
	static class AVLTree<T> {
		public TreeNode<T> root;
		
		public void add(T data) {
			
		}
		
		public void delete(T data) {
			
		}
		
		public TreeNode<T> find(T data) {
			return null;
		}
		
		public void print() {
			
		}
	}
}
