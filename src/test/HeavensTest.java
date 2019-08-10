package test;

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
public class HeavensTest {
    private List<TestAction> mActions;
    private Comparable[] mInputs;
    private Comparable[] mLaseResults;
    private int mTestNumber = 0;
    private int mTestTime;

    public HeavensTest() {
        mActions = new ArrayList<>();
    }

    public <E extends Comparable<E>> HeavensTest input(ITestInput<E> input) {
        if (input == null) {
            throw new RuntimeException("ITestInput can not be null!");
        }
        TestAction testAction = new TestAction();
        testAction.actionType = ActionType.INPUT;
        testAction.action = input;
        mActions.add(testAction);
        return this;
    }

    public <E extends Comparable<E>> HeavensTest addTestCase(ITestCase<E> operation) {
        if (operation == null) {
            throw new RuntimeException("ITestCase can not be null!");
        }
        TestAction testAction = new TestAction();
        testAction.actionType = ActionType.NORMAL;
        testAction.action = operation;
        mActions.add(testAction);
        return this;
    }

    public <E extends Comparable<E>> HeavensTest continueWith(ITestCase<E> operation) {
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
        mTestTime = 0;
        for (int i = 0; i < testCount; i ++) {
            mTestTime ++;
            println2("=========== Start test number of times:" + mTestTime + " ===========");
            mTestNumber = 0;
            for (TestAction action : mActions) {
                if (!testAction(action)) {
                    println2("=========== End test number of times:" + mTestTime + " [Result:FAILED] ===========");
                    return false;
                }
            }
            println2("=========== End test number of times:" + mTestTime + " [Result:ACCEPT] ===========");
        }
        return true;
    }

    private boolean testAction(TestAction action) {
        ActionType actionType = action.actionType;
        Comparable[] tests = null;
        Comparable[] expects = null;
        switch (actionType) {
            case INPUT: {
                ITestInput input = (ITestInput)action;
                mInputs = input.onInput();
                mLaseResults = mInputs;
                return true;
            }
            case NORMAL: {
                ITestCase testCase = (ITestCase)action;
                tests = testCase.test();
                expects = testCase.expect();
                break;
            }
            case CONTINUE: {
                ITestCase testCase = (ITestCase)action;
                tests = testCase.test();
                expects = testCase.expect();
                break;
            }
        }
        return startTest(action.actionType, tests, expects);
    }

    private boolean startTest(ActionType actionType, Comparable[] tests, Comparable[] expects) {
        println2("[TEST" + ++mTestNumber + "]"  + actionType + " Test ----->");
        Comparable[] testData = null;
        if (actionType == ActionType.NORMAL) {
            testData = mInputs;
        } else if (actionType == ActionType.CONTINUE) {
            testData = mLaseResults;
            mLaseResults = expects;
        }
        printTestData(testData);

        for (Comparable data : testData) {
            
        }
        return false;
    }

    private void printTestData(Comparable[] testData) {
        StringBuilder stringBuilder = new StringBuilder("Test Data:\n");
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
        E[] test();
        E[] expect();
    }

    private interface IAction {
    }
}
