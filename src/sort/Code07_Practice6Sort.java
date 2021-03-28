package sort;

import util.Checker;
import util.P;
import util.U;

import java.util.Arrays;
import java.util.Random;

public class Code07_Practice6Sort {

    public static void main(String[] args) {
        int[] arr = {6, 3, 5, 7, 8, 1};
        P.print(arr);

        choiceSort(arr);
        P.print(arr);

        P.divider();
        check(100000, 100, 100);
    }

    private static void check(int times, int maxSize, int maxValue) {
        boolean success = true;
        while (times-- > 0) {
            int[] arr = Checker.generate(maxSize, maxValue);
            int[] arr0 = Checker.copy(arr);
            int[] arr1 = Checker.copy(arr);
            int[] arr2 = Checker.copy(arr);
            int[] arr3 = Checker.copy(arr);
            int[] arr4 = Checker.copy(arr);
            int[] arr5 = Checker.copy(arr);
            int[] arr6 = Checker.copy(arr);

            try {
                Arrays.sort(arr0);
                choiceSort(arr1);
                bubbleSort(arr2);
                insertSort(arr3);
                fastSort(arr4);
                mergeSort(arr5);
                heapSort(arr6);

                Checker.compare(arr0, arr1, arr2, arr3, arr4, arr5, arr6);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                P.print(arr);
                P.print(arr0);
                P.print(arr1);
                P.print(arr2);
                P.print(arr3);
                P.print(arr4);
                P.print(arr5);
                P.print(arr6);
                success = false;
                break;
            }
        }

        if (success) {
            System.out.println("成功！！！！！");
        }
    }

    /**
     * 【1】选择排序
     * 【一句话】选出最大和头换
     * <p>
     * 第1轮，在0~9共中选出最小的，和下标0交换
     * 第2轮，在1~9共中选出最小的，和下标1交换
     * ...
     * 第9轮，在8~9共中选出最小的，和下标8交换
     * 第i轮，在i~n-1中选出最小的，和下标i交换
     */
    private static void choiceSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i <= len - 2; i++) {
            int min = i;
            for (int j = i; j <= len - 1; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (min != i) {
                U.swap(arr, i, min);
            }
        }
    }

    /**
     * 【2】冒泡排序
     * 【一句话】两两交换，大的沉底
     */
    private static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = len - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (arr[j] > arr[j + 1]) {
                    U.swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 【3】插入排序
     * 【一句话】往前打关，小的交换
     */
    private static void insertSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j >= 1; j--) {
                if (arr[j] < arr[j - 1]) {
                    U.swap(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * 【4】快排
     * partition+netherlandsFlag
     * process(arr,0,len-1)，process中先partition找到more、less，再递归调用process
     */
    private static void fastSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        fastSortProcess(arr, 0, arr.length - 1);
    }

    /**
     * 快排分区，返回小于、大于区指针
     */
    private static int[] fastSortPartition(int[] arr, int l, int r) {
        // 随机分区元素才能达到O(nlogn)
        int divider = arr[new Random().nextInt(r - l + 1) + l];

        int i = l;
        int less = l - 1;
        int more = r + 1;
        while (i < more) {
            if (arr[i] == divider) {
                i++;
            } else if (arr[i] < divider) {
                U.swap(arr, ++less, i++);
            } else {
                U.swap(arr, --more, i);
            }
        }

        return new int[]{less, more};
    }

    /**
     * 快排递归实现
     */
    private static void fastSortProcess(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int[] partitionResult = fastSortPartition(arr, l, r);
        int less = partitionResult[0];
        int more = partitionResult[1];

        fastSortProcess(arr, l, less);
        fastSortProcess(arr, more, r);
    }

    /**
     * 【5】归并排序
     * 【一句话】拆成稀巴烂+左右copy大法
     */
    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        mergeSortProcess(arr, 0, arr.length - 1);
    }

    /**
     * 归并递归实现
     */
    private static void mergeSortProcess(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        int mid = l + ((r - l) >> 1);
        mergeSortProcess(arr, l, mid);
        mergeSortProcess(arr, mid + 1, r);
        // merge放在递归调用后面，会先递归到底，先把数组拆成最碎的部分（2个元素），merge以后return到递归上一层再merge
        mergeSortMerge(arr, l, mid, r);
    }

    /**
     * 归并的merge操作
     */
    private static void mergeSortMerge(int[] arr, int l, int m, int r) {
        int lIndex = l;
        int rIndex = m + 1;

        int i = 0;
        int len = r - l + 1;
        int[] sorted = new int[len];

        // 谁小先copy谁
        while (lIndex <= m && rIndex <= r) {
            sorted[i++] = arr[lIndex] <= arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }

        // 处理剩下没copy完的，这俩while只会走一个
        while (lIndex <= m) {
            sorted[i++] = arr[lIndex++];
        }
        while (rIndex <= r) {
            sorted[i++] = arr[rIndex++];
        }

        // copy回原数组
        i = 0;
        while (i < len) {
            arr[l + i] = sorted[i++];
        }
    }

    /**
     * 【6】堆排序
     * 【一句话】建堆（从最后一个非叶子节点开始heapify到顶）+ 排序（从堆尾开始和root交换后heapify root）
     */
    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int len = arr.length;
        int lastNonLeafIndex = (len - 2) / 2;

        // 建堆：从最后一个非叶子节点开始heapify
        while (lastNonLeafIndex >= 0) {
            heapify(arr, lastNonLeafIndex--, len);
        }

//        System.out.println("堆：");
//        P.print(arr);

        // 排序：从根开始，和未有序的最后一个元素交换后heapify
        int heapTailIndex = len - 1;
        while (heapTailIndex >= 0) {
            U.swap(arr, heapTailIndex, 0);
            heapify(arr, 0, heapTailIndex--);
        }
    }

    private static void heapify(int[] arr, int index, int len) {
        if (index >= len || index < 0) {
            return;
        }

        int currentIndex = index;
        while (true) {
            // 没孩子
            int lChildIndex = (currentIndex * 2) + 1;
            if (lChildIndex >= len) {
                break;
            }

            // 比孩子大
            int rChildIndex = lChildIndex + 1;
            int largerChildIndex = rChildIndex < len && arr[rChildIndex] >= arr[lChildIndex] ? rChildIndex : lChildIndex;
            if (arr[currentIndex] >= arr[largerChildIndex]) {
                break;
            }

            // 否则和孩子交换，并且视角来到大孩子
            U.swap(arr, currentIndex, largerChildIndex);
            currentIndex = largerChildIndex;
        }
    }
}
