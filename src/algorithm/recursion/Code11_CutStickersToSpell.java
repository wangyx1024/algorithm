package algorithm.recursion;


import algorithm.util.P;

/**
 * 剪贴纸
 * <p>
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 */
public class Code11_CutStickersToSpell {

    public static void main(String[] args) {
        String target = "aaaaabbabababcabcabcacabbabccaaca";
        String[] stickers = {"cca", "abbbbc", "aaaa", "abbaaa"};
        System.out.println(violentSolveTry1(stickers, target));
        System.out.println(violentSolveTry2(stickers, target));
    }

    private static int violentSolveTry1(String[] stickers, String target) {
        // 比如target = "aabbbc"，stickers = ["abc", "aaaaa", "ccbb"]
        // 尝试方法：从第0~len-1张遍历，每种贴纸取0~x张
        int[][] stickersArr = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] stickerInChar = stickers[i].toCharArray();
            for (int j = 0; j < stickerInChar.length; j++) {
                stickersArr[i][stickerInChar[j] - 'a']++;
            }
        }

//        P.print(stickersArr);

        int[] targetArr = new int[26];
        char[] targetInChar = target.toCharArray();
        for (int i = 0; i < target.length(); i++) {
            targetArr[targetInChar[i] - 'a']++;
        }

//        P.print(targetArr);
        return violentProcessTry1(stickersArr, targetArr, 0);
    }

    /**
     * 第一种尝试
     * 遍历贴纸，对于第i张贴纸，使用0~x张，取消耗贴纸最少的
     * x怎么确定？默认x=0，对于某字符比如a，target需要10个，且sticker有3个，x=ceil(10/3)=4
     * 特殊情况，如果当前sticker没有一个target需要的字符，x保持为0不更新
     */
    private static int violentProcessTry1(int[][] stickers, int[] target, int index) {
        int len = stickers.length;
        // 如果已经没有贴纸了，且target不为空字符串，说明本方案无效，否则有效
        if (index == len) {
            int not0Index = getNot0Index(target);
            return not0Index == -1 ? 0 : -1;
        }

        int[] sticker = stickers[index];

        // 如果还有贴纸，尝试本贴纸使用0~x张。要看我缺啥，你有啥
        // 如果我缺3个a，你一个a都没有，就不用试了，直接0张
        // 看我我缺&你有的字母种，缺的最大数量
        int zhangMax = 0;
        for (int i = 0; i < 26; i++) {
            int iNeed = target[i];
            int uHave = sticker[i];
            if (iNeed > 0 && uHave > 0) {
                int zhangNew = (int) Math.ceil(iNeed * 1.0D / uHave);
                zhangMax = Math.max(zhangMax, zhangNew);
            }
        }

        // 第index张牌使用从0~zhangMax张，哪种方案使用的牌少，用哪个方案
        int need = -1;
        for (int zhang = 0; zhang <= zhangMax; zhang++) {
            // 当前sticker用i张
            int[] nextTarget = getNewTargetAfterUseStickers(target, sticker, zhang);
            int nextNeed = violentProcessTry1(stickers, nextTarget, index + 1);
            if (nextNeed != -1) {
                int totalNeed = zhang + nextNeed;
                need = need == -1 ? totalNeed : Math.min(need, totalNeed);
            }
        }

        return need;
    }

    private static int violentSolveTry2(String[] stickers, String target) {
        int[][] stickersArr = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] stickerInChar = stickers[i].toCharArray();
            for (int j = 0; j < stickerInChar.length; j++) {
                stickersArr[i][stickerInChar[j] - 'a']++;
            }
        }

//        P.print(stickersArr);

        int[] targetArr = new int[26];
        char[] targetInChar = target.toCharArray();
        for (int i = 0; i < target.length(); i++) {
            targetArr[targetInChar[i] - 'a']++;
        }

//        P.print(targetArr);
        return violentProcessTry2(stickersArr, targetArr);
    }

    /**
     * 第二种尝试
     * 遍历x次，每次从0~n-1张贴纸中选一张，target-sticker=newTarget，拿newTarget进行下一轮尝试
     */
    private static int violentProcessTry2(int[][] stickers, int[] target) {
        int not0Index = getNot0Index(target);
        if (not0Index == -1) {
            return 0;
        }

        int len = stickers.length;
        int result = -1;
        for (int i = 0; i < len; i++) {
            int[] sticker = stickers[i];
            // 判断当前sticker，是否包含target所需字符
            if (sticker[not0Index] == 0) {
                continue;
            }

            int[] newTarget = getNewTargetAfterUseStickers(target, sticker, 1);
            int next = violentProcessTry2(stickers, newTarget);
            if (next == -1) {
                continue;
            } else {
                next = next + 1;
                result = result == -1 ? next : Math.min(result, next);
            }
        }

        return result;
    }

    private static int getNot0Index(int[] target) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] > 0) {
                return i;
            }
        }

        return -1;
    }

    private static int[] getNewTargetAfterUseStickers(int[] target, int[] sticker, int zhang) {
        int[] ret = new int[26];
        for (int i = 0; i < 26; i++) {
            ret[i] = Math.max(target[i] - sticker[i] * zhang, 0);
        }
        return ret;
    }
}
