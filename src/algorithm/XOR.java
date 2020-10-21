package algorithm;

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
        return -1;
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
        int[] result = new int[2];
        return result;
    }
}
