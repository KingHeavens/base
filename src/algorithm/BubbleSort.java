package algorithm;

import test.HeavensTest;
import test.TestGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 冒泡排序
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/9/27
 **/
public class BubbleSort {
    public void bubbleSort1(int[] arr, int n) {
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

    public void bubbleSort2(int[] arr, int n) {
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    sorted = false;
                }
            }
            n--;
        }
    }

    public void bubbleSort3(int[] arr, int n) {
        if (arr == null || n < 2) {
            return;
        }
        boolean sorted = false;
        for (int i = 0; i < n - 1; i++) {
            if (sorted) {
                break;
            }
            sorted = true;
            for (int j = n - 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    sorted = false;
                }
            }
        }
    }
    private void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }


    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
        /*int[] test = new int[] {
          6,5,4,3,2,1
        };
        new BubbleSort().bubbleSort3(test, test.length);
        List<Integer> list = new ArrayList<>();
        for (int i : test) {
            list.add(i);
        }
        Printer.printIterator(list);*/
    }

    private static void test1() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-1000, 1000, 1000));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                BubbleSort bubbleSort = new BubbleSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testData.length; i++) {
                    testArray[i] = testData[i];
                }
                bubbleSort.bubbleSort1(testArray, testArray.length);
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
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-1000, 1000, 1000));
        integerHeavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                BubbleSort bubbleSort = new BubbleSort();
                int[] testArray = new int[testData.length];
                for (int i = 0; i < testArray.length; i++) {
                    testArray[i] = testData[i];
                }
                bubbleSort.bubbleSort2(testArray, testArray.length);
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

    private static void test3() {
        HeavensTest<Integer> integerHeavensTest = new HeavensTest<>(Integer.class);
        integerHeavensTest.input(() -> TestGenerator.generateRandomArray(-100, 100, 10));
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
                List<Integer> testList = Arrays.asList(testData);
                Collections.sort(testList);

                return testList.toArray(testData);
            }
        });
        integerHeavensTest.test(1);
    }
}
