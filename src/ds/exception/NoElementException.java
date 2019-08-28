package ds.exception;

/**
 * 没有元素异常
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/2
 **/
public class NoElementException extends RuntimeException {
    public NoElementException(String struct) {
        super("no element found in " + struct);
    }
}
