package algorithm.tree;

import algorithm.datastruct.tree.ParentPointerBinaryTreeNode;

/**
 * 前驱和后继节点
 */
public class Code07_PrecursorAndSuccessorNode {

    public static void main(String[] args) {
        ParentPointerBinaryTreeNode node8 = new ParentPointerBinaryTreeNode(8, null, null, null);
        ParentPointerBinaryTreeNode node5 = new ParentPointerBinaryTreeNode(5, null, node8, null);
        ParentPointerBinaryTreeNode node4 = new ParentPointerBinaryTreeNode(4, null, null, null);
        ParentPointerBinaryTreeNode node2 = new ParentPointerBinaryTreeNode(2, node4, node5, null);

        ParentPointerBinaryTreeNode node6 = new ParentPointerBinaryTreeNode(6, null, null, null);
        ParentPointerBinaryTreeNode node7 = new ParentPointerBinaryTreeNode(7, null, null, null);
        ParentPointerBinaryTreeNode node3 = new ParentPointerBinaryTreeNode(3, node6, node7, null);

        ParentPointerBinaryTreeNode node1 = new ParentPointerBinaryTreeNode(1, node2, node3, null);

        node8.parent = node5;
        node4.parent = node2;
        node5.parent = node2;
        node2.parent = node1;
        node6.parent = node3;
        node7.parent = node3;
        node3.parent = node1;

        ParentPointerBinaryTreeNode node = node8;

        ParentPointerBinaryTreeNode precursorNode = findPrecursorNode(node);
        if (precursorNode == null) {
            System.out.println("没有前驱节点");
        } else {
            System.out.println("前驱节点值：" + precursorNode.value);
        }

        ParentPointerBinaryTreeNode successorNode = findSuccessorNode(node);
        if (successorNode == null) {
            System.out.println("没有后继节点");
        } else {
            System.out.println("后继节点值：" + successorNode.value);
        }
    }

    public static ParentPointerBinaryTreeNode findSuccessorNode(ParentPointerBinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }

            return node;
        } else {
            ParentPointerBinaryTreeNode parent = node.parent;
            while (parent != null && node != parent.left) {
                node = parent;
                parent = parent.parent;
            }

            return parent;
        }
    }


    public static ParentPointerBinaryTreeNode findPrecursorNode(ParentPointerBinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            node = node.left;
            while (node.right != null) {
                node = node.right;
            }

            return node;
        } else {
            ParentPointerBinaryTreeNode parent = node.parent;
            while (parent != null && node != parent.right) {
                node = parent;
                parent = parent.parent;
            }

            return parent;
        }
    }
}
