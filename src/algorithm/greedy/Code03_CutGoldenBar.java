package algorithm.greedy;


import algorithm.util.Checker;

import java.util.*;

/**
 * 切金条问题
 */
public class Code03_CutGoldenBar {

    public static void main(String[] args) {
//        int[] arr = {2};
//        int[] plan1 = greedySolve(arr);
//        int cost1 = calCost(plan1);
//        System.out.println("cost1 = " + cost1);
//
//        int[] plan2 = violentSolve(arr);
//        int cost2 = calCost(plan2);
//        System.out.println("cost2 = " + cost2);


        check(100000, 5, 10);
    }

    public static void check(int times, int maxSection, int sectionMaxVal) {
        int[] arr = null;
        try {
            while (times-- > 0) {
                arr = buildGoldBar(maxSection, sectionMaxVal);
                int[] plan1 = greedySolve(arr);
                int[] plan2 = violentSolve(arr);

//                P.print(arr);
//                P.print(plan1);
//                P.print(plan2);

                int cost1 = calCost(plan1);
                int cost2 = calCost(plan2);

                if (cost1 != cost2) {
                    throw new RuntimeException("fucking fucked!!!!!!!");
                }
            }

            System.out.println("成功!!!!");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(arr);
        }
    }

    private static int[] buildGoldBar(int maxSection, int sectionMaxVal) {
        int len = Checker.generateRandomPositiveNumNoMoreThan(maxSection);
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Checker.generateRandomPositiveNumNoMoreThan(sectionMaxVal) + 1;
        }

        return arr;
    }


    private static class MinComparator implements Comparator<HeapNode> {

        @Override
        public int compare(HeapNode o1, HeapNode o2) {
            return o1.gold - o2.gold;
        }
    }

    private static int[] violentSolve(int[] arr) {
        if (arr == null) {
            return new int[0];
        }

        List<Integer> plan = new ArrayList<>(arr.length);
        List<List<Integer>> plans = new ArrayList<>();
        violentProcess(arr, plan, plans);
//        System.out.println(plans);

        int lowerCost = Integer.MAX_VALUE;
        int[] bestPlan = new int[0];
        for (List<Integer> p : plans) {
            int[] planArr = getArrFromList(p);
            int cost = calCost(planArr);

            if (cost < lowerCost) {
                lowerCost = cost;
                bestPlan = planArr;
            }
        }

        return bestPlan;
    }

    private static void violentProcess(int[] arr, List<Integer> plan, List<List<Integer>> plans) {
        if (plan.size() == arr.length) {
            List<Integer> realPlan = new ArrayList<>(plan.size());
            for (Integer index : plan) {
                realPlan.add(arr[index]);
            }

            plans.add(realPlan);
        } else {
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                if (plan.contains(i)) {
                    continue;
                }

                List<Integer> copiedPlan = copy(plan);
                copiedPlan.add(i);
                violentProcess(arr, copiedPlan, plans);
            }
        }
    }

    private static List<Integer> copy(List<Integer> list) {
        List<Integer> ret = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            ret.add(list.get(i));
        }

        return ret;
    }

    private static int[] getArrFromList(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    public static class HeapNode {
        public int gold;
        public boolean join;

        public HeapNode(int gold, boolean join) {
            this.gold = gold;
            this.join = join;
        }
    }

    private static int calCost(int[] arr) {
        int cost = 0;
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i; j < len; j++) {
                cost += arr[j];
            }
        }
        return cost;
    }

    private static int[] greedySolve(int[] arr) {
        if (arr == null) {
            return new int[]{};
        }

        PriorityQueue<HeapNode> heap = new PriorityQueue(new MinComparator());
        for (int i : arr) {
            heap.add(new HeapNode(i, false));
        }

        // 需要切几刀
        int index = 0;
        int length = arr.length;
        int[] plan = new int[length];
        while (heap.size() > 1) {
            HeapNode n1 = heap.poll();
            HeapNode n2 = heap.poll();
            int cost = n1.gold + n2.gold;
            heap.add(new HeapNode(cost, true));

            if (!n1.join) {
                plan[length - index++ - 1] = n1.gold;
            }

            if (!n2.join) {
                plan[length - index++ - 1] = n2.gold;
            }
        }

        return plan;
    }
}
