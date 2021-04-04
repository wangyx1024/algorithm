package tree;

import datastruct.BinaryTreeNode;
import util.U;

/**
 * 打印一棵树
 */
public class Code06_PrintBinaryTree {

    public static void main(String[] args) {
        BinaryTreeNode root1 = U.getBinaryTree(1, -222222222, 3, Integer.MIN_VALUE, null, 55555555, 66, null, 777, null, null, null, null);
        printTree(root1);
    }

    private static void printTree(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        reverseInOrderTravel(root, 1, "🌲");
    }

    private static void reverseInOrderTravel(BinaryTreeNode node, int lvl, String symbol) {
        if (node == null) {
            return;
        }

        reverseInOrderTravel(node.right, lvl + 1, "⬇️");
        printNode(node, symbol, lvl);
        reverseInOrderTravel(node.left, lvl + 1, "⬆️");
    }

    private static void printNode(BinaryTreeNode node, String symbol, int lvl) {
        if (node == null) {
            return;
        }

        String nodeValue = symbol + node.value + symbol;
        int nodeValueLength = nodeValue.length();
        int nodeValueMargin = (int) Math.ceil((20 - nodeValueLength) / 2D);
        String nodeValue2 = padding(nodeValue, nodeValueMargin, 20, ' ');
        String tab = getTab((lvl - 1) * 20, ' ');
        String str = tab + nodeValue2;
        System.out.println(str);
    }

    private static String padding(String str, int count, int totalLength, char c) {
        StringBuilder paddingLeftStr = new StringBuilder();
        while (count-- > 0) {
            paddingLeftStr.append(c);
        }

        count = totalLength - str.length() - count;
        StringBuilder paddingRightStr = new StringBuilder();
        while (count-- > 0) {
            paddingRightStr.append(c);
        }

        return paddingLeftStr + str + paddingRightStr;
    }

    private static String getTab(int count, char c) {
        StringBuilder tabStr = new StringBuilder();
        while (count-- > 0) {
            tabStr.append(c);
        }

        return tabStr.toString();
    }
}
