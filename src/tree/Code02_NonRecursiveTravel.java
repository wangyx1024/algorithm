package tree;

import datastruct.BinaryTreeNode;
import util.P;
import util.U;

import java.util.Stack;

/**
 * 二叉树非递归遍历
 */
public class Code02_NonRecursiveTravel {

    public static void main(String[] args) {
        /**
         *    1
         *  2   3
         * 4 5 6 7
         */
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

        preOrderTravel(node1);
        P.divider();
        inOrderTravel(node1);
        P.divider();
        postOrderTravel(node1);
    }

    /**
     * 前序：头左右
     * push(root)
     * while (stack.isNotEmpty()) {
     * node = stack.pop();
     * print(node);
     * push(node.right);
     * push(node.left);
     * }
     */
    private static void preOrderTravel(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);

        System.out.println("preOrderTravel:");

        BinaryTreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            System.out.print(curr.value + " ");
            if (curr.right != null) {
                stack.push(curr.right);
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    /**
     * 中序：左头右
     * while (curr!=null||stack.isNotEmpty()) {
     * if (curr!=null) {
     * stack.push(curr);
     * curr=curr.left;
     * } else {
     * curr = stack.pop();
     * print(stack);
     * curr = curr.right;
     * }
     * }
     */
    private static void inOrderTravel(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println("inOrderTravel:");

        BinaryTreeNode curr = root;
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                System.out.print(curr.value + " ");
                curr = curr.right;
            }
        }
    }

    /**
     * 后序：左右头
     * 逆序后是：头右左，和前序区别不大，只要交换前序中入栈顺序即可
     */
    private static void postOrderTravel(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> outputStack = new Stack<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);

        System.out.println("postOrderTravel:");

        BinaryTreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();

            // 压栈代替直接输出
            outputStack.push(curr);

            // 和先序交换入栈顺序
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }

        while (!outputStack.isEmpty()) {
            System.out.print(outputStack.pop().value + " ");
        }
    }
}
