package algorithm.sort;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 堆排序
 * 堆结构是一个完全二叉树
 * 可以用数组表示
 * 堆的左孩子：left = 2*index + 1
 * 堆的右孩子: right = 2*index + 2
 * 堆的父节节点; parent = (index - 1) / 2
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/10/27
 **/
public class HeapSort {
    /**
     * 思路:将数组构建成大根堆，然后堆顶数据与数组最后一个数交换，
     * 然后将新的堆大小-1，堆化新的堆，不断循环这个步骤直到堆的大小变为1
     *
     */
    public void heapSort(int[] arr, int N) {
        if (arr == null || N < 2) {
            return;
        }
        for (int i = 0; i < N; i++) {//「0 - N」建堆
            heapInsert(arr, i);
        }
        int heapSize = N;
        while (heapSize > 1) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);//交换 0 和 heapSize - 1位置后堆化新的堆
        }
    }

    /**
     * 在数组index位置向上形成堆
     * O(log(N))
     */
    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) { //当前节点比自己的父节点大的时候不断比较交换
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 将数组index位置堆化，往下形成堆
     *
     * @param arr arr
     * @param index 位置
     * @param heapSize 堆大小
     */
    public void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; //找到左节点
        while (left < heapSize) { //不断查找左子节点直到没有左子节点
            //选取 左子节点 和 右子节点中最大的一个
            int largestIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //选取当前节点和孩子节点中最大的一个
            largestIndex = arr[index] > arr[largestIndex] ? index : largestIndex;
            //如果当前节点就是最大的，说明堆化已完成
            if (largestIndex == index) {
                break;
            }
            //当前节点与最大节点交换
            swap(arr, index, largestIndex);
            //当前节点指向选取的最大节点的位置
            index = largestIndex;
            //找到新节点的左孩子节点
            left = index * 2 + 1;
        }
    }

    /**
     * 一个几乎有序的数组，每个元素最多移动K个位置就能保证数组有序，给数组排序
     *
     * O(Nlogk)
     *
     * 将数组前K个数放入小根堆中。然后不断从堆中取头部节点放入arr,数组有序
     *
     * @param arr
     * @param k
     */
    public void sortDistanceKArray(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (k >= arr.length) {
            k = arr.length - 1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index;//标记已入堆的位置
        //提供K+1大小的堆才能保证最多移动K位
        for (index = 0; index <= k; index++) {
            heap.add(arr[index]);
        }
        int i = 0;//标识出堆放入数组的位置
        while (index < arr.length) {
            arr[i++] = heap.poll();
            heap.add(arr[index++]);
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public void testArrDistanceLessK(int[] arr, int k) {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    private void swap(int[] arr, int i ,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        //testHeapSort();
        testDistanceKArray();
    }

    private static void testDistanceKArray() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> new Integer[] {
                2, 1, 5, 3, 6, 4
        });
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                HeapSort heapSort = new HeapSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                heapSort.sortDistanceKArray(testArray, 7);
                for (int i = 0; i < testArray.length; i++) {
                    testData[i] = testArray[i];
                }
                return testData;
            }

            @Override
            public Integer[] expect(Integer[] testData) {
                HeapSort heapSort = new HeapSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                heapSort.testArrDistanceLessK(testArray, 2);
                for (int i = 0; i < testArray.length; i++) {
                    testData[i] = testArray[i];
                }
                return testData;
            }
        });
        integerHeavensTest.test(10);
    }

    private static void testHeapSort() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-10000, 10000, 100));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                HeapSort heapSort = new HeapSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                heapSort.heapSort(testArray, testArray.length);
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
