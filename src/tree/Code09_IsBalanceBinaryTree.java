package tree;

import datastruct.tree.BinaryTreeNode;
import util.U;

/**
 * 判断是否平衡二叉树
 */
public class Code09_IsBalanceBinaryTree {

    public static void main(String[] args) {
//        BinaryTreeNode root = U.getBinaryTree(1, 2, 3, null, null, 4, null, 5);
        BinaryTreeNode root = U.getBinaryTree(1, 2, 3, 4, 5, 6, null, 7);
        Code06_PrintBinaryTree.printTree(root);
        boolean balanced = isBalanced(root);
        System.out.println("balanced = " + balanced);
    }

    private static class Info {
        public int height;
        public boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    private static boolean isBalanced(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        return process(root).isBalanced;
    }

    private static Info process(BinaryTreeNode node) {
        if (node == null) {
            return new Info(0, true);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && (Math.abs(leftInfo.height - rightInfo.height) <= 1);
        return new Info(height, isBalanced);
    }
}
