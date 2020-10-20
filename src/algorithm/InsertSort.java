package algorithm;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;

/**
 * 插入排序
 *
 * 6 5 4 3 2 1
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/9/27
 **/
public class InsertSort {
    public void insertSort(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        for (int i = 1; i < n; i ++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void  main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-10000, 10000, 100));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                InsertSort bubbleSort = new InsertSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                bubbleSort.insertSort(testArray, testArray.length);
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

    private static void test2() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> new Integer[] {
                6,5,4,3,2,1
        });
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                BubbleSort bubbleSort = new BubbleSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                bubbleSort.bubbleSort3(testArray, testArray.length);
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
        integerHeavensTest.test(1);
    }
}
