package algorithm.monotone_stack;

import algorithm.util.Checker;
import algorithm.util.P;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 对于arr的某个子数组sub，可以求得sub元素的和*最小值，求其中最大的
 * arr都是正整数
 * 如果有负数怎么办
 */
public class Code01_MaxValueOfSumPlusMinOfSubArray {

    public static void main(String[] args) {
//        int[] arr = {3, 8, 9, 6, 5, 4};
//        int[] arr = {1, 1, 2, 2, 3, 3};
//        int[] arr = {1, 2, 3, 2};
//        System.out.println(violentSolve(arr));
//        P.divider();
//        System.out.println(solve(arr));

//        P.print(getNoRepeatElementNsArr(arr));
//        P.divider();
//        P.print(getNsArr(arr));


        check();
    }

    public static void check() {
        int times = 100000;
        int[] arr = Checker.generate(10, 10, true, true, true);

        try {
            while (times-- > 0) {
                int r1 = violentSolve(arr);
                int r2 = solve(arr);
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

    private static int violentSolve(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int valMax = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                int min = Integer.MAX_VALUE;
                int index = i;
                while (index <= j) {
                    sum += arr[index];
                    min = Math.min(min, arr[index]);
                    index++;
                }

                int val = min * sum;
//                System.out.println(i + "~" + j + "," + min + "," + sum + "," + val);
                if (val > valMax) {
                    valMax = val;
                }
            }
        }

        return valMax;
    }

    private static int solve(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 对于sub，val = sum(sub) * min(sub)，在min(sub)不变的情况下，sub越长元素越多，val越大
        // 先用单调栈整出来，对于每一个数组元素，它左、右比它小且离他最近的元素分别是什么
        // 如果左近小==-1，表示左边没有比它小的了，所以它左边的势力范围到0，如果右近小==-1，说明右边没有比它小的了，所以右边势力范围到len-1
        // 比如对于i，左、右近小元素下标分别是l、r，那么l+1和r-1就是以下标i为最小值的范围，那么val = sum(arr[l+1, r-1]) * arr[i]，就是以i为最小值的子数组的最大val
        int[][] nsArr = getNsArr(arr);
//        P.print(nsArr);

        int valMax = 0;
        int len = nsArr.length;
        for (int i = 0; i < len; i++) {
            int lRange = nsArr[i][0] == -1 ? 0 : nsArr[i][0] + 1;
            int rRange = nsArr[i][1] == -1 ? len - 1 : nsArr[i][1] - 1;
            int sum = 0;
            for (int j = lRange; j <= rRange; j++) {
                sum += arr[j];
            }

            int val = arr[i] * sum;
//            System.out.println(lRange + "~" + rRange + "," + arr[i] + "," + sum + "," + val);
            valMax = Math.max(val, valMax);
        }

        return valMax;
    }

    /**
     * [i][0]、[i][1]分别是下标i元素的左右近小
     */
    private static int[][] getNoRepeatElementNsArr(int[] arr) {
        int len = arr.length;
        int[][] nsArr = new int[len][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            // 栈不为空且我比栈顶元素小
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                // 当前栈顶，要生成ns数据的元素
                int stackTop = stack.pop();
                // 右侧近小，让它出栈的元素
                int rightNs = i;
                // 左侧近小，它下面的元素，如果栈空就是-1
                int leftNs = stack.isEmpty() ? -1 : stack.peek();
                nsArr[stackTop] = new int[]{leftNs, rightNs};
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int stackTop = stack.pop();
            int leftNs = stack.isEmpty() ? -1 : stack.peek();
            nsArr[stackTop] = new int[]{leftNs, -1};
        }

        return nsArr;
    }

    private static int[][] getNsArr(int[] arr) {
        int len = arr.length;
        int[][] nsArr = new int[len][2];
        Stack<LinkedList<Integer>> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            // 栈不为空且我比栈顶元素小
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().peekFirst()]) {
                // 当前栈顶，要生成ns数据的元素
                LinkedList<Integer> stackTop = stack.pop();
                // 右侧近小，让它出栈的元素
                int rightNs = i;
                // 左侧近小，它下面的元素，如果栈空就是-1
                int leftNs = stack.isEmpty() ? -1 : stack.peek().peekLast();
                while (!stackTop.isEmpty()) {
                    nsArr[stackTop.pop()] = new int[]{leftNs, rightNs};
                }
            }

            if (!stack.isEmpty() && arr[i] == arr[stack.peek().peekFirst()]) {
                stack.peek().addLast(i);
            } else {
                LinkedList newStackTop = new LinkedList();
                newStackTop.add(i);
                stack.push(newStackTop);
            }
        }

        while (!stack.isEmpty()) {
            LinkedList<Integer> stackTop = stack.pop();
            int leftNs = stack.isEmpty() ? -1 : stack.peek().peekLast();
            while (!stackTop.isEmpty()) {
                nsArr[stackTop.pop()] = new int[]{leftNs, -1};
            }
        }

        return nsArr;
    }
}
