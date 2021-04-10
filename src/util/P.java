package util;

import datastruct.linkedlist.SingleNode;

public class P {

    public static void print(SingleNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }

        System.out.println();
    }

    public static void print(int[] arr) {
        print(arr, arr.length);
    }

    public static void print(int[] arr, int len) {
        int i = 0;
        while (i < len) {
            System.out.print(arr[i] + " ");
            i++;
        }

        System.out.println();
    }

    public static void divider() {
        System.out.println("\n--------------------我是分割线--------------------");
    }
}
