package algorithm.sort;

import algorithm.util.Checker;
import algorithm.util.P;

import java.util.Arrays;

public class Code05_MergeSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 9, 8, 5};
        P.print(arr);

        mergeSort(arr, 0, arr.length - 1);
        P.print(arr);

        P.divider();

        check();
    }

    public static void check() {
        int times = 10;
        while (times-- > 0) {
            int[] arr = Checker.generate(100, 100);
            int[] arr1 = Checker.copy(arr);
            int[] arr2 = Checker.copy(arr);

            try {
                mergeSort(arr1, 0, arr1.length - 1);
                Arrays.sort(arr2);
//                System.out.println("arr1==" + Arrays.toString(arr1));
//                System.out.println("arr2==" + Arrays.toString(arr2));
//                P.divider();

                Checker.compare(arr1, arr2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                P.print(arr);
                P.print(arr1);
                P.print(arr2);
                break;
            }
        }

        System.out.println("成功!!!!");
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (r <= l) {
            return;
        }

        // >>2 = 除4，不影响结果
//        int mid = l + ((r - l) >> 2);
        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] arr2 = new int[r - l + 1];
        int i = 0;

        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            arr2[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            arr2[i++] = arr[p1++];
        }

        while (p2 <= r) {
            arr2[i++] = arr[p2++];
        }

        p1 = l;
        i = 0;
        while (p1 <= r) {
            arr[p1++] = arr2[i++];
        }
    }
}
