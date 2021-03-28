package sort;

import util.Checker;
import util.P;

public class Code08_LessSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        P.print(arr);

        System.out.println("lessSum=" + getLessSum(arr));

        check(1, 100, 100);
    }

    /**
     * 小和是啥：数组中每一个元素，左边比它小的数的总和 -> 数组中每一个元素，如果右边的数比它大就加上自己
     * 归并排序的merge就是左右数组对比的过程，且左右两数组已经有序
     * merge会移动数组元素，没关系，merge影响的是相对小尺度，在merge前把小和处理完，不影响大尺度上的小和
     */
    private static int getLessSum(int[] arr) {
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
            if (arr[lIndex] < arr[rIndex]) {
                lessSum += arr[lIndex] * (r - rIndex + 1);
                sorted[i++] = arr[lIndex++];
            } else {
                sorted[i++] = arr[rIndex++];
            }
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

    private static int badButEasy1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int sum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[j] > arr[i]) {
                    sum += arr[i];
                }
            }
        }

        return sum;
    }

    private static int badButEasy2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int sum = 0;
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    sum += arr[i];
                }
            }
        }

        return sum;
    }


    private static void check(int times, int maxSize, int maxValue) {
        boolean success = true;
        while (times-- > 0) {
            int[] arr = Checker.generate(maxSize, maxValue);
            P.print(arr);
            int[] arr1 = Checker.copy(arr);
            int[] arr2 = Checker.copy(arr);

            try {
                int sum1 = getLessSum(arr1);
                int sum2 = badButEasy1(arr2);
                int sum3 = badButEasy2(arr2);
                System.out.println("sum1==" + sum1 + ";sum2==" + sum2 + ";sum3==" + sum3);

                if (sum1 != sum2 || sum2 != sum3) {
                    throw new RuntimeException("sum1!=sum2!!!!!!");
                }

//                Checker.compare(arr1, arr2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                success = false;
                break;
            }
        }

        if (success) {
            System.out.println("成功！！！！！");
        }
    }
}
