package algorithm;

import test.Printer;
import test.TestGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * 递归获取做大值
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/12/3
 **/
public class GetMax {
    public int getMax(int[] arr) {
        return getMax(arr, 0, arr.length -1);
    }

    private int getMax(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = getMax(arr, L, mid);
        int rightMax = getMax(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        Integer[] data = TestGenerator.generateRandomArray(-100, 200, 20);
        List<Integer> array = Arrays.asList(data);
        Printer.printIterator(array);

        int[] arr = new int[data.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = data[i];
        }

        GetMax getMax = new GetMax();
        int max = getMax.getMax(arr);
        Printer.print("max:" + max);
    }
}
