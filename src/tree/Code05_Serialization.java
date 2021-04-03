package tree;

import datastruct.BinaryTreeNode;
import util.P;
import util.U;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大宽度（非二叉树呢）
 * todo 最大宽度 & 最大宽度所在层？
 */
public class Code05_Serialization {

    public static void main(String[] args) {
        // todo 根据输入数据构造一颗完全二叉树
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);

        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node7 = new BinaryTreeNode(7);
        BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);

        BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);

        Queue<Integer> queue1 = preOrderSerialize(node1);
        System.out.println(queue1);
        BinaryTreeNode root1 = preOrderDeserialize(queue1);
        Code01_RecursiveTravel.preOrderTravel(root1);

        P.divider();

        Queue<Integer> queue2 = bfsSerialize(node1);
        System.out.println(queue2);
        BinaryTreeNode root2 = preOrderDeserialize(queue2);
        Code01_RecursiveTravel.preOrderTravel(root2);

        P.divider();

        Queue<Integer> queue3 = U.getQueue(1, 2, 3, null, 4, 5, null, null, null, null, null);
        BinaryTreeNode root3 = bfsDeserialize(queue3);
        Code03_BFSTravel.bfsTravel(root3);
    }

    private static Queue<Integer> preOrderSerialize(BinaryTreeNode root) {
        Queue<Integer> queue = new LinkedList<>();

        preOrderSerializeProcess(root, queue);

        return queue;
    }

    private static void preOrderSerializeProcess(BinaryTreeNode node, Queue<Integer> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            queue.add(node.value);
            preOrderSerializeProcess(node.left, queue);
            preOrderSerializeProcess(node.right, queue);
        }
    }

    private static BinaryTreeNode preOrderDeserialize(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }

        return preOrderDeserializeProcess(queue);
    }

    private static BinaryTreeNode preOrderDeserializeProcess(Queue<Integer> queue) {
        Integer value = queue.poll();
        if (value == null) {
            return null;
        }

        BinaryTreeNode node = new BinaryTreeNode(value);
        node.left = preOrderDeserializeProcess(queue);
        node.right = preOrderDeserializeProcess(queue);

        return node;
    }

    private static Queue<Integer> bfsSerialize(BinaryTreeNode root) {
        Queue<Integer> result = new LinkedList<>();
        if (root == null) {
            result.add(null);
        } else {
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.add(root);
            result.add(root.value);

            BinaryTreeNode curr;
            BinaryTreeNode lchild;
            BinaryTreeNode rchild;
            while (!queue.isEmpty()) {
                curr = queue.poll();

                lchild = curr.left;
                if (lchild != null) {
                    queue.add(lchild);
                    result.add(lchild.value);
                } else {
                    result.add(null);
                }

                rchild = curr.right;
                if (rchild != null) {
                    queue.add(rchild);
                    result.add(rchild.value);
                } else {
                    result.add(null);
                }
            }
        }

        return result;
    }

    public static BinaryTreeNode bfsDeserialize(Queue<Integer> serializeResult) {
        if (serializeResult == null || serializeResult.isEmpty()) {
            return null;
        }

        Integer rootValue = serializeResult.poll();
        if (rootValue == null) {
            return null;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode head = new BinaryTreeNode(rootValue);
        queue.add(head);

        BinaryTreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = buildByValue(serializeResult.poll());
            node.right = buildByValue(serializeResult.poll());

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return head;
    }

    private static BinaryTreeNode buildByValue(Integer value) {
        return value == null ? null : new BinaryTreeNode(value);
    }
}
