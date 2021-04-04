package tree;

import datastruct.BinaryTreeNode;
import util.U;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * 判断是否满二叉树
 */
public class Code15_FirstPublicAncestor {

    public static void main(String[] args) {
//        BinaryTreeNode root = U.getBinaryTree(-53, null, -4);
//        Code06_PrintBinaryTree.printTree(root);
//        Info info = recursiveProcess(root);
//        boolean result2 = easyButSilly(root);
//        System.out.println(info.isComplete);
//        System.out.println(result2);

        check(10000, 10, 100);
    }

    public static void check(int times, int maxTreeLevel, int maxNodeValue) {
        BinaryTreeNode root = null;
        try {
            while (times-- > 0) {
                root = U.generateRandomBinaryTree(maxTreeLevel, maxNodeValue);
                BinaryTreeNode o1 = U.getRandomNode(root);
                BinaryTreeNode o2 = U.getRandomNode(root);
//                Code06_PrintBinaryTree.printTree(root);
//                System.out.println(o1.value);
//                System.out.println(o2.value);

//                Info result1 = recursiveProcess(root, o1, o2);
//                BinaryTreeNode result2 = easyButSilly(root, o1, o2);
//                if (
//                        (result1.intersection == null && result2 != null)
//                                || (result1.intersection != null && result2 == null)
//                ) {
//                    throw new RuntimeException("一个是null，另一个不是");
//                }
////
//                if (result1.intersection != null && result2 != null && result1.intersection != result2) {
//                    throw new RuntimeException("fucking fucked");
//                }
            }

            System.out.println("成功!!!!");
        } catch (Exception e) {
            System.out.println(e.toString());
            Code06_PrintBinaryTree.printTree(root);
        }
    }

    private static class Info {
        public boolean hasO1;
        public boolean hasO2;
        public BinaryTreeNode intersection;

        public Info(boolean hasO1, boolean hasO2, BinaryTreeNode intersection) {
            this.hasO1 = hasO1;
            this.hasO2 = hasO2;
            this.intersection = intersection;
        }
    }

    private static Info recursiveProcess(BinaryTreeNode node, BinaryTreeNode o1, BinaryTreeNode o2) {
        if (node == null) {
            return new Info(false, false, null);
        }

        // 根据X和O1，O2的关系分以下几种情况：
        // 1.O1、O2都在左子树上
        // 2.O1、O2都在右子树上
        // 3.O1在左，O2在右
        // 4.O1在右，O2在左
        // 5.O1是X，O2在左右子树
        // 6.O2是X，O1在左右子树

        // Info需要是否有O1、是否有O2、交汇点
        Info leftInfo = recursiveProcess(node.left, o1, o2);
        Info rightInfo = recursiveProcess(node.right, o1, o2);

        // 在左子树已经相交了
        if (leftInfo.intersection != null) {
            return new Info(true, true, leftInfo.intersection);
        }
        // 在右子树已经相交了
        if (rightInfo.intersection != null) {
            return new Info(true, true, rightInfo.intersection);
        }

        // 在以X为头的树上有O1又有O2
        boolean hasO1 = leftInfo.hasO1 || rightInfo.hasO1 || node == o1;
        boolean hasO2 = leftInfo.hasO2 || rightInfo.hasO2 || node == o2;
        if (hasO1 && hasO2) {
            return new Info(true, true, node);
        }

        return new Info(hasO1, hasO2, null);
    }

    private static BinaryTreeNode easyButSilly(BinaryTreeNode root, BinaryTreeNode o1, BinaryTreeNode o2) {
        if (root == null) {
            return null;
        }

        Map<BinaryTreeNode, BinaryTreeNode> parentMap = new HashMap<>();
        buildParentMap(root, parentMap);

        Set<BinaryTreeNode> o1Ancestors = new HashSet<>();
        o1Ancestors.add(o1);

        BinaryTreeNode o1Ancestor;
        while ((o1Ancestor = parentMap.get(o1)) != null) {
            o1Ancestors.add(o1Ancestor);
            o1 = o1Ancestor;
        }

        BinaryTreeNode o2Ancestor = o2;
        while (o2 != null && !o1Ancestors.contains(o2)) {
            o2 = o2Ancestor;
        }

        return o2;
    }

    private static void buildParentMap(BinaryTreeNode node, Map<BinaryTreeNode, BinaryTreeNode> parentMap) {
        if (node == null) {
            return;
        }

        buildParentMap(node.left, parentMap);
        buildParentMap(node.right, parentMap);
        if (node.left != null) {
            parentMap.put(node.left, node);
        }

        if (node.right != null) {
            parentMap.put(node.right, node);
        }
    }

}
