package ds.exception;

/**
 * Author: Aaron
 * Create Date: 2019/7/28
 * Version: v1.0
 * <p>
 * DSA
 */
public class OutOfIndexException extends RuntimeException {
    public OutOfIndexException(int size, int index) {
        super("out of index, current index = " + index + " capacity = " + size);
    }
}
