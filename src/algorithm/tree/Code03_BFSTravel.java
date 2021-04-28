package algorithm.tree;

import algorithm.datastruct.tree.BinaryTreeNode;
import algorithm.util.U;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历
 */
public class Code03_BFSTravel {

    public static void main(String[] args) {
//        BinaryTreeNode node4 = new BinaryTreeNode(4);
//        BinaryTreeNode node5 = new BinaryTreeNode(5);
//        BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);
//
//        BinaryTreeNode node6 = new BinaryTreeNode(6);
//        BinaryTreeNode node7 = new BinaryTreeNode(7);
//        BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);
//
//        BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);
        BinaryTreeNode node1 = U.getBinaryTree(1, 2, 3, 4, 5, 6, 7);

        bfsTravel(node1);
    }


    public static void bfsTravel(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        BinaryTreeNode curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            System.out.print(curr.value + " ");

            if (curr.left != null) {
                queue.add(curr.left);
            }

            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }
}
