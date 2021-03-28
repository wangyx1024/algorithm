package linkedlist;

import datastruct.Node;
import util.L;
import util.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 2021-03-28
 * 判断是否回文
 */
public class Code04_Palindrome {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 1};
        Node head = L.getSingleNodeList(arr);
        P.print(head);
        boolean result1 = easyButSilly1(head);
        boolean result2 = easyButSilly2(head);
        boolean result3 = isPalindrome(head);
        boolean result4 = isPalindrome2(head);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        P.print(head);
    }

    public static void check(int times, int question) {
//        int len = 0;
//        Node head1 = null;
//        Node head2 = null;
//
//        try {
//            while (times-- >= 0) {
//                len = times;
//                head1 = L.getSingleNodeList(len);
//                head2 = L.getSingleNodeList(len);
//                Node mid1;
//                Node mid2;
//                if (question == 1) {
//                    mid1 = getMid1(head1);
//                    mid2 = easyButSilly1(head2);
//                } else if (question == 2) {
//                    mid1 = getMid2(head1);
//                    mid2 = easyButSilly2(head2);
//                } else if (question == 3) {
//                    mid1 = getMid3(head1);
//                    mid2 = easyButSilly3(head2);
//                } else if (question == 4) {
//                    mid1 = getMid4(head1);
//                    mid2 = easyButSilly4(head2);
//                } else {
//                    mid1 = null;
//                    mid2 = null;
//                }
//
//                if (mid1 == null) {
//                    if (mid2 == null) {
//                        System.out.println("len==" + len + ";mid==null");
//                    } else {
//                        throw new RuntimeException("mid1 == null && mid2 != null");
//                    }
//                } else {
//                    if (mid2 == null) {
//                        throw new RuntimeException("mid1 != null && mid2 == null");
//                    } else {
//                        if (mid1.value != mid2.value) {
//                            throw new RuntimeException("mid1 != null && mid2 == null");
//                        } else {
//                            System.out.println("len==" + len + ";mid==" + mid1.value);
//                        }
//                    }
//                }
//            }
//
//            System.out.println("成功！！！");
//        } catch (Exception e) {
//            System.out.println("报错！！！" + e.getMessage());
//            e.printStackTrace();
//            System.out.println(len);
//            P.print(head1);
//            P.print(head2);
//        }
    }

    /**
     * 【1】用栈
     * 把链表前半段入栈
     * 遍历后半段的同时，栈pop，比较数值，直到栈空
     */
    private static boolean isPalindrome(Node head) {
        if (head == null) {
            return false;
        }

        Stack<Integer> stack = new Stack<Integer>();

        // 把上半段（奇数截止中点，偶数截止上中点）入栈
        Node slow = head;
        Node fast = head;
        stack.push(head.value);
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            stack.push(slow.value);
        }

        // 奇数先pop一下，把中点弹出
        if (fast.next == null) {
            stack.pop();
        }

        // 比较栈pop和链表next
        while (!stack.isEmpty()) {
            Node listItem = slow.next;
            Integer stackItem = stack.pop();
            if (stackItem != listItem.value) {
                return false;
            }

            slow = slow.next;
        }

        return true;
    }

    /**
     * 【2】reverse
     * 快慢指针，fast到尾，slow到中间（奇数中点，偶数上中点）
     * fast从尾开始
     */
    private static boolean isPalindrome2(Node head) {
        if (head == null) {
            return false;
        }

        // 1.利用快慢指针让slow到中点（奇数中点，偶数上中点）
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2.从slow开始reverse，reverse后slow指null，tail为尾巴
        Node tail = reverse(slow);

        // 3.head和tail分别步进并比较大小，tail边步进边reverse
        // 这里的边界条件搞了半天
        // 注意！reverse时，每过一个节点，只能把当前节点的指针正确指向，比如过尾节点时只是把尾节点指向了null
        // 偶数长度时，当head指向上中点的下一个（null）时，tail指向上中点，此时，1.所有节点已比较完毕，2.需要把slow指向tailPre
        // 奇数长度时，当head指向中点（slow）时，tail也指向中点，此时：1.head、tail都到中点了，无需比较，2.slow指向null，需要把slow指向tailPre
        boolean isPalindrome = true;
        Node headCurr = head;
        Node tailPre = null;
        Node tailCurr = tail;
        while (headCurr != null && headCurr != tailCurr) {
            if (isPalindrome && headCurr.value != tailCurr.value) {
                isPalindrome = false;
            }

            // head向前移动
            headCurr = headCurr.next;

            // tail边向前移动边reverse回来
            Node tailNext = tailCurr.next;
            tailCurr.next = tailPre;
            tailPre = tailCurr;
            tailCurr = tailNext;
        }

        slow.next = tailPre;

        return isPalindrome;
    }

    /**
     * 装进数组，找到中点（奇数中点前一点，偶数上中点），判断是否对称
     */
    private static boolean easyButSilly1(Node head) {
        if (head == null) {
            return false;
        }

        // mid = (len + 1) / 2
        List<Node> list = new ArrayList<Node>();
        Node headCurr = head;
        while (headCurr != null) {
            list.add(headCurr);
            headCurr = headCurr.next;
        }

        int len = list.size();
        // 偶数：上中点，奇数中点前一个
        int mid = len / 2 - 1;

        for (int i = 0; i <= mid; i++) {
            Node node1 = list.get(i);
            Node node2 = list.get(len - 1 - i);
            if (node1.value != node2.value) {
                return false;
            }
        }

        return true;
    }

    /**
     * 整个入栈，依次出栈和原链表比
     */
    private static boolean easyButSilly2(Node head) {
        if (head == null) {
            return false;
        }

        Stack<Integer> stack = new Stack<Integer>();
        Node headCurr = head;
        while (headCurr != null) {
            stack.push(headCurr.value);
            headCurr = headCurr.next;
        }

        headCurr = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != headCurr.value) {
                return false;
            }

            headCurr = headCurr.next;
        }

        return true;
    }

    private static Node reverse(Node head) {
        if (head == null) {
            return null;
        }

        Node pre = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }
}
