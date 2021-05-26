package algorithm.recursion;


/**
 * 汉诺塔
 */
public class Code01_HanoiTower {

    public static void main(String[] args) {
        process1(3);
    }

    private static void process1(int n) {
        if (n < 1) {
            return;
        }

        move2(n, "左", "右", "中");
    }

    /**
     * 定义：从上到下依次为1、2、3...N号圆盘
     * 把N层圆盘从from移动到to，拆解为3步
     * 1.把除最后一层外（1~N-1）从from挪到extra
     * 2.把最后一层（N）从from挪到to
     * 3.把中间的一坨（1~N-1）移到to
     */
    private static void move1(int n, String from, String to, String extra) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            move1(n - 1, from, extra, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            move1(n - 1, extra, to, from);
        }
    }

    private static void move2(int n, String from, String to, String extra) {
        if (n == 0) {
            return;
        } else {
            move2(n - 1, from, extra, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            move2(n - 1, extra, to, from);
        }
    }
}
