package algorithm.recursion;


import algorithm.util.U;

import java.util.ArrayList;
import java.util.List;

/**
 * 背包问题
 * <p>
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code05_KnapsackProblem {

    public static void main(String[] args) {
        int[] weights = new int[]{10, 10, 10, 15};
        int[] values = new int[]{10, 20, 30, 60};
        int index = 0;
        int rest = 20;

        int result1 = violentSolve(rest, weights, values);
        System.out.println("result1 = " + result1);

        int result2 = violentProcess2(weights, values, index, rest);
        System.out.println("result2 = " + result2);

        int result3 = violentProcess2(weights, values, index, rest);
        System.out.println("result3 = " + result3);

        int result4 = dpProcess(weights, values, index, rest);
        System.out.println("result4 = " + result4);
    }

    private static int violentSolve(int bagLimit, int[] weights, int[] values) {
        List<List<Integer>> plans = new ArrayList<>();
        violentProcess(bagLimit, weights, values, 0, 0, new ArrayList<>(), plans);
        System.out.println(plans);

        int bestValue = 0;
        List<Integer> bestPlan = null;
        for (List<Integer> plan : plans) {
            int planValue = 0;
            for (int i = 0; i < plan.size(); i++) {
                planValue += values[plan.get(i)];
            }

            if (planValue > bestValue) {
                bestValue = planValue;
                bestPlan = plan;
            }
        }

        return bestValue;
    }

    /**
     * 暴力解，枚举每种装法，最后再多循环一次计算每种装法的货物价值
     */
    private static void violentProcess(int bagLimit, int[] weights, int[] values, int from, int bagWeight, List<Integer> bag, List<List<Integer>> plans) {
        if (from == weights.length || bagWeight >= bagLimit) {
            plans.add(bag);
            return;
        } else {
            List<Integer> copiedBag1 = U.copy(bag);
            violentProcess(bagLimit, weights, values, from + 1, bagWeight, copiedBag1, plans);

            if (bagWeight + weights[from] <= bagLimit) {
                List<Integer> copiedBag2 = U.copy(bag);
                copiedBag2.add(from);
                bagWeight += weights[from];
                violentProcess(bagLimit, weights, values, from + 1, bagWeight, copiedBag2, plans);
            }
        }
    }

    /**
     * 暴力递归的优化：只求最大价值；用rest代替bagLimit-bagWeight
     */
    private static int violentProcess2(int[] weights, int[] values, int index, int rest) {
        // 如果背包剩余空间是负数了，当然说明这种方案行不通，返回0
        // 删掉，在递归中调用的时候已经判断过newRest必须>0了
//        if (rest < 0) {
//            return 0;
//        }

        // 否则说明背包还有空间
        // index超过了说明已经没有货物可以装了
        if (index == weights.length) {
            return 0;
        }

        // 来到第i件货物，有两种选择：
        // 1.不装，价值=剩余背包容量不变的后续
        int r1 = violentProcess2(weights, values, index + 1, rest);
        // 2.装，价值=i货物的价值+剩余背包容量减少后的后续，实际有可能装不下
        // 装不下当然说明方案无效
        int newRest = rest - weights[index];
        int r2 = newRest < 0 ? 0 : values[index] + violentProcess2(weights, values, index + 1, newRest);

        // 这两种方案取最大值
        return Math.max(r1, r2);
    }

    private static int dpProcess(int[] w, int[] v, int index, int rest) {
        // 横坐标，表index，值取0~len，共len+1个
        // 纵坐标，表rest，值取0~rest，共rest+1个
        int len = w.length;
        int[][] matrix = new int[len + 1][rest + 1];

        // 赋值，matrix[index][rest] = ?
        // int[len][...] = 0已经默认赋值
        // 剩下的观察暴力递归的赋值规则：1.index总是参考index+1，所以从下往上赋值；2.newRest总是比rest小，所以从右往左赋值
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= rest; j++) {
                int r1 = matrix[i + 1][j];
                int newRest = j - w[i];
                int r2 = newRest < 0 ? 0 : v[i] + matrix[i + 1][newRest];
                matrix[i][j] = Math.max(r1, r2);
            }
        }

        return matrix[index][rest];
    }
}
