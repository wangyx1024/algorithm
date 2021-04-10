package linkedlist;

import datastruct.linkedlist.SingleNode;
import util.P;
import util.U;

public class Code01_Reverse {

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 9, 6, 0};
        SingleNode head = U.getSingleNodeList2(arr);
        P.print(head);

        SingleNode head2 = reverse(head);
        P.print(head2);
    }

    private static SingleNode reverse(SingleNode head) {
        SingleNode pre = null;
        while (head != null) {
            SingleNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }
}
