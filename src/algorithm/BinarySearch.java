package algorithm;

import test.Printer;

import java.util.Arrays;
import java.util.List;

/**
 * binary
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/12/4
 **/
public class BinarySearch {
    public boolean binaryExits(int[] sortedArr, int num) {
        return false;
    }

    public int binaryExitsIndex(int[] sortedArr, int num) {
        return -1;
    }

    public int binaryNearLeftIndex(int[] sortedArr, int num) {
        return -1;
    }

    /**
     * 求数组中局部最小值
     *
     * @param arr
     * @return
     */
    public int getLessIndex(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                6, 5, 4, 1, 3, 2, 8, 10, 21, 22
        };
        BinarySearch binarySearch = new BinarySearch();

        int lessIndex = binarySearch.getLessIndex(arr);
        List<Integer> integers = Arrays.asList(arr);
        Printer.printIterator(integers);
        Printer.println("lessIndex:" + lessIndex);
    }
}
