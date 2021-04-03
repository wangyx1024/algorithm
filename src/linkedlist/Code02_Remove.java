package linkedlist;

import datastruct.SingleNode;
import util.P;
import util.U;

public class Code02_Remove {

    public static void main(String[] args) {
        int[] arr = {3, 3, 2, 8, 6, 3, 3, 5, 1, 9, 6, 0, 3, 3};
        SingleNode head = U.getSingleNodeList2(arr);
        P.print(head);

        SingleNode head2 = remove(head, 3);
        P.print(head2);
    }

    private static SingleNode remove(SingleNode head, int targetData) {
        // 防止前n个节点连续是目标值，遇到相同就跳过
        while (head != null) {
            if (head.value == targetData) {
                // head向后移动，不用担心内存回收，因为head向后移动后，之前的节点没有人指向会被jvm自动gc掉
                head = head.next;
            } else {
                break;
            }
        }

        // 经过上面的步骤后，head指向第一个非目标值的节点
        // 从该节点开始，继续向后遍历，找到目标值节点A后，记住该节点的前一个节点pre
        // 从A后继续连续查找目标值节点（防止中间连续出现目标值节点），如果找到就跳过，直到非目标值节点B为止
        // 把pre指向B，这样就把中间的目标值节点全部跳过了，等待被jvm gc掉
        SingleNode pre = head;
        SingleNode curr = head;
        while (curr != null) {
            if (curr.value != targetData) {
                pre = curr;
                curr = curr.next;
            } else {
                while (curr != null) {
                    if (curr.value == targetData) {
                        curr = curr.next;
                    } else {
                        break;
                    }
                }

                pre.next = curr;
            }
        }

        return head;
    }
}
