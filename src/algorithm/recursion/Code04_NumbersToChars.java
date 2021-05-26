package algorithm.recursion;


import java.util.ArrayList;
import java.util.List;

/**
 * 1~26分别代表A~Z，把数字字符串转成字母字符串的转法数量
 */
public class Code04_NumbersToChars {

    public static void main(String[] args) {
        solve("111222333");
    }

    private static int solve(String str) {
        List<String> results = new ArrayList<>();
        violentProcess(str, 0, "", results);
        int r2 = violentProcess2(str, 0);
        int r3 = dpProcess(str);

        System.out.println(results.size() + "" + results);
        System.out.println(r2);
        System.out.println(r3);
        return 0;
    }

    /**
     * 暴力递归，求出所有可能的字符串
     */
    private static void violentProcess(String str, int from, String result, List<String> results) {
        if (from == str.length()) {
            results.add(result);
        } else {
            String s1 = str.charAt(from) + "";
            // 当某个数以0开头时说明这条路走不通，直接return
            if ("0".equals(s1)) {
                return;
            } else {
                // 否则有两种转换方式：1.当前位置的数字单独转换为一个字母，2.当前位置+下一个位置的两个字符转换为一个字母
                violentProcess(str, from + 1, result + parseNumberStrToChar(s1), results);
                if (from + 1 < str.length()) {
                    String s2 = s1 + str.charAt(from + 1);
                    int num = Integer.parseInt(s2);
                    if (num <= 26) {
                        violentProcess(str, from + 2, result + parseNumberStrToChar(s2), results);
                    }
                }
            }
        }
    }

    /**
     * 暴力递归2，只求数量
     */
    private static int violentProcess2(String str, int i) {
        int len = str.length();
        if (i == len) {
            return 1;
        } else {
            if (str.charAt(i) == '0') {
                return 0;
            } else {
                // 否则有两种转换方式
                // 1.i位置的数字单独转换为一个字母
                // 2.i位置和i+1位置的两个数字一起转换为一个字母
                if (i + 1 < len && Integer.parseInt(str.charAt(i) + "" + str.charAt(i + 1)) <= 26) {
                    return violentProcess2(str, i + 1) + violentProcess2(str, i + 2);
                } else {
                    return violentProcess2(str, i + 1);
                }
            }
        }
    }

    /**
     * dp转移方程
     */
    private static int dpProcess(String str) {
        // 下标，表i，范围0~len，共len+1
        int len = str.length();
        int[] arr = new int[len + 1];

        // 赋值，arr[i]=?
        arr[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (i == len) {
                arr[i] = 1;
            } else {
                if (str.charAt(i) == '0') {
                    arr[i] = 0;
                } else {
                    // 否则有两种转换方式
                    // 1.i位置的数字单独转换为一个字母
                    // 2.i位置和i+1位置的两个数字一起转换为一个字母，有条件
                    if (i + 1 < len && Integer.parseInt(str.charAt(i) + "" + str.charAt(i + 1)) <= 26) {
                        arr[i] = arr[i + 1] + arr[i + 2];
                    } else {
                        arr[i] = arr[i + 1];
                    }
                }
            }
        }

        return arr[0];
    }

    private static char parseNumberStrToChar(String str) {
        int num = Integer.parseInt(str);
        return (char) ('A' - 1 + num);
    }
}
