package tree;

/**
 * 折纸后的凹凸规律
 */
public class Code08_PrintFoldedPaperShape {

    public static void main(String[] args) {
        print(4);
    }

    private static void print(int n) {
        if (n <= 0) {
            return;
        }

        process(1, n, "1⬇️️");
    }

    private static void process(int lvl, int depth, String str) {
        if (lvl > depth) {
            return;
        }

        process(lvl + 1, depth, depth + "⬇️ ");
        System.out.print(str + " ");
        process(lvl + 1, depth, depth + "⬆️ ");
    }
}
