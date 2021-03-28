package util;

public class U {

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
