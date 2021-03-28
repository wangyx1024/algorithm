package util;


import datastruct.Node;

public class L {

    public static Node getSingleNodeList(int size) {
        if (size <= 0) {
            return null;
        }

        Node head = new Node(1);
        for (int i = 2; i <= size; i++) {
            head.add(i);
        }

        return head;
    }

    public static Node getSingleNodeList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Node head = new Node(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            head.add(arr[i]);
        }

        return head;
    }
}
