package algorithm.sort;

import algorithm.util.P;

public class Code09_ReversePair {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        P.print(arr);

        System.out.println("lessSum=" + countReversePair(arr));

//        check(1, 100, 100);
    }

    private static int countReversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        int m = l + ((r - l) >> 1);
        return process(arr, l, m) +
                process(arr, m + 1, r) +
                merge(arr, l, m, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int lIndex = l;
        int rIndex = m + 1;

        int i = 0;
        int len = r - l + 1;
        int lessSum = 0;
        int[] sorted = new int[len];

        while (lIndex <= m && rIndex <= r) {

        }

        while (lIndex <= m) {
            sorted[i++] = arr[lIndex++];
        }

        while (rIndex <= r) {
            sorted[i++] = arr[rIndex++];
        }

        i = 0;
        while (i < len) {
            arr[l + i] = sorted[i++];
        }

        return lessSum;
    }
}
