package test;

/**
 * 自制简易测试框架，用于验证正确性
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/6
 **/
public class Tester<E extends Comparable> {

    private ITestOperation mOpration;
    private ITestInput mInput;

    public Tester() {
    }

    public Tester inputTestData(ITestInput input) {
        if (input == null) {
            throw new RuntimeException("ITestInput can not be null!");
        }
        mInput = input;
        return this;
    }

    public Tester addTestOperation(ITestOperation operation) {
        if (operation == null) {
            throw new RuntimeException("ITestOperation can not be null!");
        }
        mOpration = operation;
        return this;
    }

    public boolean test(int testCount) {

        return false;
    }

    public interface ITestInput<E> {
        E[] onInputTestData();
    }

    public interface ITestOperation<E> {
        E[] test();
        E[] expect();
    }
}
