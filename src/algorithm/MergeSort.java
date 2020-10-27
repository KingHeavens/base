package algorithm;


/**
 *  递归时间复杂度分析
 *  master 公式
 *  T(N) = a * T(N/b) + O(N^d)
 *  T(N) = 算法整体规模
 *  a = 算法中以平均分后的数据规模处理了几次
 *  T(N/b) = 算法规模被平均分成了几份
 *  O(N^d) = 其余的算法时间复杂度
 *
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
     * 归并排序
     */
    public void mergeSort(int[] arr, int N){

    }

    private void mergeProcess(int[] arr, int L, int R) {

    }

    private void merge(int[] arr, int L, int M, int R) {

    }

    /**
     * 小和问题
     *
     * @param arr 数组
     * @param N 数组长度
     */
    public void smallSum(int[] arr, int N) {

    }

    public int mergeSmallSum(int[] arr, int L, int M, int R) {
        return 0;
    }

    public static void main(String[] args) {

    }
}
