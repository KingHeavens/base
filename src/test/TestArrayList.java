package test;

import ds.ArrayList;

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
        heavensTest.input(TestGenerator::generateRandomArray);
        heavensTest.addTestCase(new HeavensTest.ITestCase<Integer>() {
                    @Override
                    public Integer[] test(Integer[] testData) {
                        ArrayList<Integer> list = new ArrayList<>();
                        for (Integer aTestData : testData) {
                            list.addLast(aTestData);
                        }
                        Integer[] results = new Integer[list.getSize()];
                        for (int i = 0; i < list.getSize(); i ++) {
                            results[i] = list.get(i);
                        }
                        return results;
                    }

                    @Override
                    public Integer[] expect(Integer[] testData) {
                        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
                        Collections.addAll(list, testData);
                        Integer[] results = new Integer[list.size()];
                        for (int i = 0; i < list.size(); i ++) {
                            results[i] = list.get(i);
                        }
                        return results;
                    }
                });
        heavensTest.test(10);
    }
}
