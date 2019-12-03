package test;


import java.util.Iterator;

/**
 * 打印类
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/6
 **/
public class Printer {

    public static void println(String log) {
        System.out.println(log);
    }

    public static void println2(String log) {
        System.out.println(log);
        println();
    }

    public static void println() {
        System.out.println();
    }

    public static void print(String log) {
        System.out.print(log);
    }

    public static void printIterator(Iterable iterable) {
        if (iterable == null) {
            return;
        }
        Iterator iterator = iterable.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            stringBuilder.append(next.toString()).append(",");
        }
        System.out.println(stringBuilder.toString());
    }
}
