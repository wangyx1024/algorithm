package algorithm.kmp;

import algorithm.util.Checker;
import algorithm.util.P;
import com.sun.tools.javac.comp.Check;

import java.util.Arrays;

/**
 * @Author: wyx
 * @Date: 2021/5/30
 */
public class Code01_KMP {

    public static void main(String[] args) {
//        String match = "abcabc";
//        P.print(match);
//        P.print(getNextArr(match));

//        String str = "aaaaa";
//        String match = "abcabcx";
//        int result = solve(str, match);
//        System.out.println(result);
//        int result2 = violentSolve(str, match);
//        System.out.println(result2);

//        String str = Checker.generateLowerLetterString(100);
//        System.out.println(str);

        check(100000);
    }

    public static void check(int times) {
        String str = "";
        String match = "";
        try {
            while (times-- > 0) {
                str = Checker.generateLowerLetterString(20, 5);
                match = Checker.generateLowerLetterString(5, 5);
                int r1 = violentSolve(str, match);
                int r2 = solve(str, match);
                if (r1 != r2) {
                    throw new RuntimeException("r1!=r2");
                }
            }

            System.out.println("成功!!!!");
        } catch (Exception e) {
            System.out.println("失败！！！！！");
            System.out.println(str);
            System.out.println(match);
            e.printStackTrace();
        }
    }

    private static int violentSolve(String str, String match) {
        if (str == null || "".equals(str) || match == null || "".equals(str)) {
            return -1;
        }

        int slen = str.length();
        int mlen = match.length();
        if (mlen > slen) {
            return -1;
        }

        char[] strArr = str.toCharArray();
        char[] matchArr = match.toCharArray();

        int i = 0;
        while (i < slen) {
            int i2 = i;
            int j = 0;
            while (i2 < slen && j < mlen) {
                if (strArr[i2] == matchArr[j]) {
                    i2++;
                    j++;
                } else {
                    break;
                }
            }

            if (j == mlen) {
                return i;
            } else {
                i++;
            }
        }

        return -1;
    }

    private static int solve(String str, String match) {
        if (str == null || "".equals(str) || match == null || "".equals(str) || match.length() > str.length()) {
            return -1;
        }

        int i = 0;
        int j = 0;
        int slen = str.length();
        int mlen = match.length();
        char[] strArr = str.toCharArray();
        char[] matchArr = match.toCharArray();

        int[] nextArr = getNextArr(match);
//        P.print(match);
//        P.print(nextArr);
        while (true) {
            if (j == mlen) {
                return i - mlen;
            } else if (i == slen) {
                return -1;
            }

            if (strArr[i] == matchArr[j]) {
                i++;
                j++;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = nextArr[j];
                }
            }
        }
    }

    private static int[] getNextArr(String match) {
        if (match == null || match.length() == 0) {
            return new int[]{};
        }

        int len = match.length();
        int[] next = new int[len];
        if (len >= 1) {
            next[0] = -1;
        }
        if (len >= 2) {
            next[1] = 0;
        }

        // 已知next[0..i-1]，求next[i]
        // next[i-1]=7，说明arr[0~6]==arr[i-8..i-2]
        // 如果arr[i-1]==arr[next[i-1]]，说明next[i]=next[i-1]+1
        char[] arr = match.toCharArray();
        for (int i = 2; i < len; i++) {
            // 从i-1开始，比较arr[i-1]和arr[next[i-1]]
            // point一路往前跳，直到point==0
            // arr[0]和arr[i-1]都不等，说明next[i]=0了，不用赋值默认就是0
            int point = i - 1;
            while (point != 0) {
                point = next[point];
                if (arr[i - 1] == arr[point]) {
                    next[i] = point + 1;
                    break;
                }
            }
        }

        return next;
    }
}
