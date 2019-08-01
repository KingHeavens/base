package algorithm;

public class BubbleSort {
    public static void bubbleSort1(int[] arr, int n) {
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                    sorted = false;
                }
            }
            n--;
        }
    }

    public static void bubbleSort2(int[] arr, int n) {
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i , i + 1);
                    sorted = false;
                }
            }
            n--;
        }
    }

    private static void swap(int[] arr, int position1, int position2) {
        int temp = arr[position1];
        arr[position1] = arr[position2];
        arr[position2] = temp;
    }
}
