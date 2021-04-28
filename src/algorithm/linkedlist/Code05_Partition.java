package algorithm.linkedlist;

import algorithm.datastruct.linkedlist.SingleNode;
import algorithm.util.Checker;
import algorithm.util.P;
import algorithm.util.U;

/**
 * 2021-03-29
 * 单链表partition
 */
public class Code05_Partition {

    public static void main(String[] args) {
//        int[] arr = {5, 5, 2, 2, 8, 8};
//        SingleNode head = L.getSingleNodeList(arr);
//        P.print(head);
//
//        SingleNode head2 = arrPartition(head, 3);
//        P.print(head2);
        int[] arr = Checker.generate(10, 10);
        int partitionValue = Checker.generateRandomNumNoMoreThan(10);
        SingleNode head = U.getSingleNodeList2(arr);
        P.print(head);
        System.out.println(partitionValue);

        SingleNode head2 = partition(head, partitionValue);
        P.print(head2);

//        SingleNode head2 = easyButSilly(head, partitionValue);
//        P.print(head2);
    }

    /**
     * 1.分别定义小于区、等于区、大于区的头尾指针
     * 2.遍历链表，根据节点值判断扔到哪个区，扔过去时处理该区头尾指针
     * 3.把三个区连起来
     */
    private static SingleNode partition(SingleNode head, int partitionValue) {
        if (head == null) {
            return null;
        }

        SingleNode sH = null;
        SingleNode sT = null;
        SingleNode eH = null;
        SingleNode eT = null;
        SingleNode bH = null;
        SingleNode bT = null;

        SingleNode headCurr = head;
        while (headCurr != null) {
            SingleNode headNext = headCurr.next;
            int headCurrValue = headCurr.value;
            headCurr.next = null;
            if (headCurrValue < partitionValue) {
                if (sH == null) {
                    sH = headCurr;
                    sT = headCurr;
                } else {
                    sT.next = headCurr;
                    sT = headCurr;
                }
            } else if (headCurrValue > partitionValue) {
                if (bH == null) {
                    bH = headCurr;
                    bT = headCurr;
                } else {
                    bT.next = headCurr;
                    bT = headCurr;
                }
            } else {
                if (eH == null) {
                    eH = headCurr;
                    eT = headCurr;
                } else {
                    eT.next = headCurr;
                    eT = headCurr;
                }
            }

            headCurr = headNext;
        }

        // 0xx
        if (sH == null) {
            // 00x
            if (eH == null) {
                // 000
                if (bH == null) {
                    return null;
                }
                // 001
                else {
                    return bH;
                }
            }
            // 01x
            else {
                // 010
                if (bH == null) {
                    return eH;
                }
                // 011
                else {
                    eT.next = bH;
                    return eH;
                }
            }
        }
        // 1xx
        else {
            // 10x
            if (eH == null) {
                // 100
                if (bH == null) {
                    return sH;
                }
                // 101
                else {
                    sT.next = bH;
                    return sH;
                }
            }
            // 11x
            else {
                // 110
                if (bH == null) {
                    sT.next = eH;
                    return sH;
                }
                // 111
                else {
                    sT.next = eH;
                    eT.next = bH;
                    return sH;
                }
            }
        }
    }

    /**
     * 放到链表里partition完了再拼起来
     * 时间复杂度O(n)，额外空间复杂度O(n)
     */
    private static SingleNode easyButSilly(SingleNode head, int partitionValue) {
        // 1.获取长度，创建数组
        int len = 0;
        SingleNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        SingleNode[] arr = new SingleNode[len];

        // 2.把链表Node扔到数组里
        int i = 0;
        curr = head;
        while (curr != null) {
            arr[i++] = curr;
            SingleNode next = curr.next;
            curr.next = null;
            curr = next;
        }

        // 3.对数组做partition
        arrPartition(arr, partitionValue);

        // 4.把数组拼成链表并返回head
        return U.getSingleNodeList(arr);
    }

    private static void arrPartition(SingleNode[] arr, int partitionValue) {
        int i = 0;
        int less = -1;
        int more = arr.length;

        while (i < more) {
            if (arr[i].value < partitionValue) {
                U.swap(arr, i++, ++less);
            } else if (arr[i].value > partitionValue) {
                U.swap(arr, i, --more);
            } else {
                i++;
            }
        }
    }
}
