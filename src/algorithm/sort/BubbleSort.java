package algorithm.sort;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 从小到大的排序
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/9/27
 **/
@SuppressWarnings("WeakerAccess")
public class BubbleSort {
    /**
     * 冒小泡的排序1
     *  6 5 4 3 2 1
     */
    public void bubbleMinSort1(int[] arr, int n) {
        if (arr == null || n < 2) {//数组元素小于2，不需要排序
            return;
        }
        boolean sorted = false; //添加判断数组是否已经排好序的标志
        for (int i = 0; i < n - 1; i ++) {//最差情况需要冒泡排序n-1次就可以完成排序
            if (sorted) {//如果数组已经有序不需要进行下一轮冒泡排序
                break;
            }
            sorted = true;//假设数组已经有序
            for (int j = n - 1; j > 0; j--) {//冒小泡，数组从后往前冒泡[n-1, 1]往前比较
                if (arr[j] < arr[j - 1]) {//后一个元素比前一个小，交换，
                    swap(arr, j, j - 1);
                    sorted = false;//说明这一轮交换前是无序的.
                }
            }
        }
    }

    /**
     * 冒小泡的排序2
     */
    public void bubbleMinSort2(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        boolean sorted = false;
        for (int i = 0; i < n - 1; i++) {
            if (sorted) {
                break;
            }
            sorted = true;
            for (int j = n - 2; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    sorted = false;
                }
            }
        }
    }

    /**
     * 冒大泡的排序1
     */
    public void bubbleMaxSort1(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        boolean sorted = false;
        while(!sorted) { //数组有序前不断冒泡
            sorted = true;//假设数组有序
            for (int i = 0; i < n - 1; i ++) {//[0,n-2]位置之间进行一轮冒泡
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i +1);
                    sorted = false;
                }
            }
            n--;//每冒泡一次，n - 1位置（包含在内）往后的元素已经是有序的
        }
    }

    /**
     * 冒大泡的排序2
     */
    public void bubbleMaxSort2(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
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

    /**
     * 冒大泡的排序3
     * 6 5 4 3 2 1
     *
     */
    public void bubbleMaxSort3(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        boolean sorted = false;
        for (int i = n - 1; i > 0; i--) {
            if (sorted) {
                break;
            }
            sorted = true;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    sorted = false;
                }
            }
        }
    }


    /**
     * 冒大泡的排序3
     */
    public void bubbleMaxSort4(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        boolean sorted = false;
        for (int i = n - 1; i > 0; i--) {
            if (sorted) {
                break;
            }
            sorted = true;
            for (int j = 1; j <= i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    sorted = false;
                }
            }
        }
    }

    /**
     * 冒大泡的排序3
     */
    public void bubbleMaxSort5(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        boolean sorted = false;
        for (int i = 0; i < n - 1; i++) {
            if (sorted) {
                break;
            }
            sorted = true;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    sorted = false;
                }
            }
        }
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-100000, 100000, 1000));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                BubbleSort bubbleSort = new BubbleSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testData.length; i++) {
                    testArray[i] = testData[i];
                }
                //bubbleSort.bubbleMaxSort1(testArray, testArray.length);
                bubbleSort.bubbleMinSort1(testArray, testArray.length);
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
}
