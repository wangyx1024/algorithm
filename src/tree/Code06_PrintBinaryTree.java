package tree;

import datastruct.tree.BinaryTreeNode;
import util.U;

/**
 * 打印一棵树
 */
public class Code06_PrintBinaryTree {

    public static void main(String[] args) {
        BinaryTreeNode root1 = U.getBinaryTree(1, -222222222, 3, Integer.MIN_VALUE, null, 55555555, 66, null, 777, null, null, null, null);
        printTree(root1);
    }

    public static void printTree(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        reverseInOrderTravel(root, 1, "🌲");
    }

    private static void reverseInOrderTravel(BinaryTreeNode node, int lvl, String symbol) {
        if (node == null) {
            printNode(lvl, "⛔️", symbol);
            return;
        } else {
            String nodeContent = node.value + "";
            reverseInOrderTravel(node.right, lvl + 1, "⬇️");
            printNode(lvl, nodeContent, symbol);
            reverseInOrderTravel(node.left, lvl + 1, "⬆️");
        }
    }

    private static void printNode(int nodeLvl, String nodeValue, String symbol) {
        int nodeLen = 20;
        String nodeContent = symbol + nodeValue + symbol;
        int nodeContentLen = nodeContent.length();
        String paddedNodeContent = padding(nodeContent, nodeLen, ' ');
        String tab = getTab((nodeLvl - 1) * nodeLen, ' ');
        String rowToPrint = tab + paddedNodeContent;
        System.out.println(rowToPrint);
    }

    private static String padding(String originalStr, int terminalLen, char paddingChar) {
        int originalStrLen = originalStr.length();
        int paddingLeftLen = (terminalLen - originalStrLen) / 2;
        StringBuilder paddingLeftStr = new StringBuilder();
        while (paddingLeftLen-- > 0) {
            paddingLeftStr.append(paddingChar);
        }

        int paddingRightLen = terminalLen - originalStr.length() - paddingLeftLen;
        StringBuilder paddingRightStr = new StringBuilder();
        while (paddingRightLen-- > 0) {
            paddingRightStr.append(paddingChar);
        }

        return paddingLeftStr + originalStr + paddingRightStr;
    }

    private static String getTab(int len, char tabChar) {
        StringBuilder tabStr = new StringBuilder();
        while (len-- > 0) {
            tabStr.append(tabChar);
        }

        return tabStr.toString();
    }
}
