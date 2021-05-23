package algorithm.sliding_window;

import algorithm.util.Checker;
import algorithm.util.P;

import java.util.LinkedList;

/**
 * 给一个数组arr，如果其中的一个子数组sub中的最大值-最小值<=limit，则达标
 * 求达标子数组的数量
 */
public class Code02_DiffOfWindowUnderLimit {

    public static void main(String[] args) {
        int limit = 50;
        int[] arr = {11, 22, 33, 11, 11, 22, 33, 55};
        System.out.println(violentSolve(arr, limit));
        System.out.println(solve(arr, limit));
//        check();
    }

    public static void check() {
        int times = 100;
        int[] arr = Checker.generate(10, 100);
        try {
            while (times-- > 0) {
                int r1 = violentSolve(arr, 50);
                int r2 = solve(arr, 50);
                if (r1 != r2) {
                    throw new RuntimeException("r1!=r2");
                }
            }

            System.out.println("成功!!!!");
        } catch (Exception e) {
            System.out.println("失败！！！！！");
            P.print(arr);
            e.printStackTrace();
        }
    }

    private static int violentSolve(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            Integer min = null;
            Integer max = null;
            int diff;
            for (int j = i; j < arr.length; j++) {
                min = (min == null || arr[j] < min) ? arr[j] : min;
                max = (max == null || arr[j] > max) ? arr[j] : max;
                diff = max - min;
                if (diff > limit) {
                    break;
                } else {
                    result++;
                }
            }
        }

        return result;
    }

    private static int solve(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 【方案】
        // 如果某个sub满足条件，那么sub的所有子数组都满足条件
        // 如果某个sub不满足条件，那么以sub为子数组的所有数组都不满足条件
        // 算以i元素为窗口左边界时满足条件的子数组数量，i从0~arr.len-1，求和
        // 【实现】
        // 来两个双端队列largeQueue、smallQueue，分别记录当前窗口内的最大值下标max和最小值下标min，差值diff=arr[max]-arr[min]
        // 遍历arr，下标left代表窗口左边界，left从0~len-1，left更新时，吐出left-1，右边界right代表当前窗口真实右边界的下一位，所以right>=left+1
        // 如果此时窗口内的diff<=limit，吸纳right+1，直到diff>limit或者right到达len-1

        int right = 0;
        int result = 0;
        int len = arr.length;

        LinkedList<Integer> largeQueue = new LinkedList<>();
        LinkedList<Integer> smallQueue = new LinkedList<>();

        for (int left = 0; left < len; left++) {
            int prev = left - 1;
            if (!largeQueue.isEmpty() && largeQueue.peekFirst() == prev) {
                largeQueue.pollFirst();
            }
            if (!smallQueue.isEmpty() && smallQueue.peekFirst() == prev) {
                smallQueue.pollFirst();
            }

            while (right < len) {
                while (!largeQueue.isEmpty() && arr[right] >= arr[largeQueue.peekLast()]) {
                    largeQueue.pollLast();
                }
                largeQueue.addLast(right);

                while (!smallQueue.isEmpty() && arr[right] <= arr[smallQueue.peekLast()]) {
                    smallQueue.pollLast();
                }
                smallQueue.addLast(right);

                if (arr[largeQueue.peekFirst()] - arr[smallQueue.peekFirst()] > limit) {
                    break;
                }
                right++;
            }

            result += right - left;
        }

        return result;
    }
}
