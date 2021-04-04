package tree;

import datastruct.BinaryTreeNode;
import util.U;

/**
 * 判断是否满二叉树
 */
public class Code13_IsFullBinaryTree {

    public static void main(String[] args) {
//        BinaryTreeNode root = U.getBinaryTree(
//                1,
//                2, 3,
//                4, 5, 6, 7);
//        Code06_PrintBinaryTree.printTree(root);
//        Info info = process(root);
//        System.out.println(info.isFull);

        check(10000000, 10, 100);
    }

    public static void check(int times, int maxTreeLevel, int maxNodeValue) {
        BinaryTreeNode root = null;
        try {
            while (times-- > 0) {
                root = U.generateRandomBinaryTree(maxTreeLevel, maxNodeValue);
//                Code06_PrintBinaryTree.printTree(root);
                Info info = process(root);
                Info2 info2 = process2(root);

                if ((info.isFull ? 1 : 0) + (info2.getIsFull() ? 1 : 0) == 1) {
                    throw new RuntimeException("fucking fucked!!!!!");
                }
            }

            System.out.println("成功!!!!");
        } catch (Exception e) {
            System.out.println(e.toString());
            Code06_PrintBinaryTree.printTree(root);
        }
    }

    private static class Info {
        public int depth;
        public boolean isFull;

        public Info(int depth, boolean isFull) {
            this.depth = depth;
            this.isFull = isFull;
        }
    }

    private static Info process(BinaryTreeNode node) {
        if (node == null) {
            return new Info(0, true);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int depth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.depth == rightInfo.depth;

        return new Info(depth, isFull);
    }

    private static class Info2 {
        public int depth;
        public int nodes;

        public Info2(int depth, int nodes) {
            this.depth = depth;
            this.nodes = nodes;
        }

        public boolean getIsFull() {
            return this.nodes == (1 << this.depth) - 1;
        }
    }

    private static Info2 process2(BinaryTreeNode node) {
        if (node == null) {
            return new Info2(0, 0);
        }

        Info2 leftInto = process2(node.left);
        Info2 rightInto = process2(node.right);

        int depth = Math.max(leftInto.depth, rightInto.depth) + 1;
        int nodes = leftInto.nodes + rightInto.nodes + 1;

        return new Info2(depth, nodes);
    }
}
