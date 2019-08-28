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

    public void input(ITestInput<E> input) {
        if (input == null) {
            throw new RuntimeException("ITestInput can not be null!");
        }
        TestAction testAction = new TestAction();
        testAction.actionType = ActionType.INPUT;
        testAction.action = input;
        mActions.add(testAction);
    }

    private void getHeavensTest(ITestCase<E> operation, ActionType type, String... testDesc) {
        if (operation == null) {
            throw new RuntimeException("ITestCase can not be null!");
        }
        TestAction testAction = new TestAction();
        testAction.actionType = type;
        testAction.action = operation;
        if (testDesc.length > 0) {
            testAction.testDesc = testDesc[0];
        }
        mActions.add(testAction);
    }

    public void addTestCase(ITestCase<E> operation, String... testDesc) {
        getHeavensTest(operation, ActionType.NORMAL, testDesc);
    }

    public void continueWith(ITestCase<E> operation, String... testDesc) {
        getHeavensTest(operation, ActionType.CONTINUE, testDesc);
    }

    public boolean test(int testCount) {
        if (testCount <= 0) {
            testCount = 1;
        }
        boolean accept = false;
        int realTestCount = 1;
        long allTestTime = System.nanoTime();
        long startTime;
        for (int i = 0; i < testCount; i ++) {
            println("=========== Start Test:[" + realTestCount + "] =========================");
            startTime = System.nanoTime();
            for (TestAction action : mActions) {
                if (!testAction(action)) {
                    println("Test [" + realTestCount + "] cost " + getTestTime(startTime) + "s");
                    println2("=========== End Test: [" + realTestCount + "]:【FAILED】 ===========");
                    break;
                }
            }
            println("Test [" + realTestCount + "] cost " + getTestTime(startTime) + "s");
            println2("=========== End Test: [" + realTestCount + "]:【ACCEPT】 ===========");
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
        BigDecimal divide = number.divide(new BigDecimal("1000000000"), 10, RoundingMode.HALF_UP);
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
                println("Normal Test:" + action.testDesc);
                testData = mInputs;
                break;
            }
            case CONTINUE: {
                println("Continue Test:" + action.testDesc);
                testData = mLaseResults;
                break;
            }
        }

        long testStartTime = System.nanoTime();
        ITestCase testCase = (ITestCase)action.action;
        printTestData(testData, getTestTime(testStartTime));

        testStartTime = System.nanoTime();
        E[] tests = (E[]) testCase.test(testData);
        printResultData(tests, getTestTime(testStartTime));

        testStartTime = System.nanoTime();
        E[] expects = (E[]) testCase.expect(mInputs);
        printExceptData(tests, getTestTime(testStartTime));

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


    private void printTestData(E[] testData, String costTime) {
        printTestTypeData("△△△---TEST-INPUT----→ ", testData, costTime);
    }

    private void printResultData(E[] testData, String costTime) {
        printTestTypeData("☆☆☆---TEST-RESULT----→ ", testData, costTime);
    }

    private void printExceptData(E[] testData, String costTime) {
        printTestTypeData("★★★---TEST-EXCEPT----→ ", testData, costTime);
    }

    private void printTestTypeData(String type, E[] testData, String costTime) {
        StringBuilder stringBuilder = new StringBuilder(String.format("%s cost %ss\n", type, costTime));
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
        private String testDesc = "";
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
