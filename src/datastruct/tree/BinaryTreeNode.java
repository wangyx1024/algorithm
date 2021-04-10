package datastruct.tree;

public class BinaryTreeNode {

    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public BinaryTreeNode(int value, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(int value, int lvalue, int rvalue) {
        this.value = value;
        this.left = new BinaryTreeNode(lvalue);
        this.right = new BinaryTreeNode(rvalue);

    }
}
