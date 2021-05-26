package algorithm.recursion;

/**
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 返回让所有咖啡杯变干净的最早完成时间
 * 三个参数：int[] arr、int a、int b
 */
public class Code13_WashCoffeeCups {

    public static void main(String[] args) {
        // 和咖啡的时间点，喝咖啡不需要时间
//        int[] drinks = {1, 6, 12};
        int[] drinks = {0, 0, 0, 12};
        // 洗杯子耗时
        int washTime = 3;
        // 自然挥发耗时
        int dryTime = 10;

        System.out.println(violentSolve(drinks, washTime, dryTime));
    }

    private static int violentSolve(int[] drinks, int washTime, int dryTime) {
        return violentProcess(drinks, washTime, dryTime, 0, 0);
    }

    /**
     * 尝试方法：第index杯，洗or自然挥发
     * 一个杯子可以选择洗或自然挥发
     * 自然挥发不占用洗碗机，洗占用洗碗机
     * <p>
     * 结束时间
     * 最后一个人喝完时，洗碗机空闲的：最后一个人喝完的时间 + 洗最后一个杯子的时间
     * 最后一个人喝完时，洗碗机没闲着：
     * 1.最后一杯选择洗：洗完流水线再加一杯，结束时间=washLine + washTime
     * 2.最后一杯选择晾干：喝完后风干，结束时间=喝完的时间 + dryTime
     */
    private static int violentProcess(int[] drinks, int washTime, int dryTime, int index, int washMachineFree) {
        // 最后一杯的抉择
        if (index == drinks.length - 1) {
            // 洗碗机闲着
            if (drinks[index] >= washMachineFree) {
                return drinks[index] + washTime;
            }
            // 洗碗机没闲着
            else {
                return Math.min(
                        // 让洗碗机洗
                        washMachineFree + washTime,
                        // 喝完晾干
                        drinks[index] + dryTime);
            }
        }

        // 第index杯，选择洗
        int p1 = violentProcess(drinks, washTime, dryTime, index + 1, washMachineFree + washTime);
        // 第index杯，选择晾干
        int p2 = violentProcess(drinks, washTime, dryTime, index + 1, washMachineFree) + dryTime;

        return Math.min(p1, p2);
    }

    private static int dpProcess(int[] drinks, int washTime, int dryTime) {
        int len = drinks.length;
        int[][] matrix = new int[len][];

        return 0;
    }
}
