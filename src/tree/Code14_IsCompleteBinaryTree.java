package tree;

import datastruct.BinaryTreeNode;
import util.U;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 判断是否满二叉树
 */
public class Code14_IsCompleteBinaryTree {

    public static void main(String[] args) {
        //        BinaryTreeNode root = U.getBinaryTree(-53, null, -4);
//        Code06_PrintBinaryTree.printTree(root);
//        Info info = recursiveProcess(root);
//        boolean result2 = easyButSilly(root);
//        System.out.println(info.isComplete);
//        System.out.println(result2);

                check(100000, 100, 100);
    }

    public static void check(int times, int maxTreeLevel, int maxNodeValue) {
        BinaryTreeNode root = null;
        try {
            while (times-- > 0) {
                root = U.generateRandomBinaryTree(maxTreeLevel, maxNodeValue);
//                Code06_PrintBinaryTree.printTree(root);
                Info info = recursiveProcess(root);
                boolean result2 = easyButSilly(root);
                if ((info.isComplete ? 1 : 0) + (result2 ? 1 : 0) == 1) {
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
        public boolean isComplete;

        public Info(int depth, boolean isFull, boolean isComplete) {
            this.depth = depth;
            this.isFull = isFull;
            this.isComplete = isComplete;
        }
    }

    private static Info recursiveProcess(BinaryTreeNode node) {
        if (node == null) {
            return new Info(0, true, true);
        }

        // 完全二叉树分类
        // 1.不过左树：左树完全，右树满，且左树比右树高度大1
        // 2.过了左树：左树满，右树完全，且左树高度=右树

        // 需要信息
        // 高度，是否满，是否完全
        Info leftInfo = recursiveProcess(node.left);
        Info rightInfo = recursiveProcess(node.right);

        int depth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.depth == rightInfo.depth;
        boolean isComplete =
                (leftInfo.isComplete && rightInfo.isFull && leftInfo.depth == rightInfo.depth + 1)
                        || (leftInfo.isFull && rightInfo.isComplete && leftInfo.depth == rightInfo.depth);
        return new Info(depth, isFull, isComplete);
    }

    private static boolean easyButSilly(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        // bfs时，如果一个节点，有右无左，非完全，return false
        // 或者如果出现了一个非双全节点之后，又出现了非叶节点，return false

        boolean everNotFullChild = false;

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        BinaryTreeNode curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();

            if (curr.left == null && curr.right != null) {
                return false;
            }

            if (everNotFullChild && (curr.left != null || curr.right != null)) {
                return false;
            }

            if (!everNotFullChild && (curr.left == null || curr.right == null)) {
                everNotFullChild = true;
            }

            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }

        return true;
    }
}
