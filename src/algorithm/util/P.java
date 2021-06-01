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
            System.out.print(pad(i++ + "", 3, ' ', true) + ",");
        }
        System.out.println();

        i = 0;
        while (i < len) {
            System.out.print(pad(arr[i++] + "", 3, ' ', true) + ",");
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

    public static String pad(String str, int targetLen, char c, boolean left) {
        if (str == null) {
            return "";
        }
        int currentLen = str.length();
        if (currentLen > targetLen) {
            return str;
        }

        int diffLen = targetLen - currentLen;
        String padStr = "";
        while (diffLen-- > 0) {
            padStr += c;
        }

        return left ? padStr + str : str + padStr;
    }
}
