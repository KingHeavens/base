package algorithm;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 选择排序
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/9/27
 **/
public class SelectSort {
    public void selectSort(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            if (i != minIndex) {
                swap(arr, i, minIndex);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test1() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-100000, 100000, 1000));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                SelectSort selectSort = new SelectSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testData.length; i++) {
                    testArray[i] = testData[i];
                }
                selectSort.selectSort(testArray, testArray.length);
                for (int i = 0; i < testArray.length; i++) {
                    testData[i] = testArray[i];
                }
                return testData;
            }

            @Override
            public Integer[] expect(Integer[] testData) {
                List<Integer> testList = Arrays.asList(testData);
                Collections.sort(testList);

                return testList.toArray(testData);
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
                SelectSort selectSort = new SelectSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testData.length; i++) {
                    testArray[i] = testData[i];
                }
                selectSort.selectSort(testArray, testArray.length);
                for (int i = 0; i < testArray.length; i++) {
                    testData[i] = testArray[i];
                }
                return testData;
            }

            @Override
            public Integer[] expect(Integer[] testData) {
                List<Integer> testList = Arrays.asList(testData);
                Collections.sort(testList);

                return testList.toArray(testData);
            }
        });
        integerHeavensTest.test(10);
    }
}
