package datastruct;

public class Node {

    public int value;
    public Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public void add(int value) {
        if (this.next == null) {
            this.next = new Node(value);
        } else {
            this.next.add(value);
        }
    }
}
