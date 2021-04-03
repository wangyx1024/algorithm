package tree;

import datastruct.BinaryTreeNode;
import util.P;

/**
 * 二叉树的递归遍历
 */
public class Code01_RecursiveTravel {

    public static void main(String[] args) {
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);

        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node7 = new BinaryTreeNode(7);
        BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);

        BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);

        preOrderTravel(node1);
        P.divider();
        inOrderTravel(node1);
        P.divider();
        postOrderTravel(node1);
    }

    public static void preOrderTravel(BinaryTreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");
        preOrderTravel(node.left);
        preOrderTravel(node.right);
    }

    private static void inOrderTravel(BinaryTreeNode node) {
        if (node == null) {
            return;
        }

        inOrderTravel(node.left);
        System.out.print(node.value + " ");
        inOrderTravel(node.right);
    }

    private static void postOrderTravel(BinaryTreeNode node) {
        if (node == null) {
            return;
        }

        postOrderTravel(node.left);
        postOrderTravel(node.right);
        System.out.print(node.value + " ");
    }
}
