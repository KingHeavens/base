package algorithm;


import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;

/**
 * 递归时间复杂度分析
 * master 公式
 * T(N) = a * T(N/b) + O(N^d)
 * T(N) = 算法整体规模
 * a = 算法中以平均分后的数据规模处理了几次
 * T(N/b) = 算法规模被平均分成了几份
 * O(N^d) = 其余的算法时间复杂度
 * <p>
 * 补充阅读：http://www.gocalf.com/blog/algorithm-complexity-and-master-theorem.html
 * <p>
 * 1） log(b, a) > d     ==》 O(N^log(a, b))
 * 2） log(b, a) == d    ==》 O(N^d * logN)
 * 3） log(b, a) < d     ==》 O(N^d)
 * <p>
 * 归并排序
 */
public class MergeSort {

    /**
     * 二分查找最大值
     *
     * @param arr 数组
     * @param L   数组左侧index
     * @param R   数组右侧index
     * @return 数组中最大值
     */
    public int getMax(int[] arr, int L, int R) {
        if (arr == null || arr.length <= 0) {
            throw new RuntimeException("arr is empty");
        }
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int maxL = getMax(arr, L, mid); //数组左半边最大值
        int maxR = getMax(arr, mid + 1, R);//数组右半边最大值
        return maxL > maxR ? maxL : maxR;//比较左半边和右半边最大值
    }

    /**
     * 归并排序
     */
    public void mergeSort(int[] arr, int N) {
        if (arr == null || N < 2) {
            return;
        }
        mergeProcess(arr, 0, N - 1);
    }

    private void mergeProcess(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        mergeProcess(arr, L, M);//归并排序左半边的数据
        mergeProcess(arr, M + 1, R);//归并排序右半边数据
        merge(arr, L, M, R);//合并两个排好序的数组
    }

    private void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];//新建一个辅助数组用于存放合并后的数据
        int i = 0;//辅助数组指针
        int pl = L;//左侧数组指针，初始指向最左侧
        int pr = M + 1;//右侧数组指针，初始指向右侧数组最左侧
        //当左侧数组没有合并完并且右侧数组没有合并完一直合并入辅助数组
        while (pl <= M && pr <= R) {
            //比较左侧数组指向的数与右侧数组指向的数，小的先进辅助数组，如果相等先将左侧数组放入辅助数组保证稳定性
            help[i++] = arr[pl] <= arr[pr] ? arr[pl++] : arr[pr++];
        }
        //左侧数组如果还有数据，将其放入辅助数组
        while (pl <= M) {
            help[i++] = arr[pl++];
        }
        //右侧数组如果还有数据，将其放入辅助数组
        while (pr <= R) {
            help[i++] = arr[pr++];
        }
        //将辅助数组排好序的数据放回原始数组
        for (i = 0; i < R - L + 1; i++) {
            arr[L + i] = help[i];
        }
    }

    /**
     * 小和问题,给定一个数组，数组中每一个数的左边所有比这个数小的和
     * <p>
     * 思路：可以转化为找到 arr[i] 右边比这个数大的数的个数 * arr[i] 的所有和累加和
     *
     * @param arr 数组
     * @param N   数组长度
     */
    public int smallSum(int[] arr, int N) {
        if (arr == null || N < 2) {
            return 0;
        }
        return mergeSmallSumProcess(arr, 0, N - 1);
    }

    public int mergeSmallSumProcess(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return mergeSmallSumProcess(arr, L, M)
                + mergeSmallSumProcess(arr, M + 1, R)
                + mergeSmallSum(arr, L, M, R);
    }

    public int mergeSmallSum(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int pl = L;
        int pr = M + 1;
        int sum = 0;
        while (pl <= M && pr <= R) {
            sum += arr[pl] < arr[pr] ? arr[pl] * (R - pr + 1) : 0;
            help[i++] = arr[pl] < arr[pr] ? arr[pl++] : arr[pr++];
        }
        while (pl <= M) {
            help[i++] = arr[pl++];
        }
        while (pr <= R) {
            help[i++] = arr[pr++];
        }
        for (int j = 0; j < R - L + 1; j++) {
            arr[L + j] = help[j];
        }
        return sum;
    }

    /**
     * 小和问题，逐个遍历方式
     */
    public int smallSum0(int[] arr, int N) {
        if (arr == null || N < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
               if (arr[j] < arr[i]) {
                   sum += arr[j];
               }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //testMergeSort();
        testSmallSum();
    }

    private static void testMergeSort() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-10000, 10000, 10));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                MergeSort mergeSort = new MergeSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                mergeSort.mergeSort(testArray, testArray.length);
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

    private static void testSmallSum() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-10000, 10000, 10));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                MergeSort mergeSort = new MergeSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                int sum = mergeSort.smallSum(testArray, testArray.length);
                return new Integer[] {sum};
            }

            @Override
            public Integer[] expect(Integer[] testData) {
                MergeSort mergeSort = new MergeSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                int sum = mergeSort.smallSum0(testArray, testArray.length);
                return new Integer[] {sum};
            }
        });
        integerHeavensTest.test(10);
    }
}
