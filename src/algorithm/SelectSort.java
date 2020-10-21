package algorithm;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 选择排序
 * 从小到大的排序
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/9/27
 **/
public class SelectSort {
    /**
     * 优先选择最小值
     * 6 5 4 3 2 1
     * <p>
     * 依次确定值的位置i：[0, n - 1)
     * 依次需要选择最小值的位置：j = i + 1, foreach [j, n)
     */
    public void selectMinSort(int[] arr, int n) {
        if (arr == null || n < 2) { //数组长度小于2可以看做已经排好顺序
            return;
        }
        int minIndex;//优先选择最小值
        for (int i = 0; i < n - 1; i++) { //依次确定[0,n-2]位置上的值, 前面选好了 n-1位置自然就是最大值
            minIndex = i; //假设i位置是i后面所有数中的最小值
            for (int j = i + 1; j < n; j++) { //选择 [i+1, n-1]位置上的最小值
                minIndex = arr[minIndex] > arr[j] ? j : minIndex; //如果找到更小的数更改minIndex的值
            }
            if (minIndex != i) { //如果选择好的最小值不是最初假设的i,交换i和minIndex位置上的值
                swap(arr, i, minIndex);
            }
        }
    }

    /**
     * 优先选择最大值
     * 6 5 4 3 2 1
     * <p>
     * 依次确定值的位置i：[n - 1, 0)
     * 依次需要选择最大值的位置：j = i - 1, foreach [j, 0]
     */
    public void selectMaxSort(int[] arr, int n) {
        if (arr == null || n < 2) { //数组长度小于2可以看做已经排好顺序
            return;
        }
        int maxIndex; //优先选择最大值
        for (int i = n - 1; i > 0; i--) { //依次确定[n-1, 1]位置上的值，剩下的0位置自然就是最小值
            maxIndex = i;//假设最大值就是i位置上的值
            for (int j = i - 1; j >= 0; j--) { //选择[i-1, 0]位置上最大的值的位置
                maxIndex = arr[maxIndex] < arr[j] ? j : maxIndex;
            }
            if (maxIndex != i) {//如果假设的maxIndex位置不是i,交换两个位置的值
                swap(arr, maxIndex, i);
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
                selectSort.selectMinSort(testArray, testArray.length);
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
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-100000, 100000, 1000));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                SelectSort selectSort = new SelectSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testData.length; i++) {
                    testArray[i] = testData[i];
                }
                selectSort.selectMaxSort(testArray, testArray.length);
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
