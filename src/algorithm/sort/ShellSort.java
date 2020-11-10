package algorithm.sort;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 希尔排序
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/10/27
 **/
@SuppressWarnings("Duplicates")
public class ShellSort {
    /**
     * 思路：不断对arr每隔gap个（分量）分组进行一次调整使每一组有序
     *      调整有序使用插入排序的方式
     *      希尔分量：gap /= 2
     *
     * 插入排序使用元素交换法
     * @param arr
     * @param N
     */
    public void shellSort(int[] arr, int N) {
        if (arr == null || N < 2) {
            return;
        }
        for (int gap = N / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < N; i++) {
                for (int j = i - gap; j >= 0 && arr[j] > arr[j + gap]; j-=gap) {
                    swap(arr, j, j + gap);
                }
            }
        }
    }
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 希尔排序
     * 插入排序使用元素移动法
     * @param arr
     * @param N
     */
    public void shellSort2(int[] arr, int N) {
        if (arr == null || N < 2) {
            return;
        }
        for (int gap = N / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < N; i++) {
                int j = i;
                if (arr[j - gap] > arr[i]) {
                    int temp = arr[i]; //元素移动法要记住这个元素，要不然就变了
                    while (j - gap >= 0 && arr[j - gap] > temp) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-100000, 100000, 1000));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                ShellSort shellSort = new ShellSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testData.length; i++) {
                    testArray[i] = testData[i];
                }
                shellSort.shellSort2(testArray, testArray.length);
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
        }, new String[]{});
        integerHeavensTest.test(10);
    }
}
