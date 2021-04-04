package tree;

import datastruct.BinaryTreeNode;
import util.U;

/**
 * 获取最大搜索二叉子树的信息
 */
public class Code11_HeadValueOfMaxSearchBinaryTree {

    public static void main(String[] args) {
        BinaryTreeNode root = U.getBinaryTree(
                128,
                64, 256,
                32, 96, 192, 512);
        Code06_PrintBinaryTree.printTree(root);
        Info info = process(root);
        if (info == null) {
            System.out.println("null");
        } else {
            System.out.println(info.headValue);
        }
    }

    private static class Info {
        public boolean isAllBst;
        public int maxSubBstSize;
        public int minValue;
        public int maxValue;
        public int headValue;

        public Info(boolean isAllBst, int maxSubBstSize, int minValue, int maxValue, int headValue) {
            this.isAllBst = isAllBst;
            this.maxSubBstSize = maxSubBstSize;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.headValue = headValue;
        }
    }

    private static Info process(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        // 搜索树：对于X，左子树的max<X.value，右子树的min>X.value
        // 1.不包含X：不需要条件
        // 2.包含X：左右树都是bst，且左树最大值小于X，且右树最小值大于X

        int minValue = node.value;
        int maxValue = node.value;
        int headValue = node.value;
        if (leftInfo != null) {
            minValue = Math.min(minValue, leftInfo.minValue);
            maxValue = Math.min(maxValue, leftInfo.maxValue);
        }
        if (rightInfo != null) {
            minValue = Math.min(minValue, rightInfo.minValue);
            maxValue = Math.min(maxValue, rightInfo.maxValue);
        }

        int maxSubBstSize = 0;
        if (leftInfo != null) {
            maxSubBstSize = Math.max(maxSubBstSize, leftInfo.maxSubBstSize);
        }
        if (rightInfo != null) {
            maxSubBstSize = Math.max(maxSubBstSize, rightInfo.maxSubBstSize);
        }

        boolean isAllBst = false;
        if ((leftInfo == null || leftInfo.isAllBst)
                && (rightInfo == null || rightInfo.isAllBst)
                && (leftInfo == null || leftInfo.maxValue < node.value)
                && (rightInfo == null || rightInfo.minValue > node.value)
        ) {
            maxSubBstSize = 1
                    + (leftInfo == null ? 0 : leftInfo.maxSubBstSize)
                    + (rightInfo == null ? 0 : rightInfo.maxSubBstSize);
            isAllBst = true;
        }

        return new Info(isAllBst, maxSubBstSize, minValue, maxValue, headValue);
    }
}
