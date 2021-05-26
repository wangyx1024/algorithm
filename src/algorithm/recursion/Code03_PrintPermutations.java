package algorithm.recursion;


import algorithm.util.U;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印全排列
 */
public class Code03_PrintPermutations {

    public static void main(String[] args) {
        print("abc");
    }

    private static void print(String str) {
        if (str == null) {
            return;
        }

        // a, b, c
        // ab, ac, ba, bc, ca, cb
        // abc, acb, bac, bca, cab, cba
        Set<String> results3 = new HashSet<>();
        test(str, new ArrayList<>(), results3);
        System.out.println(results3);

        Set<String> results4 = new HashSet<>();
        printFullPermutations2(str.toCharArray(), 0, results4);
        System.out.println(results4);

    }

    private static void test(String str, List<Integer> result, Set<String> results) {
        if (str.length() == result.size()) {
            String strResult = "";
            for (int i = 0; i < result.size(); i++) {
                Integer v = result.get(i);
                if (v != -1) {
                    strResult += str.charAt(v);
                }
            }

            results.add(strResult);
        } else {
            for (int i = -1; i < str.length(); i++) {
                if (i != -1 && result.contains(i)) {
                    continue;
                }
                List<Integer> newResult = U.copy(result);
                newResult.add(i);
                test(str, newResult, results);
            }
        }
    }

    /**
     * 全排列
     */
    private static void printFullPermutations2(char[] str, int from, Set<String> results) {
        if (from == str.length) {
            results.add(String.valueOf(str));
        } else {
            for (int i = from; i < str.length; i++) {
                swap(str, i, from);
                printFullPermutations2(str, from + 1, results);
                swap(str, i, from);
            }
        }
    }

    /**
     * 分支限界版本（提前杀死支路）
     */
    private static void printDistinctFullPermutations(char[] str, int from, Set<String> results) {
        if (from == str.length) {
            results.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = from; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, from, i);
                    printDistinctFullPermutations(str, from + 1, results);
                    swap(str, from, i);
                }
            }
        }
    }


    private static void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }
}
