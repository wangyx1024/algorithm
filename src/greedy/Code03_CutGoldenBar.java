package greedy;


import java.util.*;

/**
 * 切金条问题
 */
public class Code03_CutGoldenBar {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        int cost = greedySolve(arr);
        System.out.println("cost = " + cost);
    }

    private static class MinComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    // todo
    private static int violentSolve(int[] arr) {
        return 0;
    }

    private static int greedySolve(int[] arr) {
        if (arr == null) {
            return 0;
        }

        PriorityQueue<Integer> queue = new PriorityQueue(new MinComparator());
        for (int i : arr) {
            queue.add(i);
        }

        int cost = 0;
        while (queue.size() > 1) {
            int c1 = queue.poll();
            int c2 = queue.poll();
            int c = c1 + c2;
            queue.add(c);
            cost += c;
        }

        return cost;
    }
}
