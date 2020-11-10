package algorithm.bs;

import test.Printer;

/**
 * 二分查找相关
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/12/4
 **/
public class BinarySearch {
    /**
     * 二分查找
     * 1 2 3 4 5 6
     */
    public boolean bsExits(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length <= 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int M;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (sortedArr[M] == num) {
                return true;
            } else if (sortedArr[M] > num) {
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return false;
    }

    /**
     * 查找有序数组中存在数的index
     */
    public int bsExitsIndex(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length <= 0) {
            return -1;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int M;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (sortedArr[M] == num) {
                return M;
            } else if (sortedArr[M] > num) {
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return -1;
    }

    /**
     *      1 2 2 3 4 5 5 6 7 8 8 9
     *
     * 有序数组中查找大于等于某个数的最左端index
     */
    public int bsNearLeftIndex(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length < 1) {
            return num;
        }
        if (sortedArr.length == 1 && sortedArr[0] >= num) {
            return 0;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int M;
        int index = -1;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (sortedArr[M] >= num) {
                index = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return index;
    }

    /**
     *      1 2 2 3 4 5 6 7 8 8 9
     *
     * 有序数组中查找小于等于某个数的最右端index
     */
    public int bsNearRightIndex(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length < 1) {
            return -1;
        }
        if (sortedArr.length == 1 && sortedArr[0] <= num) {
            return 0;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int M;
        int index = -1;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (sortedArr[M] <= num) {
                index = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return index;
    }

    /**
     * 数组中数据各不相同，求数组中局部最小值
     * 9 2 1 3 4 2 8
     */
    public int getLocalMinimumIndex1(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public int getLocalMinimumIndex(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length -2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int M;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (arr[M] > arr[M - 1]) {
                R = M - 1;
            } else if (arr[M] > arr[M + 1]) {
                L = M + 1;
            } else {
                return M;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{
                6, 2, 1, 8, 5, 1, 3, 2, 8, 10
        };
        int[] sortedArr = new int[]{
                1, 2, 2, 3, 4, 6, 8, 9, 10, 12, 12
        };
        BinarySearch binarySearch = new BinarySearch();
        //boolean exits = binarySearch.bsExits(sortedArr, 0);
        //Printer.println("exits:" + exits);

//        int index = binarySearch.bsExitsIndex(sortedArr, 12);
//        Printer.println("exits index:" + index);
//        int index = binarySearch.bsNearLeftIndex(sortedArr, 12);
//        Printer.println("exits index:" + index);
//        int index = binarySearch.bsNearRightIndex(sortedArr, 12);
        int index = binarySearch.getLocalMinimumIndex(arr);
        Printer.println("index:" + index);
    }
}
