package sum;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SumDoubleSpaceTest extends SumDoubleTest {
    public SumDoubleSpaceTest(final SChecker checker) {
        super(checker);
    }

    public static void main(final String... args) {
        new SumDoubleSpaceTest(new SumDoubleChecker("SumDoubleSpace")).run();
    }

    @Override
    protected String getSpaces() {
        return " \u2000\u2001\u2002\u2003\u00A0";
    }
}
