package search;

import util.Checker;
import util.P;
import util.U;

import java.util.Arrays;

public class Code01_BinarySearch {

    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 9, 8, 5};
        P.print(arr);

        sort(arr);
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
                sort(arr1);
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

    private static void sort(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    U.swap(arr, j, j + 1);
                }
            }
        }
    }
}
