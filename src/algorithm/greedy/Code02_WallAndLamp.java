package algorithm.greedy;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 墙和路灯问题
 */
public class Code02_WallAndLamp {

    public static void main(String[] args) {
//        String street = ".X....XX.X.";
//        String street = buildStreet(10);
//        System.out.println(street);
//        int leastLamp1 = greedySolve(street);
//        System.out.println("leastLamp1 = " + leastLamp1);
//        int leastLamp2 = violentSolve(street);
//        System.out.println("leastLamp2 = " + leastLamp2);

        check(50000, 10);
    }

    private static char[] BLOCKS = {'.', 'X', '.'};

    private static String buildStreet(int len) {
        char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            arr[i] = BLOCKS[new Random().nextInt(BLOCKS.length)];
        }

        return new String(arr);
    }

    public static void check(int times, int len) {
        String street = null;
        try {
            while (times-- > 0) {
                street = buildStreet(len);
//                System.out.println(street);
                int leastLamp1 = greedySolve(street);
                int leastLamp2 = violentSolve(street);

                if (leastLamp1 != leastLamp2) {
                    throw new RuntimeException("fucking fucked!!!!!!!");
                }
            }

            System.out.println("成功!!!!");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(street);
        }
    }

    public static int violentSolve(String street) {
        if (street == null) {
            return 0;
        }

        char[] arr = street.toCharArray();
        List<Integer> plan0 = new ArrayList<>();
        List<List<Integer>> plans = new ArrayList<>();
        collectPlans(arr, 0, plan0, plans);

//        System.out.println("size==" + plans.size());
//        for (List<Integer> plan : plans) {
//            System.out.println(plan);
//        }

//        P.divider();

        List<List<Integer>> filteredPlans = filterPlans(arr, plans);
//        System.out.println("size==" + filteredPlans.size());
//        for (List<Integer> plan : filteredPlans) {
//            System.out.println(plan);
//        }

//        P.divider();

        int leastLamp = getLeastLamp(filteredPlans);
//        System.out.println("leastLamp==" + leastLamp);

        return leastLamp;
    }

    private static int getLeastLamp(List<List<Integer>> plans) {
        int leastLamp = Integer.MAX_VALUE;
        for (List<Integer> plan : plans) {
            int lamp = 0;
            for (Integer i : plan) {
                lamp += i;
            }

            leastLamp = Math.min(leastLamp, lamp);
        }

        return leastLamp;
    }

    private static List<List<Integer>> filterPlans(char[] arr, List<List<Integer>> plans) {
        List<List<Integer>> filteredPlans = new ArrayList<>();
        for (List<Integer> plan : plans) {
            boolean good = true;
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                if (arr[i] == '.') {
                    int pre = Math.max(i - 1, 0);
                    int next = Math.min(i + 1, len - 1);
                    if (plan.get(pre) == 0 && plan.get(i) == 0 && plan.get(next) == 0) {
                        good = false;
                        break;
                    }
                }
            }

            if (good) {
                filteredPlans.add(plan);
            }
        }

        return filteredPlans;
    }

    private static void collectPlans(char[] arr, int from, List<Integer> plan, List<List<Integer>> plans) {
        int len = arr.length;
        if (from == arr.length) {
            plans.add(plan);
            return;
        }

        List<Integer> branch1Plan = copy(plan);
        branch1Plan.add(0);
        collectPlans(arr, from + 1, branch1Plan, plans);

        if (arr[from] == '.') {
            List<Integer> branch2Plan = copy(plan);
            branch2Plan.add(1);
            collectPlans(arr, from + 1, branch2Plan, plans);
        }
    }

    private static List<Integer> copy(List<Integer> list) {
        List<Integer> copyList = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            copyList.add(list.get(i));
        }

        return copyList;
    }

    public static int greedySolve(String street) {
        if (street == null) {
            return 0;
        }

        char[] arr = street.toCharArray();

        int i = 0;
        int num = 0;
        int len = arr.length;
        while (i < len) {
            if (arr[i] == 'X') {
                i++;
            } else if (arr[i] == '.') {
                if (i + 1 >= len) {
                    num++;
                    i++;
                } else {
                    if (arr[i + 1] == 'X') {
                        num++;
                        i = i + 2;
                    } else {
                        num++;
                        i = i + 3;
                    }
                }
            }
        }

        return num;
    }
}
