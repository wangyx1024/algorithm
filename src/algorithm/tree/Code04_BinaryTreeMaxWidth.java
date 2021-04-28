package algorithm.tree;

import algorithm.datastruct.tree.BinaryTreeNode;
import algorithm.util.U;

import java.util.*;

/**
 * 二叉树最大宽度
 */
public class Code04_BinaryTreeMaxWidth {

    public static void main(String[] args) {
//        BinaryTreeNode node4 = new BinaryTreeNode(4);
//        BinaryTreeNode node5 = new BinaryTreeNode(5);
//        BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);
//
//        BinaryTreeNode node6 = new BinaryTreeNode(6);
//        BinaryTreeNode node7 = new BinaryTreeNode(7);
//        BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);

//        BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);
        BinaryTreeNode node1 = U.getBinaryTree(1, 2, 3, 4, 5, 6, 7);

        int maxWidth1 = getMaxWidth1(node1);
        int maxWidth2 = getMaxWidth2(node1);
        System.out.println("maxWidth1 = " + maxWidth1);
        System.out.println("maxWidth2 = " + maxWidth2);
    }

    /**
     * 用map
     */
    private static int getMaxWidth1(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        int currLvl = 1;
        int maxLvlNodes = 0;
        int currLvlNodes = 0;
        BinaryTreeNode curr;
        Map<BinaryTreeNode, Integer> nodeLvlMap = new HashMap<>();
        nodeLvlMap.put(root, 1);

        while (!queue.isEmpty()) {
            curr = queue.poll();

            if (curr.left != null) {
                queue.add(curr.left);
                nodeLvlMap.put(curr.left, currLvl + 1);
            }

            if (curr.right != null) {
                queue.add(curr.right);
                nodeLvlMap.put(curr.right, currLvl + 1);
            }

            int nodeLvl = nodeLvlMap.get(curr);
            if (nodeLvl == currLvl) {
                currLvlNodes++;
            } else {
                maxLvlNodes = Math.max(currLvlNodes, maxLvlNodes);
                currLvl++;
                currLvlNodes = 1;
            }
        }

        return Math.max(currLvlNodes, maxLvlNodes);
    }

    /**
     * 不用map
     */
    private static int getMaxWidth2(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        int maxLvlNodes = 0;
        int currLvlNodes = 0;
        BinaryTreeNode curr;
        BinaryTreeNode currLvlEnd = root;
        BinaryTreeNode nextLvlEnd = null;

        while (!queue.isEmpty()) {
            curr = queue.poll();

            if (curr.left != null) {
                queue.add(curr.left);
                nextLvlEnd = curr.left;
            }

            if (curr.right != null) {
                queue.add(curr.right);
                nextLvlEnd = curr.right;
            }

            currLvlNodes++;
            if (curr == currLvlEnd) {
                maxLvlNodes = Math.max(currLvlNodes, maxLvlNodes);
                currLvlNodes = 0;
                currLvlEnd = nextLvlEnd;
            }
        }

        return maxLvlNodes;
    }
}
