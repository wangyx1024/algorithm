package algorithm.recursion;


import java.util.HashMap;
import java.util.Map;

/**
 * 零钱
 * <p>
 * 给定数组arr，arr中所有的值都为正数且不重复
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张
 * 再给定一个整数 aim，代表要找的钱数
 * 求组成 aim 的方法数
 */
public class Code10_ChangeMoney {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 10, 20, 50, 100};
        int aim = 10000;

//        long tp0 = System.currentTimeMillis();
//        int r1 = new Code10_ChangeMoney().violentSolve(arr, aim);
//        long tp1 = System.currentTimeMillis();
//        System.out.println(r1 + "-" + (tp1 - tp0));
//
        long tp2 = System.currentTimeMillis();
        int r2 = new Code10_ChangeMoney().dpSillyCacheSolve1(arr, aim);
        long tp3 = System.currentTimeMillis();
        System.out.println(r2 + "-" + (tp3 - tp2));

        long tp4 = System.currentTimeMillis();
        int r3 = new Code10_ChangeMoney().dpSillyCacheSolve2(arr, aim);
        long tp5 = System.currentTimeMillis();
        System.out.println(r3 + "-" + (tp5 - tp4));

        long tp6 = System.currentTimeMillis();
        int r4 = new Code10_ChangeMoney().dpClassicSolve1(arr, aim);
        long tp7 = System.currentTimeMillis();
        System.out.println(r4 + "-" + (tp7 - tp6));

        long tp8 = System.currentTimeMillis();
        int r5 = new Code10_ChangeMoney().dpClassicSolve2(arr, aim);
        long tp9 = System.currentTimeMillis();
        System.out.println(r5 + "-" + (tp9 - tp8));
    }

    /**
     * 暴力递归
     */
    private int violentSolve(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        return violentProcess(arr, 0, aim);
    }

    private int violentProcess(int[] arr, int index, int aim) {
        // 如果要凑的金额竟然是负数了，当然是无效方案
        if (aim < 0) {
            return 0;
        }

        // 所有面值都过完了，需要凑的金额是0是有效方案，否则无效
        int len = arr.length;
        if (index == len) {
            if (aim == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        // 需要从index开始的面值凑aim元
        // index的面值可选张数为：0~使剩余aim>=的最大张数
        int plan = 0;
        for (int zhang = 0; arr[index] * zhang <= aim; zhang++) {
            plan += violentProcess(arr, index + 1, aim - arr[index] * zhang);
        }

        return plan;
    }

    /**
     * dp-傻缓存式-Map作缓存
     */
    private int dpSillyCacheSolve1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        Map<String, Integer> cache = new HashMap<>();
        int r = dpSillyCacheProcess1(arr, 0, aim, cache);
//        System.out.println(cache);
        return r;
    }

    private int dpSillyCacheProcess1(int[] arr, int index, int aim, Map<String, Integer> cache) {
        // 如果要凑的金额竟然是负数了，当然是无效方案
        if (aim < 0) {
            return 0;
        }

        String cacheKey = index + "-" + aim;
        Integer cacheVal = cache.get(cacheKey);
        if (cacheVal != null) {
            return cacheVal;
        }

        // 所有面值都过完了，需要凑的金额是0是有效方案，否则无效
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }

        // 需要从index开始的面值凑aim元
        // index的面值可选张数为：0~使剩余aim>=的最大张数
        int plan = 0;
        for (int zhang = 0; arr[index] * zhang <= aim; zhang++) {
            plan += dpSillyCacheProcess1(arr, index + 1, aim - arr[index] * zhang, cache);
        }

        cache.put(cacheKey, plan);
        return plan;
    }

    /**
     * dp-傻缓存式-数组做缓存（更接近经典动态规划的转移方程）
     */
    private int dpSillyCacheSolve2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        // 初始化成-1
        Integer[][] cache = new Integer[arr.length + 1][aim + 1];
        int r = dpSillyCacheProcess2(arr, 0, aim, cache);

