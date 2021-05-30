package algorithm.util;

import algorithm.datastruct.linkedlist.SingleNode;

public class P {

    public static void print(SingleNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }

        System.out.println();
    }

    public static void print(String str) {
        int i = 0;
        while (i < str.length()) {
            System.out.print(str.charAt(i++) + "\t");
        }
        System.out.println();
    }

    public static void print(int[] arr) {
        print(arr, arr.length);
    }

    public static void print(int[] arr, int len) {
        int i = 0;
        while (i < len) {
            System.out.print(i++ + "\t");
        }
        System.out.println();

        i = 0;
        while (i < len) {
            System.out.print(arr[i++] + "\t");
        }

        System.out.println();
    }

    public static void print(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        for (int i = 0; i < row; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < col; j++) {
                String str = arr[i][j] + "";
                int pad = 3 - str.length();
                while (pad-- > 0) {
                    str = " " + str;
                }
                System.out.print(str + ", ");
            }

            System.out.println();
        }
    }

    public static void divider() {
        System.out.println("\n--------------------我是分割线--------------------");
    }
}
