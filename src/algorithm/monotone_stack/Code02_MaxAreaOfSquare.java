package algorithm.monotone_stack;

import algorithm.util.Checker;
import algorithm.util.P;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 正方形的最大面积
 */
public class Code02_MaxAreaOfSquare {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 2, 1, 4};
//        int[][] nsArr = getNsArr(arr);
//        P.print(nsArr);

        System.out.println(solve(arr));
    }

//    public static void check() {
//        int times = 100000;
//        int[] arr = Checker.generate(10, 10, true, true);
//
//        try {
//            while (times-- > 0) {
//                int r1 = violentSolve(arr);
//                int r2 = solve(arr);
//                if (r1 != r2) {
//                    throw new RuntimeException("r1!=r2");
//                }
//            }
//
//            System.out.println("成功!!!!");
//        } catch (Exception e) {
//            System.out.println("失败！！！！！");
//            P.print(arr);
//            e.printStackTrace();
//        }
//    }

    private static int solve(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 用单调栈获取左右近小元素
        // 对于arr[i]，计算以arr[i]为最大高度的矩形面积=arr[i]*(rNs-1 - (lNs+1))
        int[][] nsArr = getNsArr(arr);
        int len = arr.length;
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            int height = arr[i];
            int width = nsArr[i][1] - nsArr[i][0] - 1;
            int area = width * height;
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }


    private static int[][] getNsArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[][]{};
        }

        int len = arr.length;
        int[][] nsArr = new int[len][2];
        Stack<LinkedList<Integer>> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            // 如果栈顶比我大，要踢出
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().peekLast()]) {
                LinkedList<Integer> stackTop = stack.pop();
                int lNs = stack.isEmpty() ? -1 : stack.peek().peekLast();
                while (!stackTop.isEmpty()) {
                    nsArr[stackTop.pop()] = new int[]{lNs, i};
                }
            }

            // 如果栈顶和我相等，带我一个
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().peekLast()]) {
                stack.peek().addLast(i);
            }
            // 如果栈顶比我小
            else {
                LinkedList<Integer> list = new LinkedList<>();
                list.addFirst(i);
                stack.push(list);
            }
        }

        // 栈里最后剩下的
        while (!stack.isEmpty()) {
            LinkedList<Integer> stackTop = stack.pop();
            int lNs = stack.isEmpty() ? -1 : stack.peek().peekLast();
            while (!stackTop.isEmpty()) {
                nsArr[stackTop.pop()] = new int[]{lNs, len};
            }
        }

        return nsArr;
    }
}
