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
        int[] nextArr = new int[len];
        if (len >= 1) {
            nextArr[0] = -1;
        }
        if (len >= 2) {
            nextArr[1] = 0;
        }

        char[] matchArr = match.toCharArray();
        for (int i = 2; i < len; i++) {
            int tail = i - 1;
            int next = tail;
            while (true) {
                next = nextArr[next];
                if (next == -1) {
                    break;
                }

                if (matchArr[next] == matchArr[tail]) {
                    nextArr[i] = next + 1;
                    break;
                }
            }
        }

        return nextArr;
    }
}
