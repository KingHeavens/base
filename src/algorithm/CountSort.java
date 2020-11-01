package algorithm;

import test.HeavensTest;
import test.Printer;
import test.TestGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 计数排序
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/10/27
 **/
public class CountSort {
    /**
     * 适合有限数据的集合
     *
     * 这里给定元素大小[0 - 200]
     * @param arr
     * @param N
     */
    public void countSort(int[] arr, int N) {
        if (arr == null || N < 2){
            return;
        }
        //找到数组中最大的数
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        //统计每个数的个数
        int[] countArr = new int[max + 1];
        for (int i = 0; i < N; i++) {
            countArr[arr[i]]++;
        }
        //将元素还原到原来的数组中
        int j = 0;
        for (int i = 0; i < countArr.length; i++) {
            while(countArr[i]-- > 0) {//注意每次减一
                arr[j++] = i;
            }
        }
    }

    public static void main(String[] args) {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(0, 10, 10));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                CountSort countSort = new CountSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testData.length; i++) {
                    testArray[i] = testData[i];
                }
                countSort.countSort(testArray, testArray.length);
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
