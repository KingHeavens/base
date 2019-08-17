package test;

/**
 * 测试线性表
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/6
 **/
public class TestArrayList {

    public static void main(String[] args) {

    }

    @SuppressWarnings("unchecked")
    private static void testCase1() {
        HeavensTest<Integer> heavensTest = new HeavensTest<>();
        heavensTest.input(new HeavensTest.ITestInput<Integer>() {
            @Override
            public Integer[] onInput() {
                return new Integer[0];
            }
        });
        heavensTest.addTestCase(new HeavensTest.ITestCase() {
                    @Override
                    public Comparable[] test(Comparable[] testData) {
                        return new Comparable[0];
                    }

                    @Override
                    public Comparable[] expect(Comparable[] testData) {
                        return new Comparable[0];
                    }
                });
    }
}
