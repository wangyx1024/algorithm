package algorithm.bfprt;

import algorithm.util.Checker;
import algorithm.util.P;
import algorithm.util.U;
import com.sun.tools.javac.comp.Check;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: wyx
 * @Date: 2021/6/1
 */
public class Code01_bfprt {

    public static void main(String[] args) {
//        int len = 10;
//        int k = 3;
//        int[] arr = Checker.generate(len, 10, false, false, false);
//        int[] arr1 = Checker.copy(arr);
//        int[] arr2 = Checker.copy(arr);
//        P.print(arr1);
//        System.out.println(solve0(arr1, k));
//
//        Arrays.sort(arr2);
//        System.out.println(arr2[k - 1]);

//        check(500000);

//        int[] arr = {-1, 5, 7, -10, -1, 8, 0, 6, 3, -5};
//        P.print(arr);
//        int[] arr1 = Checker.copy(arr);
//        int r1 = bfprtSolve(arr1, 7);
//        System.out.println(r1);

        check(100000);
    }

    private static void check(int times) {
        int len = 10;
        int k = Checker.generateRandomPositiveNumNoMoreThan(len - 1) + 1;
        int[] arr = Checker.generate(len, 10, false, false, false);
        try {
            while (times-- > 0) {
                int[] arr1 = Checker.copy(arr);
                int[] arr2 = Checker.copy(arr);
                int[] arr3 = Checker.copy(arr);

                int r1 = normalSolve(arr1, k);

                Arrays.sort(arr2);
                int r2 = arr2[k - 1];

                int r3 = bfprtSolve(arr3, k);
                if (r1 != r2 || r2 != r3) {
                    throw new RuntimeException("!!!");
                }
            }

            System.out.println("成功！");
        } catch (Exception e) {
            P.print(arr);
            System.out.println("k=" + k);
            e.printStackTrace();
        }
    }

    /**
     * 普通解，快排partition的思想，pivot随机选
     */
    private static int normalSolve(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int len = arr.length;
        if (k < 1 || k > len) {
            return -1;
        }

        int left = 0;
        int right = len - 1;
        int kIndex = k - 1;

        while (true) {
            int pivot = pickPivotByRandom(arr, left, right);
            int[] partitionResult = partition(arr, left, right, pivot);
            int less = partitionResult[0];
            int more = partitionResult[1];

            // 判断
            // 注意less的概念，是小于区的边界，[left, less]是小于区，less+1才是等于区！
            if (kIndex <= less) {
                right = less;
            } else if (kIndex >= more) {
                left = more;
            } else {
                return arr[kIndex];
            }
        }
    }

    private static int bfprtSolve(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int len = arr.length;
        if (k < 1 || k > len) {
            return -1;
        }

        int left = 0;
        int right = len - 1;
        int kIndex = k - 1;

        while (true) {
            int pivot = pickPivotByBfprt(arr, left, right);
            int[] partitionResult = partition(arr, left, right, pivot);
            int less = partitionResult[0];
            int more = partitionResult[1];

            // 判断
            // 注意less的概念，是小于区的边界，[left, less]是小于区，less+1才是等于区！
            if (kIndex <= less) {
                right = less;
            } else if (kIndex >= more) {
                left = more;
            } else {
                return arr[kIndex];
            }
        }
    }

    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int less = left - 1;
        int more = right + 1;

        // partition
        int i = left;
        while (i < more) {
            if (arr[i] < pivot) {
                U.swap(arr, i++, ++less);
            } else if (arr[i] > pivot) {
                U.swap(arr, i, --more);
            } else {
                i++;
            }
        }

        return new int[]{less, more};
    }

    /**
     * 复习快排
     */
    private static void checkFastSort() {
        try {
            for (int i = 0; i < 100000; i++) {
                int[] arr = Checker.generate(10, 10);
                int[] arr1 = Checker.copy(arr);
                int[] arr2 = Checker.copy(arr);

                fastSort(arr1, 0, arr.length - 1);
                Arrays.sort(arr2);

                if (!Arrays.equals(arr1, arr2)) {
                    throw new RuntimeException("arr1!=arr2");
                }
            }

            System.out.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fastSort(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0) {
            return;
        }

        fastSortProcess(arr, left, right);
    }

    private static void fastSortProcess(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        // 定义大于、小于区的边界
        int less = left - 1;
        int more = right + 1;
        // 挑选pivot
        int pivot = pickPivotByRandom(arr, left, right);

        // 对范围内partition
        for (int i = left; i < more; ) {
            // 比pivot小的发货到小于区
            if (arr[i] < pivot) {
                U.swap(arr, ++less, i++);
            }
            // 比pivot大的发货到大于区，此时i不++，因为不知道换过来的是啥
            else if (arr[i] > pivot) {
                U.swap(arr, --more, i);
            }
            // 和pivot一样大的不动，相当于保留在less、more之间的等于区，i++
            else {
                i++;
            }
        }

        fastSortProcess(arr, left, less);
        fastSortProcess(arr, more, right);
    }

    private static int pickPivotByRandom(int[] arr, int left, int right) {
//        return arr[right];
        return arr[left + new Random().nextInt(right - left + 1)];
    }

    private static int pickPivotByBfprt(int[] arr, int l, int r) {
        int scale = 5;

        // 长度不超过5，直接取上中点
        int len = r - l + 1;
        if (len <= scale) {
            return arr[l + (len - 1) / 2];
        }

        // 5个一组，内部排序
        int group = (int) Math.ceil(1.0D * len / scale);
        int[] mArr = new int[group];
        for (int left = l; left <= r; left += scale) {
            int right = Math.min(left + scale - 1, len - 1);
            fastSort(arr, left, right);
            mArr[left / scale] = arr[left + (right - left) / 2];
        }

        return pickPivotByBfprt(mArr, 0, mArr.length - 1);
    }
}
