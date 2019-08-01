import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[]{2, 8, 9, 10, 11, 12};
        int max = getMax(arr, 0, arr.length - 1);
        show("max=" + max);
    }

    private static int getMax(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        int mid = i + ((j - i) >> 1);// safe  (i + j) / 2
        int l = getMax(arr, i, mid);
        show("postion L max = " + l);
        int r = getMax(arr, mid + 1, j);
        show("position R max = " + r);
        return l > r ? l : r;
    }

    private static void show(String str) {
        System.out.println(str);
    }
}
