package test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static test.Printer.println;
import static test.Printer.println2;

/**
 * 自制简易测试框架，用于验证正确性
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/6
 **/
public class HeavensTest<E extends Comparable<E>> {
    private List<TestAction> mActions;
    private E[] mInputs;
    private E[] mLaseResults;

    public HeavensTest() {
        mActions = new ArrayList<>();
    }

    public HeavensTest input(ITestInput<E> input) {
        if (input == null) {
            throw new RuntimeException("ITestInput can not be null!");
        }
        TestAction testAction = new TestAction();
        testAction.actionType = ActionType.INPUT;
        testAction.action = input;
        mActions.add(testAction);
        return this;
    }

    public HeavensTest addTestCase(ITestCase<E> operation) {
        if (operation == null) {
            throw new RuntimeException("ITestCase can not be null!");
        }
        TestAction testAction = new TestAction();
        testAction.actionType = ActionType.NORMAL;
        testAction.action = operation;
        mActions.add(testAction);
        return this;
    }

    public HeavensTest continueWith(ITestCase<E> operation) {
        if (operation == null) {
            throw new RuntimeException("ITestCase can not be null!");
        }
        TestAction testAction = new TestAction();
        testAction.actionType = ActionType.CONTINUE;
        testAction.action = operation;
        mActions.add(testAction);
        return this;
    }

    public boolean test(int testCount) {
        boolean accept = false;
        int realTestCount = 1;
        long allTestTime = System.nanoTime();
        long testTime;
        for (int i = 0; i < testCount; i ++) {
            println("=========== Start Test:【" + realTestCount + "】 =========================");
            testTime = System.nanoTime();
            for (TestAction action : mActions) {
                if (!testAction(action)) {
                    println("Test 【" + realTestCount + "】 cost " + getTestTime(testTime) + "s");
                    println2("=========== End Test:【" + realTestCount + "】 [Result:FAILED] ===========");
                    break;
                }
            }
            println("Test 【" + realTestCount + "】 + cost " + getTestTime(testTime) + "s");
            println2("=========== End Test:【" + realTestCount + "】 [Result:ACCEPT] ===========");
            realTestCount ++;
            if (i == testCount - 1) {
                accept = true;
            }
        }
        println("Test total cost " + getTestTime(allTestTime) + "s");
        println("Test " + testCount + " times, Test Result:" + (accept ? "【Accept】" : "【FAILED】"));
        return accept;
    }

    private String getTestTime(long testTime) {
        BigDecimal number = new BigDecimal((System.nanoTime() - testTime) + "");
        BigDecimal divide = number.divide(new BigDecimal("1000000000"), 18, RoundingMode.HALF_UP);
        return divide.toString();
    }

    @SuppressWarnings("unchecked")
    private boolean testAction(TestAction action) {
        ActionType actionType = action.actionType;

        if (actionType == ActionType.INPUT) {
            ITestInput<E> input = (ITestInput<E>) action.action;
            mInputs = input.onInput();
            mLaseResults = mInputs;
            return true;
        }

        E[] testData = null;

        switch (actionType) {
            case NORMAL: {
                println("Normal Test:");
                testData = mInputs;
                break;
            }
            case CONTINUE: {
                println("Continue Test:");
                testData = mLaseResults;
                break;
            }
        }

        ITestCase testCase = (ITestCase)action.action;
        printTestData(testData);

        E[] tests = (E[]) testCase.test(testData);
        printResultData(tests);

        E[] expects = (E[]) testCase.expect(mInputs);
        printExceptData(tests);

        mLaseResults = expects;
        return evaluate(tests, expects);
    }

    @SuppressWarnings("unchecked")
    private boolean evaluate(Comparable[] tests, Comparable[] expects) {
        if (tests == null && expects == null) {
            return true;
        }
        if (tests == null || expects == null) {
            return false;
        }
        if (tests.length != expects.length) {
            return false;
        }
        for (int i = 0; i < expects.length; i++) {
            if (expects[i].compareTo(tests[i]) != 0) {
                return false;
            }
        }
        return true;
    }


    private void printTestData(E[] testData) {
        printTestTypeData("TestData", testData);
    }

    private void printResultData(E[] testData) {
        printTestTypeData("ResultData", testData);
    }

    private void printExceptData(E[] testData) {
        printTestTypeData("ExceptData", testData);
    }

    private void printTestTypeData(String type, E[] testData) {
        StringBuilder stringBuilder = new StringBuilder(String.format("%s:\n", type));
        stringBuilder.append("[");
        if (testData == null) {
            stringBuilder.append("no test data");
        } else {
            for (int i = 0; i < testData.length; i++) {
                stringBuilder.append(testData[i].toString());
                if (i != testData.length - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        stringBuilder.append("]");
        println(stringBuilder.toString());
    }

    private enum ActionType {
        INPUT,
        NORMAL,
        CONTINUE
    }

    private class TestAction {
        private ActionType actionType;
        private IAction action;
    }

    public interface ITestInput<E extends Comparable<E>> extends IAction {
        E[] onInput();
    }

    public interface ITestCase<E extends Comparable<E>> extends IAction {
        E[] test(E[] testData);
        E[] expect(E[] testData);
    }

    private interface IAction {
    }
}
