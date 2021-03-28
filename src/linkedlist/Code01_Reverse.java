package linkedlist;

import datastruct.Node;
import util.L;
import util.P;

public class Code01_Reverse {

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 9, 6, 0};
        Node head = L.getSingleNodeList(arr);
        P.print(head);

        Node head2 = reverse(head);
        P.print(head2);
    }

    private static Node reverse(Node head) {
        Node pre = null;
        while (head != null) {
            Node next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }
}
