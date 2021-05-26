package algorithm.recursion;


/**
 * 扑克抽牌问题
 */
public class Code06_PokerPicking {

    public static void main(String[] args) {

    }

    private static int solve(int[] arr) {
        process(arr, 0, arr.length - 1, 1, 0, 0);
        return 0;
    }

    private static void process(int[] arr, int left, int right, int step, int first, int second) {
        if (left > right) {

        } else {
            int leftValue = arr[left];
            int rightValue = arr[right];

            // 先手的人的回合
            if (step % 2 == 1) {
                process(arr, left + 1, right, step + 1, first + leftValue, second);
                process(arr, left, right - 1, step + 1, first + rightValue, second);
            } else {
                process(arr, left + 1, right, step + 1, first, second + leftValue);
                process(arr, left, right - 1, step + 1, first, second + rightValue);
            }
        }
    }
}
