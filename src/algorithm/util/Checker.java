package algorithm.util;

public class Checker {

    public static void compare(int[]... arrs) throws RuntimeException {
        for (int i = 0; i < arrs[0].length; i++) {
            for (int j = 0; j < arrs.length - 1; j++) {
                int len1 = arrs[j].length;
                int len2 = arrs[j + 1].length;
                if (len1 != len2) {
                    throw new RuntimeException("长度都不一样比个jer");
                }

                int num1 = arrs[j][i];
                int num2 = arrs[j + 1][i];
                if (num1 != num2) {
                    throw new RuntimeException("fucking fucked!!!!!");
                }
            }
        }
    }

    public static int[] copy(int[] arr) {
        int size = arr.length;
        int[] arr2 = new int[size];
        for (int i = 0; i < size; i++) {
            arr2[i] = arr[i];
        }

        return arr2;
    }

    public static int[] generate(int maxLen, int maxValue) {
        return generate(maxLen, maxValue, true, false, false);
    }

    public static int[] generate(int maxLen, int maxValue, boolean randomLen, boolean positiveValue, boolean noRepeat) {
        int len = randomLen ? generateRandomPositiveNumNoMoreThan(maxLen) : maxLen;

        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            if (noRepeat) {
                while (true) {
                    boolean contains = false;
                    int num = positiveValue ? generateRandomPositiveNumNoMoreThan(maxValue) : generateRandomNumNoMoreThan(maxValue);
                    for (int j = 0; j < i; j++) {
                        if (arr[j] == num) {
                            contains = true;
                            break;
                        }
                    }

                    if (!contains) {
                        arr[i] = num;
                        break;
                    }
                }
            } else {
                arr[i] = positiveValue ? generateRandomPositiveNumNoMoreThan(maxValue) : generateRandomNumNoMoreThan(maxValue);
            }
        }

        return arr;
    }

    public static int generateRandomNumNoMoreThan(int value) {
        return generateRandomPositiveNumNoMoreThan(value) - generateRandomPositiveNumNoMoreThan(value);
    }

    public static int generateRandomPositiveNumNoMoreThan(int value) {
        // [0, 1)的任意小数
        double a = Math.random();
        // [0, range)的任意小数
        double b = a * (value + 1);
        // [0, range-1]的任意小数
        return (int) b;
    }

    public static String generateLowerLetterString(int len, int range) {
        char[] arr = new char[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ('a' + generateRandomPositiveNumNoMoreThan(range));
        }
        return new String(arr);
    }
}
