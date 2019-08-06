package test;


import ds.ArrayList;

import static test.Printer.println;

/**
 * 测试线性表
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/6
 **/
public class TestArrayList {

    public static void main(String[] args) {
        println("1、---- Test ArrayList addLast()----");
        ArrayList<Integer> testArray = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            testArray.addLast(i);
        }
        print(testArray);

        println("2、---- Test ArrayList addFirst()----");
        testArray.addFirst(-999);
        print(testArray);

        println("3、---- Test ArrayList add()----");
        println("---- Test ArrayList add position 0, element 100 ----");
        testArray.add(0, 100);
        print(testArray);

        println("4---- Test ArrayList add position 2, element 200 ----");
        testArray.add(2, 200);
        print(testArray);

        println("5---- Test ArrayList add position 5, element 500 ----");
        testArray.add(5, 500);
        print(testArray);

        println("6---- Test ArrayList add position 10, element 300 ----");
        testArray.add(10, 300);
        print(testArray);

        try {
            println("7、---- Test ArrayList add position 100, element 1000 ----");
            testArray.add(100, 1000);
        } catch (Exception e) {
            println(e.toString());
        }
        println();

        println("8、---- Test ArrayList remove position 0 ----");
        testArray.remove(0);
        print(testArray);

        println("9、---- Test ArrayList removeFirst ----");
        testArray.removeFirst();
        print(testArray);

        println("10、---- Test ArrayList removeLast ----");
        testArray.removeLast();
        print(testArray);

        println("11、---- Test ArrayList removeElementFromStart 200----");
        testArray.removeElementFromStart(200);
        print(testArray);

        println("12、---- Test ArrayList removeElementFromStart 6----");
        testArray.removeElementFromLast(6);
        print(testArray);
    }

    private static void print(ArrayList<Integer> testArray) {
        println(testArray.toString());
        println();
    }

}
