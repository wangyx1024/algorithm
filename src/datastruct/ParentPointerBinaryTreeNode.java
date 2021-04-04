package datastruct;

public class ParentPointerBinaryTreeNode {

    public int value;
    public ParentPointerBinaryTreeNode left;
    public ParentPointerBinaryTreeNode right;
    public ParentPointerBinaryTreeNode parent;

    public ParentPointerBinaryTreeNode() {
    }

    public ParentPointerBinaryTreeNode(int value) {
        this.value = value;
    }

    public ParentPointerBinaryTreeNode(int value, ParentPointerBinaryTreeNode left, ParentPointerBinaryTreeNode right, ParentPointerBinaryTreeNode parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
