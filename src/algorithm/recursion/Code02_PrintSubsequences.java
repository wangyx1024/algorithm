package algorithm.recursion;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印子序列
 */
public class Code02_PrintSubsequences {

    public static void main(String[] args) {
        print("abc");
    }

    private static void print(String str) {
        if (str == null) {
            return;
        }

        List<String> results1 = new ArrayList<>();
        printAllSubsequences(str, 0, "", results1);
        System.out.println(results1);

        Set<String> results2 = new HashSet<>();
        printDistinct(str, 0, "", results2);
        System.out.println(results2);
    }

    /**
     * 全部子序列
     */
    private static void printAllSubsequences(String str, int from, String result, List<String> results) {
        if (from == str.length()) {
            results.add(result);
        } else {
            printAllSubsequences(str, from + 1, result, results);
            printAllSubsequences(str, from + 1, result + str.charAt(from), results);
        }
    }

    /**
     * 去重
     */
    private static void printDistinct(String str, int from, String result, Set<String> results) {
        if (from == str.length()) {
            results.add(result);
        } else {
            printDistinct(str, from + 1, result, results);
            printDistinct(str, from + 1, result + str.charAt(from), results);
        }
    }
}