//        for (int i = 0; i < arr.length + 1; i++) {
//            for (int j = 0; j < aim + 1; j++) {
//                System.out.print(i + "-" + j + "=" + cache[i][j] + ",");
//            }
//        }
//        System.out.println();

        return r;
    }

    private int dpSillyCacheProcess2(int[] arr, int index, int aim, Integer[][] cache) {
        // 如果要凑的金额竟然是负数了，当然是无效方案
        if (aim < 0) {
            return 0;
        }

        String cacheKey = index + "-" + aim;
        Integer cacheVal = cache[index][aim];
        if (cacheVal != null) {
            return cacheVal;
        }

        // 所有面值都过完了，需要凑的金额是0是有效方案，否则无效
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }

        // 需要从index开始的面值凑aim元
        // index的面值可选张数为：0~使剩余aim>=的最大张数
        int plan = 0;
        for (int zhang = 0; arr[index] * zhang <= aim; zhang++) {
            plan += dpSillyCacheProcess2(arr, index + 1, aim - arr[index] * zhang, cache);
        }

        cache[index][aim] = plan;
        return plan;
    }

    private int dpClassicSolve1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        return this.dpClassicProcess1(arr, 0, aim);
    }

    /**
     * dp-经典转移方程式-赋值行为中有枚举行为，可以继续优化
     */
    private int dpClassicProcess1(int[] arr, int index, int aim) {
        // 【1】定义转移方程
        // 行，index，当前面值下标，0~index，共index+1
        // 列，aim，剩余要凑的面值，0~aim，共aim+1
        // 值，在index、aim条件下，有多少种凑钱方案
        // 求，matrix[0][aim]
        int len = arr.length;
        int[][] matrix = new int[len + 1][aim + 1];

        // 【2】开始赋值，matrix[][]=?
        // 当index==len时，只有aim==0时值为1，其他都是0
        // matrix[len][1...aim]=0
        matrix[len][0] = 1;
        // 暴力递归中，子问题的index=index+1（从下往上推），子问题的aim不依赖于本行的数据所以左右方向无所谓
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int plan = 0;
                for (int zhang = 0; arr[i] * zhang <= j; zhang++) {
                    plan += matrix[i + 1][j - arr[i] * zhang];
                }
                matrix[i][j] = plan;
            }
        }

        return matrix[0][aim];
    }

    private int dpClassicSolve2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        return this.dpClassicProcess2(arr, 0, aim);
    }

    /**
     * dp-经典转移方程式-优化赋值行为中的枚举行为
     */
    private int dpClassicProcess2(int[] arr, int index, int aim) {
        // 【1】定义转移方程
        // 行，index，当前面值下标，0~index，共index+1
        // 列，aim，剩余要凑的面值，0~aim，共aim+1
        // 值，在index、aim条件下，有多少种凑钱方案
        // 求，matrix[0][aim]
        int len = arr.length;
        int[][] matrix = new int[len + 1][aim + 1];

        // 【2】开始赋值，matrix[][]=?
        // 当index==len时，只有aim==0时值为1，其他都是0
        // matrix[len][1...aim]=0
        matrix[len][0] = 1;
        // 暴力递归中，子问题的index=index+1（从下往上推），子问题的aim不依赖于本行的数据所以左右方向无所谓
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                // 从一个具体的位置分析，枚举行为是否可以优化
                // 比如matrix[6][100]=matrix[7][100-0*arr[i]]+matrix[7][100-1*arr[i]]+...+matrix[7][xx]（xx>=0）
                // 也就是说，每一格都等于前面之和，进而可以推出，本格=前一格（如果存在的话）+这一格下方

                int pre = j - arr[i] < 0 ? 0 : matrix[i][j - arr[i]];
                matrix[i][j] = pre + matrix[i + 1][j];
            }
        }

        return matrix[0][aim];
    }
}
