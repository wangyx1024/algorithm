package util;

import datastruct.BinaryTreeNode;
import datastruct.SingleNode;
import tree.Code05_Serialization;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class U {

    public static void main(String[] args) {
        SingleNode[] arr = {new SingleNode(1), new SingleNode(2), new SingleNode(3)};

        SingleNode head = getSingleNodeList(arr);
        P.print(head);


        int[] arr2 = {9, 5, 2, 7};
        SingleNode head2 = getSingleNodeList2(arr2);

        P.print(head2);
    }

    public static SingleNode getSingleNodeList(int size) {
        if (size <= 0) {
            return null;
        }

        SingleNode head = new SingleNode(1);
        for (int i = 2; i <= size; i++) {
            head.add(i);
        }

        return head;
    }

    @Deprecated
    public static SingleNode getSingleNodeList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        SingleNode head = new SingleNode(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            head.add(arr[i]);
        }

        return head;
    }

    public static SingleNode getSingleNodeList2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        SingleNode head = new SingleNode(arr[0]);
        SingleNode pre = head;
        for (int i = 1; i < arr.length; i++) {
            SingleNode curr = new SingleNode(arr[i]);
            pre.next = curr;
            pre = curr;
        }

        return head;
    }

    public static SingleNode getSingleNodeList(SingleNode[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        SingleNode head = arr[0];
        SingleNode pre = head;
        for (int i = 1; i < arr.length; i++) {
            pre.next = arr[i];
            pre = arr[i];
        }

        return head;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void swap(Object[] arr, int i, int j) {
        if (i == j) {
            return;
        }
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];

        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static Queue<Integer> getQueue(Integer... arr) {
        return new LinkedList<>(Arrays.asList(arr));
    }

    public static BinaryTreeNode getBinaryTree(Integer... arr) {
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(arr));
        return Code05_Serialization.bfsDeserialize(queue);
    }
}
