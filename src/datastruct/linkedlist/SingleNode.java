package datastruct.linkedlist;

public class SingleNode {

    public int value;
    public SingleNode next;

    public SingleNode() {
    }

    public SingleNode(int value) {
        this.value = value;
    }

    public void add(int value) {
        if (this.next == null) {
            this.next = new SingleNode(value);
        } else {
            this.next.add(value);
        }
    }
}
