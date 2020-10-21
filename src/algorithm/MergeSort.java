package algorithm;


/**
 *  递归时间复杂度分析
 *  master 公式
 *  T(N) = a * T(N/b) + O(N^d)
 *  补充阅读：http://www.gocalf.com/blog/algorithm-complexity-and-master-theorem.html
 *
 * 1） log(b, a) > d     ==》 O(N^log(a, b))
 * 2） log(b, a) == d    ==》 O(N^d * logN)
 * 3） log(b, a) < d     ==》 O(N^d)
 *
 * 归并排序
 */
public class MergeSort {

    /**
     * 二分查找最大值
     *
     * @param arr 数组
     * @param L 数组左侧index
     * @param R 数组右侧index
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
        int maxL = getMax(arr, L, mid);
        int maxR = getMax(arr, mid + 1, R);
        return maxL > maxR ? maxL : maxR;
    }

    /**
     *
     *
     * @param arr
     * @param L
     * @param R
     */
    public void mergeSort(int[] arr, int L, int R){

    }
}
