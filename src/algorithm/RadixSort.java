package algorithm;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 基数排序
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/10/27
 **/
@SuppressWarnings("Duplicates")
public class  RadixSort {
    public void radixSort(int[] arr, int N) {
        if (arr == null || N < 2) {
            return;
        }
        radixSort(arr, 0, N - 1);
    }

    /**
     * 获取数组中最大数的最大位数
     * @param arr
     * @return
     */
    private int getMaxBit(int[] arr) {
        int max = -1;
        for (int num : arr) {
            max = num > max ? num : max;
        }
        int bit = 0;
        while (max > 0) {
            bit ++;
            max /= 10;
        }
        return bit;
    }

    /**
     * 获取数字相应位上的数字
     *
     * @param num 数字
     * @param bit 从0位开始数
     * @return 相应的数字
     */
    private int getDigitWithBit(int num, int bit) {
        return (num / (int)Math.pow(10, bit) % 10);
    }

    /**
     * 21 21 11 12 32 92 23 17 88 79
     *
     *  countArr
     *  [0  1  4  7  7  7  7  8  9  10]
     *   0  1  2  3  4  5  6  7  8  9
     *
     *  从左到右放入桶中排序十位数时是错误的,会将其他位排好的数据打乱
     *  bucket
     *  [   12 11    21 21              ]
     *   0  1  2  3  4  5  6  7  8  9
     *
     * @param arr
     * @param L
     * @param R
     */
    private void radixSort(int[] arr, int L, int R) {
        int[] bucket = new int[R - L + 1];
        //从0 位到最大位不断进桶出桶
        for (int bit = 0; bit < getMaxBit(arr); bit++) {
            //统计某一位上0 - 9 数字个数的数组
            int[] bitCountArr = new int[10];
            //开始统计
            for (int i = L; i <= R; i++) {
                int digit = getDigitWithBit(arr[i], bit);
                bitCountArr[digit]++;
            }
            //将统计数组变为<=某个数的个数
            for (int i = 1; i < bitCountArr.length; i++) {
                bitCountArr[i] = bitCountArr[i] + bitCountArr[i - 1];
            }
            //入桶
            for (int i = R; i >= L; i--) {//从后往前遍历以防打乱其他位排好的顺序，因为排序时是从后往前放数的
                int digit = getDigitWithBit(arr[i], bit);
                bucket[--bitCountArr[digit]] = arr[i];
            }
            //出桶
            int j = 0;
            for (int i = L; i <= R; i++) {
                arr[i] = bucket[j++];
            }
        }

    }

    public static void main(String[] args) {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(0, 1000000, 1000));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                RadixSort radixSort = new RadixSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testData.length; i++) {
                    testArray[i] = testData[i];
                }
                radixSort.radixSort(testArray, testArray.length);
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
