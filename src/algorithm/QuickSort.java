package algorithm;

import test.HeavensTest;
import test.Printer;
import test.TestGenerator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 快速排序
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/10/27
 **/
public class QuickSort {
    /**
     * 荷兰国旗问题
     *
     * 题意：小于num的放在arr中的左侧，等于num的放arr中间，大于num的放arr右边
     *
     * @param arr 数组
     * @param num num
     */
    public void dutchFlagQuestion(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        partitionDutchFlag(arr, 0, arr.length - 1, num);
    }

    private int[] partitionDutchFlag(int[] arr, int L, int R, int num) {
        int less = L - 1;//初始化小于num区域
        int more = R + 1;//初始化大于num区域
        while(L < more) {//当指针位置到达大于区域之前一直与num做比较
            if (arr[L] < num) {
                //当前位置的数小于num的时候当前位置与小于位置的后一个位置交换，小于位置+1，当前指针位置+1
                swap(arr, L++, ++less);
            } else if (arr[L] > num) {
                //当亲位置的数大于num的时候当前位置与大于区域的前一个位置交换，大于区域+1，当前指针不变
                swap(arr, L, --more);
            } else {
                //当前位置的数等于num的时候，当前指针+1
                L++;
            }
        }
        // 将等于区域返回
        int[] result = new int[2];
        result[0] = less + 1;
        result[1] = more - 1;
        return result;
    }

    public void quickSort(int[] arr, int N) {
        if (arr == null || N < 2) {
            return;
        }
        quickSort(arr, 0, N - 1);
    }

    private void quickSort(int[] arr, int L, int R) {
        if (R <= L) {
            return;
        }
        int random = (int)(Math.random() * (R - L + 1));
        swap(arr, L + random, R);
        int[] partitionResult = partition(arr, L, R);
        quickSort(arr, L, partitionResult[0] - 1);
        quickSort(arr, partitionResult[1] + 1, R);
    }


    private int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] > arr[R]) {
                swap(arr, L, --more);
            } else if (arr[L] < arr[R]) {
                swap(arr, L++, ++less);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        int[] result = new int[2];
        result[0] = less + 1;
        result[1] = more;
        return result;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        //testDutchFlag();
        testQuickSort();
    }

    private static void testQuickSort() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-10000, 10000, 10));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                QuickSort quickSort = new QuickSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                quickSort.quickSort(testArray, testArray.length);
                for (int i = 0; i < testArray.length; i++) {
                    testData[i] = testArray[i];
                }
                return testData;
            }

            @Override
            public Integer[] expect(Integer[] testData) {
                Arrays.sort(testData);
                return testData;
            }
        });
        integerHeavensTest.test(10);
    }

    private static void testDutchFlag() {
        int[] testData = {9, 8, 2, 10, 11, 22, 33, 11, 11, 12, 13, 18, 12};
        QuickSort quickSort = new QuickSort();
        quickSort.dutchFlagQuestion(testData, 18);
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : testData) {
            list.add(num);
        }
        Printer.printIterator(list);
    }
}
