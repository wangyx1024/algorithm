package algorithm.sort;

import algorithm.util.Checker;
import algorithm.util.P;
import algorithm.util.U;

import java.util.Arrays;

public class Code03_InsertSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 9, 8, 5};
        P.print(arr);

        insertSort(arr);
        P.print(arr);

        P.divider();

        check();
    }

    public static void check() {
        int times = 500000;
        while (times-- > 0) {
            int[] arr = Checker.generate(100, 100);
            int[] arr1 = Checker.copy(arr);
            int[] arr2 = Checker.copy(arr);

            try {
                insertSort(arr1);
                Arrays.sort(arr2);

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

    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (arr[j] < arr[j - 1]) {
                    U.swap(arr, j, j - 1);
                }
            }
        }
    }
}
