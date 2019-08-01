package ds.exception;

/**
 * Author: Aaron
 * Create Date: 2019/7/28
 * Version: v1.0
 * <p>
 * 容量已满异常
 */
public class OutOfCapacityException extends RuntimeException {
    public OutOfCapacityException(int capacity) {
        super("out of capacity size of:" + capacity);
    }
}
