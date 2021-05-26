package algorithm.recursion;

import algorithm.util.P;

/**
 * 最长公共子序列
 */
public class Code12_LogestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(solve("a123b", "c123d"));
    }

    private static int solve(String s1, String s2) {
        return process(s1, s2);
    }

    private static int process(String s1, String s2) {
        int row = s1.length();
        int col = s2.length();
        int[][] matrix = new int[row][col];

        char[] s1arr = s1.toCharArray();
        char[] s2arr = s2.toCharArray();

        // s1，s2各拿出第一个字符，这时最长公共子序列的长度，第一个字符相同是1，否则是0
        matrix[0][0] = s1arr[0] == s2arr[0] ? 1 : 0;

        // s1，拿出下标1~[i...len-1]，s2拿出第一个字符
        for (int i = 1; i < row; i++) {
            matrix[i][0] = matrix[i - 1][0] == 1 ? 1 : (s1arr[i] == s2arr[0] ? 1 : 0);
        }

        // s2，拿出下标1~[i...len-1]，s1拿出第一个字符
        for (int i = 1; i < col; i++) {
            matrix[0][i] = matrix[0][i - 1] == 1 ? 1 : (s2arr[i] == s1arr[0] ? 1 : 0);
        }

        // 求s1 s2的最大公共子序列，分3种情况
        // 1.最长公共子序列的结尾字符，和s1、s2结尾的字符都不同
        // 2.最长公共子序列的结尾字符，和s1的结尾字符相同，但和s2的结尾字符不同
        // 3.最长公共子序列的结尾字符，和s2的结尾字符相同，但和s1的结尾字符不同
        // 4.最长公共子序列的结尾字符，和s1、s2结尾的字符相同
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                matrix[i][j] =
                        s1arr[i] == s2arr[j]
                                ? matrix[i - 1][j - 1] + 1
                                : Math.max(matrix[i - 1][j], matrix[i][j - 1]);
            }
        }

        return matrix[row - 1][col - 1];
    }
}
