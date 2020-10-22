package algorithm;

import test.Printer;

/**
 * 0^N = N
 * N^N = 0
 * A^B = B^A
 * A^B^C = A^(B^C)
 *
 * XOR
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/12/4
 **/
public class XOR {
    /**
     * 数组中只有一个数出现了奇数次，其余都是偶数次，求这个出现奇数次的数?
     *
     *
     * @param arr 数组
     * @return
     */
    public int getOddCountNumber(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int eor = 0;
        for (int number : arr) { //因为N^N=0 0^N=N
            eor ^= number;
        }
        return eor;
    }

    /**
     * 数组中有两个数出现了奇数次，其余都是偶数次，求这两个出现奇数次的数?
     *
     * 知识点：求一个数二进制 最右侧的1出现的位置的数
     *
     *  number & (~number + 1)
     *
     * @param arr 数组
     * @return 两个出现奇数次的数
     */
    public int[] getTwoOddCountNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        int[] result = new int[2];
        int eor = 0;
        for (int number : arr) {//得到a^b
            eor ^= number;
        }

        //a^b != 0 a和b中肯定有一个其中一位是1

        int oneBitNumber = eor & (~eor + 1);//得到a^b中最右侧的1表示的数

        int eor2 = 0;
        for (int number : arr) {
            if ((number & oneBitNumber) == oneBitNumber) {
                eor2 ^= number;
            }
        }
        result[0] = eor2;
        result[1] = eor ^ eor2;
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {
                20, 90
        };
        XOR xor = new XOR();
        int number = xor.getOddCountNumber(arr);
        Printer.println("number:" + number);

        int[] numbers = xor.getTwoOddCountNumber(arr);
        Printer.println("number0:" + numbers[0] + " number1:" + numbers[1]);
    }
}
