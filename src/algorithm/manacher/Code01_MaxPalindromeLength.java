package algorithm.manacher;

import algorithm.util.Checker;

/**
 * @Author: wyx
 * @Date: 2021/5/31
 */
public class Code01_MaxPalindromeLength {

    public static void main(String[] args) {
//        String str = "abctabcxxcbatcba";
//        System.out.println(solve(str));
//        System.out.println(violentSolve(str));

        check(1);
    }

    public static void check(int times) {
        String str = "";
        try {
            while (times-- > 0) {
                str = Checker.generateLowerLetterString(20, 5);
                int r1 = violentSolve(str);
                int r2 = solve(str);
                System.out.println(str + "\t" + r1 + "\t" + r2);
                if (r1 != r2) {
                    throw new RuntimeException("r1!=r2");
                }
            }

            System.out.println("成功!!!!");
        } catch (Exception e) {
            System.out.println("失败！！！！！");
            System.out.println(str);
            e.printStackTrace();
        }
    }

    private static int violentSolve(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        String s = addSymbolToManacherStr(str);
        int len = s.length();
        char[] arr = s.toCharArray();
        int pMax = 0;
        int[] pArr = new int[len];
        for (int i = 0; i < len; i++) {
            int right = expand(arr, i, i);
            pArr[i] = (right - i) * 2 + 1;
            pMax = Math.max(pArr[i] / 2, pMax);
        }

        return pMax;
    }

    private static int solve(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        String s = addSymbolToManacherStr(str);
        int len = s.length();

        int R = -1;
        int C = -1;
        int pMax = 0;
        int[] pArr = new int[len];
        char[] arr = s.toCharArray();
        for (int i = 0; i < len; i++) {
            // i在r外，暴力扩
            if (i > R) {
                int right = expand(arr, i, i);
                pArr[i] = (right - i) * 2 + 1;
                // 记录最右边界和对应的中心点
                if (right > R) {
                    R = right;
                    C = i;
                }
                pMax = Math.max(pArr[i], pMax);
            }
            // i不在r外，i在r上或者r在r内
            else {
                // 找到i关于c对称的i'，i'的左边界在L内
                int R_ = 2 * C - R;
                int i_ = 2 * C - i;
                int i_left = i_ - pArr[i_] / 2;
                // i的对称点的左边界在R'内
                if (i_left > R_) {
                    pArr[i] = pArr[i_];
                }
                // i的对称点的左边界在R'外
                else if (i_left < R_) {
                    pArr[i] = R - i + 1;
                }
                // i的对称点的左边界=R'
                else {
                    // 从R外开始扩
                    int right = expand(arr, 2 * i - R, R);
                    pArr[i] = (right - i) * 2 + 1;
                    // 记录最右边界和对应的中心点
                    if (right > R) {
                        R = right;
                        C = i;
                    }
                    pMax = Math.max(pArr[i], pMax);
                }
            }
        }

        return pMax / 2;
    }

    private static String addSymbolToManacherStr(String str) {
        String result = "#";
        for (int i = 0; i < str.length(); i++) {
            result += str.charAt(i) + "#";
        }

        return result;
    }

    private static int expand(char[] arr, int left, int right) {
        int len = arr.length;
        while (true) {
            int nextLeft = left - 1;
            int nextRight = right + 1;
            if (nextLeft <= -1 || nextRight >= len) {
                break;
            }

            if (arr[nextLeft] == arr[nextRight]) {
                left--;
                right++;
            } else {
                break;
            }
        }

        return right;
    }
}
