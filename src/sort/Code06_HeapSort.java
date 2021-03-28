package sort;

import util.Checker;
import util.P;
import util.U;

import java.util.Arrays;

import static sort.Code04_FastSort.fastSort;

public class Code06_HeapSort {

    public static void main(String[] args) throws Exception {
        int[] arr = {4, 6, 1, 9};
        P.print(arr);

        MyHeap myHeap = new MyHeap(100);
        for (int val : arr) {
            myHeap.push(val);
            P.print(myHeap.heap, myHeap.size);
        }

        P.divider();

        System.out.println(myHeap.pop());
        System.out.println(myHeap.pop());
        System.out.println(myHeap.pop());
        System.out.println(myHeap.pop());

    }

    public static void check() {
        int times = 100000;
        while (times-- > 0) {
            int[] arr = Checker.generate(100, 100);
            int[] arr1 = Checker.copy(arr);
            int[] arr2 = Checker.copy(arr);

            try {
                fastSort(arr1);
                Arrays.sort(arr2);
//                System.out.println("arr1==" + Arrays.toString(arr1));
//                System.out.println("arr2==" + Arrays.toString(arr2));
//                P.divider();

                Checker.compare(arr1, arr2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                P.print(arr);
                P.print(arr1);
                P.print(arr2);
                break;
            }
        }

        System.out.println("成功!!!!");
    }

    public static class MyHeap {
        private int size;
        private int limit;
        private int[] heap;

        public MyHeap() {

        }

        public MyHeap(int limit) {
            this.size = 0;
            this.limit = limit;
            this.heap = new int[limit];
        }

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public boolean isFull() {
            return this.size == this.limit;
        }

        public void push(int value) throws Exception {
            if (this.isFull()) {
                throw new Exception("堆满了！！！");
            }

            this.heapInsert(value);
        }

        public int pop() throws Exception {
            if (this.isEmpty()) {
                throw new Exception("堆空了！！！");
            }

            // 堆头，大根堆中是当前堆中最大的数
            int head = this.heap[0];
            if (this.size == 1) {
                return this.heap[--this.size];
            }
//            P.print(this.heap);
            // 拿堆尾和root换
            U.swap(this.heap, 0, --this.size);
//            P.print(this.heap);
            // heapify使重新变成大根堆
            this.heapify(0);
//            P.print(this.heap);

            return head;
        }

        private void heapInsert(int value) {
            this.heap[this.size++] = value;

            int currentIndex = this.size - 1;
            while (true) {
                // 自己是root没有爹，其他情况都有爹
                if (currentIndex == 0) {
                    break;
                }

                // 比爹小不管
                int parentIndex = (currentIndex - 1) / 2;
                if (this.heap[currentIndex] < this.heap[parentIndex]) {
                    break;
                }

                // 比爹大，和爹换，视角来到爹
                U.swap(this.heap, currentIndex, parentIndex);
                currentIndex = parentIndex;
            }
        }

        private void heapify(int index) throws Exception {
            if (index < 0 || index >= this.size) {
                throw new Exception("无效heapify位置！！！");
            }

            int currentIndex = index;
            while (true) {
                // 左孩子下标
                int lChildIndex = currentIndex * 2 + 1;
                if (lChildIndex >= this.size) {
                    break;
                }

                // 右孩子下标
                int rChildIndex = lChildIndex + 1;
                // 大孩子，右孩子存在且右孩子大于左孩子时右孩子是大孩子
                int largerChildIndex = rChildIndex < this.size && this.heap[rChildIndex] > this.heap[lChildIndex] ? rChildIndex : lChildIndex;
                // 爹>=大孩子，不换了
                if (this.heap[currentIndex] >= this.heap[largerChildIndex]) {
                    break;
                }

                // 否则换
                U.swap(this.heap, currentIndex, largerChildIndex);
                // 视角来到大孩子
                currentIndex = largerChildIndex;
            }
        }
    }
}
