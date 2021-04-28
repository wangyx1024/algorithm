package algorithm.tree;

import algorithm.datastruct.tree.BinaryTreeNode;
import algorithm.util.U;

/**
 * 判断是否平衡二叉树
 */
public class Code10_BinaryTreeMaxDistance {

    public static void main(String[] args) {
        BinaryTreeNode root = U.getBinaryTree(
                1,
                2, 3,
                4, 5, 6, null,
                7, 8, null, null, 9);
//        BinaryTreeNode root = U.getBinaryTree(
//                1,
//                null, 3);
        Code06_PrintBinaryTree.printTree(root);

        int maxDistance = getMaxDistance(root);
        System.out.println("maxDistance = " + maxDistance);
    }

    private static class Info {
        private int max;
        private int depth;

        public Info(int max, int depth) {
            this.max = max;
            this.depth = depth;
        }
    }

    private static int getMaxDistance(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        return process(root).max;
    }

    private static Info process(BinaryTreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }

        // 收集信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        // 1.最大距离不包括X：左右子树中深的那个
        int max1 = Math.max(leftInfo.depth, rightInfo.depth);

        // 2.最大距离包括X：左深度+右深度+1
        int max2 = leftInfo.depth + rightInfo.depth + 1;

        // 1、2取max
        int max = Math.max(max1, max2);

        return new Info(max, max1 + 1);
    }
}
