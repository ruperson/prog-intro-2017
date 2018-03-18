package sum;

import base.Asserts;

import java.util.Collections;
import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SumDoubleChecker extends SumChecker {
    public SumDoubleChecker(final String className) {
        super(className);
    }

    @Override
    public void test(final Number result, final String... input) {
        final List<String> out = run(input);
        Asserts.assertEquals("Single line expected", 1, out.size());
        final Double actual = Double.parseDouble(out.get(0));
        Asserts.assertEquals("Sum", result.doubleValue(), actual, 1e-10);
        checkEquals(Collections.emptyList(), Collections.emptyList());
    }
}
