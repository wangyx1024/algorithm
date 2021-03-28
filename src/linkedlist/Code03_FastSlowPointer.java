package linkedlist;

import datastruct.Node;
import util.L;
import util.P;

import java.util.ArrayList;
import java.util.List;

public class Code03_FastSlowPointer {

    public static void main(String[] args) {
        check(1000, 4);
    }

    public static void check(int times, int question) {
        int len = 0;
        Node head1 = null;
        Node head2 = null;

        try {
            while (times-- >= 0) {
                len = times;
                head1 = L.getSingleNodeList(len);
                head2 = L.getSingleNodeList(len);
                Node mid1;
                Node mid2;
                if (question == 1) {
                    mid1 = getMid1(head1);
                    mid2 = easyButSilly1(head2);
                } else if (question == 2) {
                    mid1 = getMid2(head1);
                    mid2 = easyButSilly2(head2);
                } else if (question == 3) {
                    mid1 = getMid3(head1);
                    mid2 = easyButSilly3(head2);
                } else if (question == 4) {
                    mid1 = getMid4(head1);
                    mid2 = easyButSilly4(head2);
                } else {
                    mid1 = null;
                    mid2 = null;
                }

                if (mid1 == null) {
                    if (mid2 == null) {
                        System.out.println("len==" + len + ";mid==null");
                    } else {
                        throw new RuntimeException("mid1 == null && mid2 != null");
                    }
                } else {
                    if (mid2 == null) {
                        throw new RuntimeException("mid1 != null && mid2 == null");
                    } else {
                        if (mid1.value != mid2.value) {
                            throw new RuntimeException("mid1 != null && mid2 == null");
                        } else {
                            System.out.println("len==" + len + ";mid==" + mid1.value);
                        }
                    }
                }
            }

            System.out.println("成功！！！");
        } catch (Exception e) {
            System.out.println("报错！！！" + e.getMessage());
            e.printStackTrace();
            System.out.println(len);
            P.print(head1);
            P.print(head2);
        }
    }

    /**
     * 【1】奇数返回中点，偶数返回上中点
     */
    private static Node getMid1(Node head) {
        if (head == null) {
            return null;
        }

        // fast能走两步就走两步，不能走就return slow
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private static Node easyButSilly1(Node head) {
        if (head == null) {
            return null;
        }

        // mid = (len + 1) / 2
        List<Node> list = new ArrayList<Node>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int len = list.size();
        int mid = (len + 1) / 2;
        int index = mid - 1;
        return list.get(index);
    }

    /**
     * 【2】奇数返回中点，偶数返回下中点
     */
    private static Node getMid2(Node head) {
        if (head == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast.next == null) {
            return slow;
        } else {
            return slow.next;
        }
    }

    private static Node easyButSilly2(Node head) {
        if (head == null) {
            return null;
        }

        // mid = (len + 1) / 2 + 1
        List<Node> list = new ArrayList<Node>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int len = list.size();
        // 奇数依然是找中点，偶数是找下中点=上中点+1
        int mid = (len + 1) / 2;
        if (len % 2 == 0) {
            mid++;
        }

        int index = mid - 1;
        return list.get(index);
    }

    /**
     * 【3】奇数返回中点前一个，偶数返回上中点前一个
     */
    private static Node getMid3(Node head) {
        if (head == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        Node nodeBeforeSlow = null;
        while (fast.next != null && fast.next.next != null) {
            nodeBeforeSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return nodeBeforeSlow;
    }

    private static Node easyButSilly3(Node head) {
        if (head == null) {
            return null;
        }

        // mid = (len + 1) / 2 + 1
        List<Node> list = new ArrayList<Node>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int len = list.size();
        // getMid1中，中点的前一个点，len==1、2时为null
        int mid = (len + 1) / 2 - 1;
        int index = mid - 1;
        if (index < 0) {
            return null;
        } else {
            return list.get(index);
        }
    }

    /**
     * 【3】奇数返回中点前一个，偶数返回下中点前一个也就是上中点
     */
    private static Node getMid4(Node head) {
        if (head == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        Node nodeBeforeSlow = null;
        while (fast.next != null && fast.next.next != null) {
            nodeBeforeSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast.next == null) {
            return nodeBeforeSlow;
        } else {
            return slow;
        }
    }

    private static Node easyButSilly4(Node head) {
        if (head == null) {
            return null;
        }

        // mid = (len + 1) / 2 + 1
        List<Node> list = new ArrayList<Node>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int len = list.size();
        // getMid1中，中点的前一个点，len==1、2时为null
        int mid;
        if (len % 2 == 1) {
            mid = (len + 1) / 2 - 1;
        } else {
            mid = (len + 1) / 2;
        }

        int index = mid - 1;
        if (index < 0) {
            return null;
        } else {
            return list.get(index);
        }
    }
}
