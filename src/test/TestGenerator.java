package test;

import java.util.Random;

/**
 * 测试工具类，用于生成随机数
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/6
 **/
public class TestGenerator {

    public static int[] generateRandomArray() {
        return generateRandomArray(0, 5, 10);
    }

    public static int[] generateRandomArray(int from, int to, int count) {
        int[] intArr = new int[count];
        for (int i = 0; i < count; i ++) {
            intArr[i] = generateInt(from, to);
        }
        return intArr;
    }

    public static int[] generateRandomArray(int from, int to, int fromCount, int toCount) {
        int realCount = generateInt(fromCount, toCount);
        int[] intArr = new int[realCount];
        for (int i = 0; i < realCount; i ++) {
            intArr[i] = generateInt(from, to);
        }
        return intArr;
    }

    public static int generateInt() {
        Random random = new Random();
        return random.nextInt();
    }

    public static int generateInt(int from, int to) {
        Random random = new Random();
        return from + random.nextInt(to);
    }
}
