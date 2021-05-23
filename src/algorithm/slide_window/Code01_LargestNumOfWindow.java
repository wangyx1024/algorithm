package algorithm.slide_window;

import algorithm.util.P;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * 给定一个数组arr，窗口宽度为window
 * 窗口的左边沿从0开始，划过数组arr，左边沿到达arr.length
 * 返回每一次即将滑出时，窗口中元素的最大值
 */
public class Code01_LargestNumOfWindow {

    public static void main(String[] args) {
        int[] arr = {8, 4, 1, 2, 5, 9, 0, 3};
        int window = 3;

//        int[] arr = {1, 2, 3, 4, 5};
//        int window = 3;

        P.print(solve1(arr, window));
    }

    private static int[] solve1(int[] arr, int window) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        // 遍历数组元素，queue挨个吸纳之
        // 当i在0~window-1时，只吸纳，因为此时窗口内元素数量还未达到window
        // 当i>=window时，窗口内元素数量达到了window，先输出当前窗口的最大值（因为即将有元素要被滑出）
        // 吸纳新元素 & 吐出旧元素（吐的是i-window），吸吐顺序无所谓
        // 当i>=arr.len时，只吐出元素，不吸入
        int len = arr.length;
        // 记录第i个元素将要滑出窗口时，窗口内的最大值
        int[] result = new int[len];
        LinkedList<Integer> queue = new LinkedList<>();

        // 窗口右边缘从0开始滑动到n+window，当window <= r <= n+window只吐不吸
        for (int right = 0; right < len + window; right++) {
            /*
            // 只吸不吐
            if (right < window) {
                // 【2】吸
                while (!queue.isEmpty() && arr[right] > arr[queue.peekLast()]) {
                    queue.pollLast();
                }
                queue.addLast(right);
            }
            // 先打印，在吸&吐
            else if (right >= window && right < len) {
                // 最大元素：queue的第一个元素
                int indexOfLargest = queue.peekFirst();
                // 即将被滑出窗口的元素
                int indexOfReadyToInvalid = right - window;
                // 【1】记录当前窗口的最大值
                result[indexOfReadyToInvalid] = arr[indexOfLargest];
                // 【2】吸，把比right小的吐了，add right
                while (!queue.isEmpty() && arr[right] > arr[queue.peekLast()]) {
                    queue.pollLast();
                }
                queue.addLast(right);
                // 【3】吐，不用判空，queue至少有一个元素
                if (indexOfReadyToInvalid == queue.peekFirst()) {
                    queue.pollFirst();
                }
            }
            // 只吐不吸
            else {
                int indexOfLargest = queue.peekFirst();
                int indexOfReadyToInvalid = right - window;
                // 【1】记录
                result[indexOfReadyToInvalid] = arr[indexOfLargest];
                // 【3】吐
                if (indexOfReadyToInvalid == queue.peekFirst()) {
                    queue.pollFirst();
                }
            }
            */

            // 整合一下上面的过程
            // right>=window时，输入当前窗口最大值 & 吐
            if (right >= window) {
                // 最大元素：queue的第一个元素
                int indexOfLargest = queue.peekFirst();
                // 即将被滑出窗口的元素
                int indexOfReadyToInvalid = right - window;
                // 【1】记录当前窗口的最大值
                result[indexOfReadyToInvalid] = arr[indexOfLargest];

                // 【3】吐，不用判空，queue至少有一个元素
                if (indexOfReadyToInvalid == queue.peekFirst()) {
                    queue.pollFirst();
                }
            }

            // right<len时，吸
            if (right < len) {
                // 【2】吸，把比right小的吐了，add right
                while (!queue.isEmpty() && arr[right] > arr[queue.peekLast()]) {
                    queue.pollLast();
                }
                queue.addLast(right);
            }
        }

        return result;
    }
}
