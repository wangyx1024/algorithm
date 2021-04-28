package algorithm.tree;

import algorithm.datastruct.tree.BinaryTreeNode;
import algorithm.util.U;

/**
 * 获取最大搜索二叉子树的信息
 */
public class Code12_HeadOfMaxSearchBinaryTree {

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

        }
    }

    private static class Info {

    }

    private static Info process(BinaryTreeNode node) {
        return null;
    }
}
