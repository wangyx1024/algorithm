package algorithm.sort;

import algorithm.util.Checker;
import algorithm.util.P;
import algorithm.util.U;

import java.util.Arrays;

public class Code02_BubbleSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 9, 8, 5};
        P.print(arr);

        bubbleSort(arr);
        P.print(arr);

        P.divider();

        check();
    }

    public static void check() {
        int times = 1000000;
        while (times-- > 0) {
            int[] arr = Checker.generate(100, 100);
            int[] arr1 = Checker.copy(arr);
            int[] arr2 = Checker.copy(arr);

            try {
                bubbleSort(arr1);
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

    private static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    U.swap(arr, j, j + 1);
                }
            }
        }
    }
}
