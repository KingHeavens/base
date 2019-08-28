package test;

import ds.ArrayList;

import java.util.Arrays;
import java.util.Collections;

/**
 * 测试线性表
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/6
 **/
public class TestArrayList {

    public static void main(String[] args) {
        testCase1();
    }

    private static void testCase1() {
        HeavensTest<Integer> heavensTest = new HeavensTest<>();
        heavensTest.input(new HeavensTest.ITestInput<Integer>() {
            @Override
            public Integer[] onInput() {
                return TestGenerator.generateRandomArray(-100, 100, 5, 20);
            }
        });
        heavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
                    @Override
                    public Integer[] test(Integer[] testData) {
                        ArrayList<Integer> arrayList = toArrayList(testData);
                        return toArray(arrayList);
                    }

                    @Override
                    public Integer[] expect(Integer[] testData) {
                        java.util.ArrayList<Integer> array = toJavaArrayList(testData);
                        return toArray(array);
                    }
                });
        heavensTest.continueWith(new HeavensTest.ITestCase<Integer>() {
            @Override
            public Integer[] test(Integer[] testData) {
                ArrayList<Integer> arrayList = toArrayList(testData);
                arrayList.addFirst(200);
                arrayList.add(2, 0);
                arrayList.add(arrayList.getSize() - 1, 20);
                arrayList.remove(5);
                arrayList.removeFirst();
                arrayList.removeLast();
                return toArray(arrayList);
            }

            @Override
            public Integer[] expect(Integer[] testData) {
                java.util.ArrayList<Integer> list = toJavaArrayList(testData);
                list.add(0, 200);
                list.add(2, 0);
                list.add(list.size() - 1, 20);
                list.remove(5);
                list.remove(0);
                list.remove(list.size() - 1);
                return toArray(list);
            }
        });
        heavensTest.test(1);
    }

    private static ArrayList<Integer> toArrayList(Integer[] data) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer num : data) {
            list.addLast(num);
        }
        return list;
    }

    private static java.util.ArrayList<Integer> toJavaArrayList(Integer[] data) {
        return new java.util.ArrayList<>(Arrays.asList(data));
    }

    private static Integer[] toArray(ArrayList<Integer> data) {
        Integer[] array = new Integer[data.getSize()];
        for (int i = 0; i < data.getSize(); i++) {
            array[i] = data.get(i);
        }
        return array;
    }

    private static Integer[] toArray(java.util.ArrayList<Integer> data) {
        return data.toArray(new Integer[0]);
    }
}
