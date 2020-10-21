package algorithm;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;

/**
 * 插入排序
 * 从小到大的排序
 * <p>
 * 6 5 4 3 2 1
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/9/27
 **/
public class InsertSort {

    /**
     * 往左侧插入的排序
     * 6 5 4 3 2 1
     */
    public void insertLSort(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        for (int i = 1; i < n; i++) { //从[1，n-1]不断取数据往前插入
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {//取到的数不断往前比较直到j+1位置的数小于等于j位置的数
                swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 往右侧插入的排序
     * 6 5 4 3 2 1
     */
    public void insertRSort(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        for (int i = n - 2; i >= 0; i--) {//渠道的数不断往后比较，直到j位置的数大于等于j - 1位置的数
            for (int j = i + 1; j < n && arr[j - 1] > arr[j]; j++) {
                swap(arr, j - 1, j);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-10000, 10000, 100));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                InsertSort insertSort = new InsertSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                insertSort.insertRSort(testArray, testArray.length);
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
