package algorithm.sort;

import algorithm.util.Checker;
import algorithm.util.P;
import algorithm.util.U;

import java.util.Arrays;

public class Code01_ChoiceSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 9, 8, 5};
        P.print(arr);

        choiceSort(arr);
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
                choiceSort(arr1);
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

    private static void choiceSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (min != i) {
                U.swap(arr, i, min);
            }
        }
    }
}
